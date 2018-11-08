package algo;

public class MedianTwoSortedArray {
	 public double findMedianSortedArrays(int A[], int B[]) {
	        if((A== null || A.length == 0) && (B== null || B.length == 0)){
	            return 0.0d;
	        }else if(A== null || A.length == 0){
	            return findMedianArray(B);
	        }else if(B== null || B.length == 0){
	            return findMedianArray(A);
	        }
	        Integer f = findMedian(A, B);
	        if(f == null){
	            f = findMedian(B, A);
	            return calMedian(B, A, f);
	        }else{
	            return calMedian(A, B, f);
	        }
	    }
	    private double findMedianArray(int A[]){
	        boolean even = A.length %2 == 0;
	        if(even){
	            return ((double)(A[A.length/2] + A[A.length/2 -1]))/2;
	        }else{
	            return A[A.length/2];
	        }
	    }
	    private double calMedian(int A[], int B[], int m){
	        boolean even = (A.length + B.length) %2 == 0;
	        if(!even){
	            return A[m];
	        }
	        int mid = (A.length + B.length)/2 ;
	        int b = mid -m -1;
	        int next;
	        if(b < 0){
	            next = A[m-1];
	        }else if(m-1 < 0){
	            next = B[b];
	        }else{
	        	next = A[m-1] > B[b] ? A[m-1] : B[b];
	        }
	        return ((double)(A[m] + next))/2;
	    }
	    private Integer findMedian(int A[], int B[]){
	        int l=0;
	        int r=A.length-1;
	        int mid = (A.length + B.length)/2 ;
	        while(l<=r){
	            int m = (l+r)/2;
	            if(m > mid){
	                r = m;
	            }else{
	                int b = mid -m - 1;
	                if(b < 0){
	                	if(B[0] > A[m]){
	                		return m;
	                	}else{
	                		r=m-1;
	                	}
	                }else if(b >= B.length){
	                	        		l=m+1;
	                } else if(b+1 == B.length && B[b]<=A[m]){
	               		return m;
	                }else if(B[b]<=A[m] && B[b+1] >=A[m]){
	                    return m;
	                }
	                else if(B[b] > A[m]){
	                    l=m+1;
	                }else{
	                    r=m-1;
	                }
	            }
	            
	        }
	        return null;
	    }
	public static void main(String[] args) {
		System.out.println(new MedianTwoSortedArray().findMedianSortedArrays(new int[]{2,3,4}, new int[]{1,5,6,7,8}));
	}

}
