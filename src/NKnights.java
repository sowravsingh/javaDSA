public class NKnights {
    public static void main(String[] args) {

        int n =4;
        boolean[][] board = new boolean[n][n];
        findNKnightsPcement(board,0,0,n);
    }




    public static void findNKnightsPcement(boolean[][] board,int row,int col,int knights){
        if(knights==0){
            display(board);
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&7");
            return;
        }

        if(row ==board.length-1 && col==board.length){
            return;
        }

        if(col == board.length){
            findNKnightsPcement(board,row+1,0,knights);
            return;
        }


        if(isSafeCell(row,col,board)){
            board[row][col]=true;
            findNKnightsPcement(board,row,col+1,knights-1);
            board[row][col]=false;
        }

        findNKnightsPcement(board,row,col+1,knights);






    }

     public static boolean isSafeCell(int row,int column,boolean[][] board){

        if(isValid(board,row-2,column-1)){
            if(board[row-2][column-1]){
                return false;
            }
        }


         if(isValid(board,row-2,column+1)){
             if(board[row-2][column+1]){
                 return false;
             }
         }


         if(isValid(board,row-1,column-2)){
             if(board[row-1][column-2]){
                 return false;
             }
         }

         if(isValid(board,row-1,column+2)){
             if(board[row-1][column+2]){
                 return false;
             }
         }



        return true;
     }



     public static boolean isValid(boolean[][] board,int row,int col){
        if(row>=0 && row<board.length && col>=0 && col<board.length){
            return true;
        }
        return false;
     }
    public static void display(boolean[][] board){
        for(boolean[] row : board){
            for(boolean element :row){
                if(element){
                    System.out.print("N");
                }else{
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }
}


















