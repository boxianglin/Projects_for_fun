import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanCode {

	public static void main(String[] args) {
		String content = "i like like like java do you like a java";
		byte [] contentBytes = content.getBytes();
		System.out.print(contentBytes.length);

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

