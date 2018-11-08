package algo;

import java.util.ArrayList;

import java.util.Collections;

import java.util.HashMap;

import java.util.HashSet;

import java.util.List;

import java.util.Map;

import java.util.Set;

import java.util.SortedMap;

import java.util.TreeMap;

public class MySolution {

	private final static int CAPACITY = 100;

	long[] pool = new long[CAPACITY];

	private int cursor;

	public boolean isRLAllowed() {

		long tsInSec = System.currentTimeMillis() / 1000;

		if (pool[cursor] == tsInSec) {

			return false;

		}

		return true;

	}

	private int next(int i) {

		return i == CAPACITY - 1 ? 0 : i + 1;

	}

	public int kEmptySlots2(int[] flowers, int k) {

		int[] days = new int[flowers.length];

		for (int i = 0; i < flowers.length; i++) {

			days[flowers[i] - 1] = i;

		}

		int l = 0;

		int r = l + k + 1;

		int i = l + 1;

		int mday = -1;

		while (r < flowers.length) {

			if (days[i] > days[l] && days[i] > days[r]) {

				i++;

				if (i == r) {

					int day = days[l] + 1 < days[r] + 1 ? days[r] + 1
							: days[l] + 1;

					if (mday == -1 || day < mday) {

						mday = day;

					}

					l = i;

					r = l + k + 1;

					i = l + 1;

				}

			} else {

				l = i;

				r = l + k + 1;

				i = l + 1;

			}

		}

		return mday;

	}

	public int kEmptySlots(int[] flowers, int k) {

		DoubleListNode<Integer> head = null;

		for (int i = 0; i < flowers.length; i++) {

			int pos = flowers[i];

			if (head == null) {

				head = new DoubleListNode<Integer>(pos);

				continue;

			}

			DoubleListNode<Integer> prev = null;

			DoubleListNode<Integer> node = head;

			while (node != null) {

				if (node.val > pos) {

					break;

				}

				prev = node;

				node = node.next;

			}

			if (node == head) {

				node = new DoubleListNode<Integer>(pos);

				head.prev = node;

				node.next = head;

				head = node;

			} else if (node == null) {

				node = new DoubleListNode<Integer>(pos);

				prev.next = node;

				node.prev = prev;

			} else {

				node = new DoubleListNode<Integer>(pos);

				prev.next.prev = node;

				node.next = prev.next;

				prev.next = node;

				node.prev = prev;

			}

			if (node.prev != null && (node.val - node.prev.val - 1) == k) {

				return i + 1;

			}

			if (node.next != null && (node.next.val - node.val - 1) == k) {

				return i + 1;

			}

		}

		return -1;

	}

	public String nextClosestTime(String time) {

		// get all hours

		// get all minutes

		String[] pair = time.split(":");

		String currentHour = pair[0];

		String currentMinute = pair[1];

		char[] allDigites = new char[4];

		allDigites[0] = currentHour.charAt(0);

		allDigites[1] = currentHour.charAt(1);

		allDigites[2] = currentMinute.charAt(0);

		allDigites[3] = currentMinute.charAt(1);

		List<String> allHours = new ArrayList<String>();

		List<String> allMinutes = new ArrayList<String>();

		for (int i = 0; i < 4; i++) {

			for (int j = 0; j < 4; j++) {

				String num = "" + allDigites[i] + allDigites[j];

				// valid hour

				if (num.compareTo("24") < 0) {

					if (!allHours.contains(num)) {

						allHours.add(num);

					}

				}

				// valid minute

				if (num.compareTo("60") < 0) {

					if (!allMinutes.contains(num)) {

						allMinutes.add(num);

					}

				}

			}

		}

		Collections.sort(allHours);

		Collections.sort(allMinutes);

		// if there is minutes greater than current minute, use current hour and
		// minutes

		if (allMinutes.get(allMinutes.size() - 1).compareTo(currentMinute) > 0) {

			for (String m : allMinutes) {

				if (m.compareTo(currentMinute) > 0) {

					return currentHour + ":" + m;

				}

			}

		} else if (allHours.get(allHours.size() - 1).compareTo(currentHour) > 0) {

			// else if there is hour greater than current hour, use hour and
			// smallest

			// minutes

			for (String h : allHours) {

				if (h.compareTo(currentHour) > 0) {

					return h + ":" + allMinutes.get(0);

				}

			}

		} else {

			// else use smallest hour and smallest minute

			return allHours.get(0) + ":" + allMinutes.get(0);

		}

		return null;

	}

	public class DoubleListNode<T> {

		T val;

		public DoubleListNode<T> prev;

		public DoubleListNode<T> next;

		public DoubleListNode(T val) {

			this.val = val;

		}

	}

	int longestPath = 0;

	public int longestUnivaluePath(TreeNode root) {

		if (root != null) {

			visit(root);

		}

		return longestPath;

	}

	private int visit(TreeNode root) {

		int l = 1;

		int r = 1;

		if (root.left == null && root.right == null) {

			return 1;

		}

		if (root.left != null) {

			int v = visit(root.left);

			if (root.val == root.left.val) {

				l = v + 1;

			}

		}

		if (root.right != null) {

			int v = visit(root.right);

			if (root.val == root.right.val) {

				r = v + 1;

			}

		}

		if (l + r - 2 > longestPath) {

			longestPath = l + r - 2;

		}

		return l > r ? l : r;

	}

	public String licenseKeyFormatting(String S, int K) {

		S = S.replaceAll("-", "");

		S = S.toUpperCase();

		char[] array = S.toCharArray();

		StringBuilder sb = new StringBuilder();

		int k = 0;

		for (int i = array.length - 1; i >= 0; i--) {

			if (k < K) {

				sb.insert(0, array[i]);

				k++;

			} else {

				sb.insert(0, "-");

				sb.insert(0, array[i]);

				k = 1;

			}

		}

		return sb.toString();

	}

	public int[] plusOne(int[] digits) {

		if (digits == null) {

			return null;

		}

		int[] digitsPlusOne = new int[digits.length + 1];

		int carry = 1;

		for (int i = digits.length - 1; i >= 0; i--) {

			int n = digits[i];

			if (n + carry == 10) {

				digitsPlusOne[i + 1] = 0;

				carry = 1;

			} else {

				digitsPlusOne[i + 1] = n + carry;

				carry = 0;

			}

		}

		if (carry == 1) {

			digitsPlusOne[0] = 1;

			return digitsPlusOne;

		} else {

			int[] ret = new int[digits.length];

			for (int i = 0; i < digits.length; i++) {

				ret[i] = digitsPlusOne[i + 1];

			}

			return ret;

		}

	}

	public int trap(int[] height) {

		int l = -1;

		int r = -1;

		int volume = 0;

		int solid = 0;

		if (height.length <= 2) {

			return 0;

		}

		// From left to right

		for (int i = 0; i < height.length; i++) {

			if (l >= 0 && height[i] >= height[l]) {

				volume += height[l] * (i - l - 1) - solid;

				l = -1;

				solid = 0;

			}

			if ((i == 0 && height[i] > height[i + 1])
					|| (i == height.length - 1 && height[i] > height[i - 1])

					|| (i > 0 && i < height.length - 1
							&& height[i] > height[i + 1] && height[i] >= height[i - 1])) {

				if (l == -1) {

					l = i;

					solid = 0;

				} else {

					solid += height[i] > height[l] ? height[l] : height[i];

				}

			} else {

				if (l >= 0) {

					solid += height[i] > height[l] ? height[l] : height[i];

				}

			}

		}

		// From right to left

		for (int i = height.length - 1; i >= 0; i--) {

			if (r >= 0 && height[i] > height[r]) {

				volume += height[r] * (r - i - 1) - solid;

				r = -1;

			}

			if ((i == 0 && height[i] > height[i + 1])
					|| (i == height.length - 1 && height[i] > height[i - 1])

					|| (i > 0 && i < height.length - 1
							&& height[i] >= height[i + 1] && height[i] > height[i - 1])) {

				if (r == -1) {

					r = i;

					solid = 0;

				} else {

					if (r >= 0) {

						solid += height[i] > height[r] ? height[r] : height[i];

					}

				}

			} else {

				if (r >= 0) {

					solid += height[i] > height[r] ? height[r] : height[i];

				}

			}

		}

		return volume;

	}

	public int lengthOfLongestSubstringKDistinct(String s, int k) {

		int l = 0;

		int r = 0;

		int uc = 0;

		char[] array = s.toCharArray();

		Map<Character, Integer> counts = new HashMap<Character, Integer>();

		int max = 0;

		if (s == null || s.isEmpty()) {

			return 0;

		}

		if (k <= 0) {

			return 0;

		}

		while (r < array.length) {

			char c = array[r];

			// first time see this char

			Integer count = counts.get(c);

			if (count == null) {

				count = 0;

			}

			if (count == 0) {

				uc++;

			}

			// increase count so far

			counts.put(c, count + 1);

			if (uc <= k) {

				// check length

				int len = r - l + 1;

				max = len > max ? len : max;

			} else {

				// move l pointer

				while (l < r && uc > k) {

					char cc = array[l];

					// decrease count so far

					count = counts.get(cc);

					count--;

					if (count == 0) {

						uc--;

					}

					counts.put(cc, count);

					l++;

				}

			}

			r++;

		}

		return max;

	}

	public String addBoldTag(String s, String[] dict) {

		if (s == null || s.isEmpty()) {

			return s;

		}

		if (dict == null || dict.length == 0) {

			return s;

		}

		SortedMap<Integer, int[]> map = new TreeMap<Integer, int[]>();

		for (String d : dict) {

			int idx = 0;

			while ((idx = s.indexOf(d, idx)) >= 0) {

				int[] range = new int[2];

				range[0] = idx;

				range[1] = idx + d.length();

				map.put(idx, range);

				idx += d.length();

			}

		}

		// merge

		List<int[]> tagPos = new ArrayList<int[]>();

		int[] range = null;

		for (int[] value : map.values()) {

			if (range == null) {

				range = value;

			} else if (value[0] <= range[1]) {

				range[1] = value[1] > range[1] ? value[1] : range[1];

			} else {

				tagPos.add(range);

				range = value;

			}

		}

		if (range != null) {

			tagPos.add(range);

		}

		StringBuilder sb = new StringBuilder();

		int i = 0;

		for (int[] tag : tagPos) {

			for (; i < tag[0]; i++) {

				sb.append(s.charAt(i));

			}

			sb.append("<b>");

			for (; i < tag[1]; i++) {

				sb.append(s.charAt(i));

			}

			sb.append("</b>");

		}

		for (; i < s.length(); i++) {

			sb.append(s.charAt(i));

		}

		return sb.toString();

	}

	public void gameOfLife(int[][] board) {

		for (int i = 0; i < board.length; i++) {

			for (int j = 0; j < board[i].length; j++) {

				int v = board[i][j];

				int sum = 0;

				if (i > 0) {

					sum += getBoardValue(board[i - 1][j]);

					if (j > 0) {

						sum += getBoardValue(board[i - 1][j - 1]);

					} else if (j < board[i].length - 1) {

						sum += getBoardValue(board[i - 1][j + 1]);

					}

				}

				if (i < board.length - 1) {

					sum += getBoardValue(board[i + 1][j]);

					if (j > 0) {

						sum += getBoardValue(board[i + 1][j - 1]);

					} else if (j < board[i].length - 1) {

						sum += getBoardValue(board[i + 1][j + 1]);

					}

				}

				if (j > 0) {

					sum += getBoardValue(board[i][j - 1]);

				} else if (j < board[i].length - 1) {

					sum += getBoardValue(board[i][j + 1]);

				}

				if (v == 1) {

					if (sum == 0 || sum == 1) {

						board[i][j] = v + 3;

					} else if (sum > 3) {

						board[i][j] = v + 3;

					}

				} else if (sum == 3) {

					board[i][j] = v + 3;

				}

			}

		}

		for (int i = 0; i < board.length; i++) {

			for (int j = 0; j < board[i].length; j++) {

				if (board[i][j] == 3) {

					board[i][j] = 1;

				} else if (board[i][j] == 4) {

					board[i][j] = 0;

				}

			}

		}

	}

	private int getBoardValue(int n) {

		switch (n) {

		case 0:

			return 0;

		case 1:

			return 1;

		case 3:

			return 0;

		case 4:

			return 1;

		}

		return 0;

	}

	public boolean isOneEditDistance(String s, String t) {

		if (s == null || t == null) {

			return false;

		}

		if (s.length() == t.length()) {

			int diff = 0;

			for (int i = 0; i < s.length(); i++) {

				if (s.charAt(i) != t.charAt(i)) {

					diff++;

					if (diff > 1) {

						return false;

					}

				}

			}

		} else if (s.length() == t.length() + 1) {

			int diff = 0;

			int i = 0;

			int j = 0;

			while (j < t.length()) {

				if (s.charAt(i) != t.charAt(j)) {

					diff++;

					if (diff > 1) {

						return false;

					}

					j--;

				}

				i++;

				j++;

			}

		} else if (s.length() == t.length() - 1) {

			int diff = 0;

			int i = 0;

			int j = 0;

			while (j < s.length()) {

				if (s.charAt(j) != t.charAt(i)) {

					diff++;

					if (diff > 1) {

						return false;

					}

					j--;

				}

				i++;

				j++;

			}

		} else {

			return false;

		}

		return true;

	}

	public boolean isPalindrome(String s) {

		if (s == null || s.isEmpty()) {

			return true;

		}

		s = s.toLowerCase();

		int p = 0;

		int q = s.length() - 1;

		while (p < q) {

			char c1 = s.charAt(p);

			char c2 = s.charAt(q);

			if (!((c1 >= 'a' && c1 <= 'z') || (c1 >= 'A' && c1 <= 'Z') || (c1 >= '0' && c1 <= '9'))) {

				p++;

				continue;

			}

			if (!((c2 >= 'a' && c2 <= 'z') || (c2 >= 'A' && c2 <= 'Z') || (c2 >= '0' && c2 <= '9'))) {

				q--;

				continue;

			}

			if (c1 != c2) {

				return false;

			}

			p++;

			q--;

		}

		return true;

	}

	public ListNode mergeKLists(ListNode[] lists) {

		ListNode head = null;

		ListNode node = null;

		ListNode current = null;

		while ((node = pickSmallest(lists)) != null) {

			if (current == null) {

				current = node;

				head = node;

			} else {

				current.next = node;

				current = node;

			}

			current.next = null;

		}

		return head;

	}

	private ListNode pickSmallest(ListNode[] lists) {

		int smallest = -1;

		int i = 0;

		for (i = 0; i < lists.length; i++) {

			ListNode node = lists[i];

			if (node != null) {

				if (smallest == -1) {

					smallest = i;

				} else {

					smallest = lists[smallest].val <= node.val ? smallest : i;

				}

			}

		}

		ListNode node = null;

		if (smallest != -1) {

			node = lists[smallest];

			lists[smallest] = lists[smallest].next;

		}

		return node;

	}

	public int numDecodings(String s) {

		if (s == null || s.isEmpty()) {

			return 0;

		}

		char[] array = s.toCharArray();

		int[] ways = new int[array.length];

		if (array.length == 1) {

			return 1;

		}

		ways[0] = 1;

		for (int i = 1; i < array.length; i++) {

			ways[i] = ways[i - 1];

			int number = (array[i - 1] - '0') * 10 + (array[i] - '0');

			if (number >= 1 && number <= 26) {

				ways[i] = ways[i] + 1;

			}

		}

		return ways[array.length - 1];

	}

	public boolean wordBreak(String s, List<String> wordDict) {

		if (s == null || s.isEmpty())

		{

			return false;

		}

		int len = s.length();

		boolean[] f = new boolean[len];

		for (int i = 0; i < len; i++)

		{

			if (inDict(wordDict, s.substring(0, i + 1))) {

				f[i] = true;

			} else {

				for (int j = 0; j < i; j++) {

					if (f[j] && inDict(wordDict, s.substring(j + 1, i + 1))) {

						f[i] = true;

						break;

					}

				}

			}

		}

		return f[len - 1];

	}

	private boolean inDict(List<String> wordDict, String s) {

		for (String w : wordDict) {

			if (w.equals(s)) {

				return true;

			}

		}

		return false;

	}

	public int wordsTyping(String[] sentence, int rows, int cols) {

		int[] f = new int[sentence.length];

		for (int i = 0; i < sentence.length; i++) {

			int count = 0;

			int j = i;

			int len = 0;

			while (len < cols) {

				if (sentence[j].length() + len > cols) {

					break;

				} else {

					count++;

					f[i] = count;

					len += sentence[j].length();

					len++;

					j = j == sentence.length - 1 ? 0 : j + 1;

				}

			}

		}

		int wordCount = 0;

		int start = 0;

		for (int i = 0; i < rows; i++) {

			wordCount += f[start];

			start = wordCount % sentence.length;

		}

		return wordCount / sentence.length;

	}

	public int minDistance(String word1, String word2) {

		int m = word1.length();

		int n = word2.length();

		int[][] f = new int[m + 1][n + 1];

		for (int i = 0; i <= m; i++) {

			f[i][0] = i;

		}

		for (int i = 0; i <= n; i++) {

			f[0][i] = i;

		}

		for (int i = 1; i <= m; i++) {

			for (int j = 1; j <= n; j++) {

				if (word1.charAt(i) == word2.charAt(j)) {

					f[i][j] = f[i - 1][j - 1];

				} else {

					int a = f[i - 1][j]; // delete

					int b = f[i - 1][j - 1];

					int c = f[i][j - 1];

					int min = a < b ? a : b;

					min = min < c ? min : c;

					f[i][j] = min;

				}

			}

		}

		return f[m][n];

	}

	public int minPathSum(int[][] grid) {

		int[][] f = new int[grid.length][grid[0].length];

		f[0][0] = grid[0][0];

		for (int i = 0; i < grid.length; i++) {

			for (int j = 0; j < grid[i].length; j++) {

				if (i == 0 && j == 0) {

					f[0][0] = grid[0][0];

				} else if (i == 0 && j > 0) {

					f[i][j] = f[i][j - 1] + grid[i][j];

				} else if (j == 0 && i > 0) {

					f[i][j] = f[i - 1][j] + grid[i][j];

				} else {

					int a = f[i][j - 1];

					int b = f[i - 1][j];

					f[i][j] = a < b ? a : b;

					f[i][j] += grid[i][j];

				}

			}

		}

		return f[grid.length - 1][grid[0].length - 1];

	}

	public int rob(int[] nums) {

		if (nums.length == 0) {

			return 0;

		} else if (nums.length == 1) {

			return nums[0];

		} else if (nums.length == 2) {

			return nums[0] > nums[1] ? nums[0] : nums[1];

		}

		int max = 0;

		int[][] money = new int[nums.length][nums.length];

		for (int i = 0; i < nums.length; i++) {

			money[i][i] = nums[i];

			max = max < nums[i] ? nums[i] : max;

		}

		for (int i = 0; i < nums.length - 1; i++) {

			int j = (i == nums.length - 1) ? 0 : i + 1;

			for (int k = 0; k < nums.length - 2; k++) {

				int prev = j - 1 < 0 ? j + nums.length - 1 : j - 1;

				int prev2 = j - 2 < 0 ? j - 2 + nums.length : j - 2;

				if (i - j == 1 || j - i == 1) {

					money[i][j] = nums[i] > nums[j] ? nums[i] : nums[j];

				} else {

					int a = money[i][prev];

					int b = nums[j] + money[i][prev2];

					if (a > b) {

						money[i][j] = a;

					} else {

						money[i][j] = b;

					}

					max = max < money[i][j] ? money[i][j] : max;

				}

				j = (j == nums.length - 1) ? 0 : j + 1;

			}

		}

		return max;

	}

	public double[] calcEquation(String[][] equations, double[] values,
			String[][] queries) {

		Map<String, Set<String>> pmap = new HashMap<String, Set<String>>();

		Map<String, Double> vmap = new HashMap<String, Double>();

		for (int i = 0; i < values.length; i++) {

			String n = equations[i][0];

			String d = equations[i][1];

			double value = values[i];

			if (pmap.get(n) != null) {

				for (String a : pmap.get(n)) {

					Double v = vmap.get(n + "/" + a);

					if (value != 0.0d && v != null && v != 0.0d) {

						addEdge(pmap, d, a, vmap, v / value);

					}

				}

			}

			addEdge(pmap, n, d, vmap, value);

		}

		double[] ret = new double[queries.length];

		for (int i = 0; i < queries.length; i++) {

			String n = queries[i][0];

			String d = queries[i][1];

			Double value = vmap.get(n + "/" + d);

			if (value == null) {

				ret[i] = -1d;

			} else {

				ret[i] = value;

			}

		}

		return ret;

	}

	private void addEdge(Map<String, Set<String>> pmap, String n, String d,
			Map<String, Double> vmap, double value) {

		Set<String> set = pmap.get(n);

		if (set == null) {

			set = new HashSet<String>();

		}

		set.add(d);

		pmap.put(n, set);

		set = pmap.get(d);

		if (set == null) {

			set = new HashSet<String>();

		}

		set.add(n);

		pmap.put(d, set);

		vmap.put(n + "/" + d, value);

		vmap.put(d + "/" + d, 1.0d);

		if (value != 0.0d) {

			vmap.put(d + "/" + n, 1 / value);

			vmap.put(n + "/" + n, 1.0d);

		}

	}

	private TreeNode prev = null;

	private TreeNode successor = null;

	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {

		inorderVisit(root, p);

		return successor;

	}

	private void inorderVisit(TreeNode root, TreeNode p) {

		if (root == null) {

			return;

		}

		inorderVisit(root.left, p);

		if (prev != null && prev.val == p.val) {

			successor = root;

		}

		prev = root;

		inorderVisit(root.right, p);

	}

	public int[] findRedundantDirectedConnection(int[][] edges) {

		List<Integer>[] adjencies = new List[edges.length + 1];

		int[] in = new int[edges.length + 1];

		int[] ret = new int[2];

		for (int[] edge : edges) {

			in[edge[1]] = in[edge[1]] + 1;

			List<Integer> adjency = adjencies[edge[0]];

			if (adjency == null) {

				adjency = new ArrayList<Integer>();

				adjencies[edge[0]] = adjency;

			}

			adjency.add(edge[1]);

		}

		for (int i = edges.length - 1; i >= 0; i--) {

			int[] edge = edges[i];

			int root = 0;

			for (int j = 1; j <= edges.length; j++) {

				if ((in[j] == 0 && edge[1] != j)

				|| (in[j] == 1 && edge[1] == j)) {

					root = j;

					break;

				}

			}

			if (root != 0) {

				adjencies[edge[0]].remove(new Integer(edge[1]));

				if (travel(adjencies, root) == edges.length) {

					return edges[i];

				}

				adjencies[edge[0]].add(edge[1]);

			}

		}

		return ret;

	}

	private int travel(List<Integer>[] adjencies, int root) {

		boolean[] visited = new boolean[adjencies.length + 1];

		int n = 0;

		visited[root] = true;

		n++;

		if (adjencies[root] != null) {

			n += _travel(adjencies, adjencies[root], root, visited);

		}

		return n;

	}

	private int _travel(List<Integer>[] adjencies, List<Integer> list,
			int from, boolean[] visited) {

		int n = 0;

		for (Integer to : list) {

			if (!visited[to]) {

				visited[to] = true;

				n++;

				if (adjencies[to] != null) {

					n += _travel(adjencies, adjencies[to], to, visited);

				}

			}

		}

		return n;

	}

	public boolean canFinish(int numCourses, int[][] prerequisites) {

		List<Integer>[] vertexes = new List[numCourses];

		for (int[] prerequisit : prerequisites) {

			int from = prerequisit[0];

			int to = prerequisit[1];

			List<Integer> vertex = vertexes[from];

			if (vertex == null) {

				vertex = new ArrayList<Integer>();

				vertexes[from] = vertex;

			}

			vertex.add(to);

		}

		for (int i = 0; i < numCourses; i++) {

			boolean[] visited = new boolean[numCourses];

			visited[i] = true;

			if (vertexes[i] != null) {

				for (Integer to : vertexes[i]) {

					boolean yes = hasCircle(i, to, vertexes, visited);

					if (yes) {

						return false;

					}

				}

			}

		}

		return true;

	}

	private boolean hasCircle(int start, int from, List<Integer>[] vertexes,
			boolean[] visited) {

		if (start == from) {

			return true;

		}

		if (!visited[from]) {

			visited[from] = true;

			if (vertexes[from] != null) {

				for (Integer to : vertexes[from]) {

					boolean yes = hasCircle(start, to, vertexes, visited);

					if (yes) {

						return true;

					}

				}

			}

		}

		return false;

	}

	public boolean isValidBST(TreeNode root) {

		if (root == null) {

			return true;

		}

		int[] triple = getMinAndMax(root);

		return triple[0] == 1;

	}

	private int[] getMinAndMax(TreeNode root) {

		int[] triple = new int[] { 1, root.val, root.val };

		if (root.left == null && root.right == null) {

			return triple;

		}

		if (root.left != null) {

			int[] leftPair = getMinAndMax(root.left);

			if (leftPair[0] == 0 || root.val <= leftPair[2]) {

				return new int[] { 0, 0, 0 };

			}

			triple[1] = leftPair[1];

		}

		if (root.right != null) {

			int[] rightPair = getMinAndMax(root.right);

			if (rightPair[0] == 0 || root.val >= rightPair[1]) {

				return new int[] { 0, 0, 0 };

			}

			triple[2] = rightPair[2];

		}

		return triple;

	}

	public int closestValue(TreeNode root, double target) {

		int val = 0;

		TreeNode node = root;

		double minDiff = 0.0d;

		while (node != null) {

			double diff = (double) node.val - target;

			if (root == node) {

				minDiff = Math.abs(diff);

				val = node.val;

			} else {

				if (Math.abs(diff) < minDiff) {

					minDiff = Math.abs(diff);

					val = node.val;

				}

			}

			if (diff == 0.0d) {

				return node.val;

			} else if (diff < 0) {

				node = node.right;

			} else {

				node = node.left;

			}

		}

		return val;

	}

	public String minWindow(String s, String t) {

        Map<Character, Integer> map = new HashMap<Character, Integer>();
        Map<Character, Integer> baseMap = new HashMap<Character, Integer>();
        for(char c : t.toCharArray()) {
        	map.put(c, 0);
        	Integer n = baseMap.get(c);
        	if(n==null){
        		n = 0;
        	}
        	baseMap.put(c, n+1);
        }
        int tChars = map.size();
        int uniqueChars = 0;
        int left=0;
        int right=0;
        int[] range = null;
        char[] array = s.toCharArray();
        while(right< array.length) {
        	char c = array[right];
        	Integer count = map.get(c);
        	if(count != null) {
        		if(count == baseMap.get(c) - 1){
        			uniqueChars++;
        			map.put(c, baseMap.get(c));
        			if(uniqueChars == tChars){
        				//move left
        				char cl = 0;
        				while(left <= right){
            				cl = array[left];
            				Integer countLeft = map.get(cl);
        					if(countLeft == null){//unknown character, we don't care
        						left++;
        					}else{
        						if(countLeft > baseMap.get(cl)){
        							map.put(cl, countLeft-1);
        							left++;
        						}else{
        							break;
        						}
        					}
        				}
        				
        				if(range == null){
        					range = new int[2];
        					range[0] = left;
    						range[1] = right;
        				}else if(right - left < range[1] - range[0]){
        					range[0] = left;
    						range[1] = right;
        				}
        				map.put(cl, baseMap.get(cl) - 1);
        				left++;
        				uniqueChars --;
        				
        			}
        			
        		}else{
        			map.put(c, count+1);
        		}

        	}
        	right++;
        }
        if(range == null){
        	return "";
        }
        return s.substring(range[0], range[1] + 1);
    }

	public static void main(String[] args) {

		MySolution app = new MySolution();

		// System.out.println(app.kEmptySlots2(new int[]
		// {3,9,2,8,1,6,10,5,4,7},1));

		// System.out.println(app.nextClosestTime("23:59"));

		// TreeNode l = new TreeNode(1);

		// TreeNode r = new TreeNode(1);

		// TreeNode n1 = new TreeNode(4);

		// n1.left = l;

		// n1.left = r;

		// r = new TreeNode(5);

		// TreeNode n2 = new TreeNode(5);

		// n2.right = r;

		// TreeNode root = new TreeNode(5);

		// root.left = n1;

		// root.right = n2;

		// System.out.println(app.longestUnivaluePath(root));

		// System.out.println(app.licenseKeyFormatting("5F3Z-2e-9-w", 4));

		// System.out.println(app.plusOne(new int[] {4,3,2,1}));

		// System.out.println(app.trap(new int[] {5,5,1,7,1,1,5,2,7,6}));

		// System.out.println(app.lengthOfLongestSubstringKDistinct("aba", 1));

		// System.out.println(app.addBoldTag("aaabbcc", new String[]
		// {"aaa","aab","bc","aaabbcc"}));

		// app.gameOfLife(new int[][] {{0,1,0},{0,0,1},{1,1,1},{0,0,0}});

		// System.out.println(app.isOneEditDistance("1203", "1213"));

		// System.out.println(app.isPalindrome("race a car"));

		// System.out.println(app.numDecodings("12"));

		// System.out.println(app.wordBreak("catsandog", Arrays.asList("cats",
		// "dog", "sand", "and", "cat"))) ;

		// System.out.println(app.wordsTyping(new String[]
		// {"I","had","apple","pie"}, 4, 5));

		// System.out.println(app.rob(new int[] {2,2,4,3,2,5}));

		// System.out.println(app.calcEquation(new String[][] { {"a", "e"},
		// {"b", "e"} },

		// new double[] {4.0, 3.0}

		// ,new String[][] {{"a", "b"}, {"e", "e"}, {"x", "x"}}));

//		System.out.println(app.findRedundantDirectedConnection(new int[][] {
//				{ 2, 1 }, { 3, 1 }, { 4, 2 }, { 1, 4 } }));
		System.out.println(app.minWindow("babb", "baba"));

	}

}