package models.pojo.reqres;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataModel {
    Integer id;
    String email;

    @JsonProperty("first_name")
    String firstname;

    @JsonProperty("last_name")
    String lastname;

    String avatar;

    public Integer getId () {return id;}

    public String getEmail () {return email;}

    public String getFirstname() {return firstname;}

    public String getLastname() {return lastname;}

    public String getAvatar() {return avatar;}
}
