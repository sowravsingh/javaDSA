public class WordSearch {

    public static boolean exist(char[][] board, String word) {
        boolean[][] checkArray = new boolean[board.length][board[0].length];
        String s ="";
        for(int i =0;i<board.length;i++){
            for(int j =0;j<board[0].length;j++){
                boolean isFound=returnRecurr(board,word,s,checkArray,i,j);
                if(isFound){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean returnRecurr(char[][] board,String word,String s , boolean[][] checkArray,int r,int c){
        if(word.equalsIgnoreCase(s)){
            return true;
        }

        if(!checkIsValid(board,word,s,checkArray,r,c)){
            return false;
        }


        checkArray[r][c]=true;
        boolean top = returnRecurr(board,word,s+word.charAt(s.length()),checkArray,r-1,c);
        boolean down = returnRecurr(board,word,s+word.charAt(s.length()),checkArray,r+1,c);
        boolean left = returnRecurr(board,word,s+word.charAt(s.length()),checkArray,r,c+1);
        boolean right= returnRecurr(board,word,s+word.charAt(s.length()),checkArray,r,c-1);
        checkArray[r][c]=false;

        return (top|| down||right||left);
    }


    public static boolean checkIsValid(char[][] board,String word,String s , boolean[][] checkArray,int r,int c){
        if(r<0 || r==board.length || c<0 || c==board[0].length){
            return false;
        }

        // checking already it was visited in current iteration;
        if(checkArray[r][c]){
            return false;
        }

        // checking current cell vALUE equals to given string character
        if(board[r][c]!=word.charAt(s.length())){
            return false;
        }

        return true;

    }


    public static void main(String[] args) {

        char[][] board ={{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','F'}};
        System.out.println(exist(board,"SEE"));
    }
}
