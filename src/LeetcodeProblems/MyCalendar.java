package LeetcodeProblems;

import java.util.Arrays;

public class MyCalendar {
    int[] arr ;

    public MyCalendar() {
        arr= new int[10000000];
    }
    public boolean book(int start, int end) {
        if (arr[start]==0 && arr[end-1]==0){
            Arrays.fill(arr,1,start,end-1);
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        MyCalendar mc = new MyCalendar();
        System.out.println(mc.book(1,4));
    }
}
