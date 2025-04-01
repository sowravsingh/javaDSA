import java.util.Scanner;

public class TicTac {

    public static void startGame (){
        char[][] board = new char[3][3];
        for(int i =0;i<3;i++){
            for(int j =0;j<3;j++){
                board[i][j]=' ';
            }
        }

        Scanner sc = new Scanner(System.in);
        char player ='X';
        boolean gameOver = false;
        while(!gameOver){
            System.out.println(" Player "+player+ " Please enter the row : ");
            int row= sc.nextInt();
            System.out.println(" Player "+player+ " Please enter the column : ");
            int column = sc.nextInt();
            if(board[row][column] == ' '){
                board[row][column]=player;
                printBoard(board);
                if (isGameOver(board,player)){
                    System.out.println(" Game is Over winner is "+player);
                    gameOver=true;
                }
            }else{
                System.out.println("Wrong Move ");
            }

            player = player=='X'?'O':'X';
        }

        return;
    }

    public static boolean isGameOver(char[][] board , char player){

        // check horizontally
        for(int i=0;i<3;i++){
            if(board[i][0]==player && board[i][1] == player && board[i][2] == player){
                return true;
            }
        }

        // check vetically
        for(int i=0;i<3;i++){
            if(board[0][i]==player && board[1][i] == player && board[2][i] == player){
                return true;
            }
        }

        // check right diagnally
        if(board[0][0] == player && board[1][1] == player && board[2][2] == player){
            return true;
        }

        // check left diagnally
        if(board[0][2] == player && board[1][1] == player && board[2][0] == player){
            return true;
        }


        return false;

    }

    public static void printBoard(char[][] board){
        for(int i =0;i<board.length;i++){
            for(int j =0;j<board[0].length;j++){
                System.out.print("| "+board[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        startGame();
    }
}
