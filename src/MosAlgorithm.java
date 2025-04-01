public class MosAlgorithm {

    int[] blockArray;
    int[] inputArray;
    int sqrt;

    public MosAlgorithm(){
    }

    public void createBlockArray(int[] arr){
        inputArray = arr;
         sqrt = (int) Math.sqrt(arr.length);
        blockArray = new int[sqrt+1];
        int index=-1;
        for(int i =0;i<arr.length;i++){
            if(i%sqrt ==0){
                index++;
            }
            blockArray[index]= blockArray[index]+arr[i];
        }
    }

    public  int getRangeSum(int start ,int end){
        int sum =0;

        //calculating left block
        while (start%sqrt !=0){
            sum=sum+inputArray[start];
            start++;
        }

        while(end>= start+(sqrt-1)){
            sum=sum+blockArray[start/sqrt];
            start=start+sqrt;
        }

        while (start<=end){
            sum=sum+inputArray[start];
            start++;
        }


        return sum;
    }


    public void updateIndexValue(int index, int value){
       int originalValue =  inputArray[index];
       inputArray[index]= value;
       blockArray[index/sqrt]= (blockArray[index/sqrt]- originalValue)+value;
    }
}
