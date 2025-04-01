package Exception;

public class Main {


    public static double divideNumber(int a,int b) throws CustomNumberFormatException {
        if(b==0 ){
            throw new CustomNumberFormatException(" B value should not be zero");
        }
        return a/b;

    }


    public static void main(String[] args) {
        System.out.println(callDivideMethod());
    }





    public static double callDivideMethod(){
        double output =0.0;
        try{
           output =  divideNumber(2,0);
        }catch (CustomNumberFormatException e){
            e.printStackTrace();
            return -1;
        }finally {
            System.out.println("this will be executed no matter of exception");
        }
        return output;
    }



}
