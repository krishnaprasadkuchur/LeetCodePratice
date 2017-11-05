package LeetCodeMedium;
//Problem 692:
// Given a non-empty list of words, return the k most frequent elements.
//
//        Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.
//
//        Example 1:
//        Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
//        Output: ["i", "love"]
//        Explanation: "i" and "love" are the two most frequent words.
//        Note that "i" comes before "love" due to a lower alphabetical order.
//        Example 2:
//        Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
//        Output: ["the", "is", "sunny", "day"]
//        Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
//        with the number of occurrence being 4, 3, 2 and 1 respectively.
//        Note:
//        You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
//        Input words contain only lowercase letters.
//        Follow up:
//        Try to solve it in O(n log k) time and O(n) extra space.

import java.util.*;

public class TokKFrequency692 {

    public static void main(String[] args){
        TokKFrequency692 tokKFrequency692 = new TokKFrequency692();
        String[] words = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        int k = 4;
        System.out.println(k+" frequently occuring words are: "+tokKFrequency692.topKFrequent(words,k));


    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> countmap = new HashMap<>();


        for(String word: words){
            countmap.put(word, countmap.getOrDefault(word, 0)+1);
        }

        List<String> resultSet = new ArrayList(countmap.keySet());

        Collections.sort(resultSet, (w1, w2) -> countmap.get(w1) == countmap.get(w2)?
                w1.compareTo(w2): countmap.get(w2) - countmap.get(w1) );
        return resultSet.subList(0, k);


    }
}

