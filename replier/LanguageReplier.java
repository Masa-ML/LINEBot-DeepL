package com.example.linebot.replier;

import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

public class LanguageReplier implements Replier{

    private String language;

    public LanguageReplier(String language) {
        this.language = language;
    }

    @Override
    public Message reply() {
        String country = getCountryName(language);
        return new TextMessage("翻訳先の言語を" + country + "に設定しました。");
    }

    private String getCountryName(String language) {
        switch (language) {
            case "ja":
                return "日本語";
            case "en-US":
                return "英語(アメリカ)";
            case "en-GB":
                return "英語(イギリス)";
            case "zh":
                return "中国語";
            case "ko":
                return "韓国語";
            case "fr":
                return "フランス語";
            case "de":
                return "ドイツ語";
            case "es":
                return "スペイン語";
            case "it":
                return "イタリア語";
            default:
                return "未知の言語";
        }
    }
}
