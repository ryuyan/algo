package algo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordLadder {

	 public int ladderLength(String start, String end, Set<String> dict) {
		 	List<String> dict2 = new ArrayList<String>(dict);
		 	dict2.remove(start);
		 	dict2.remove(end);
		 	return minLength(start, end, dict2);
	    }
	    
	    private int minLength(String start, String end, List<String> dict) {
	    	if(start.equals(end)){
	            return 1;
	        }
	        if(isDistanceOne(start, end)){
	            return 2;
	        }
	        int min = 0;
	        for(String s : dict){
	            if(isDistanceOne(start, s)){
	            	List<String> dict2 = new ArrayList<String>(dict);
	                dict2.remove(s);
	                int len = minLength(s, end, dict2);
	                if(len == 0){
	                    continue;
	                }
	                if(min == 0){
	                    min = len + 1;
	                }else{
	                    min = len + 1 < min ? len + 1 : min;
	                }
	            }
	        }
	        return min;
	    }
	    private boolean isDistanceOne(String w1, String w2){
	        int distance = 0;
	        for(int i=0;i<w1.length();i++){
	            char c1 = w1.charAt(i);
	            char c2 = w2.charAt(i);
	            if(c1 != c2){
	                distance++;
	            }
	            if(distance > 1){
	                return false;
	            }
	        }
	        return distance == 1;
	    }
	public static void main(String[] args) {
		WordLadder ladder = new WordLadder();
		String[] array = {"si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"};
		Set<String> dict = new HashSet<String>();
		for(String w : array){
			dict.add(w);
		}
		System.out.println(ladder.ladderLength("qa", "sq", dict));
	}

}
