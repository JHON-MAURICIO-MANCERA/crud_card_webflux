package com.reactive.crud_card.repository;


import com.reactive.crud_card.entity.Card;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface CardRepository extends ReactiveMongoRepository<Card,String> {

    Flux<Card> findBytype(String type);




}
