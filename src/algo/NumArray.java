package algo;

public class NumArray {
	int[] sums = null;
    public NumArray(int[] nums) {
        if(nums != null){
            sums = new int[nums.length];
            int sum=0;
            for(int i=0;i<nums.length;i++){
                sum += nums[i];
                sums[i] = sum;
            }
        }
    }

    public int sumRange(int i, int j) {
        if(sums == null || i < 0 || j < 0 || i>j){
            return 0;
        }
        
        int high = sums[j];
        int low = (i == 0) ? 0 : sums[i-1];
        return high - low;
    }
	public static void main(String[] args) {
		NumArray numArray = new NumArray(new int[]{-2,0,3,-5,2,-1});
		System.out.println(numArray.sumRange( 2,5));
	}

}
