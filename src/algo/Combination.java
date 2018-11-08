package algo;

import java.util.ArrayList;
import java.util.List;

public class Combination {
	private List<String> result = new ArrayList<String>();
	public List<String> getResult() {
		return result;
	}
	public List<String> run(String s){
		combination(0, s);
		return result;
	}
	private List<String> combination(int idx, String s){
		List<String> list = new ArrayList<String>();
		if(idx == s.length() -1){
			list.add(s.substring(idx, s.length()));
		}else{
			String cur = s.substring(idx, idx + 1);
			List<String> sublist = combination(idx+1, s);
			list.add(cur);
			for(String ss : sublist){
				list.add(cur + ss);
			}
		}
		result.addAll(list);
		return list;
	}
	public static void main(String[] args) {
		for(String s : new Combination().run("wxyz")){
			System.out.println(s);
		}
	}

}
