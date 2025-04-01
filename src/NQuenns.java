import com.sun.xml.internal.ws.addressing.WsaActionUtil;

import java.util.ArrayList;
import java.util.List;

public class NQuenns {



    public static List<List<String>> returnNQueens(boolean[][] board,int r){

        if(r==board.length){
            display(board);
            System.out.println("***********");
            List<List<String>> rs = returnList(board);
            return rs;
        }

        List<List<String>> rs = new ArrayList<>();
        for(int i =0;i<board.length;i++){
            if(isValidcell(r,i,board)){
                board[r][i]=true;
                rs.addAll(returnNQueens(board,r+1));
                board[r][i]=false;
            }
        }

        return rs;
    }

    public static void display(boolean[][] board){
           for(boolean[] row :board){
               for (boolean element :row){
                   if(element){
                       System.out.print("Q");
                   }else{
                       System.out.print("*");
                   }
               }
               System.out.println();
           }
    }


    public static List<List<String>> returnList(boolean[][] board){
        List<List<String>> rs = new ArrayList<>();
        List<String> path = new ArrayList<>();

        for(boolean[] row : board){
            StringBuilder str =new StringBuilder();
            for(boolean element : row){
                if(element){
                    str.append("Q");
                }
                else{
                    str.append(".");
                }
            }
            path.add(String.valueOf(str));
        }
        rs.add(path);
        return rs;
    }
    public static boolean isValidcell(int r , int c ,boolean[][] board){

//        System.out.println(r);
//        System.out.println(c);
        //checking bottom
        for(int i =0;i<r;i++){
            if(board[i][c]){
                return false;
            }
        }


        //checking leftDiagnol
        int maxLeft = Math.min(r,c);
        for(int i =0;i<=maxLeft;i++){
            if(board[r-i][c-i]){
                return false;
            }
        }


//        System.out.println(r);
//        System.out.println(c);
        //checking right diagnol
        int maxRight = Math.min(r,board.length-c-1);
        //System.out.println(maxRight);
        for(int i =0;i<=maxRight;i++){
            if(board[r-i][c+i]){
                return false;
            }
        }


        return true;
    }
    public static void main(String[] args) {

        boolean[][] board= new boolean[4][4];
        System.out.println(returnNQueens(board,0));
    }
}
