package glblcd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {


    public static void main(String[] args) {
        String json = null;
        try {
            byte[] contents = Files.readAllBytes(Paths.get("src/main/resources/cake.json"));
            json = new String(contents);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println(json);
    }
}
