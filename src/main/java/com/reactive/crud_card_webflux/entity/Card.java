package com.reactive.crud_card_webflux.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Card {

    private String title;

    private String date;


    private String number;


    private String type;

    @Id
    private String cod;

    public Card() {
    }

    public Card(String title, String date, String number, String type, String cod) throws Exception {
        this.title = title;
        this.date = date;
        this.number =number;
        this.type = type;
        this.cod = cod;
    }

    public Card(String title, String date, String number, String type)  {
        this.title = title;
        this.date = date;
        this.number = number;
        this.type = type;
    }

    public static String validateNumberCard(String number) throws Exception {
        if (number.length()>7 && number.length()<3){
             throw new Exception("no sirve");
        }
        return number;
    }
 /*   public static  String validateData(String date){


    }*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
