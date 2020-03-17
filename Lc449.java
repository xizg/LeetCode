/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null)
            return "";
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        String layer ="";
        while(!q.isEmpty()){
            int len = q.size();

            while(len-->0){
                TreeNode node = q.poll();
                if(node == null) 
                    layer+="-";
                
                else{ 
                    layer+= node.val;
                    q.offer(node.left);
                    q.offer(node.right);  
                }
                layer+="^";
            }
        }
        return layer.substring(0, layer.length()-1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data=="")
	            return null;
        
        String values[] = data.split("\\^");
        TreeNode root = new TreeNode(Integer.valueOf(values[0]));
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        int idx=1;
        while(!q.isEmpty()){
            
            int len=q.size();
            while(len-->0){
                TreeNode node = q.poll();
                // if(node == null) continue;

                if(values[idx++].equals("-")) node.left=null;               
                else node.left = new TreeNode(Integer.parseInt(values[idx-1]));

                if(values[idx++].equals("-")) node.right=null;
                else node.right = new TreeNode(Integer.parseInt(values[idx-1]));
                if(node.left!=null) q.offer(node.left);
                if(node.right!=null) q.offer(node.right); 
            }
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));