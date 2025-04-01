package LeetcodeProblems;

import java.util.*;
import java.util.stream.Collectors;

public class LeetCodeProblems3 {
    public static void main(String[] args) {

        LeetCodeProblems3 ll = new LeetCodeProblems3();
       // int[][] board ={{5,1},{4,5},{11,9},{9,4}};
        int[][] board ={{1,3},{1,2},{3,1}};
        int[] arr ={-2,0,10,-19,4,6,-8};
        char c='a';
       char b =(char) (c+1);
        System.out.println(b);
      //  System.out.println(b);

//        List<String> names = Arrays.asList("sowrav","teja","rama","jai","sai");
//        Map<Integer, Long> collect = names.stream().collect(Collectors.groupingBy(String::length, Collectors.counting()));
//        System.out.println(collect);
         // System.out.println((ll.checkIfExist(arr)));
        System.out.println( ll.canMakeSubsequence("zc","ad"));

    }

    public boolean canMakeSubsequence(String str1, String str2) {
        int firstIndex =0;
        int secondIndex =0;
        while (firstIndex<str1.length()){

            if (str2.charAt(secondIndex)== str1.charAt(firstIndex) || str2.charAt(secondIndex)== str1.charAt(firstIndex)+1 || (str2.charAt(secondIndex) =='a' && str1.charAt(firstIndex)=='z' )){
                System.out.println(firstIndex);
                secondIndex++;
            }
            if (secondIndex== str2.length()){
                return true;
            }
            firstIndex++;
        }

        return false;
    }

    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num :arr){
            set.add(num);
        }

        for (int num :set){
            set.remove(num);
            if (set.contains(num*2) || (num%2 ==0 && set.contains(num/2))){
                System.out.println(num);
                return true;
            }
        }

        return false;
    }
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] s = sentence.split(" ");
        int index=-1;
        for (int i =0;i<s.length;i++){

            System.out.println(s[i]);
            if (s[i].startsWith(searchWord)){
                return i+1;
            }
        }
        return index;
    }

    List<Integer> resultList = new ArrayList<>();
    public int[][] validArrangement(int[][] pairs) {
        Map<Integer,Queue<Integer>> nodeVSNeighbours = new HashMap<>();
        Map<Integer,Integer> nodeVSInCount = new HashMap<>();
        Map<Integer,Integer> nodeVsOutCount = new HashMap<>();

        for (int[] pair :pairs){
            int start = pair[0];
            int end   = pair[1];
            nodeVSNeighbours.computeIfAbsent(start, k->new LinkedList<>()).offer(end);
            nodeVsOutCount.put(start,nodeVsOutCount.getOrDefault(start,0)+1);
            nodeVSInCount.put(end,nodeVSInCount.getOrDefault(end,0)+1);
        }

        int startNode =-1;
        for (int node :nodeVsOutCount.keySet()){
            if (nodeVSInCount.getOrDefault(node,0)+1 ==nodeVsOutCount.getOrDefault(node,0)){
                startNode= node;
                break;
            }
        }

        if (startNode ==-1){
            startNode = pairs[0][0];
        }


        dfs(startNode,nodeVSNeighbours);


        Collections.reverse(resultList);
        System.out.println(resultList);

        int[][] resultArr = new  int[pairs.length][2];
        int index =0;
        for(int i =1;i<resultList.size();i++){
            int[]arr = new int[2];
            arr[0]= resultList.get(i-1);
            arr[1]= resultList.get(i);
            resultArr[index]= arr;
            index++;
        }

        return resultArr;

    }

    public void dfs(int u , Map<Integer,Queue<Integer>> nodeVSNeighbours){
        Queue<Integer> queue = nodeVSNeighbours.get(u);
        while (queue !=null && !queue.isEmpty()){
            int v = queue.poll();
            dfs(v,nodeVSNeighbours);
        }
        resultList.add(u);
    }

    int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
    public int minimumTime(int[][] grid) {
        if (grid[0][1] >1 || grid[1][0]>1){
            return -1;
        }

        int[][] dp =new int[grid.length][grid[0].length];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) ->{
            return a[2]-b[2];
        });

        for(int[] row:dp){
            Arrays.fill(row,Integer.MAX_VALUE);
        }



        pq.offer(new int[]{0,0,0});

        while (!pq.isEmpty()){
            int[] cell = pq.poll();
            int row = cell[0];
            int col= cell[1];
            int dist = cell[2];
            if (row== grid.length-1 && col== grid[0].length-1){
                return dist;
            }

            for (int[] dir : directions){

                int r = row+dir[0];
                int c= col+dir[1];


                if (isValid(r,c,grid)){
                    int f = (grid[r][c]-dist)%2==0? 1 : 0;
                    int t = Math.max(dist+1, grid[r][c]+f);
                  //  System.out.println(t);

                    if (t< dp[r][c]){

                        pq.offer(new int[]{r,c,t});
                        dp[r][c]= t;
                    }

                }
            }




        }


        return dp[grid.length-1][grid[0].length-1];

    }



    public int minimumObstacles(int[][] grid) {

        int[][] dp =new int[grid.length][grid[0].length];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) ->{
            return a[2]-b[2];
        });

        PriorityQueue<Integer> pq2 = new PriorityQueue<>(( a,  b)->{
            return a-b;
        });

        for(int[] row:dp){
            Arrays.fill(row,Integer.MAX_VALUE);
        }



        pq.offer(new int[]{0,0,0});

        while (!pq.isEmpty()){
            int[] cell = pq.poll();
            int row = cell[0];
            int col= cell[1];
            int dist = cell[2];

            for (int[] dir : directions){

                int r = row+dir[0];
                int c= col+dir[1];

                if (r== grid.length-1 && c== grid[0].length){
                    return dist;
                }
                if (isValid(r,c,grid)){
                    int newDist = dist+grid[r][c];
                    if (newDist< dp[r][c]){
                        pq.offer(new int[]{r,c,newDist});
                        dp[r][c]= newDist;
                    }

                }
            }




        }


        return dp[grid.length-1][grid[0].length-1];
    }


    public boolean isValid(int row,int column, int[][] grid){
        return (row>=0 && row<grid.length && column>=0 && column<grid[0].length);
    }



    List<List<Integer>> indexVSedges = new ArrayList<>();
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        for (int i =1;i<=n;i++){
            List<Integer> list = new ArrayList<>();
            list.add(i);
            indexVSedges.add(list);
        }
        System.out.println(indexVSedges);

        int[] resultArr = new int[queries.length];
        int index =0;
        for (int[] query : queries){
            List<Integer> edges = indexVSedges.get(query[0]);
            edges.add(query[1]);
            indexVSedges.set(query[0],edges);
            resultArr[index] = getShortestPath(n);
            System.out.println(resultArr[index]);
            index++;
        }
        return resultArr;
    }


    public int getShortestPath(int n){
       // System.out.println(indexVSedges);
       Queue<Integer> queue = new LinkedList<>();

       int levels =0;
       queue.add(0);
       boolean[]dp= new boolean[n];
       while (!queue.isEmpty()){
           int size = queue.size();
           for (int i =0;i<size;i++){
               int num = queue.poll();
               dp[num]= true;
               if (num == n-1){
                   return levels;
               }

               List<Integer> edges = indexVSedges.get(num);
               for (int edge :edges){
                   if (!dp[edge]){
                       queue.offer(edge);
                   }
               }
           }

           levels++;
       }

       return levels;
    }


    int minSteps= Integer.MAX_VALUE;
    Map<String,Integer> patternVsSteps = new HashMap<>();
    public int slidingPuzzle(int[][] board) {
        String idealString ="123450";
        Map<Integer, List<Integer>> indexVsPossibleSwapIndex = new HashMap<>();
        indexVsPossibleSwapIndex.put(0, Arrays.asList(1,3));
        indexVsPossibleSwapIndex.put(1, Arrays.asList(0,2,4));
        indexVsPossibleSwapIndex.put(2, Arrays.asList(1,5));
        indexVsPossibleSwapIndex.put(3, Arrays.asList(0,4));
        indexVsPossibleSwapIndex.put(4, Arrays.asList(1,3,5));
        indexVsPossibleSwapIndex.put(5, Arrays.asList(2,4));

        StringBuilder sb =new StringBuilder();
        for (int[] row :board ){
            for (int num :row){
                sb.append(num);
            }
        }

        slidingPuzzleRecur(indexVsPossibleSwapIndex,idealString,sb.toString(),0);

        if (minSteps == Integer.MAX_VALUE){
            return -1;
        }else {
            return minSteps;
        }

    }


    public void slidingPuzzleRecur(Map<Integer, List<Integer>> indexVsPossibleSwapIndex, String idealString, String str, int steps){

        if (str.equals(idealString)){
            minSteps = Math.min(minSteps,steps);
            return;
        }
        if (patternVsSteps.get(str)!=null && patternVsSteps.get(str)<steps){
            return;
        }

        patternVsSteps.put(str,steps);
        //visitedPatterns.add(str);

        // find index of zero

        int index =-1;
        for (char ch :str.toCharArray()){
            if (ch =='0'){
                index = str.indexOf('0');
            }
        }

       List<Integer> indexList =  indexVsPossibleSwapIndex.get(index);
        for (int possibleIndex : indexList){
            String newString =swapString(str,index,possibleIndex);
//            if (newString.equals("412503")){
//                System.out.println(steps);
//            }
            slidingPuzzleRecur(indexVsPossibleSwapIndex,idealString,newString,steps+1);
        }




    }


    public String swapString(String originalStr, int currentIndex, int swapIndex){
        StringBuilder sb = new StringBuilder(originalStr);

        char c = sb.charAt(swapIndex);
        sb.setCharAt(swapIndex,'0');
        sb.setCharAt(currentIndex,c);

        return sb.toString();

    }


}
