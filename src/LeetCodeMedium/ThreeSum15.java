package LeetCodeMedium;

//Problem 15: Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
//
//        Note: The solution set must not contain duplicate triplets.
//
//        For example, given array S = [-1, 0, 1, 2, -1, -4],
//
//        A solution set is:
//        [
//        [-1, 0, 1],
//        [-1, -1, 2]
//        ]
import com.sun.deploy.util.StringUtils;

import java.util.*;

public class ThreeSum15 {

    public static void main(String[] args){

        ThreeSum15 threeSum15 = new ThreeSum15();
        int[] array = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> answerset = threeSum15.threeSum(array);

        // Printing individual lists
//        Iterator<List<Integer>> iter = answerset.iterator();
//        while(iter.hasNext()){
//            System.out.print("[");
//            StringJoiner joiner = new StringJoiner(",");
//            Iterator<Integer> siter = iter.next().iterator();
//            while(siter.hasNext()){
//
//                joiner.add(siter.next().toString());
//            }
//            System.out.print(joiner);
//            System.out.println("]");
//        }

        System.out.print("The list of triplets amount to 0 is:"+answerset);



     }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resultset = new LinkedList<>() ;
        Arrays.sort(nums);
        int len = nums.length;

        for(int i=0; i< len-2; i++){
            if((i == 0) || (i>0 && nums[i] != nums[i-1])) {
                int low = i + 1;
                int high = len - 1;
                int sum = 0 - nums[i];
                while (low < high) {
                    if(sum == nums[low] + nums[high]){
                        resultset.add(Arrays.asList(nums[i],nums[low], nums[high]));
                        while(low < high && nums[low] == nums[low+1]) low++;
                        while(low < high && nums[high] == nums[high - 1]) high--;
                        low++;
                        high--;
                    } else if(nums[low] + nums[high] < sum) low++;
                    else high--;


                }

            }
        }

        return resultset;
    }
}
