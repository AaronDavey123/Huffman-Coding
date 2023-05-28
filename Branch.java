package huffman.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * A branch node in a Huffman tree.
 */
public class Branch extends Node {

    private Node left;
    private Node right;

    public Branch(int freq, Node left, Node right) {
        super(freq);
        this.left = left;
        this.right = right;
    }

    /**
     * The traverse method is used to construct the map of characters (stored in leaves of the tree)
     * and binary codes for each character. A binary code is a list of true and false values representing the path
     * from the root to a leaf, where `false` represents Left and `true` represents Right. In the Branch class this method
     * should be called recursively on the left and right children of the branch, adding `false` to the list
     * of booleans passed to the recursive call on the left child, and `true` to the list of booleans passed to
     * the recursive call on the right child. The return value should be the merged map containing the results of both
     * recursive calls. In the Leaf class the method should create a new map then store both the character (the label
     * of the leaf) and the list of booleans (the path to that leaf), and return the resulting map.
     *
     * @param list  The list representing paths through the tree, modified copies of which are passed to
     *              recursive invocations of the method.
     * @return the map of characters and paths
     */
    public Map<Character, List<Boolean>> traverse(List<Boolean> list) {
    	
    	Map<Character, List<Boolean>> leftMap = new HashMap<>();
    	Map<Character, List<Boolean>> rightMap = new HashMap<>();
    	
    		if(this.left != null) {
    			ArrayList<Boolean> lList = new ArrayList<Boolean>(list);
    			lList.add(false);
    			leftMap = this.left.traverse(lList);
    		}
    		
    		/*constructing map of characters, and checking the current position for null
    		 * if not null we need to traverse using the booleans false for left and true for right
    		 * adding the boolean to the new arraylist and then making the left or right list = to the 
    		 * already constructed hashmaps
    		*/
    		
    		if(this.right != null) {
    			ArrayList<Boolean> rList = new ArrayList<Boolean>(list);
    			rList.add(true);
    			rightMap = this.right.traverse(rList);
    		}
    	
    		Map<Character, List<Boolean>> mergedMaps = new HashMap<>();
    		mergedMaps.putAll(rightMap);
    		mergedMaps.putAll(leftMap);		/*merged both maps together to create a map of booelans representing 
    										the path from the root node to a leaf.
    		 								*/
    		
		return mergedMaps;
       // throw new UnsupportedOperationException("Method not implemented");
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
