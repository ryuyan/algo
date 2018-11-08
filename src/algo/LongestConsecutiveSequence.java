package algo;

import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {

	 public int longestConsecutive(int[] num) {
		 if(num == null)   {
	           return 0;
	       }
	       Map<Integer, Integer> numMap = new HashMap<Integer, Integer>();
	       Map<Integer, Integer> numLeftBoundMap = new HashMap<Integer, Integer>();
	       Map<Integer, Integer> numRightBoundMap = new HashMap<Integer, Integer>();
	       int longest = 1;
	       for(int n : num){
	           if(numMap.get(n) != null){
	               continue;
	           }
	           int longestLeft = numMap.get(n-1) == null ? 0 : numMap.get(n-1);
	           int longestRight = numMap.get(n+1) == null ? 0 : numMap.get(n+1);
	           longest = (longestLeft + longestRight + 1) > longest ? (longestLeft + longestRight + 1) : longest;
	           numMap.put(n, (longestLeft + longestRight + 1));
	           if(longestLeft != 0 && longestRight != 0){
	        	   int leftBound = numLeftBoundMap.get(n-1);
	        	   int rightBound = numRightBoundMap.get(n+1);
	        	   numMap.put(leftBound, (longestLeft + longestRight + 1));
	        	   numMap.put(rightBound, (longestLeft + longestRight + 1));
	        	   numLeftBoundMap.put(rightBound, leftBound);
	        	   numRightBoundMap.put(leftBound, rightBound);
	           }else if(longestLeft != 0){
	               int leftBound = numLeftBoundMap.get(n-1);
	               numMap.put(leftBound, (longestLeft + longestRight + 1));
	               numLeftBoundMap.put(n, leftBound);
	               numRightBoundMap.put(leftBound, n);
	           }else if(longestRight != 0){
	               int rightBound = numRightBoundMap.get(n+1);
	               numMap.put(rightBound, (longestLeft + longestRight + 1));
	               numLeftBoundMap.put(rightBound, n);
	               numRightBoundMap.put(n, rightBound);
	           }else{
	               numLeftBoundMap.put(n, n);
	               numRightBoundMap.put(n, n);
	           }
	       }
	       return longest;
	    }
	public static void main(String[] args) {
		int[] num = {0,3,7,2,5,8,4,6,0,1};
		LongestConsecutiveSequence lcs = new LongestConsecutiveSequence();
		lcs.longestConsecutive(num);

	}

}
