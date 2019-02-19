package com.jaygame.base.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="users")
@DynamicUpdate
@Data
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class User implements Serializable{
    @Id
    @GeneratedValue
    private Integer user_id;
    private String username;
    private String email;
    private String password;
    private Integer usr_privacy_id;
    private String gender;
    private String country;
    private String state;
    private String language;
    private Timestamp creation_date;

    public User(){}

}
