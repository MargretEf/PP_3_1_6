package rest.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class Communication {


    @Autowired
    private RestTemplate restTemplate;
    private String cookie;
    private final String URL = "http://94.198.50.185:7081/api/users";


    public ResponseEntity<String> getAllUsers() {
        cookie = restTemplate.headForHeaders(URL).get("Set-Cookie").get(0);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Cookie", cookie);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

    }

    public ResponseEntity<String> saveUser() {
        User user = new User(3L, "James", "Brown", (byte) 25);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Cookie", cookie);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return restTemplate.postForEntity(URL, entity, String.class);
    }


    public ResponseEntity<String> updateUser() {
        User user = new User(3L, "Thomas", "Shelby", (byte) 25);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Cookie", cookie);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
    }

    public ResponseEntity<String> deleteUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Cookie", cookie);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(URL + "/3", HttpMethod.DELETE, entity, String.class);
    }
}
