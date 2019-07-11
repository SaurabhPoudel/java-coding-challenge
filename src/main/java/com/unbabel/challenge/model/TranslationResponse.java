package com.unbabel.challenge.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TranslationResponse {
    //{"order_number": 41649,
    // "price": 6.0,
    // "source_language": "en",
    // "status": "new",
    // "target_language": "pt",
    // "text": "Hello, world!",
    // "text_format": "text",
    // "uid": "d5752ee72d"}

    //{"order_number": 41654,
    // "price": 6.0,
    // "source_language": "en",
    // "status": "completed",
    // "target_language": "pt",
    // "text": "Hello, world!",
    // "text_format": "text",
    // "translatedText": "Olá Mundo!",
    // "uid": "b65e02f976"}%
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private long order_number;
    private double price;
    private String source_language;
    private String status;
    private String target_language;
    private String text;
    private String text_format;
    private String translatedText;
    private String uid;

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }
    public long getId(){
        return id;
    }
    public long getOrder_number() {
        return order_number;
    }

    public void setOrder_number(long order_number) {
        this.order_number = order_number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSource_language() {
        return source_language;
    }

    public void setSource_language(String source_language) {
        this.source_language = source_language;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTarget_language() {
        return target_language;
    }

    public void setTarget_language(String target_language) {
        this.target_language = target_language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText_format() {
        return text_format;
    }

    public void setText_format(String text_format) {
        this.text_format = text_format;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
