package com.reactive.crud_card_webflux.repository;


import com.reactive.crud_card_webflux.entity.Card;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CardRepository extends ReactiveMongoRepository<Card,String> {

    Flux<Card> findBytype(String type);




}
