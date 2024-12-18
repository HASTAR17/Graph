import java.util.PriorityQueue;
import java.util.Scanner;

class HuffmanNode implements Comparable<HuffmanNode> {
    char c;
    int freq;
    HuffmanNode left, right;

    HuffmanNode(char c, int freq) {
        this.c = c;
        this.freq = freq;
    }

    @Override
    public int compareTo(HuffmanNode node) {
        return this.freq - node.freq;
    }
}

public class Huffman {
    public static void printCodes(HuffmanNode root, String code) {
        if (root == null) return;

        // If leaf node, print character and code
        if (root.left == null && root.right == null) {
            System.out.println(root.c + ": " + code);
            return;
        }

        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text for Huffman Encoding:");
        String text = scanner.nextLine();

        // Calculate frequency of characters
        int[] freq = new int[256];
        for (char c : text.toCharArray()) {
            freq[c]++;
        }

        // Create priority queue for Huffman tree
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) {
                pq.add(new HuffmanNode((char) i, freq[i]));
            }
        }

        // Build Huffman tree
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode parent = new HuffmanNode('-', left.freq + right.freq);
            parent.left = left;
            parent.right = right;
            pq.add(parent);
        }

        // Print Huffman Codes
        HuffmanNode root = pq.poll();
        System.out.println("Huffman Codes:");
        printCodes(root, "");
    }
}
