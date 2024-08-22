package ru.naumov.ComputerClub.util.enrichClient;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class AddData {
    public static void main(String[] args) {
        addClients();
        addComputers();
        addTariffs();
    }

    public static void addClients(){
        final String url = "http://localhost:8080/api/clients/add";
        Map<String, Object> jsonData1 = new HashMap<>();
        jsonData1.put("clientName", "Никита");
        jsonData1.put("age", 20);
        jsonData1.put("isActive", true);
        makePostRequestWithJSONData(url, jsonData1);
        Map<String, Object> jsonData2 = new HashMap<>();
        jsonData2.put("clientName", "Валера");
        jsonData2.put("age", 33);
        jsonData2.put("isActive", false);
        makePostRequestWithJSONData(url, jsonData2);
        Map<String, Object> jsonData3 = new HashMap<>();
        jsonData3.put("clientName", "Евгений");
        jsonData3.put("age", 66);
        jsonData3.put("isActive", true);
        makePostRequestWithJSONData(url, jsonData3);
    }

    public static void addComputers(){
        final String url = "http://localhost:8080/api/computers/add";
        Map<String, Object> jsonData1 = new HashMap<>();
        jsonData1.put("number", "PC01");
        jsonData1.put("status", true);
        jsonData1.put("specification", "Intel core i3, 8GB RAM, 128GB SSD");
        makePostRequestWithJSONData(url, jsonData1);
        Map<String, Object> jsonData2 = new HashMap<>();
        jsonData2.put("number", "PC02");
        jsonData2.put("status", true);
        jsonData2.put("specification", "Intel core i5, 16GB RAM, 512GB SSD");
        makePostRequestWithJSONData(url, jsonData2);
        Map<String, Object> jsonData3 = new HashMap<>();
        jsonData3.put("number", "PC03");
        jsonData3.put("status", true);
        jsonData3.put("specification", "Intel core i9, 32GB RAM, 1TB SSD");
        makePostRequestWithJSONData(url, jsonData3);
    }

    public static void addTariffs(){
        final String url = "http://localhost:8080/api/tariffs/add";
        Map<String, Object> jsonData1 = new HashMap<>();
        jsonData1.put("tariffName", "Эконом");
        jsonData1.put("price", 50);
        jsonData1.put("description", "Без доп.опций, скажите спасибо, что не бьют");
        makePostRequestWithJSONData(url, jsonData1);
        Map<String, Object> jsonData2 = new HashMap<>();
        jsonData2.put("tariffName", "Стандарт");
        jsonData2.put("price", 100);
        jsonData2.put("description", "Энергетик в подарок");
        makePostRequestWithJSONData(url, jsonData2);
        Map<String, Object> jsonData3 = new HashMap<>();
        jsonData3.put("tariffName", "Премиум");
        jsonData3.put("price", 250);
        jsonData3.put("description", "Кошка девочка и миска риса в подарок");
        makePostRequestWithJSONData(url, jsonData3);
    }

    private static void makePostRequestWithJSONData(String url, Map<String, Object> jsonData) {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> request = new HttpEntity<>(jsonData, headers);
        try {
            restTemplate.postForObject(url, request, String.class);
            System.out.println("Данные успешно отправлены на сервер!");
        } catch (HttpClientErrorException e) {
            System.out.println("ОШИБКА!");
            System.out.println(e.getMessage());
        }
    }

}