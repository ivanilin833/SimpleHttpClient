import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    private static final String TOKEN = "vnwz5iPoeVtRaNOGmg50MzUlzojghYd8FJl1wGSx";
    private static final String URL = "https://api.nasa.gov/planetary/apod?api_key=" + TOKEN;

    public static void main(String[] args) {
        Client client = new Client(URL, new ObjectMapper());
        RequestFromNasa request;
        try {
            request = client.deserializable(client.getResponce().getEntity().getContent(), RequestFromNasa.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String hdUrl = request.getHdurl();
        String fileName = hdUrl.split("/")[hdUrl.split("/").length - 1];
        Client client2 = new Client(hdUrl, new ObjectMapper());

        try (FileOutputStream fos = new FileOutputStream(new File("./" + fileName))) {
            fos.write(client2.getResponce().getEntity().getContent().readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
