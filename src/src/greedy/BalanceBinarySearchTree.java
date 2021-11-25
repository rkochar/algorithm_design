package greedy;

import java.util.ArrayList;

public class BalanceBinarySearchTree {

	public TreeNode balanceBST(TreeNode root) {
		ArrayList<TreeNode> al = new ArrayList<>();
		inorder(root, al);
		return BalancedBST(0, al.size()-1, al);
	}
	public void inorder(TreeNode node, ArrayList<TreeNode> al){
		if(node == null) return;
		inorder(node.left, al);
		al.add(node);
		inorder(node.right, al);
	}
	public TreeNode BalancedBST(int left, int right, ArrayList<TreeNode> al){
		if(right<left) return null;
		int x = (int)((left+right)/2);
		TreeNode node = new TreeNode(al.get(x).val);
		node.left = BalancedBST(left, x-1, al);
		node.right = BalancedBST(x+1, right, al);
		return node;
	}
}

 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }
