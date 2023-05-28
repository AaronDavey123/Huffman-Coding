
import huffman.Huffman;
import huffman.HuffmanCoding;
import huffman.PQueue;
import java.util.*;
import huffman.tree.*;


public class test {

	public static void main(String[] args) {
		
		/*PQueue pq = new PQueue();
				
		pq.enqueue(new Leaf('a', 42));
		pq.enqueue(new Leaf('b', 1));
		pq.enqueue(new Leaf('c', 101));
		pq.enqueue(new Leaf('d', -101));
		
		System.out.println("PQueue Size " + pq.size());
		
		int numNodes = pq.size();
		for (int i = 0; i < numNodes; i++) {
			Leaf lTest = (Leaf)pq.dequeue();
			System.out.println("Queue[" + i + "] : " + lTest.getLabel() + lTest.getFreq());
		} 

		
		Map<Character, Integer> testTable = Huffman.freqTable("Oh I do like to be beside the seaside, I do like to be beside the sea");
		System.out.println("here");
		
		for(Map.Entry<Character, Integer> entry: testTable.entrySet()) {
			Character entryKey = entry.getKey();
			int entryFreq = entry.getValue();
			System.out.println("Key: " + entryKey);
			System.out.println("Value: " + entryFreq);
		}*/
		
		
		Map<Character, Integer> testTable1 = Huffman.freqTable("aaaabaac");
		System.out.println("here 2nd");
		Huffman.treeFromFreqTable(testTable1);
		
		
		
		//String input = "Oh I do like to be beside the seaside, I do like to be beside the sea";
		String input = "aaaabaac";
		
		Map<Character, Integer> freqTable = Huffman.freqTable(input);
		Node hTree = Huffman.treeFromFreqTable(freqTable);
		
		List<Boolean> huffList = new ArrayList<>();
		Map<Character, List<Boolean>> huffMap = hTree.traverse(huffList);
		
		//System.out.println("Code: " + huffMap);
		
		HuffmanCoding hc1 = Huffman.encode(input);
		System.out.println("Encoding : " + hc1.getCode());
		System.out.println("Encoding : " + hc1.getData());
		
		Node treeCode = Huffman.treeFromCode(huffMap);
		System.out.println(treeCode);
		
		String str = Huffman.decode(hc1.getCode(), hc1.getData());
		
		System.out.println(str);
		
	
	}

}
