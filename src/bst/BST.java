/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bst;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
import static java.lang.Integer.max;
import java.util.Random;

public class BST implements OrderedSet {
	
	protected static class Node {
		public Comparable data;
		public Node left;
		public Node right;
		public Node parent;
		
		public Node(Comparable data, Node parent) {
			this.data = data;
			this.left = null;
			this.right = null;
			this.parent = parent;
		}
		
		public Node(Comparable data) {
			this(data, null);
		}
		
	}
	
	// The root of the tree. If null, then tree is empty.
	protected Node root;
	
	// The Node containing the data that our iterator has most 
	// recently returned from first() or next().
	protected Node current;
	
	// A counter of how many unique values are in the tree.
	protected int size;
	
	// Constructor creates an empty tree.
	public BST() {
		root = null;
		current = null;
		size = 0;
	}
	
	// Inserts the new data item into the tree, if it does not
	// exist already. Nothing is done if it already exists in
	// the tree.
	public void add(Comparable data) {
		if (root == null) {
			root = new Node(data);
			size = 1;
		} else {
			bstAdd(root, data);
		}
	}
	
	// This method does the actual work of inserting a new node.
	// There are three cases:
	//
	//    1 - data is equal to n.data:
	//        In this case you can just return without doing anything;
	//        it's a duplicate, so we can ignore the insert.
	//
	//    2 - data < n.data
	//        In this case, check to see if the left child of n is null.
	//        If null, then add a new node as n.left; if not, recursively add
	//        to the n.left subtree.
	//
	//    3 - data > n.data
	//        In this case, check to see if the right child of n is null.
	//        If null, then add a new node as n.right; if not, recursively add
	//        to the n.right subtree.
	//
	//    Please ignore the following requirement to rebalance the tree. (It does not apply to this implementation.)
	//    In cases 2 and 3 above, you must also rebalance the tree if 
	//    the "balance" flag is set. This is done by calling "resolveInbalance(n)".
	//
	protected void bstAdd(Node n, Comparable data) {
            
            current = root;
            Node newNode = new Node(data);
            while(current != null){
                if (data.equals(n.data)) {
                    // do nothing
                     return;
                }
                else if (data.compareTo(n.data) < 0){
                    if (current.left == null){
                        current.left = newNode;
                        current = null;
                    }
                    else {
                        current = current.left;
                    }
                }
                else {  // data > n.data
                    if (current.right == null){
                        current.right = newNode;
                        current = null;
                    }
                    else {
                        current = current.right;
                    }
                }
            }
		
	}
	
	
	
	// Returns true if and only if the data item exists in the
	// tree. Two items are equal if their .equals() method returns
	// true.
	public boolean contains(Comparable data) {  // *
              if (bstFind(root, data) == null){
                  return true;
              }            
              return true;
	}
	
	// Helper recursive function to locate a data value somewhere in
	// the given subtree rooted at "n", if it exists. If found, returns
	// the Node that contains the data value; if not, returns null.
	protected Node bstFind(Node n, Comparable data) {  // start Node & the key
            current = root;
            while(current != null){
                if (data.equals(n.data)){
                    return current;
                }
                else if (data.compareTo(n.data) < 0){
                    current = current.left;
                }
                else {
                    current = current.right;
                }
            }            
            return null;
	}
	
	
	// Returns the first (smallest) value in the tree. Can be used
	// to start an iteration, along with "next()". Returns null if
	// the tree is empty.
	public Comparable first() {
		current = root;
		if (current != null) current = leftMostDecendant(root);
		return current.data;
	}
	
	// Returns the next highest-valued data item after the one
	// most recently returned by either "first()" or "next()".
	// If the most recently returned value was the largest in
	// the tree, returns null. The most recently returned value
	// should be referenced by the "current" Node data member.
	public Comparable next() {
		if (current == null) return null;
		else {
			current = successor(current);
			return current == null ? null : current.data;
		}
	}
	
	// Returns a count of how many items are in the tree.
	public int size() { 
		return size;
	}
	
	// Helper method to find the node that has the smallest value
	// in the left subtree of the given node. If the node
	// has no left subtree just returns null.
	protected Node leftMostDecendant(Node n) {  // *
		return null;
	}
	
	// Helper method to find the node that has the next highest value
	// after the given node.
	protected Node successor(Node n) {  // *
		return null;
	}
	
	// Returns the current height of the tree. An empty tree
	// has height 0; a tree with just a single Node has a height of 1.
	public int height() {  // *
		return height(root);
	}
	
	// Returns the height of the subtree rooted at "n".
	protected int height(Node n) {  // *
            int leftHeight;
            int rightHeight;
            if (n == null){
                return 0;
            }
            else {
                leftHeight = height(n.left);
                rightHeight = height(n.right);
                return 1 + max(leftHeight, rightHeight);
            }
            
	}
	
	// Public method to display all values in the tree using an
	// inorder traversal. Valuable for debugging with small data sets.
	public void inorder() {
		inorder(root);
		System.out.println();
	}
	
	
	// The recursive helper method that does the actual inorder traversal.
	protected void inorder(Node n) {  // *
	   // if n is not null:
	   //     - do inorder traversal of left subtree
	   //     - print n's data value plus a space character
	   //     - do inorder traversal of right subtree
	}
	
	
	// A program that can be used to do a basic test on the
	// add() method and traversals for a BST.
	//
	// There are two optional command-line arguments:
	//    --debug - when present, prints the inorder traversal after inserting
	//              the values. Also restricts the values to 2-digit positive values.
	//              Default is no debug, so random integers are unrestricted and no
	//              inorder traversal is printed.
	//
	//    --n=<integer> - sets the total number of random integers to generate. 
	//                    Defaults to 10.
	//
	//    --seed=<integer> - sets the seed for the random number generator.
	//                       Defaults to empty seed, meaning different set of numbers each time.
	public static void main(String[] args) {
		
		BST bst = new BST();
		boolean bstError = false;
		
		int n = 10;
		int seed = -1;
		boolean setSeed = false;
		boolean debug = false;
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("--debug")) debug = true;
			if (args[i].startsWith("--n=")) {
				String[] parts = args[i].split("=");
				n = Integer.parseInt(parts[1]);
			}
			if (args[i].startsWith("--seed=")) {
			   String[] parts = args[i].split("=");
			   seed = Integer.parseInt(parts[1]);
			   setSeed = true;
			}
		}
		
		Random rand;
		if (setSeed) rand = new Random( seed );
		else rand = new Random();

		System.out.println("****** BEGIN INSERTION ******");
		for (int i = 0; i < n; i++) {
			int data = debug ? rand.nextInt(100) : rand.nextInt();
			if (!bstError) {
				try {
					bst.add(new Integer(data));
				} catch (Exception e) {
					System.out.println("Error inserting into BST.");
					bstError = true;
				}
			}
			
		}
		System.out.println("****** END INSERTION ******\n");

		if (!bstError) {
			System.out.println("****** BEGIN BST ORDER TEST ******");
			if (debug) bst.inorder();
			Comparable last = null, next = null;
			try {
				last = bst.first();
			} catch (Exception e) {
				System.out.println("ERROR! Exception when calling BST first():");
				e.printStackTrace();
				bstError = true;
			}
			while (!bstError && last != null) {
				try {
					next = bst.next();
				} catch (Exception e) {
					System.out.println("ERROR! Exception when calling BST next():");
					e.printStackTrace();
					bstError = true;
				}
				if (next != null) {
					if (next.compareTo(last) < 0) {
						System.out.println("ERROR! " + last + " came before " + next);
						bstError = true;
					} else if (next.compareTo(last) == 0) {
						System.out.println("ERROR! Duplicate entries for " + last);
						bstError = true;
					}
				}
				last = next;
			}
			System.out.println("****** END BST ORDER TEST ******");
			System.out.println("BST height: " + bst.height());
			System.out.println("BST size: " + bst.size());
			System.out.println();
		}
	}
}

