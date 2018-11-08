package algo;

public class SurroundedRegions {

	public static void main(String[] args) {
		char[][] board = {{'x','x','x'}, {'x','o','x'}, {'x','x','x'}};
		
        for(int i = 0; i < board.length; i++){
            for(int j=0; j < board[i].length; j++){
                if(board[i][j] == 'x'){
                    continue;
                }
                
                int k = 0;
                boolean allo = true;
                for(k = j -1 ; k >=0 ; k--){
                    if(board[i][k] == 'x'){
                        allo = false;
                        break;
                    }
                }
                if(allo){
                    continue;
                }
                
                allo = true;
                for(k = j + 1 ; k < board[i].length ; k++){
                    if(board[i][k] == 'x'){
                        allo = false;
                        break;
                    }
                }
                if(allo){
                    continue;
                }
                
                allo = true;
                for(k = i - 1 ; k >=0 ; k--){
                    if(board[k][j] == 'x'){
                        allo = false;
                        break;
                    }
                }
                if(allo){
                    continue;
                }
                
                allo = true;
                for(k = i + 1 ; k < board.length ; k++){
                    if(board[k][j] == 'x'){
                        allo = false;
                        break;
                    }
                }
                if(allo){
                    continue;
                }
                
                board[i][j] = 'x';
            }
        }
        System.out.println();
	}

}
