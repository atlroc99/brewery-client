package com.breweryclient.web.client;

import com.breweryclient.web.model.BeerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient2 {

    private String BEER_PATH_V1 = "api/v1/beer";
    private String apiHost;
    private RestTemplate restTemplate;

    public BreweryClient2(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public BeerDto getBeerById(UUID uuid) {
      return restTemplate.getForObject(apiHost + BEER_PATH_V1 + "/" + uuid.toString(), BeerDto.class);
    }

    public BeerDto getRandomBeer(UUID uuid) {
        System.out.println("Inside get random-beer");
        String fullpath = apiHost + BEER_PATH_V1 + "/random-beer/" + uuid.toString();
        System.out.println("Get Random Beer: " + fullpath);
        return restTemplate.getForObject(fullpath, BeerDto.class);
    }

}
