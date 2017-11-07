package LeetCodeMedium;

//Problem 355
// Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:
//
//        postTweet(userId, tweetId): Compose a new tweet.
//        getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
//        follow(followerId, followeeId): Follower follows a followee.
//        unfollow(followerId, followeeId): Follower unfollows a followee.
//        Example:
//
//        Twitter twitter = new Twitter();
//
//// User 1 posts a new tweet (id = 5).
//        twitter.postTweet(1, 5);
//
//// User 1's news feed should return a list with 1 tweet id -> [5].
//        twitter.getNewsFeed(1);
//
//// User 1 follows user 2.
//        twitter.follow(1, 2);
//
//// User 2 posts a new tweet (id = 6).
//        twitter.postTweet(2, 6);
//
//// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
//// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
//        twitter.getNewsFeed(1);
//
//// User 1 unfollows user 2.
//        twitter.unfollow(1, 2);
//
//// User 1's news feed should return a list with 1 tweet id -> [5],
//// since user 1 is no longer following user 2.
//        twitter.getNewsFeed(1);

import java.util.*;

public class Twitter {

    private static int TimeStamp = 0;
    private Map<Integer, User> twittermap;

    Twitter() {

        twittermap = new HashMap<>();
    }

    private class User {
        public int id;
        public Set<Integer> following;
        public Tweet tweethead;


        User(int userid) {
            this.id = userid;
            following = new HashSet<>();
            follow(id);
            tweethead = null;
        }

        public void follow(int userid) {
            following.add(userid);

        }

        public void unfollow(int userid) {
            following.remove(userid);
        }

        public void post(int id) {

            Tweet tweet = new Tweet(id);
            tweet.next = tweethead;
            tweethead = tweet;

        }

    }

    private class Tweet {
        public int id;
        public int time;
        public Tweet next;

        public Tweet(int id) {
            this.id = id;
            time = TimeStamp++;
            next = null;
        }

    }

    public void postTweet(int userId, int tweetId) {
        if (!twittermap.containsKey(userId)) {
            User u = new User(userId);
            twittermap.put(userId, u);
        }
        twittermap.get(userId).post(tweetId);
    }

    public void unfollow(int followerId, int followeeId) {
        if(!twittermap.containsKey(followerId) || followerId==followeeId)
            return;
        twittermap.get(followerId).unfollow(followeeId);
    }

    public void follow(int followerId, int followeeId) {
        if(!twittermap.containsKey(followerId)){
            User u = new User(followerId);
            twittermap.put(followerId, u);
        }
        if(!twittermap.containsKey(followeeId)){
            User u = new User(followeeId);
            twittermap.put(followeeId, u);
        }
        twittermap.get(followerId).follow(followeeId);
    }

    // Best part of this.
    // first get all tweets lists from one user including itself and all people it followed.
    // Second add all heads into a max heap. Every time we poll a tweet with
    // largest time stamp from the heap, then we add its next tweet into the heap.
    // So after adding all heads we only need to add 9 tweets at most into this
    // heap before we get the 10 most recent tweet.
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new LinkedList<>();

        if(!twittermap.containsKey(userId))   return res;

        Set<Integer> users = twittermap.get(userId).following;
        PriorityQueue<Tweet> q = new PriorityQueue<Tweet>(users.size(), (a,b)->(b.time-a.time));
        for(int user: users){
            Tweet t = twittermap.get(user).tweethead;
            // very imporant! If we add null to the head we are screwed.
            if(t!=null){
                q.add(t);
            }
        }
        int n=0;
        while(!q.isEmpty() && n<10){
            Tweet t = q.poll();
            res.add(t.id);
            n++;
            if(t.next!=null)
                q.add(t.next);
        }

        return res;

    }

    public static void main(String[] args){
        Twitter obj = new Twitter();
        obj.postTweet(1,1);
        obj.follow(1,2);
        obj.postTweet(2,2);
        System.out.println("Latest tweets:"+obj.getNewsFeed(1));
        obj.unfollow(1,2);
        System.out.println("Latest tweets:"+obj.getNewsFeed(1));
    }
}