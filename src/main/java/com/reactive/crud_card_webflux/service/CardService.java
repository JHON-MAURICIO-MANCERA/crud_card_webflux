package com.reactive.crud_card_webflux.service;


import com.reactive.crud_card_webflux.entity.Card;
import com.reactive.crud_card_webflux.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<Void>  deleById(String number){  return repository.deleteById(number);}
    public Mono<Card> findId(String number){return repository.findById(number);}





    public Mono<Card> insert(Mono<Card> cardMono) {
        return cardMono
                .flatMap(card -> {card.setType(TypeCard.valType(card.getCod()));
                return repository.save(card);

                });

    }
}