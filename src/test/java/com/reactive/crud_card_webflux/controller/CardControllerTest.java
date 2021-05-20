package com.reactive.crud_card_webflux.controller;


import com.reactive.crud_card_webflux.entity.Card;
import com.reactive.crud_card_webflux.repository.CardRepository;
import com.reactive.crud_card_webflux.service.CardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CardController.class)
class CardControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @SpyBean
    private CardService cardService;

    @MockBean
    private CardRepository repository;

    @Captor
    private ArgumentCaptor<Mono<Card>> argumentCaptor;

    @Test
    void saveCard() {
        var request = Mono.just(new Card(
                "nueva", LocalDate.of(2020, 05, 2), "22", "12"));

        when(repository.save(any(Card.class))).thenReturn(request);

        webTestClient.post()
                .uri("/card/save")
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(request, Card.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.cod").isEqualTo(request.block().getCod())
                .jsonPath("$.title").isEqualTo(request.block().getTitle())
                .jsonPath("$.date").isEqualTo(request.block().getDate().toString())
                .jsonPath("$.number").isEqualTo(request.block().getNumber());
    }

    @Test
    void getAll() {
        var list = Flux.just(
                new Card(
                        "nueva", LocalDate.of(2020, 05, 2), "22", "12"));
        when(repository.findAll()).thenReturn(list);
        webTestClient.get()
                .uri("/card/allcards")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].cod").isEqualTo(list.blockFirst().getCod())
                .jsonPath("$[0].title").isEqualTo(list.blockFirst().getTitle())
                .jsonPath("$[0].date").isEqualTo(list.blockFirst().getDate().toString())
                .jsonPath("$[0].number").isEqualTo(list.blockFirst().getNumber());
    }

    @Test
    void getByType2() {
        var list = Flux.just(
                new Card("nueva"
                        , LocalDate.of(2020, 05, 2)
                        , "22"
                        , "06"));

        when(repository.findBytype("VISA")).thenReturn(list);

        webTestClient.get()
                .uri("/card/type/VISA")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].cod").isEqualTo(list.blockFirst().getCod())
                .jsonPath("$[0].title").isEqualTo(list.blockFirst().getTitle())
                .jsonPath("$[0].date").isEqualTo(list.blockFirst().getDate().toString())
                .jsonPath("$[0].number").isEqualTo(list.blockFirst().getNumber());
        verify(cardService).finBytype("VISA");
        verify(repository).findBytype("VISA");
    }

    @Test
    void delete() {
        webTestClient.delete()
                .uri("/card/1/delete")
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();
    }

    @Test
    void update() {
        var request = Mono.just(
                new Card("nueva"
                        , LocalDate.of(2020, 05, 2)
                        , "22"
                        , "06"));
        when(repository.save(any(Card.class))).thenReturn(request);
        webTestClient.put()
                .uri("/card/update")
                .body(request, Card.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.cod").isEqualTo(request.block().getCod())
                .jsonPath("$.title").isEqualTo(request.block().getTitle())
                .jsonPath("$.date").isEqualTo(request.block().getDate().toString())
                .jsonPath("$.number").isEqualTo(request.block().getNumber());
    }
}