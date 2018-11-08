package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Solution {
	 int[] visited = null;
	 List<Integer> longestPath = new ArrayList<Integer>();
	 public List<Integer> findMinHeightTrees(int n, int[][] edges) {
	        List<Integer> ret = new ArrayList<Integer>();
	        if(n<2 || edges == null){
	            return ret;
	        }
	        
	        int[][] matrix = new int[n][n];
	        for(int i=0;i<edges.length;i++){
	            int j=edges[i][0];
	            int k=edges[i][1];
	            matrix[j][k] = 1;
	            matrix[k][j] = 1;
	        }
	        
	        int[] path = new int[n];
	        visited = new int[n];
	        visit(matrix, 0, path, 0);
	        int length = longestPath.size();
	        ret.add(longestPath.get(length/2 ));
	        if(length %2 == 0){
	        	ret.add(0, longestPath.get(length/2 - 1));
	        }
	        return ret;
	    }
	 public int maxCoins(int[] nums) {
	        return _maxCoins(nums);
	    }
	    
	    public int _maxCoins(int[] nums){
	        if(nums == null || nums.length == 0){
	            return 0;
	        }
	        if(nums.length == 1){
	            return nums[0];
	        }
	        int max = 0;
	        for(int i=0;i<nums.length;i++){
	            int val = 0;
	            int[] newnums = new int[nums.length-1];
	            int j=0;
	            for(;j<i;j++){
	                newnums[j] = nums[j];
	            }
	            for(j=i;j<nums.length-1;j++){
	                newnums[j] = nums[j+1];
	            }
	            if(i==0){
	                val = nums[i]*nums[i+1];
	            }else if(i==nums.length-1){
	                val = nums[i]*nums[i-1];
	            }else{
	                val = nums[i]*nums[i-1]*nums[i+1];
	            }
	            val += _maxCoins(newnums);
	            max = val > max ? val : max;
	        }
	        return max;
	    }
	    
	private void visit(int[][] matrix, int curr, int[] path, int pathLen){
		visited[curr] = 1;
		path[pathLen] = curr;
		boolean isLeaf = true;
		for(int i=0;i<matrix.length;i++){
			if(curr == i){
				continue;
			}
			if(matrix[curr][i] == 1 && visited[i] == 0){
				isLeaf = false;
				visit(matrix, i, path, pathLen+1);
			}
		}
		if(isLeaf){
			if(pathLen + 1 > longestPath.size()){
				longestPath.clear();
				for(int i=0;i<=pathLen;i++){
					longestPath.add(path[i]);
				}
			}
		}
	}
	
	public int strStr(String haystack, String needle) {
		if(haystack == null 
			|| haystack.length() == 0 
			|| needle == null 
			|| needle.length() == 0){
			return -1;
		}
		
		int n=haystack.length();
		int m=needle.length();
		int[] prev = preprocess(needle);
		int p=0;
		int q=0;
		while(p+q<n){
			char cp = haystack.charAt(p);
			while(q>=0 && needle.charAt(q) != cp){
				q = prev[q] - 1;
			}
			q++;
			p++;
			if(q==m){
				return p-q;
			}
			
		}
		return -1;
		
    }
	
	public int[] preprocess(String needle){
		int[] prev = new int[needle.length()];
		prev[0] = 0;
		int p=0;
		int q=1;
		while(q<needle.length()){
			char cq = needle.charAt(q);
			while(p>=0 && needle.charAt(p) != cq){
				p = prev[p]-1;
			}
			prev[q] = p+1;
			p++;
			q++;
		}
		return prev;
	}
	
	
	
	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> result = new ArrayList<List<String>>();
		if(strs == null || strs.length == 0){
			return result;
		}
		int len = 0;
		for(String str : strs){
			len = len < str.length() ? str.length() : len;
		}
		char[][] strArrays = new char[strs.length][len];
		for(int i=0;i<strs.length;i++){
			String str = strs[i];
			for(int j=0;j<str.length();j++){
				strArrays[i][j]=str.charAt(j);
			}
			Arrays.sort(strArrays[i]);
		}
		
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for(int i=0;i<strArrays.length;i++){
		    String str = new String(strArrays[i]);
		    List<String> list = map.get(str);
		    if(list == null){
		    	list = new ArrayList<String>();
		    	map.put(str, list);
		    }
		    list.add(strs[i]);
		}
		return new ArrayList(map.values());
    }

	public boolean isMatch(String s, String p) {
		if(s == null || s.isEmpty()){
			return true;
		}
		
		if(p == null || p.isEmpty()){
			return false;
		}
		char[] array = p.toCharArray();
		char preceding=0;
		for(int i=0;i<array.length;i++){
			int k=i;
			if(array[k]=='*'){
				continue;
			}
			
			int j=0;
			for(j=0;j<s.length();){
				if(k == array.length){
					break;
				}
				char cs = s.charAt(j);
				char cp = array[k];
				if(cs == cp){
					j++;
					k++;
					preceding = cs;
					//move both
				}else if(cp == '.'){
					j++;
					k++;
					preceding = '.';
				}else if(cp == '*'){
					if(k < array.length-1 && array[k+1] == '*'){
						k++;
					}else if(preceding == '.' || cs == preceding){
						if(k < array.length-1 && array[k+1] == cs){
							array[k] = preceding;
							array[k+1] = '*';
						}
						j++;
					}else if(cs != preceding){
						k++;
					}
					//move j;keep
				}else{
					break;
				}
			}
			if(j == s.length()){
				return true;
			}
		}
		return false;
    }

	int x,y,z;
	List<String> state = new ArrayList<String>();
    public boolean canMeasureWater(int x, int y, int z) {
        if(x < y){
            this.x = x;
            this.y = y;
        }else{
            this.x = y;
            this.y = x;
        }
        this.z = z;
        
        int l = 0;
        int h = 0;
        return canMeasure(l, h);
    }
    
    private boolean canMeasure(int l, int h){
        if(l == z || h == z || (l+h) == z){
            return true;
        }
        if(state.contains(l+","+h)){
        	return false;
        }else{
        	state.add(l+","+h);
        }
        boolean can = false;
        if(l < x){
            can = canMeasure(x, h);
            if(can){
                return true;
            }
        }
        if(h < y){
            can = canMeasure(l, y);
            if(can){
                return true;
            }
        }
        
        if(l > 0){
            can = canMeasure(0, h);
            if(can){
                return true;
            }
        }
        
        if(h > 0){
            can = canMeasure(l, 0);
            if(can){
                return true;
            }
        }
        
        if(l>0 && h<y){
            can = canMeasure((y-h)>l ? 0 : (l+h-y), (l+h)>y? y : (l+h));
            if(can){
                return true;
            }
        }
        if(h>0 && l < x){
            can = canMeasure((l+h)>x? x : (l+h), (x-l)>h ? 0 : (l+h-x));
            if(can){
                return true;
            }
        }
        return false;
    }
    
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if(nums == null || nums.length == 0){
            return new ArrayList<Integer>();
        }
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        int idx = 0;
        int maxlen = 1;
        for(int n : nums){
            List<Integer> set = new ArrayList<Integer>();
            set.add(n);
            list.add(set);
        }
        
        for(int i = 0;i<list.size();i++){
            List<Integer> set = list.get(i);
            if(set.size() == 1 && set.contains(18)){
            	System.out.println();
            }
            for(int j=0;j<nums.length;j++){
            	int n = nums[j];
                if(i==j){
                    continue;
                }
                
                boolean add = true;
                for(Integer k : set){
                    if(n % k != 0 && k % n != 0){
                        add = false;
                        break;
                    }
                }
                if(add){
                    set.add(n);
                    if(set.size() > maxlen){
                        maxlen = set.size();
                        idx = i;
                    }
                }
            }
            System.out.println(set);
        }
        return list.get(idx);
    }
    
    public int superPow(int a, int[] b) {
    	List<Integer> modList = new ArrayList<Integer>();
    	int mod = 1;
    	while(true){
    		long l = ((long)a) * mod;
    		mod = (int)(l % 1337);
    		if(modList.size() == 0 || mod != modList.get(0)){
    			modList.add(mod);
    		}else{
    			break;
    		}
    	}
    	int stops = modList.size();
    	mod = 0;
    	for(int p : b){
    		mod = (mod*10 + p) % stops;
    	}
    	return modList.get(mod == 0 ? stops - 1 : mod - 1);
    }
    
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> ret = new ArrayList<int[]>();
        int[][] arr = new int[nums1.length*nums2.length][2];
        for(int i=0;i<nums1.length;i++){
        	for(int j=0;j<nums2.length;j++){
        		int x = i*nums1.length + j;
        		arr[x][0] = nums1[i];
        		arr[x][1] = nums2[j];
        	}
        }
        for(int i=arr.length/2-1;i>=0;i--){
        	heapify(arr, arr.length, i);
        }
        
        for(int i=0;i<k && i<arr.length;i++){
        	int tmp = arr[arr.length-1-i][0];
			arr[arr.length-1-i][0] = arr[0][0];
			arr[0][0] = tmp;
			
			tmp = arr[arr.length-1-i][1];
			arr[arr.length-1-i][1] = arr[0][1];
			arr[0][1] = tmp;
			
			ret.add(arr[arr.length-1-i]);
			heapify(arr, arr.length - 1 - i, 0);
        }
        return ret;
    }
 
	private void heapify(int[][] arr, int len, int root) {
		int m = root;
		int min = arr[m][0] + arr[m][1];
		while(m<len){
			min = arr[m][0] + arr[m][1];
			int l = m*2+1;
			int r = m*2+2;
			if(l < len && (arr[l][0] + arr[l][1]) < min){
				m = l;
				min = arr[l][0] + arr[l][1];
			}
			
			if(r < len && (arr[r][0] + arr[r][1]) < min){
				m = r;
				min = arr[r][0] + arr[r][1];
			}
			
			if(m != root){
				int tmp = arr[m][0];
				arr[m][0] = arr[root][0];
				arr[root][0] = tmp;
				tmp = arr[m][1];
				arr[m][1] = arr[root][1];
				arr[root][1] = tmp;
				root = m;
			}else{
				break;
			}
			
		}
		
	}
	 int combination=0;
	    public int combinationSum4(int[] nums, int target) {
	        int[] comb = new int[target+1];
	        for(int i=1;i<=target;i++){
	        	for(int j=0;j<nums.length;j++){
	        		if(nums[j]==i){
	        			comb[i] = comb[i] + 1;
	        		}else if(i-nums[j] > 0 && comb[i-nums[j]] > 0){
	        			comb[i] = comb[i-nums[j]] + comb[i];
	        		}
	        	}
	        }
	        return comb[target];
	    }
	    
	    private void isCombination(int[] nums, int target){
	        if(target ==0){
	        	combination++;
	        }else if(target < 0){
	            return;
	        }else{
	            for(int n : nums){
	                isCombination(nums, target-n);
	            }
	        }
	    }
	    
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        n = k<n? k : n;
        int[] pointers = new int[n];
        for(int i=0;i<pointers.length;i++){
        	pointers[i] = -1;
        }
        int minIdx = -1;
        for(int j=0;j<k;j++){
        	boolean first = true;
	        for(int i=0;i<n;i++){
	        	if(pointers[i] + 1 < n){
	        		if(first){
	        			first = false;
	        			minIdx = i;
	        		}else if(matrix[i][pointers[i] + 1] < matrix[minIdx][pointers[minIdx]+1]){
	        			minIdx = i;
	        		}
	        	}
	    	}
	        pointers[minIdx] = pointers[minIdx] + 1;
        }
        return matrix[minIdx][pointers[minIdx]];
     }
	private int findNext(int[][] matrix, int[][] visit, int s) {
		int len = visit.length;
		int i = s/len;
		int j = s%len;
		int mini=0, minj=0;
		boolean first=true;
		if(i>0 && j<len-1 && visit[i-1][j+1] != 1){
			if(first){
				mini = i-1;
				minj = j+1;
				first = false;
			}else{
				if(matrix[i-1][j+1] < matrix[mini][minj]){
					mini = i-1;
					minj = j+1;
				}
			}
		}
		
		if(j<len-1 && visit[i][j+1] != 1){
			if(first){
				mini = i;
				minj = j+1;
				first = false;
			}else{
				if(matrix[i][j+1] < matrix[mini][minj]){
					mini = i;
					minj = j+1;
				}
			}
			
		}
		
		if(i<len-1 && visit[i+1][j] != 1){
			if(first){
				mini = i+1;
				minj = j;
				first = false;
			}else{
				if(matrix[i+1][j] < matrix[mini][minj]){
					mini = i+1;
					minj = j;
				}
			}
			
		}
		
		if(i<len-1 && j>0 && visit[i+1][j-1] != 1){
			if(first){
				mini = i+1;
				minj = j-1;
				first = false;
			}else{
				if(matrix[i+1][j-1] < matrix[mini][minj]){
					mini = i+1;
					minj = j-1;
				}
			}
			
		}
		visit[mini][minj] = 1;
		return mini*len+minj;
	}
	public String decodeString(String s) {
		if(s == null){
			return null;
		}
        Stack<Object> stack = new Stack<Object>();
        char[] array = s.toCharArray();
        int i=0;
        int number = 0;
        StringBuilder sb = new StringBuilder();
        while(i<array.length){
        	char c = array[i];
        	if(c >= '0' && c <= '9'){
        		if(sb.length() != 0){
        			stack.push(sb.toString());
        			sb = new StringBuilder();
        		}
        		number = number*10 + c - '0';
        		i++;
        	}else if(c == '['){
        		stack.push(number);
        		number = 0;
        		sb = new StringBuilder();
        		i++;
        	}else if(c == ']'){
        		String ss = null;
        		if(sb.length() == 0){
        			ss = (String)stack.pop();
        		}else{
        			ss = sb.toString();
        			sb = new StringBuilder();
        		}
        		Object o = stack.pop();
        		while(o instanceof String){
        			ss = o.toString() + ss;
        			o = stack.pop();
        		}
        		int n = (Integer)o;
        		StringBuilder tmpS = new StringBuilder(); 
        		for(int j=0;j<n;j++){
        			tmpS.append(ss);
        		}
       			stack.push(tmpS.toString());
        		i++;
        	}else{
        		sb.append(c);
        		i++;
        	}
        }
        stack.push(sb.toString());
        sb = new StringBuilder();
        while(!stack.isEmpty()){
        	sb.insert(0, stack.pop());
        }
        return sb.toString();
	}
	
	public int longestSubstring(String s, int k) {
		if(s == null || s.length() == 0){
			return 0;
		}
		char[] array = s.toCharArray();
		int max = 0;
		for(int i=0;i<array.length;i++){
			int[] count = new int[26];
			Set<Character> list = new HashSet<Character>();
			for(int j=i;j<array.length;j++){
				char c = array[j];
				count[c-'a']++;
				if(count[c-'a'] >=k){
					list.remove(c);
				}else if(!list.contains(c)){
					list.add(c);
				}
				if(list.isEmpty()){
					int len = j-i+1;
					if(len > max){
						max = len;
					}
				}
			}
		}
		return max;
    }
	
	public int maxRotateFunction(int[] A) {
        if(A.length <= 1){
        	return 0;
        }

        int max = 0;
        for(int i=0;i<A.length;i++){
        	max += i*A[i];
        }
        int sum = max;
        int cur = 0;
        int next = 1;
        for(int i=1;i<A.length;i++){
        	int diff = 0;
        	for(int j=1;j<A.length;j++){
        		diff += j*(A[cur]-A[next]);
        		cur = cur+1 == A.length ? 0 : cur+1;
        		next = next+1 == A.length ? 0 : next +1;
        	}
        	sum += diff;
        	max = sum > max ? sum : max;
        }
        return max;
    }
	public int integerReplacement(int n) {
		if(n == 1){
			return 0;
		}
		if(n % 2 == 0){
			return 1 + integerReplacement(n/2);
		}else{
			int m1 = integerReplacement(n/2+1);
			int m2 = integerReplacement(n/2);
			return m1 > m2 ? m2 + 2 : m1 + 2;
		}
		
    }
	
	public String removeKdigits(String num, int k) {
		StringBuilder sb = new StringBuilder();
		int start=0;
		int idx = 0;
		int n=k;
		while(k>0 && sb.length() < num.length() - k){
			idx = smallest(num, start, n);
			sb.append(num.charAt(idx));
			n -= idx - start;
			start = idx + 1;
		}
		if(k==0){
			sb.append(num.substring(idx+1));
		}
		String s = sb.toString();
		int i=0;
		for(;i<s.length();i++){
			if(s.charAt(i) != '0'){
				break;
			}
		}
		return i < s.length() ? s.substring(i) : "0";
    }
	
	private int smallest(String num, int start, int k){
		char c = num.charAt(start);
		int smallest = start;
		for(int i=1;i<=k;i++){
			if(num.charAt(start+i) < c){
				smallest = start+i;
				c = num.charAt(start+i);
			}
		}
		return smallest;
	}
	
	public int shortestDistance(int[][] grid) {
		int row = grid.length;
		int col = grid[0].length;
    }
	public static void main(String[] args){
		Solution solution = new Solution();
//		System.out.println(solution.maxCoins(new int[]{7,9,8,0,7,1,3,5,5,2,3}));
//		System.out.println(solution.findMinHeightTrees(4, new int[][]{{1, 0}, {1, 2}, {1, 3}}));
//		System.out.println(solution.findMinHeightTrees(6, new int[][]{{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}}));
//		int[] prev = solution.preprocess("AABAACAABAA");
//		for(int i=0;i<prev.length;i++)
//			System.out.print(prev[i]);
//			
//		System.out.println(solution.strStr("AABACBAACAABAA", "BAA"));
//		solution.groupAnagrams(new String[]{"", "b"});
//		System.out.println(solution.largestDivisibleSubset(new int[]{359,376,43,315,167,216,777,625,498,442,172,324,987,400,280,367,371,24,418,208,812,488,861,646,63,804,863,853,102,174,443,901,486,126,419,701,254,550,48,214,873,386,965,504,753,336,527,522,895,339,361,755,423,558,551,276,11,724,70,823,624,555,300,42,607,554,84,508,953,649,732,338,613,236,90,762,612,194,452,972,140,747,209,690,22,220,413,91,36,998,341,77,956,246,512,464,198,547,888,476,782,977,776,896,940,321,347,264,621,10,829,383,939,825,441,326,822,754,130,379,265,945,577,491,252,273,792,168,699,866,319,704,708,148,230,521,914,988,846,88,121,600,217,499,513,427,344,3,242,947,627,325,146,469,375,12,815,46,67,193,648,963,876,78,366,531,49,532,475,875,398,69,821,454,497,170,922,872,533,736,917,951,609,461,598,571,118,798,981,835,113,530,799,995,930,682,38,405,557,787,377,810,278,874,331,199,97,215,286,13,165,473,115,816,584,707,237,568,72,166,249,805,247,746,534,408,759,739,925,855,305,210,219,470,807,936,974,417,519,288,15,64,438,581,455,250,503,496,145,256,327,255,346,251,109,650,813,679,119,619,721,406,593,489,924,964,563,897,27,769,687,608,224,462,432,39,937,384,990,45,33,154,723,152,772,795,364,283,833,395,495,164,181,232,116,899,458,548,191,320,889,587,353,661,856,814,764,529,737,948,127,335,695,960,858,801,543,916,588,478,103,592,20,481,958,618,334,424,397,694,314,158,114,700,381,287,683,966,459,923,902,332,892,235,938,178,431,631,296,885,820,409,585,141,223,535,688,258,689,884,720,365,611,277,985,684,416,666,182,961,108,355,525,862,412,549,186,244,589,421,52,76,718,352,702,510,117,290,692,603,864,323,388,536,392,151,436,350,788,75,900,490,306,975,207,261,870,188,729,231,485,348,507,676,238,111,180,984,135,771,671,51,1,997,675,869,950,445,434,92,137,221,907,245,17,794,360,935,370,239,362,175,620,973,784,106,136,122,281,426,196,134,68,634,672,28,385,411,526,735,633,841,227,86,500,653,906,933,932,129,435,756,262,698,329,204,941,614,668,139,403,229,243,808,857,659,640,545,345,82,228,516,734,566,868,414,474,506,363,87,173,578,575,312,169,908,929,444,685,657,23,524,358,225,9,41,999,834,546,920,849,456,93,651,433,586,882,942,457,62,839,818,260,369,773,890,865,596,98,271,669,962,311,996,160,200,767,539,163,800,757,582,343,538,131,567,446,213,378,959,299,915,761,313,845,712,330,253,573,18,138,317,56,691,349,605,463,652,781,992,422,32,664,711,284,741,289,57,697,368,583,943,40,298,430,851,913,745,65,179,705,630,401,674,465,487,878,477,240,35,572,838,968,678,342,775,30,806,680,969,2,241,909,803,979,460,518,156,85,643,850,597,843,89}));
		
//		System.out.println(solution.kthSmallest(new int[][]{{1,2},{1,3}}, 2));
		System.out.println(solution.removeKdigits("1101219",3));
	}
	
	
}
