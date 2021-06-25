package com.webbfontaine.githubanalysisfe.components;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
@RequiredArgsConstructor
@Slf4j
public class RestCaller {

    private final RestTemplate restTemplate;

    private HttpHeaders getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        return headers;
    }

    public String makeRequest(String url, String json, HttpMethod method) throws Exception {
        RequestEntity<String> request = null;
        ResponseEntity<String> response = null;
        log.info("Making a {} request to {} with object {}", method, url, json);
        HttpHeaders header = getHeader();
        switch (method) {
            case GET:
                request = new RequestEntity<String>(null, header, method, new URI(url));
                response = restTemplate.exchange(request, String.class);
                break;
            case POST:
                request = new RequestEntity<String>(json, header, method, new URI(url));
                response = restTemplate.exchange(request, String.class);
                break;
            default:
                return null;
        }
        log.info("{} request to {} with object {} returns {}", method, url, json, response.getBody());
        return response.getBody();
    }
}


