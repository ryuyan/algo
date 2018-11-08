package algo;

import java.util.HashMap;
import java.util.Map;

public class TelephoneNumber {

	static Map<Integer, char[]> numberToCharMap = new HashMap<Integer, char[]>();
	static{
		numberToCharMap.put(0, new char[]{'0'});
		numberToCharMap.put(1, new char[]{'1'});
		numberToCharMap.put(2, new char[]{'A','B','C'});
		numberToCharMap.put(3, new char[]{'D','E','F'});
		numberToCharMap.put(4, new char[]{'G','H','I'});
		numberToCharMap.put(5, new char[]{'J','K','L'});
		numberToCharMap.put(6, new char[]{'M','N','O'});
		numberToCharMap.put(7, new char[]{'P','Q','R','S'});
		numberToCharMap.put(8, new char[]{'T','U','V'});
		numberToCharMap.put(9, new char[]{'W','X','Y','Z'});
	}
	public void run(int[] numbers){
		char[] result = new char[numbers.length];
		for(int i = 0;i<numbers.length;i++){
			result[i] = numberToCharMap.get(numbers[i])[0];
		}
		while(true){
			System.out.println(new String(result));
			for(int i = 0;i<=numbers.length;i++){
				if(i == numbers.length){
					return;
				}
				int n = numbers[i];
				char[] chars = numberToCharMap.get(n);
				if(n == 1 || n == 0 || result[i] == chars[chars.length - 1]){
					continue;
				}else{
					int j = locate(result[i], chars);
					result[i] = chars[j+1];
					break;
				}
			}
		}
	}
	private int locate(char c, char[] chars) {
		for(int i = 0; i<chars.length; i++){
			if(chars[i] == c){
				return i; 
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		new TelephoneNumber().run(new int[]{4,0,8,2,1,9,9,9,5,9});
	}

}
