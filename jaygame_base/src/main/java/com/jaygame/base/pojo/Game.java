package com.jaygame.base.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name="game_info")
@DynamicUpdate
@Data
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Game {
    @Id
    @GeneratedValue
    private Integer game_id;
    private String game_title;
    private String game_download_link;
    private String game_summary;

    @Lob
    @Column(columnDefinition = "text")
    private  String game_details;

    @Lob
    @Column(columnDefinition = "text")
    private String game_specs;

    private Float game_price;
    private String game_icon;
}
