/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> al = new ArrayList<Integer>();
        if(root==null) return al;
        
        
        Stack<TreeNode> st = new Stack<TreeNode>();
        st.push(root);
        TreeNode pre = null;
        while(!st.isEmpty()){
            TreeNode cur = st.peek();
            // first visit cur
            if(pre==null || pre.left==cur || pre.right==cur){
                if(cur.left!=null)          st.push(cur.left);
                else if(cur.right!=null)    st.push(cur.right);
                else {
                    al.add(cur.val);
                    st.pop();
                }
            }
            // left been visited 
            else if(cur.left == pre)
                if(cur.right!=null) st.push(cur.right);
                else{
                    al.add(cur.val);
                    st.pop();
                }
            else if(cur.right == pre){
                al.add(cur.val);
                st.pop();
            }
            pre = cur;   
        }
       
        return al;       
    }
}
    
