import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    private static final String URL = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";

    public static void main(String[] args) {
        Client client = new Client(URL, new ObjectMapper());

        try {
            List<Fact> facts = client.deserializable(client.getResponce().getEntity().getContent(), Fact.class);
            Stream.of(facts).flatMap(value -> value.stream()).filter(fact -> fact.getUpvotes() != null && fact.getUpvotes() > 0).forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
