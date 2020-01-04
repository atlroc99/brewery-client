package com.breweryclient.web.controller;

import com.breweryclient.web.client.BreweryClient;
import com.breweryclient.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/breweryClient")
public class BreweryClientController {

    @Autowired
    BreweryClient client;

    @GetMapping("")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Hello test this", HttpStatus.OK);

    }


    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID uuid) {
        System.out.println("Insdie beer-brewery client " + uuid.toString());
        return new ResponseEntity<>(client.getBeerById(uuid.randomUUID().toString()), HttpStatus.OK);
    }

    @GetMapping("/random-beer/{beerId}")
    public ResponseEntity<BeerDto> getBeerByRandomId(@PathVariable("beerId") UUID uuid) {
        return new ResponseEntity<>(client.getBeerByRandomId(uuid),HttpStatus.OK);
    }


}
