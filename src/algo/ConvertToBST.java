package algo;

public class ConvertToBST {
	 public TreeNode sortedArrayToBST(int[] num) {
	        if(num == null){
	            return null;
	        }
	        return convertToBST(0, num.length, num);
	    }
	    
	    private TreeNode convertToBST(int left, int right, int[] num){
	        if(right - left <= 0){
	            return null;
	        }
	        if(right - left == 1){
	            return new TreeNode(num[left]);
	        }
	        
	        int middle = (right + left) / 2;
	        TreeNode root = new TreeNode(num[middle]);
	        root.left = convertToBST(left, middle, num);
	        root.right = convertToBST(middle+1, right, num);
	        return root;
	    }
	    
	      public class TreeNode {
	         int val;
	          TreeNode left;
	          TreeNode right;
	          TreeNode(int x) { val = x; }
	      }
	     
	      public static void main(String[] args){
	    	  new ConvertToBST().sortedArrayToBST(new int[]{-10,-3,0,5,9});
	      }
}
