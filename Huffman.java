package huffman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import huffman.tree.Branch;
import huffman.tree.Leaf;
import huffman.tree.Node;

/**
 * The class implementing the Huffman coding algorithm.
 */
public class Huffman {
	/**
	 * Build the frequency table containing the unique characters from the String
	 * `input' and the number of times that character occurs.
	 *
	 * @param input The string.
	 * @return The frequency table.
	 */
	public static Map<Character, Integer> freqTable(String input) {

		if (input == null || input.length() == 0) {
			return null;
		}
		Map<Character, Integer> ft = new HashMap<>();
		/*
		 * looping through the input string and checking if its already in the map if it
		 * is replaceing the current char with the new one and +1 else the char isnt in
		 * the map and we need to add it
		 */
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);

			if (ft.containsKey(c)) {
				ft.replace(c, ft.get(c) + 1);
			} else {
				ft.put(c, 1);
			}

		}

		return ft;
		// throw new UnsupportedOperationException("Method not implemented");
	}
	
	

	/**
	 * Given a frequency table, construct a Huffman tree.
	 *
	 * First, create an empty priority queue.
	 *
	 * Then make every entry in the frequency table into a leaf node and add it to
	 * the queue.
	 *
	 * Then, take the first two nodes from the queue and combine them in a branch
	 * node that is labelled by the combined frequency of the nodes and put it back
	 * in the queue. The right hand child of the new branch node should be the node
	 * with the larger frequency of the two.
	 *
	 * Do this repeatedly until there is a single node in the queue, which is the
	 * Huffman tree.
	 *
	 * @param freqTable The frequency table.
	 * @return A Huffman tree.
	 */
	public static Node treeFromFreqTable(Map<Character, Integer> freqTable) {
		if (freqTable == null || freqTable.size() == 0) {
			return null;													
		}

		PQueue pq = new PQueue();

		for (Map.Entry<Character, Integer> entry : freqTable.entrySet()) {		//loop through freqtable get char and freq and create a new leaf
			Character entryKey = entry.getKey();								// then add it to the priorty queue
			int entryFreq = entry.getValue();		

			Leaf l = new Leaf(entryKey, entryFreq);
			pq.enqueue(l);

		} // end loop

		
		if (pq.size() > 1) {
			do {
				//Start of building Tree
				Node fNode = pq.dequeue();
				int ToTfreq = fNode.getFreq();						
						
				Node sNode = pq.dequeue();				//removing the first and second node and adding there freqs together

				if (sNode != null) {

					ToTfreq = ToTfreq + sNode.getFreq();
				}
																		/*checking which nodes freq is bigger and creating a branch with the largest on the right side*/
				if (fNode.getFreq() > sNode.getFreq()) {
					Branch branch = new Branch(ToTfreq, sNode, fNode);
					pq.enqueue(branch);

				} else {
					Branch branch = new Branch(ToTfreq, fNode, sNode);
					pq.enqueue(branch);

				}

			} while (pq.size() > 1);		
		}

		Node n = pq.dequeue();

		return n;

		// throw new UnsupportedOperationException("Method not implemented");
	}

	
	
	/**
	 * Construct the map of characters and codes from a tree. Just call the traverse
	 * method of the tree passing in an empty list, then return the populated code
	 * map.
	 *
	 * @param tree The Huffman tree.
	 * @return The populated map, where each key is a character, c, that maps to a
	 *         list of booleans representing the path through the tree from the root
	 *         to the leaf node labelled c.
	 */
	public static Map<Character, List<Boolean>> buildCode(Node tree) {

		List<Boolean> emptyList = new ArrayList<>();
		Map<Character, List<Boolean>> hTree = tree.traverse(emptyList);
																			/*create an empty list and traverse the tree then return the populated list*/
		return hTree;
		// throw new UnsupportedOperationException("Method not implemented");
	}

	
	
	/**
	 * Create the huffman coding for an input string by calling the various methods
	 * written above. I.e.
	 *
	 * + create the frequency table, + use that to create the Huffman tree, +
	 * extract the code map of characters and their codes from the tree.
	 *
	 * Then to encode the input data, loop through the input looking each character
	 * in the map and add the code for that character to a list representing the
	 * data.
	 *
	 * @param input The data to encode.
	 * @return The Huffman coding.
	 */
	public static HuffmanCoding encode(String input) {

		Map<Character, Integer> fTable = Huffman.freqTable(input);		//create freqtable and then the tree from the freqtable
		Node huffTree = Huffman.treeFromFreqTable(fTable);

		Map<Character, List<Boolean>> codeTree = Huffman.buildCode(huffTree); //build the code from the tree

		List<Boolean> code = new ArrayList<Boolean>();

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);						//loop the strings length to get each char
			List<Boolean> charCode = codeTree.get(c);		//populate list with code for the char
			code.addAll(charCode);
		}
			
		HuffmanCoding hc = new HuffmanCoding(codeTree, code);		// create and return HuffmanCoding with the list and Map
		return hc;

		// throw new UnsupportedOperationException("Method not implemented");
	}
		
		

	/**
	 * Reconstruct a Huffman tree from the map of characters and their codes. Only
	 * the structure of this tree is required and frequency labels of all nodes can
	 * be set to zero.
	 *
	 * Your tree will start as a single Branch node with null children.
	 *
	 * Then for each character key in the code, c, take the list of booleans, bs,
	 * corresponding to c. Make a local variable referring to the root of the tree.
	 * For every boolean, b, in bs, if b is false you want to "go left" in the tree,
	 * otherwise "go right".
	 *
	 * Presume b is false, so you want to go left. So long as you are not at the end
	 * of the code so you should set the current node to be the left-hand child of
	 * the node you are currently on. If that child does not yet exist (i.e. it is
	 * null) you need to add a new branch node there first. Then carry on with the
	 * next entry in bs. Reverse the logic of this if b is true.
	 *
	 * When you have reached the end of this code (i.e. b is the final element in
	 * bs), add a leaf node labelled by c as the left-hand child of the current node
	 * (right-hand if b is true). Then take the next char from the code and repeat
	 * the process, starting again at the root of the tree.
	 *
	 * @param code The code.
	 * @return The reconstructed tree.
	 */
	public static Node treeFromCode(Map<Character, List<Boolean>> code) {

		Branch root = new Branch(0, null, null);

		for (Map.Entry<Character, List<Boolean>> entry : code.entrySet()) {
			char c = entry.getKey();
			List<Boolean> bs = entry.getValue();					

			Node currentNode = root;

			for (int b = 0; b < bs.size(); b++) {	//loop to check if it's false or true
				if (bs.get(b) == false) {

					if (b == bs.size() - 1) {
						((Branch) currentNode).setLeft(new Leaf(c, 0));   	//set current node as a new left leaf
					} else if (((Branch) currentNode).getLeft() == null) { 
						((Branch) currentNode).setLeft(new Branch(0, null, null));	//if not at the end of the list set current node as branch with no values
					}
					currentNode = ((Branch) currentNode).getLeft();				

				} else if (bs.get(b) == true) {

					if (b == bs.size() - 1) {
						((Branch) currentNode).setRight(new Leaf(c, 0));
					} else if (((Branch) currentNode).getRight() == null) {				//we do the same for the right side
						((Branch) currentNode).setRight(new Branch(0, null, null));
					}
					currentNode = ((Branch) currentNode).getRight();
				}

			}

		}

		return root;

		// throw new UnsupportedOperationException("Method not implemented");
	}

	
	
	/**
	 * Decode some data using a map of characters and their codes. To do this you
	 * need to reconstruct the tree from the code using the method you wrote to do
	 * this. Then take one boolean at a time from the data and use it to traverse
	 * the tree by going left for false, right for true. Every time you reach a leaf
	 * you have decoded a single character (the label of the leaf). Add it to the
	 * result and return to the root of the tree. Keep going in this way until you
	 * reach the end of the data.
	 *
	 * @param code The code.
	 * @param data The encoded data.
	 * @return The decoded string.
	 */
	public static String decode(Map<Character, List<Boolean>> code, List<Boolean> data) {

		Node treeCode = Huffman.treeFromCode(code);

		Branch currentNode = (Branch) treeCode;
		String string = "";

		for (int i = 0; i < data.size(); i++) {
			Boolean ph = data.get(i);					//get the path

			if (!ph) {
				if (currentNode.getLeft() instanceof Leaf) {
					char c = ((Leaf) currentNode.getLeft()).getLabel();
					string = string + c;
					currentNode = (Branch) treeCode;
				} else {
					currentNode = (Branch) currentNode.getLeft();
				}
/*check if current node is a leaf, if it is at the label to the string and set current node to the root
 * do the same for the right side
*/
			} else {
				if (currentNode.getRight() instanceof Leaf) {
					char c = ((Leaf) currentNode.getRight()).getLabel();
					string = string + c;
					// System.out.println("added: " + string);
					currentNode = (Branch) treeCode;

				} else {
					currentNode = (Branch) currentNode.getRight();
				}
			}

		}

		return string;

		// throw new UnsupportedOperationException("Method not implemented");
	}
}
