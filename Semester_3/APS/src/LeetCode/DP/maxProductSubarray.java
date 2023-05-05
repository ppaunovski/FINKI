package LeetCode.DP;

class Solution {
    public static void main(String[] args) {
        int nums[] = {50,2,4,20,200,30};
        System.out.println(maxProduct(nums));
    }
    public static int maxProduct(int[] nums) {
        int max = 0;
        int arr[] = new int[nums.length];

        for(int i=0; i<nums.length; i++){
            arr[i] = 1;
        }
        arr[0] = nums[0];
        for(int i=0; i<nums.length; i++){

            for(int j=i; j>=0; j--){

                if(nums[i] > nums[j]){
                    arr[i] = Math.max(arr[i], arr[j]*nums[i]);
                }
                if(arr[i] > max)
                    max = arr[i];

            }
        }

        return max;
    }
}

public class maxProductSubarray {
}
