package algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Twitter {
	Map<Integer, List<Integer>> tweetsByUser = new HashMap<Integer, List<Integer>>();
	Map<Integer, List<Integer>> followsByUser = new HashMap<Integer, List<Integer>>();
	Map<Integer, Integer> tweetSeq = new HashMap<Integer, Integer>();
	int seq = 0;
	public Twitter() {
        
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
    	List<Integer> tweets = tweetsByUser.get(userId);
    	if(tweets == null){
    		tweets = new ArrayList<Integer>();
    		tweetsByUser.put(userId, tweets);
    	}
    	tweetSeq.put(tweetId, seq++);
    	tweets.add(0, tweetId);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
    	List<List<Integer>> tweetsOfUsers = new ArrayList<List<Integer>>();
    	List<Integer> tweets = tweetsByUser.get(userId);
    	if(tweets != null){
    		tweetsOfUsers.add(tweets);
    	}
    	List<Integer> followees = followsByUser.get(userId);
    	if(followees != null){
	    	for(Integer followee : followees){
	    		tweets = tweetsByUser.get(followee);
	    		if(tweets != null){
	        		tweetsOfUsers.add(tweets);
	        	}
	    	}
    	}    	
    	int[] arr = new int[tweetsOfUsers.size()];
    	List<Integer> newsFeed = new ArrayList<Integer>();
    	while(newsFeed.size() < 10){
    		int latest = -1;
    		int userIdx = -1;
	    	for(int i=0;i<tweetsOfUsers.size();i++){
	    		tweets = tweetsOfUsers.get(i);
	    		int pos = arr[i];
	    		if(pos == tweets.size()){
	    			continue;
	    		}
	    		int tweetId = tweets.get(pos);
	    		if(latest == -1 || tweetSeq.get(tweetId) > tweetSeq.get(latest)){
	    			latest = tweetId;
	    			userIdx = i;
	    		}
	    	}
	    	
	    	if(latest == 0){
	    		break;
	    	}
	    	arr[userIdx] = arr[userIdx] + 1; 
	    	newsFeed.add(latest);
    	}
    	return newsFeed;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
    	if(followerId == followeeId){
    		return;
    	}
    	List<Integer> followees = followsByUser.get(followerId);
    	if(followees == null){
    		followees = new ArrayList<Integer>();
    		followsByUser.put(followerId, followees);
    	}
    	if(followees.contains(followeeId)){
    		return;
    	}
    	followees.add(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
    	List<Integer> followees = followsByUser.get(followerId);
    	if(followees == null){
    		return;
    	}
    	if(!followees.contains(followeeId)){
    		return;
    	}
    	followees.remove(new Integer(followeeId));
    }

    public static void main(String[] args){
    	Twitter twitter  = new Twitter();
    	twitter.postTweet(1,  5);
    	System.out.println(twitter.getNewsFeed(1));
    	twitter.follow(1,  2);
    	twitter.postTweet(2, 6);
    	System.out.println(twitter.getNewsFeed(1));
    }
}
