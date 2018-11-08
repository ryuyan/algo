package algo;

public class BestBuyStockTimeIII {
	 public int maxProfit(int[] prices) {
	        if(prices == null || prices.length < 2){
	            return 0;
	        }
	        int[] maxProfit1 = new int[prices.length];
	        int maxProfit = 0;
	        int max = 0;
	        int buyPrice = prices[0];
	        maxProfit1[0]=0;
	        for(int i=1;i<prices.length;i++){
	            int price = prices[i];
	            if(price >= buyPrice){
	                int profit = price - buyPrice;
	                max = profit > max ? profit : max;
	            }else{
	                buyPrice = price;
	            }
	            maxProfit1[i]=max;
	        }
	        
	        int[] maxProfit2 = new int[prices.length];
	        
	        int sellPrice = prices[prices.length-1];
	        maxProfit2[prices.length-1] = 0;
	        max = 0;
	        for(int j=prices.length-2;j>=0;j--){
	            int price = prices[j];
	            if(price <= sellPrice){
	                int profit = sellPrice - price;
	                max = profit > max ? profit : max;
	            }else{
	                sellPrice = price;
	            }
	            maxProfit2[j]=max;
	        }
	        for(int i=0;i<prices.length;i++){
	            maxProfit = maxProfit1[i]+maxProfit2[i]>maxProfit?maxProfit1[i]+maxProfit2[i]:maxProfit;
	        }
	        return maxProfit;
	    }

	    public static void main(String[] args) {
	    	BestBuyStockTimeIII solution = new BestBuyStockTimeIII();
	    	solution.maxProfit(new int[]{1,2,4});

		}
}
