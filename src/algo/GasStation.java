package algo;

public class GasStation {
	public int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas == null || cost == null){
            return -1;
        }
       
        for(int i = 0;i<gas.length; i++){
            int j = i;
            int remaining = 0;
            while(true){
                int gasAvailable = remaining + gas[j];
                if(gasAvailable < cost[j]){
                    break;
                }
                remaining = gasAvailable - cost[j];
                j = (++j)%gas.length;
                if(j==i){
                    break;
                }
            }
            if(i == j){
                return i;
            }
        }
        return -1;
    }
	public static void main(String[] args) {
		GasStation gas = new GasStation();

	}

}
