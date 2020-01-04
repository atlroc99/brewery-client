package com.breweryclient.web.client;

import com.breweryclient.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient client;

    @Test
    void getBeerById() {
        String uuid= "e5eadfac-af33-4fa6-96fe-4acc9befcfc6";
        BeerDto beerDto = client.getBeerById(uuid);

        assertNotNull(beerDto);
        assertEquals(beerDto.getId().toString(), "e5eadfac-af33-4fa6-96fe-4acc9befcfc6");
//        assertEquals(beerDto.getStyle(), "Red Bear");
        assertNull(beerDto.getUpc());
    }

    @Test
    void getBeerByRandomId() {
        BeerDto beerDto = client.getBeerByRandomId(UUID.randomUUID());
        assertNotNull(beerDto);

    }
}