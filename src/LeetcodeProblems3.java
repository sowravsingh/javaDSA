import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetcodeProblems3 {


    public static void main(String[] args) {
        int[][] grid ={
                {1,2,3},
                {1,5,1},
                {3,1,1}
        };
//        System.out.println(minDays(grid));
//        int[] candidates = {1,2,2,1,1};
        System.out.println(maxPoints(grid));
    }

    public int nthUglyNumber(int n) {
        int uglyNumber=1;
        if( n==1){
            return uglyNumber;
        }

        for(int i =2;i<=1690;i++){
            if(i%2 ==0 || i%3==0 || i%5 ==0){
                uglyNumber++;
            }
            if(uglyNumber==n){
                return uglyNumber;
            }
        }
        return uglyNumber;
    }



    int maxDistance =0;
    public int maxDistance(List<List<Integer>> arrays) {

        List<Integer> firstList=arrays.get(0);
        int prevMin = firstList.get(0);
        int prevMax = firstList.get(firstList.size()-1);
        int result =0;
        for(int i =1;i<arrays.size();i++) {
            List<Integer> list = arrays.get(i);
            int minValue = list.get(0);
            int maxValue= list.get(list.size()-1);

            result=Math.max(result,Math.max(Math.abs(prevMin-maxValue),Math.abs(prevMax-minValue)));
            prevMin=Math.min(prevMin,minValue);
            prevMax =Math.max(prevMax,maxValue);

        }
        return Math.abs(prevMin - prevMax);

    }


    public static long maxPoints(int[][] points) {
        Long[][] dp = new Long[points.length][points[0].length];

        int[] firstRow = points[0];
        for(int i =0;i<firstRow.length;i++){
            dp[0][i]= (long) firstRow[i];
        }
        for(int i =1;i<points.length;i++){
            int[] row = points[i];
            for(int j=0;j<row.length;j++){
                dp[i][j]=getMaxSum(row[j],dp[i-1],j);
            }
        }

        System.out.println(Arrays.toString(dp));
        Long[] lastRow = dp[dp.length-1];
        Long maxprofit =0L;
        for(int i =0;i<lastRow.length;i++){
            maxprofit= Math.max(maxprofit,lastRow[i]);
        }
        return maxprofit;
    }

    public static Long getMaxSum(int value, Long[] dp, int col){
        Long max=0L;

        for(int i = 0;i<dp.length;i++){
            Long sum = Math.abs((value+dp[i])-(Math.abs(col-i)));
            max= Math.max(max,sum);
        }

        return max;
    }


    public boolean lemonadeChange(int[] bills) {
        int balance =0;
        for(int num :bills){
            if(num==5){
                balance+=5;
            }else{
                int change =num-5;
                if(balance>=change){
                    balance-=change;
                }else{
                    return false;
                }
            }
        }
        return true;
    }



    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> resultList = new ArrayList<>();
        return combinationRecursion(candidates,target,0,new ArrayList<>(),resultList);
    }

    public static List<List<Integer>> combinationRecursion(int[] candidates,int target, int start, List<Integer> list,List<List<Integer>> resultList){
        if(target==0){
            resultList.add(new ArrayList<>(list));
        }
        if(target<0){
            return resultList;
        }

        int previousNumber =0;
        for(int i =start;i<candidates.length;i++){
            if(previousNumber == candidates[i]){
                continue;
            }else {
                previousNumber = candidates[i];
                list.add(candidates[i]);
                combinationRecursion(candidates,target-candidates[i],i+1,list,resultList);
                list.remove(list.size()-1);
            }
        }

        return resultList;

    }

    public static int minDays(int[][] grid) {

        int totalIslands = countIslands(grid);
        if(totalIslands ==0 || totalIslands>1){
            return 0;
        }

        for(int i =0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    grid[i][j]=0;
                    int islands =countIslands(grid);
                    if(islands==0 || islands>1){
                        return 1;
                    }
                    grid[i][j]=1;
                }
            }
        }
        return 2;
    }

    public static int countIslands(int[][] grid){
        int islands =0;
        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];

        for(int i =0;i<rows;i++){
            for(int j =0;j<cols;j++){
                if(grid[i][j] ==1 && !visited[i][j]){
                    islands++;
                    visited= DFS(i,j,grid,visited,rows,cols);
                }
            }
        }

        return islands;
    }


    public static boolean[][] DFS(int row,int col,int[][] grid ,boolean[][] visited,int totalRows,int totalColumns){
        if(row>=totalRows || col>=totalColumns || row<0 || col<0 || visited[row][col] || grid[row][col] ==0){
            return visited;
        }

        visited[row][col]=true;
        visited=DFS(row,col+1,grid,visited,totalRows,totalColumns);
        visited=DFS(row,col-1,grid,visited,totalRows,totalColumns);
        visited=DFS(row+1,col,grid,visited,totalRows,totalColumns);
        visited=DFS(row-1,col,grid,visited,totalRows,totalColumns);

        return visited;
    }





    public static int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {

        int totalElements = rows*cols;
        int[][] resultArray = new int[totalElements][2];
        int count =0;
        int increment =1;
        String[] directions = {"R","D","L","U"};
        // checking to add the given element or not
        if(rStart<rows && cStart <cols){
            resultArray[count][0]=rStart;
            resultArray[count][1]=cStart;
            count++;
        }

        while (count<totalElements){
            for(int i = 0;i<4;i++){
                for(int j =0;j<increment;j++){
                    if(directions[i].equalsIgnoreCase("R")){
                        cStart++;
                        if(rStart<rows && cStart<cols && rStart>=0 && cStart>=0){
                            System.out.println("enetering data of "+rStart+" col "+cStart+"for operation "+directions[i]+" with increment "+increment);
                            resultArray[count][0]=rStart;
                            resultArray[count][1]=cStart;
                            count++;
                        }
                    }
                    if(directions[i].equalsIgnoreCase("D")){
                        rStart++;
                        if(rStart<rows && cStart<cols && rStart>=0 && cStart>=0){
                            System.out.println("enetering data of "+rStart+" col "+cStart+"for operation "+directions[i]+" with increment "+increment);
                            resultArray[count][0]=rStart;
                            resultArray[count][1]=cStart;
                            count++;
                        }
                    }
                    if(directions[i].equalsIgnoreCase("L")){
                        cStart--;
                        if(rStart<rows && cStart<cols && rStart>=0 && cStart>=0){
                            System.out.println("enetering data of "+rStart+" col "+cStart+"for operation "+directions[i]+" with increment "+increment);
                            resultArray[count][0]=rStart;
                            resultArray[count][1]=cStart;
                            count++;
                        }
                    }
                    if(directions[i].equalsIgnoreCase("U")){
                        rStart--;
                        if(rStart<rows && cStart<cols && rStart>=0 && cStart>=0){
                            System.out.println("enetering data of "+rStart+" col "+cStart+"for operation "+directions[i]+" with increment "+increment);
                            resultArray[count][0]=rStart;
                            resultArray[count][1]=cStart;
                            count++;
                        }
                    }
                }




                if(i ==1 || i==3){
                    increment++;
                }

            }
        }

        return resultArray;
    }



}
