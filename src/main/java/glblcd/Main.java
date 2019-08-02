package glblcd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {


    public static void main(String[] args) {
        String location = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22"; //args[0];

        var client = HttpClient.newBuilder().build();
        URI uri = null;
        try {
            uri = new URI(location);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.exit(1);
        }
        var req = HttpRequest.newBuilder(uri).build();

        try {
            var response = client.send(req,
                    HttpResponse.BodyHandlers.ofString(Charset.defaultCharset()));
            System.out.println(parseJsonToMap(response.body()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> parseJsonToMap(String json) {
        var mapper = new ObjectMapper();
        var typeRef = new TypeReference<HashMap<String,Object>>() {};

        try {
            Map<String, Object> mJson = mapper.readValue(json, typeRef);
//            System.out.println(mJson);
            return mJson;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void run() {
        String json = null;
        try {
            var contents = Files.readAllBytes(Paths.get("src/main/resources/cake.json"));
            json = new String(contents);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println(parseJsonToMap(json));
    }

}
