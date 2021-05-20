package com.reactive.crud_card_webflux.controller;


import com.reactive.crud_card_webflux.entity.Card;
import com.reactive.crud_card_webflux.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
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
    @GetMapping("/allcards")
    public Flux<Card> list() {
        return cardService.listAll();
    }

    @GetMapping("/type/{type}")
    public Flux<Card> getCard(@PathVariable("type") String type) {
        return cardService.finBytype(type);
    }

    @DeleteMapping("/delete/{cod}")
        public Mono<Void> deleteById(@PathVariable("number") String number) {
        return cardService.deleById(number);
    }

    @PutMapping("/update")
    public Mono<Card> update(@RequestBody Mono<Card> cardMono) {
        return cardService.insert(cardMono);
    }

    @GetMapping("/number/{number}")
    public Mono<Card> findById (@PathVariable("number")String number){return cardService.findId(number);}
    }
