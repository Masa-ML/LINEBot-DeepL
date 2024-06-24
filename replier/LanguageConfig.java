package com.example.linebot.replier;

import java.util.ArrayList;

public class LanguageConfig {

    private String language;
    private ArrayList<String> languageList;

    public LanguageConfig() {
        this.language = "ja";
        languageList = new ArrayList<>() {
            {
                add("ja"); //日本語
                add("en-US"); //英語(アメリカ)
                add("en-GB"); //英語(イギリス)
                add("zh"); //中国語
                add("ko"); //韓国語
                add("fr"); //フランス語
                add("de"); //ドイツ語
                add("es"); //スペイン語
                add("it"); //イタリア語
            }
        };
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return this.language;
    }

    public boolean checkLanguageList(String text) {
        return languageList.contains(text);
    }

}
