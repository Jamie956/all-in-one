package leetC;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class _102 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if(root==null){return result;}
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while(!queue.isEmpty()){
                ArrayList<Integer> levelValues = new ArrayList<>();
                int size = queue.size();
                for(int i=0;i<size;i++){
                    TreeNode node = queue.pollFirst();
                    levelValues.add(node.val);
                    if(node.left != null) {
                        queue.add(node.left);
                    }
                    if(node.right != null) {
                        queue.add(node.right);
                    }
                }
                result.add(levelValues);
            }

            return result;
        }
    }
}
