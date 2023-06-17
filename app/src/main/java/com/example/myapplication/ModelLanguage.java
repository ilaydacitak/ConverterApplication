package com.example.myapplication;

public class ModelLanguage {

    public String languageTitle;
    String languageCode;
    String LanguageTitle;

    public ModelLanguage(String languageCode, String languageTitle){
        this.languageCode = languageCode;
        this.LanguageTitle = languageTitle;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public String getLanguageTitle(){
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public void setLanguageTitle(String languageTitle) {
        LanguageTitle = languageTitle;
    }
}
