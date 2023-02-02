import java.io.File;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Path filePath = null;
        MarkdownSimpler File1 = null;
        // Get file path
        try {
            filePath = Path.of("input.txt");
        }
        catch (InvalidPathException e) {
            System.out.println("Invalid path to file.");
        }
        // Pass path to main class
        try {
            File1 = new MarkdownSimpler(filePath);
        }
        catch (NoSuchFileException e){
            System.out.println("No file with given path found.");
        }

        if (File1 != null){
            try (PrintWriter output = new PrintWriter("output.txt")) {
                output.print(File1.separate());
            } catch (Exception e) {
                System.out.println("Error while writing data to output file.");
            }
        }


    }
}