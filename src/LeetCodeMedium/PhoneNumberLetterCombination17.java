package LeetCodeMedium;

import java.util.LinkedList;
import java.util.List;

//Problem 17
// Given a digit string, return all possible letter combinations that the number could represent.
//
//        A mapping of digit to letters (just like on the telephone buttons) is given below.
//
//
//
//        Input:Digit string "23"
//        Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
//        Note:
//        Although the above answer is in lexicographical order, your answer could be in any order you want.
public class PhoneNumberLetterCombination17 {

    public static void main(String[] args){
        PhoneNumberLetterCombination17 phoneNumberLetterCombination17 = new PhoneNumberLetterCombination17();
        String digits = "23";
        System.out.println(phoneNumberLetterCombination17.letterCombinations(digits));
    }

    public List<String> letterCombinations(String digits){
        LinkedList<String> answer = new LinkedList<>();
        String[] words = new String[] {"0","1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "xyz"};
        answer.add("");
        for(int i=0; i<digits.length(); i++){
            int x = Character.getNumericValue(digits.charAt(i));
            while(answer.peek().length() == i){
                String t = answer.remove();
                for(char s: words[x].toCharArray()){
                    answer.add(t+s);
                }
            }
        }

        return answer;

    }
}
