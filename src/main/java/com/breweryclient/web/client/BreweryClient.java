package com.breweryclient.web.client;

import com.breweryclient.web.model.BeerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    private String apihost;
    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    private final String BEER_V1 = "api/v1/beer/";

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }

    //    public BeerDto getBeerById(UUID uuid) {
    public BeerDto getBeerById(String uuid) {
        String url = apihost + BEER_V1 + uuid;
        System.out.println("api: " + url);
        return restTemplate.getForObject(url, BeerDto.class);
    }


    public BeerDto getBeerByRandomId(UUID uuid) {
        String url = apihost + BEER_V1 + "random-beer/" + uuid;
        System.out.println("api: " + url);
        return restTemplate.getForObject(url, BeerDto.class);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException e) {
        List<String> errors = new ArrayList<>(e.getConstraintViolations().size());
        e.getConstraintViolations().forEach(error -> {
            errors.add(error.getPropertyPath() + " " + error.getMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
