package com.reactive.crud_card_webflux.controller;


import com.reactive.crud_card_webflux.entity.Card;
import com.reactive.crud_card_webflux.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/save")
    public Mono<Card> post(@RequestBody Mono<Card> carMono)  {
            return cardService.insert(carMono);
    }


}
