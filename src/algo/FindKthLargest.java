package algo;

public class FindKthLargest {
	public int findKthLargest(int[] nums, int k) {
        if(nums == null){
            return -1;
        }
        
        int len = nums.length;
        
        for(int i=len /2;i>=0;i--){
            heapify(nums, len - 1, i);
        }
        
        for(int i=0;i<k-1;i++){
            nums[0] = nums[len-1-i];
            heapify(nums, len - 2 - i, 0);
        }
        return nums[0];
    }
    
    private void heapify(int[] nums, int end, int i){
        while(i<end){
            int t = i;
            int l = i*2+1;
            if(l <= end && nums[l] > nums[t]){
                t = l;
            }
            int r = (i+1)*2;
            if(r <= end && nums[r] > nums[t]){
                t = r;
            }
            
            if(t == i){
                return;
            }
            
            int tmp = nums[i];
            nums[i] = nums[t];
            nums[t] = tmp;
            
            i = t;
        }
    }
	public static void main(String[] args) {
		System.out.println(new FindKthLargest().findKthLargest(new int[]{-1,2,0}, 1));
	}

}
