class Solution {
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ans = root;
        
        while(ans != null) {
            if (p.val > root.val && q.val > root.val)      ans = ans.right;
            else if (p.val < root.val && q.val < root.val) ans = ans.left;
            else return ans;
        }
        
        return ans;
    }
}
