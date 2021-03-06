import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanAlgorithms {
	/**
	 * 
	 * @param srcFile, input address
	 * @param dstFile, output address
	 */
	public void zipFile(String srcFile, String dstFile) {
		
		
		FileInputStream is = null;
		OutputStream os = null;
		ObjectOutputStream oos = null;
		 
		try {
			////////////////INPUT////////////////////////////////
			is = new FileInputStream(srcFile);
			//a byte[] with the same size as the souce file
			byte [] b = new byte[is.available()];
			is.read(b);
			
			//compressed by huffmancodes
			byte[] huffmanBytes = huffmanZip(b); 
			
			///////////////OUTPUT////////////////////////////////
			os = new FileOutputStream(dstFile);
			oos = new ObjectOutputStream(os);
			
			oos.writeObject(huffmanBytes);
			oos.writeObject(huffmanCodes);
		
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally {
			try {
				is.close();
				oos.close();
				os.close();		 
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			 
		}
		 
	}
	
	/**
	 * 
	 * @param zipFile
	 * @param dstFile
	 */
	public void unZipFile(String zipFile, String dstFile) {
		InputStream is = null;
		ObjectInputStream ois = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(zipFile);
			ois = new ObjectInputStream(is);
			byte[] huffmanbytes =(byte[])ois.readObject();
			@SuppressWarnings("unchecked")
			Map<Byte, String> huffmancodes =(Map<Byte, String>)ois.readObject();
			
			byte[] bytes = decode(huffmancodes, huffmanbytes);
			
			os = new FileOutputStream(dstFile);
			os.write(bytes);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}finally {
			try {
				os.close();
				ois.close();
				is.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	
	/*
	 * 
	 * 
	 * <Decode> 
	 * 
	 * 1. huffmancodeByte ---------> huffmancode
	 * 2. huffmancode ----------> ascii code for string
	 * 
	 */
	
	/**
	 * 
	 * @param huffmancodes
	 * @param huffmanbytes
	 * @return
	 */
	private byte[] decode (Map<Byte, String> huffmancodes, byte[] huffmanbytes) {
		
		//1. huffmancodeByte ---------> huffmancode 
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i<huffmanbytes.length;i++) {
			//last index, doesn't need to fill in 1s for binary  
			boolean flag = (i==huffmanbytes.length-1);
			//append each return string
			stringBuilder.append(byteToBitString(!flag, huffmanbytes[i]));
		}
		System.out.println("sdsadas" + stringBuilder.toString());
		
		
		//2. huffmancode ------> asciii code 
		Map<String, Byte> map = new HashMap<>();
		for(Map.Entry<Byte, String> entry: huffmancodes.entrySet()) {
			map.put(entry.getValue(), entry.getKey());
		}
		//NOW map<huffmancode, ascii>
		
		
		List<Byte> list = new ArrayList<>();
		
		//now matching the huffmancode in string builder to the hashMap key, if match add the 
		//ascii to the list.
		for(int i = 0; i<stringBuilder.length();) {
			int count = 1;
			boolean flag = true;
			Byte b = null;
			while(flag) {
				String key = stringBuilder.substring(i,i+count);
				b = map.get(key);
				if(b==null) {
					count++;
				}else {
					flag= false;
				}
			}
			list.add(b);
			i+=count;
		}
		//transfer to the byte[]
		byte [] b = new byte[list.size()];
		for(int i = 0; i<b.length;i++) {
			b[i] = list.get(i);
		}
		return b ;
		 
	}
	
	/**
	 * 
	 * @param flag (determine if fill-in needed or not)
	 * @param b
	 * @return each byte's string 
	 */
	private String byteToBitString(boolean flag, byte b) {
		int temp = b; 
		
		if(flag) {
			temp |= 256; //Bits OR --- Fill in 1s as needed (256: 1 0000 0000)
		}

		String str = Integer.toBinaryString(temp);
		
		if(flag) {
			//as default is 32 bits, we should pick last eight digits.
			return str.substring(str.length()-8);
		}else {
			return str;
		}
		 
	}
	
	/**
	 * 
	 * @param bytes [the original bytes array]
	 * @return compressed bytes array (by huffmancode)
	 */
	
	private byte[] huffmanZip(byte[] bytes) {
		
		//convert to a list of nodes
		List <Node> nodes = getNodes(bytes);
		
		//using list of nodes to generate the huffmanTree structure
		Node huffmanTreeRoot = createHumanTree(nodes);
		
		//using huffmanTree to generate the huffmanCode (stored in a map)
		Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
		
		//store the huffmancode to the new byte array
		byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
		
		return huffmanCodeBytes;
	}
	
	
	
 
	
	/**
	 * 
	 * @param bytes array 
	 * @return a list (char,weight) = collections of char and its frequency
	 */
	private List<Node> getNodes (byte [] bytes){
		ArrayList<Node> nodes = new ArrayList<>();
		
		//store the frequency for each char in hashmap
		Map<Byte, Integer> counts = new HashMap<>();
		for(byte b : bytes) {
			Integer count = counts.get(b);
			if(count == null) {
				counts.put(b, 1);
			}else {
				counts.put(b, count+1);
			}
		}
		
		//traverse map and store into the arraylist
		for(Map.Entry<Byte, Integer> entry : counts.entrySet()) {
			nodes.add(new Node(entry.getKey(),entry.getValue()));
		}
		
		return nodes;
	}
	
	
	/**
	 * 
	 * @param nodes<List> 
	 * @return the head of Human Tree 
	 */
	private Node createHumanTree(List<Node> nodes) {
		
		while(nodes.size()>1) {
			Collections.sort(nodes);
			Node leftNode = nodes.get(0);
			Node rightNode = nodes.get(1);
			
			Node parentNode = new Node(null, leftNode.weight+rightNode.weight);
			
			//structure of huffman tree
			parentNode.left = leftNode;
			parentNode.right = rightNode;
			
			//remove the original nodes list
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			
			nodes.add(parentNode);

		}
		return nodes.get(0);
	}
	
	
	
	
	/**
	 * 
	 * @param bytes  //original byte codes
	 * @param huffmanCodes   //the huffmancodes
	 * @return huffmancodeByte = [index] = 8bits (in 2's binary) ??inverse bits and plus 1??
	 */
	private byte[] zip (byte[] bytes, Map<Byte, String> huffmanCodes) {
		
		//make the original bytes [] to string huffmancodes
		StringBuilder stringBuilder = new StringBuilder();
		for(byte b : bytes) {
			stringBuilder.append(huffmanCodes.get(b));
		}
		
		int len;
		if(stringBuilder.length() % 8 == 0) {
			len = stringBuilder.length()/8;
		}else {
			len = stringBuilder.length()/8 + 1;
		}
		
		byte[] huffmancodeByte = new byte[len];
		
		int index = 0;
		for(int i = 0; i<stringBuilder.length(); i=i+8) {
			
			String  strByte;
			
			if(i+8 > stringBuilder.length()) {
				strByte = stringBuilder.substring(i);
			}else {
				strByte = stringBuilder.substring(i,i+8);
			}
			huffmancodeByte[index] = (byte)Integer.parseInt(strByte,2);
			index++;	
		}
		return huffmancodeByte;
		
	}
	
	
	/*
	 * MAP to store the huffman code tablle
	 */
	Map<Byte, String> huffmanCodes = new HashMap<>();
	
	/*
	 * Use stringbuilder collect the path(code) from huffmanTree
	 */
	
	StringBuilder stringBuilder = new StringBuilder();
	
	
	private  Map<Byte, String> getCodes(Node root){
		if(root == null) {
			return null;
		}
		getCodes(root, "", stringBuilder); 
		return huffmanCodes;
	}
	
	/**
	 * Function: store the huffmancode by the path from node root to leaf to the map
	 *           left = 0, right = 1;
	 * @param node 
	 * @param code 
	 * @param stringBuilder
	 */
	private void getCodes(Node node, String code, StringBuilder stringBuilder) {
		StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
		//code+strBuilde2 
		stringBuilder2.append(code);
		if(node != null) {
			//non-leaf node --- code recorded
			if(node.data == null) {
				getCodes(node.left, "0", stringBuilder2);
				getCodes(node.right, "1", stringBuilder2);
			}else {
				//leaf node, put to the huffmancode map
				huffmanCodes.put(node.data, stringBuilder2.toString());
			}
		}
	}
}

//Node Class for each char and weights
class Node implements Comparable<Node> {
	Byte data;
	int weight; //frequency of char
	Node left;
	Node right;
	
	
	public Node(Byte data, int weight) {
		this.data = data;
		this.weight = weight;
	}
	
	
	@Override
	public int compareTo(Node o) {
		return this.weight - o.weight;
	}
	
	@Override
	public String toString() {
		return "Node [data = " + data + " weight = " + weight + "]";
	}
	
	public void preOrder() {
		System.out.println(this);
		if(this.left != null) {
			this.left.preOrder();
		}
		if(this.right != null) {
			this.right.preOrder();
		}
	}
	
}
