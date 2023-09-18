import Models.ResponseReqres;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Consumer {
    public static void main(String[] args) throws JsonProcessingException {
        postWithParamRequestWithoutParse();
    }

    private static void getRequest() {
        RestTemplate restTemplate = new RestTemplate();
        String getUrl = "https://reqres.in/api/users/2";
        String str = restTemplate.getForObject(getUrl, String.class);
        System.out.println(str);
    }

    private static void postRequest() {
        RestTemplate restTemplate = new RestTemplate();
        String postUrl = "https://reqres.in/api/users";
        Map<String, String> jsonToSend = new HashMap<>();
        jsonToSend.put("name", "TestName");
        jsonToSend.put("job", "TestJob");
        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonToSend);
        String str = restTemplate.postForObject(postUrl, request, String.class);
        System.out.println(str);
    }

    private static void postWithParamRequest() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String postUrl = "https://reqres.in/api/users";
        HttpEntity<Map<String, String>> request = httpEntityCreator();
        String str = restTemplate.postForObject(postUrl, request, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode obj = mapper.readTree(str);
        System.out.println("id = " + obj.get("id") +
                ", Имя = " + obj.get("name") +
                ", Должность = " + obj.get("job") +
                ", Дата регистрации = " + obj.get("createdAt"));
    }

    private static HttpEntity<Map<String, String>> httpEntityCreator(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ваше имя");
        String name = scanner.nextLine();
        System.out.println("Введите вашу должность");
        String job = scanner.nextLine();
        Map<String, String> jsonToSend = new HashMap<>();
        jsonToSend.put("name", name);
        jsonToSend.put("job", job);
        return new HttpEntity<>(jsonToSend);
    }

    private static void postWithParamRequestWithoutParse() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String postUrl = "https://reqres.in/api/users";
        HttpEntity<Map<String, String>> request = httpEntityCreator();
        ResponseReqres responseReqres = restTemplate.postForObject(postUrl, request, ResponseReqres.class);
        System.out.println("id = " + responseReqres.getId() +
                ", Имя = " + responseReqres.getName() +
                ", Должность = " + responseReqres.getJob() +
                ", Дата регистрации = " + responseReqres.getCreatedAt());
    }
}
