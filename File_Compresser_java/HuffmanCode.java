import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanCode {

	public static void main(String[] args) {
		String content = "i like like like java do you like a java";
		byte [] contentBytes = content.getBytes();
		System.out.println(contentBytes.length);
		
 
		
		byte[] compressed = huffmanZip(contentBytes);
		System.out.println(Arrays.toString(compressed) + "size: " + compressed.length);
		
		
		 
		
		
	}
	
	/**
	 * 
	 * @param bytes [the original bytes array]
	 * @return compressed bytes array (by huffmancode)
	 */
	
	private static byte[] huffmanZip(byte[] bytes) {
		
		//convert to a list of nodes
		List <Node> nodes = getNodes(bytes);
		
		//using list of nodes to generate the huffmanTree structure
		Node huffmanTreeRoot = createHumanTree(nodes);
		huffmanTreeRoot.preOrder();
	 
		
		//using huffmanTree to generate the huffmanCode (stored in a map)
		Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
		System.out.println(huffmanCodes);
		
		//store the huffmancode to the new byte array
		byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
		
		return huffmanCodeBytes;
	}
	
	
	
 
	
	/**
	 * 
	 * @param bytes array 
	 * @return a list (char,weight) = collections of char and its frequency
	 */
	private static List<Node> getNodes (byte [] bytes){
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
	private static Node createHumanTree(List<Node> nodes) {
		
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
	 * @return huffmancodeByte = [index] = 8bits (in 2's binary) ¡¾inverse bits and plus 1¡¿
	 */
	private static byte[] zip (byte[] bytes, Map<Byte, String> huffmanCodes) {
		
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
	static Map<Byte, String> huffmanCodes = new HashMap<>();
	
	/*
	 * Use stringbuilder collect the path(code) from huffmanTree
	 */
	
	static StringBuilder stringBuilder = new StringBuilder();
	
	
	private static Map<Byte, String> getCodes(Node root){
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
	private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
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
	
	
	
	/**
	 * 
	 * @param root
	 * Print the Huffman Tree in preorder
	 * 
	 */
	private static void preOrder(Node root) {
		if(root != null) {
			root.preOrder();
		}else {
			System.out.println("Huffman Tree is Empty");
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

