package com.unbabel.challenge.model;


public class TranslationRequest {

    private String text;
    private String source_language;
    private String target_language;
    private String text_format;

    public TranslationRequest(String text, String source_language, String target_language, String text_format) {
        this.text = text;
        this.source_language = source_language;
        this.target_language = target_language;
        this.text_format = text_format;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSource_language() {
        return source_language;
    }

    public void setSource_language(String source_language) {
        this.source_language = source_language;
    }

    public String getTarget_language() {
        return target_language;
    }

    public void setTarget_language(String target_language) {
        this.target_language = target_language;
    }

    public String getText_format() {
        return text_format;
    }

    public void setText_format(String text_format) {
        this.text_format = text_format;
    }
}
