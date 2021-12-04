package DivideConquer.Weblab;

public class DepthFirstSearch {

	/**
	 * Recursively searches for the element.
	 * Returns true if element can be found, else false.
	 *
	 * @param tree    - tree that you need to look in.
	 * @param element - the element that you are looking for.
	 * @return true if found, else false.
	 */
	public boolean search(BinaryTree tree, int element) {
		if (tree.getKey() == element) return true;

		if (tree.hasRight() && tree.hasLeft()) return search(tree.getLeft(), element) || search(tree.getRight(), element);
		if (tree.hasLeft()) return search(tree.getLeft(), element);
		if (tree.hasRight()) return search(tree.getRight(), element);
		return false;
	}
}

class BinaryTree {

	private int key;

	private BinaryTree left, right;

	/**
	 * Simple constructor.
	 *
	 * @param key to set as key.
	 */
	public BinaryTree(int key) {
		this.key = key;
	}

	/**
	 * Extended constructor.
	 *
	 * @param key   to set as key.
	 * @param left  to set as left child.
	 * @param right to set as right child.
	 */
	public BinaryTree(int key, BinaryTree left, BinaryTree right) {
		this.key = key;
		setLeft(left);
		setRight(right);
	}

	public int getKey() {
		return key;
	}

	/**
	 * @return the left child.
	 */
	public BinaryTree getLeft() {
		return left;
	}

	/**
	 * @return the right child.
	 */
	public BinaryTree getRight() {
		return right;
	}

	public boolean hasLeft() {
		return left != null;
	}

	public boolean hasRight() {
		return right != null;
	}

	/**
	 * @param left to set
	 */
	public void setLeft(BinaryTree left) {
		this.left = left;
	}

	/**
	 * @param right to set
	 */
	public void setRight(BinaryTree right) {
		this.right = right;
	}
}
