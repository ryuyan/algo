package algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LargestNumber {
	public String largestNumber(int[] num) {
		Stack<Integer> stack = new Stack<Integer>();
        if(num == null){
            return "";
        }
        
        List<List<Integer>> numSeq = new ArrayList<List<Integer>>();
        for(int n : num){
            numSeq.add(convert(n));
        }
        StringBuffer sb = new StringBuffer();
        while(!numSeq.isEmpty()){
            List<Integer> max = pickFromList(numSeq);
            for(int i = 0; i< max.size(); i++){
                int n = max.get(i);
                if(i == 0 && n == 0 && sb.toString().isEmpty()){
                    continue;
                }
                sb.append(n);
            }
            numSeq.remove(max);
        }
        return sb.toString().isEmpty() ? "0" : sb.toString();
    }
    
    private List<Integer> convert(int n){
        List<Integer> list = new ArrayList<Integer>();
        if(n == 0){
            list.add(0);
        }else{
            while(n!=0){
                list.add(n%10);
                n /= 10;
            }
        }
        List<Integer> ret = new ArrayList<Integer>();
        for(int i = list.size() - 1; i>=0; i--){
            ret.add(list.get(i));
        }
        return ret;
    }
    private List<Integer> pickFromList(List<List<Integer>> numSeq){
        List<Integer> max = numSeq.get(0);
        for(int i = 1; i < numSeq.size(); i++){
            List<Integer> cur = numSeq.get(i);
            int j = 0;
            int pm = 0;
            int pc = 0;
            while(j < max.size() || j < cur.size()){
                if(j<max.size()){
                    pm = max.get(j);                
                }
                if(j < cur.size()){
                    pc = cur.get(j);
                }
                if(pm > pc){
                    break;
                }else if(pm < pc){
                    max = cur;
                    break;
                }
                j++;
            }
        }
        return max;
    }
	public static void main(String[] args) {
		new LargestNumber().largestNumber(new int[]{824,938,1399,5607,6973,5703,9609,4398,8247});
	}

}
