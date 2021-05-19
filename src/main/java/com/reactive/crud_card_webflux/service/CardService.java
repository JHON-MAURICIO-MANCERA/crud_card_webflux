package com.reactive.crud_card.service;


import com.reactive.crud_card.entity.Card;
import com.reactive.crud_card.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;


@Service
public class CardService {

    private  Card card;
    private CardRepository repository;

    @Autowired
    public CardService(CardRepository repository) {
        this.repository = repository;
    }

    public Flux<Card> listAll(){return  repository.findAll();}
    public Flux<Card> finBytype(String type){return  repository.findBytype(type);}
    public Mono<Void>  deleById(String cod){  return repository.deleteById(cod);}





    public Mono<Void> insert(Mono<Card> cardMono) {
        return cardMono
                .flatMap(repository::save)
                .then();
    }
}