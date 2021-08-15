import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

class Main {
    public static void main(String[] args) throws IOException {
        // BufferedWriter를 이용한 Stdout
        writeStdout();

        // BufferedWriter를 이용한 writeFile
        writeFile();
    }

    public static void writeStdout() throws IOException {
        BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
        BW.write("BufferedWriter(Stdout) : Hello World\n");
        BW.close();
    }

    public static void writeFile() throws IOException {
        File file = new File("./log.txt");
        BufferedWriter BW = new BufferedWriter(new FileWriter(file));
        BW.write("BufferedWriter(File) : Hello World\n");
        BW.close();
    }
}