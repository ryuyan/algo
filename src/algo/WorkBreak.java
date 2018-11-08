package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WorkBreak {
	List<String> ret = new ArrayList<String>();
    public List<String> wordBreak(String s, Set<String> dict) {
         if(s == null || dict.isEmpty()){
            return ret;
        }
        
        boolean[] f = new boolean[s.length() + 1];
        boolean[][] g = new boolean[s.length() + 1][s.length() + 1];
        f[0]=true;
        for(int i=0;i<s.length();i++){
            if(!f[i]){
                continue;
            }
            for(int j=i+1;j<=s.length();j++){
                if(dict.contains(s.substring(i, j))){
                    f[j] = true;
                    g[i][j] = true;
                }
            }
        }
        if(f[s.length()]){
        	getSentences(g, s, 0, null);
        }
        return ret;
    }
    
    private void getSentences(boolean[][] g, String s, int i, String sentence){
    	Set<Integer> set = new HashSet<Integer>();
   
        if(i==s.length()){
            ret.add(sentence);
        }
        for(int j = i+1; j <= s.length();j++){
            if(g[i][j]){
                getSentences(g, s, j, sentence == null ? s.substring(i,j) : sentence + " " + s.substring(i,j));
            }
        }
    }
	
    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 ==null) return null;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<Integer> set = new ArrayList<Integer>();
        for(int n : nums1){
            Integer count = map.get(n);
            if(count == null) count =1;
            else count=count+1;
            map.put(n, count);
        }
        for(int m : nums2){
            Integer count = map.get(m);
            if(count != null){
                if(count > 0) set.add(m);
                else map.put(m, count-1);
            }
        }
        if(set.isEmpty()){
            return new int[0];
        }
        int[] ret = new int[set.size()];
        int i=0;
        for(int k : set){
            ret[i++] = k;
        }
        return ret;
    }
    
    public int getSum(int a, int b) {
        if(b == 0) return a;
        int sum = a^b;
        int carry = (a&b)<<1;
        return getSum(sum, carry);
    }
    
public int findNthDigit(int n) {
        
        long k = 1;
        long m = 1;
        if(n < 10) return n;
        while(n>k*m*9){
            n -= k*9*m;
            m++;
            k *= 10;
        }
        if(n==0) return 9;
        long h = n/(m*k) + 1;
        long l = n/m -1;
        if(n % m == 1) return (int)h;
        else if(n % m ==0) return (int)l%10;
        else{
            int digit = (int)l%10;
            for(int i=0;i<m-n%m&&l>0;i++){
                l = l/10;
                digit = (int)l %10;
            }
            return digit;
        }

    }

public List<String> readBinaryWatch(int num) {
    List<String> ret = new ArrayList<String>();
    if(num>8 || num < 0) return ret;
    for(int i=0;i<=num&&i<4;i++){
        if(num-i >=6) {
            continue;
        }
        List<String> hours = new ArrayList<String>();
        List<String> minutes = new ArrayList<String>();
        binaryValue(i, 4, 0,hours, true);
        binaryValue(num-i, 6, 0,minutes, false);
        for(int j=0;j<hours.size();j++){
            for(int k=0;k<minutes.size();k++){
                ret.add(hours.get(j)+":"+minutes.get(k));
            }
        }
    }
    
    return ret;
}


private void binaryValue(int numberOfOne, int numberOfLED, int value,  List<String> result, boolean hour){
  if(numberOfLED == 1) {
        value = 2*value + (numberOfOne > 0 ? 1 : 0);
        if(value >=12 && hour){
        	return;
        }else if(value >=60 && !hour){
        	return;
        }else if(value < 10 && !hour){
            result.add("0" + Integer.toString(value));
        }else{
            result.add(Integer.toString(value));
        }
    }
    else if(numberOfOne == numberOfLED){
        binaryValue(numberOfOne -1, numberOfLED - 1, 2*value+1, result,hour);
    }else if(numberOfOne > 0){
        binaryValue(numberOfOne -1, numberOfLED - 1, 2*value+1,result,hour);
        binaryValue(numberOfOne, numberOfLED - 1, 2*value,result,hour);
    }else{
    	binaryValue(numberOfOne, numberOfLED - 1, 2*value,result,hour);
    }
    
}

public String toHex(int num) {
    long number = 0;
    if(num == 0){
        return "0";
    }else if(num > 0){
        number = (long)num;
    }else{
        number = 0x100000000l - (long)(-1*num);
    }
    
    StringBuilder sb = new StringBuilder();
    int count = 0;
    while(number != 0 && count < 8){
        long mod = number % 16;
        char c = 0;
        if(mod >= 0 && mod <10){
            c = (char)('0' + mod);
        }else{
            c = (char)('a' + mod - 10);
        }
        sb.insert(0,c);
        number /= 16;
        count++;
    }
    return sb.toString();
}
public boolean isValidSerialization(String preorder) {
    if(preorder == null || preorder.equals("#")){
        return false;
    }
    while(!preorder.equals("#")){
        String[] arr = preorder.split(",");
        StringBuilder sb = new StringBuilder();
        boolean isValid = false;
        int i = 0;
        while(i<arr.length){
            if(i<arr.length - 2 && !arr[i].equals("#") && arr[i+1].equals("#") && arr[i+2].equals("#")){
            	if(sb.length() > 0){
            		sb.append(",");
            	}
                sb.append('#');
                isValid = true;
                i += 3;
            }else{
            	if(sb.length() > 0){
            		sb.append(",");
            	}
                sb.append(arr[i]);
                i++;
            }
        }
        if(!isValid){
            return false;
        }
        preorder = sb.toString();
    }
    return true;
}

public List<String> findItinerary(String[][] tickets) {
    boolean[] visited = new boolean[tickets.length];
    sort(tickets);
    List<String> result = new ArrayList<String>();
    int i=0;
    for(String[] ticket : tickets){
        if(ticket[0].equals("JFK")){
            visited[i] = true;
            result.add("JFK");
            result.add(ticket[1]);
            if(visit(tickets, visited, result)){
                return result;
            }else{
            	visited[i] = false;
                result.remove("JFK");
                result.remove(ticket[1]);
            }
        }
        i++;
    }
    return  new ArrayList<String>();
}

public boolean visit(String[][] tickets, boolean[] visited, List<String> result){
    String from = result.get(result.size() - 1);
    boolean foundTo = false;
    int stops = 0;
    for(int i=0;i<tickets.length;i++){
    	String[] ticket = tickets[i];
        if(visited[i]){
        	stops++;
            continue;
        }
        if(ticket[0].equals(from)){
            visited[i] = true;
            result.add(ticket[1]);
            foundTo = visit(tickets, visited, result);    
            if(foundTo){
                return true;
            }else{
                visited[i] = false;
                result.remove(result.size() - 1);
            }
        }
    }
    return stops == visited.length ? true : foundTo;
}
private void sort(String[][] tickets){
    for(int i=0;i<tickets.length-1;i++){
        int min = i;
        for(int j = i+1;j<tickets.length;j++){
            int result = compare(tickets[min], tickets[j]);
            if(result > 0){
                min = j;
            }
        }
        if(min != i){
            String tmp = tickets[min][0];
            tickets[min][0]  = tickets[i][0];
            tickets[i][0] = tmp;
            
            tmp = tickets[min][1];
            tickets[min][1]  = tickets[i][1];
            tickets[i][1] = tmp;
        }
    }
}

public boolean increasingTriplet(int[] nums) {
    if(nums == null || nums.length < 3){
        return false;
    }
    int m1=0;
    int m2=0;
    int i = 1;
    for(i=1;i<nums.length;i++){
        if(nums[i] > nums[i-1]){
            m1 = i-1;
            m2 = i;
            break;
        }
    }
    if(m1==0&&m2==0){
        return false;
    }
    i++;
    for(;i<nums.length;i++){
        if(nums[i] > nums[m2]){
            return true;
        }
        if(nums[i] > nums[i-1]){
            if(nums[i-1] > nums[m1]){
                return true;
            }else{
                m1 = i-1;
                m2 = i;
            }
            
        }
    }
    return false;
}
private int compare(String[] ticket1, String[] ticket2){
    int val = ticket1[0].compareTo(ticket2[0]);
    return val != 0 ? val : ticket1[1].compareTo(ticket2[1]);
}

int max = 0;
int total = 0;
public int rob(TreeNode root) {
    total = totalMoney(root);
    visit(root, null);
    return max;
}

private void visit(TreeNode node, TreeNode parent){
    if(node == null){
        return;
    }
    int parentMoney = parent == null? 0 : parent.val;
    int leftMoney = node.left == null ? 0 : node.left.val;
    int rightMoney = node.right == null ? 0 : node.right.val;
    int money = total - parentMoney - leftMoney - rightMoney;
    max = money > max ? money : max;
    visit(node.left, node);
    visit(node.right, node);
}

private int totalMoney(TreeNode root){
    if(root == null){
        return 0;
    }
    return totalMoney(root.left) + totalMoney(root.right);
}

public int integerBreak(int n) {
    int[] maxProducts = new int[59];
    maxProducts[1] = 1;
    maxProducts[2] = 1;
    for(int i=3;i<=n;i++){
        int max = 0;
        for(int j=1;j<=i/2;j++){
            int product = (maxProducts[j] > j ? maxProducts[j] : j) * (maxProducts[i-j] > (i-j) ? maxProducts[i-j] : (i-j));
            max = product > max ? product : max;
        }
        maxProducts[i] = max;
    }
    return maxProducts[n];
}

Map<Integer, Integer> map = new HashMap<Integer, Integer>();
public List<Integer> topKFrequent(int[] nums, int k) {
    
    List<Integer> ret = new ArrayList<Integer>();
    for(int n : nums){
        Integer count = map.get(n);
        if(count != null){
            map.put(n, count+1);
        }else{
            map.put(n, 1);
        }
    }
    int[] arr = new int[map.size()];
    int i = 0;
    for(Integer n : map.keySet()){
        arr[i++] = n;
    }
    for(i=arr.length/2-1;i>=0;i++){
        heapify(arr, arr.length, i);
    }
    for(i=1;i<=k;i++){
        ret.add(arr[0]);
        int tmp = arr[arr.length-i];
        arr[arr.length-i] = arr[0];
        arr[0] = tmp;
        heapify(arr, arr.length-i, 0);
    }
    return ret;
}

private void heapify(int[] arr, int length, int root){
    int i=root;
    int max = root;
    while(i<length/2){
        int left = i*2+1;
        int right = i*2+2;
        if(left < length){
            max = map.get(arr[max]) > map.get(arr[left]) ? max : left;
        }
        if(right < length){
            max = map.get(arr[max]) > map.get(arr[right]) ? max : right;
        }
        if(max != i){
            int tmp = arr[i];
            arr[i] = arr[max];
            arr[max] = tmp;
            i = max;
        }else{
            break;
        }
    }
    
}
	public static void main(String[] args){
		WorkBreak wb = new WorkBreak();
//		String[] dictArray = {"a","b"};
//		Set<String> dict = new HashSet<String>(Arrays.asList(dictArray));
//		List<String> ret = wb.wordBreak("ab", dict);
//		for(String s : ret){
//			System.out.println(s);
//		}

//		System.out.println(wb.intersect(new int[]{4,9,5}, new int[]{9,4,9,8,4}));
	
		System.out.println(wb.topKFrequent(new int[]{1,2}, 2));
	}
}
