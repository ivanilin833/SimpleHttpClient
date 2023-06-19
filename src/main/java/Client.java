import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Client {
    private final String url;
    private final ObjectMapper mapper;

    public Client(String url, ObjectMapper mapper) {
        this.url = url;
        this.mapper = mapper;
    }

    public CloseableHttpResponse getResponce() throws IOException {
        HttpGet request = new HttpGet(url);
        CloseableHttpClient httpClient = getHttpClient();
        return httpClient.execute(request);
    }

    public <T> T deserializable(InputStream stream, Class<T> type) throws IOException {

        return mapper.readValue(stream, type);
    }

    private CloseableHttpClient getHttpClient() {
        return HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();
    }

}
