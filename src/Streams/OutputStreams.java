package Streams;

import java.io.FileOutputStream;
import java.io.IOException;

public class OutputStreams {
    public static void main(String[] args) throws IOException {
        FileOutputStream fout = new FileOutputStream("/home/vassar/outputStream");
        String s ="writing output to o/p file";
        byte [] b = s.getBytes();
        fout.write(b);
        fout.close();

    }
}
