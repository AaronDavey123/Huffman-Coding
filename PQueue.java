package huffman;

import huffman.tree.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * A priority queue of @Node@ objects. Each node has an int as its label
 * representing its frequency. The queue should order objects in ascending order
 * of frequency, i.e. lowest first.
 */
public class PQueue {

	private List<Node> queue;

	public PQueue() {
		queue = new ArrayList<>();
		// throw new UnsupportedOperationException("Method not implemented");

	}

	/**
	 * Add a node to the queue. The new node should be inserted at the point where
	 * the frequency of next node is greater than or equal to that of the new one.
	 * 
	 * @param n The node to enqueue.
	 */
	public void enqueue(Node n) {

		int numNode = queue.size();

		if (numNode == 0) {
			queue.add(n);
		} else {

			boolean numAdded = false;
			for (int i = 0; i < numNode; i++) {
				int Freqi = queue.get(i).getFreq();		//looping through queue size
				int nodeFreq = n.getFreq();				
				

				if (Freqi >= nodeFreq) {
					queue.add(i, n);				//inserting new node where the freq of the next 
					numAdded = true;				//node is grater or equal to the new node.
					break;
				}

			}
			if (numAdded == false) {
				queue.add(n);			
			}
		}

	}

	/**
	 * Remove a node from the queue.
	 * 
	 * @return The first node in the queue.
	 */
	public Node dequeue() {
		if (queue.size() > 0) {
			return queue.remove(0);	//remove if queue size is more than 0
		} else {
			return null;
		}
		// throw new UnsupportedOperationException("Method not implemented");

	}

	/**
	 * Return the size of the queue.
	 * 
	 * @return Size of the queue.
	 */
	public int size() {
		return queue.size();
	}

}
