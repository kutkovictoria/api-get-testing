package by.itacademy.handwrytten.api;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ApiTest {
    private HttpClient client;
    private HttpResponse response;
    private HttpGet request;
    final String BASE_URL = "https://api.handwrytten.com/v1/cards/list";
    @BeforeEach
    public void beforeTest() {
        client = new DefaultHttpClient();
    }
    @Test
    public void checkStatus200ok() throws Exception{
        request = new HttpGet(BASE_URL);
        response = client.execute(request);
        Assertions.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }
    @Test
    public void checkCardName() throws Exception{
        request = new HttpGet(BASE_URL);
        response = client.execute(request);
        String responseBody = EntityUtils.toString(response.getEntity());
        Assertions.assertTrue(responseBody.contains("{\"name\":\"Flat  5x7  Card\"}"));
    }
    @AfterEach
    public void afterMethod() throws IOException {
        EntityUtils.consume(response.getEntity());
    }
}

