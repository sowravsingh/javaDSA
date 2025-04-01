public class BinarySearch {

    public static int returnNearest(int [] arr, int target,String returnType){
        int start = 0;
        int end = arr.length-1;
        while(start <= end ){
            int mid = (start+end)/2;
            if(target == arr[mid]){
                return arr[mid];
            }
            else if(target >arr[mid]){
                start = mid+1;
            }
            else if(target <arr[mid]){
                end = mid-1;
            }

        }
        return 0;
    }


    public static int returnFirstLargest(int [] arr, int target,String returnType){
        int start = 0;
        int end = arr.length-1;
        while(start <= end ){
            int mid = (start+end)/2;
            if(target == arr[mid]){
                return arr[mid];
            }
            else if(target >arr[mid]){
                start = mid+1;
            }
            else if(target <arr[mid]){
                end = mid-1;
            }

        }

        if(returnType.equals("Float"))
            return arr[end];
        else
            return arr[start];


    }



    public static int  minimumIndex(int[] arr , int target){
        int peakIndex = peakIndexInMountainArray(arr);
        int finalIndex =binarySearch(0,peakIndex,arr,target);
        if(finalIndex ==-1)
            finalIndex= binarySearch(peakIndex+1,arr.length-1,arr,target);

        return finalIndex;

    }

    public static int  binarySearch(int start,int end , int[] arr,int target){
        boolean isAsc = arr[end] >arr[start];
        while(start<end){
            int mid = (start+end)/2;

            if(arr[mid]==target){
                return mid;
            }
            if(isAsc){
                if(arr[mid]>target){
                    end = mid-1;
                }else{
                    start =mid+1;
                }
            }
            else{
                if(arr[mid]>target){
                    start = mid+1;
                }else{
                    end =mid-11;
                }
            }

        }
        return -1;
    }


    public int search(int[] nums, int target) {
        int pivotedIndex = findPeakElement(nums);
        int targetIndex = binarySearch(0,pivotedIndex,nums,target);
        if(targetIndex==-1){
            targetIndex = binarySearch(pivotedIndex+1,nums.length-1,nums,target);
        }
        return targetIndex;
    }

    public static int findPeakElement(int[] nums) {
        int start =0;
        int end = nums.length-1;
        while (start <= end){
            int mid = start + (end - start) / 2;
            if(mid<end && nums[mid]>nums[mid+1]){
                return mid;
            }
            else if(start<end && nums[mid] <nums[mid-1]){
                return mid-1;
            } else if (nums[start] <=nums[mid]) {
                start=mid+1;
            }else {
                end = mid-1;
            }
        }
        return -1;

    }


    public static int peakIndexInMountainArray(int[] arr) {
        int start = 0;
        int end = arr.length-1;
        while(start < end){
            int  mid = (start+end)/2;
            if(arr[mid] <arr[mid+1]){
                start = mid+1;
            }
            else{
                end =mid;
            }
        }
        return  start;
    }


    public int[] searchRange(int[] nums, int target) {
        int[] arr ={-1,-1};
        arr[0] = returnFirstlastOccurence(nums,target,true);
        if(arr[0] !=-1){
            arr[1] = returnFirstlastOccurence(nums,target,false);
        }

        return arr;
    }

    public int returnFirstlastOccurence(int [] arr,int target,boolean firstIndex){

        // find the first occurance//
        int start =0;
        int end = arr.length-1;
        int temp =-1;
        while(start<= end){
            int mid = (start+end)/2;

            if(target == arr[mid]){
                temp = mid;
                if(firstIndex){
                    end= mid-1;
                }else{
                  start = mid+1;
                }
            }
            else if (target< arr[mid]){
                end = mid-1;
            }
            else if(target>arr[mid]){
                start = mid+1;
            }

        }
        return temp;
    }

    public static int[] findNumber(int[][] nums,int target){
        int row = 0;
        int column = nums.length-1;
        while(row< nums.length && column>=0){
            if(nums[row][column] ==target){
                return new int[]{row,column};
            }
            else if(target>nums[row][column]){
                row++;
            }else{
                column--;

            }
        }
        return new int[]{-1,-1};
    }


    public static int[] findInMatrix(int[][] arr, int target){
        int column = arr[0].length;
        int rowStart = 0;
        int rowEnd = arr.length-1;
        int columnMid = column/2;
        while(rowStart<(rowEnd-1)){
            int mid = (rowStart+rowEnd)/2;

            if(arr[mid][columnMid]==target){
                return new int[]{mid,columnMid};
            }
            else if(target <arr[mid][columnMid]){
                rowEnd=mid;
            }else{
                rowStart=mid;
            }
        }
        if( target==arr[rowStart][columnMid]){
            return new int[]{rowStart,columnMid};
        }
        else if(target == arr[rowStart+1][columnMid]){
            return new int[]{rowStart+1,columnMid};
        }


        if(target<=arr[rowStart][columnMid-1]){
           return binarySearch(rowStart,0,columnMid-1,target,arr);

        }else if (target>=arr[rowStart][columnMid+1] && target<=arr[rowStart][column-1]){
            return binarySearch(rowStart,columnMid+1,column-1,target,arr);
        }else if(target <=arr[rowStart+1][columnMid]){
            return binarySearch(rowStart+1,0,columnMid-1,target,arr);
        }
        else{
            return binarySearch(rowStart+1,columnMid+1,column-1,target,arr);
        }

    }



    public static int[]  binarySearch(int row,int columnStart , int columnEnd,int target,int[][] arr){
        while(columnStart<=columnEnd){
            int mid = (columnStart+columnEnd)/2;

            if(arr[row][mid]==target){
                return new int[]{row,mid};
            }
            if(arr[row][mid]>target){
                columnEnd = mid-1;
            }else{
                columnStart=mid+1;
            }

        }
        return new int[]{-1,-1};
    }

    public static void main(String args[]){
        int[][] arr ={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        //int result = returnNearest(arr,15,"ceil");
//      int result2= returnNearest(arr,15,"Float");
        int[] arr1={7,6,1,2,3,4};
        int result = findPeakElement(arr1);
        System.out.println(result);
//      int[] result2= findInMatrix(arr,25);
//        System.out.println(result2[0]+" "+result2[1]);
    }
}
