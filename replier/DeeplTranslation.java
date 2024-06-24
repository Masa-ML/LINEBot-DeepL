package com.example.linebot.replier;

import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.deepl.api.*;

public class DeeplTranslation implements Replier{

    private String sentence;
    private String language;

    public DeeplTranslation(String sentence, String language) {
        this.sentence = sentence;
        this.language = language;
    }

    @Override
    public Message reply() {
        try {
            String authKey = "93548a42-f054-b761-d217-4ce3e707e335:fx";
            Translator translator = new Translator(authKey);
            if (sentence == null) {
                throw new Exception();
            }
            TextResult result = translator.translateText(sentence, null, language);
            return new TextMessage(result.getText());
        } catch (Exception e) {
            return new TextMessage("エラー：文を取得できませんでした\n" + e);
        }
    }

}
