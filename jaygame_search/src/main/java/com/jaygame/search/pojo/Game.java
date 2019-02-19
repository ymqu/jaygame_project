package com.jaygame.search.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

//indexName = database; type= table
@Document(indexName ="jaygame_game", type = "game")
public class Game implements Serializable {

    @Id
    private String _id;

    @Field
    private int game_id;

    @Field(index=true)
    private String title;

    @Field(index=true)
    private String summary;

    @Field(index=true)
    private String detail;

    @Field(index=true)
    private String specs;

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }
}
