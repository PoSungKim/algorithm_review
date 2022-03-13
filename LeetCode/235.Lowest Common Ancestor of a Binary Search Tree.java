class Solution {
    
    TreeNode ans;
    
    public boolean traverse(TreeNode curNode, TreeNode p, TreeNode q) {    
        
        if (ans != null) return false;
        
        int cnt = 0;
        if (curNode.val == p.val || curNode.val == q.val) 
            cnt++;
        
        if (curNode.left != null) 
            if (traverse(curNode.left, p, q)) 
                cnt++;
        
        if (curNode.right != null) 
            if (traverse(curNode.right, p, q))
                cnt++;
        
        if (cnt > 0) {
            if (cnt >= 2) ans = curNode;
            return true;
        }
        
        return false;
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        traverse(root, p, q);
        
        return ans;
    }
}
