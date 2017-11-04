package LeetCodeEasy;

//Problem 136: Given an array of integers, every element appears twice except for one. Find that single one.
//
//        Note:
//        Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

import java.util.HashMap;

public class SingleNumber136 {

    public static void main(String arggs[]){
        int[] nums = {0,0,1};
        System.out.println("The single occuring number using hash is: "+singleNumberWithExtraSpace(nums));
        System.out.println("The single occuring number using xor is: "+singleNumber(nums));
    }


    public static int singleNumber(int[] nums){
        int length = nums.length;

        int xor = 0;
        for(int i=0; i<length; i++){
            xor ^= nums[i];
        }
        return xor;
    }



    public static int singleNumberWithExtraSpace(int[] nums) {
        HashMap<Integer, Integer> countHash = new HashMap<>();
        int length = nums.length;
        int ans = 0;

        for(int i=0; i<length; i++){
            if (countHash.containsKey(nums[i])){
                countHash.remove(nums[i]);
            }
            else {
                countHash.put(nums[i],1);
            }
        }

        for (Integer num : countHash.keySet()) {
            ans =  num;
        }
        return ans;

    }
}
