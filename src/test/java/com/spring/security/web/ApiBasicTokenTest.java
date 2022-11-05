package com.spring.security.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.security.web.domain.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import java.util.Base64;
import java.util.List;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiBasicTokenTest {

    @LocalServerPort
    int port;

    RestTemplate restTemplate = new RestTemplate();

    @DisplayName("1. 학생 조사")
    @Test
    void test_1() throws JsonProcessingException {
        String url = format("http://localhost:%d/api/teacher/students", port);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(
                "lee:1".getBytes()
        ));
        HttpEntity<String> entity = new HttpEntity<>("", httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        List<Student> list = new ObjectMapper().readValue(response.getBody(),
                new TypeReference<List<Student>>() {
        });

        assertThat(list.size()).isEqualTo(3);
    }

    @Test
    void test() {
        String url = format("http://localhost:%d/api/teacher/students", port);
        System.out.println("url = " + url);
    }
}
