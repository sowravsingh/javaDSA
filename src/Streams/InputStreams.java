package Streams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InputStreams {
    public static void main(String[] args) throws IOException {
        FileInputStream fin = new FileInputStream("/home/vassar/inpitStream");
        int s = fin.read();
        while(s!=-1){
            System.out.print((char) s);
            s= fin.read();
        }

    }
}
