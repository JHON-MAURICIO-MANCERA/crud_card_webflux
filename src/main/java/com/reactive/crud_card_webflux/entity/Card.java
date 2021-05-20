package com.reactive.crud_card_webflux.entity;


import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Document
@NoArgsConstructor
public class Card {

    private String title;

    private LocalDate date;

    @Id
    @Size(min = 1,max = 4,message = "characters between (2-4)")
    private String number;


    private String type;


    @Size(min = 1,max = 3,message = "characters between (2-4)")
    private String cod;


    public Card(String title, LocalDate date, String number, String cod) {
        this.title = title;
        this.date = date;
        this.number = number;
        this.cod = cod;

    }

    public Card(String title, LocalDate date, String cod) {
        this.title = title;
        this.date = date;
        this.cod = cod;

    }




    public LocalDate formatDate(LocalDate date) {
            //Formato de fecha (día/mes/año)
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            String dateF = formatoFecha.format(date);
            LocalDate ldataF =LocalDate.parse(dateF);
               return ldataF;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }
}
