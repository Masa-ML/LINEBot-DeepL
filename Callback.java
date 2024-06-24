package com.example.linebot;

import com.example.linebot.replier.*;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.MessageEvent;

@LineMessageHandler
public class Callback {

    private static final Logger log = LoggerFactory.getLogger(Callback.class);

    private LanguageConfig languageConfig = new LanguageConfig(); //翻訳先の言語を設定するクラスのインスタンス化

    // フォローイベントに対応する
    @EventMapping
    public Message handleFollow(FollowEvent event) {
        // 実際はこのタイミングでフォロワーのユーザIDをデータベースにに格納しておくなど
        Follow follow = new Follow(event);
        return follow.reply();
    }

    // 文章で話しかけられたとき（テキストメッセージのイベント）に対応する
    @EventMapping
    public Message handleMessage(MessageEvent<TextMessageContent> event) {
        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();
        switch (text) {
            case "やあ":
                Greet greet = new Greet();
                return greet.reply();
            case "おみくじ":
                Omikuji omikuji = new Omikuji();
                return omikuji.reply();
            default:
                if (languageConfig.checkLanguageList(text)) {   // 翻訳先の言語を選択
                    languageConfig.setLanguage(text);
                    System.out.println(languageConfig.getLanguage());
                    LanguageReplier languageReplier = new LanguageReplier(languageConfig.getLanguage());
                    return languageReplier.reply();
                } else if (languageConfig.getLanguage() != null) {  //翻訳文を返す
                    DeeplTranslation deeplTranslation = new DeeplTranslation(text, languageConfig.getLanguage());
                    System.out.println(languageConfig.getLanguage());
                    return deeplTranslation.reply();
                }
                Parrot parrot = new Parrot(event);  //翻訳先の言語が入力する言語と同じ場合, オウム返し
                return parrot.reply();
        }
    }

}
