package com.breweryclient.web.client;

import com.breweryclient.web.model.BeerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    private String apihost;
    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    private final String BEER_V1  = "api/v1/beer/";

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
        String url = apihost + BEER_V1 + "random-beer/" +uuid;
        System.out.println("api: " + url);
        return restTemplate.getForObject(url, BeerDto.class);
    }
}
