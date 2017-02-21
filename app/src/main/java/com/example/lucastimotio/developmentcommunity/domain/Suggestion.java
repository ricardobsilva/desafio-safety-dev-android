package com.example.lucastimotio.developmentcommunity.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lucastimotio on 20/02/17.
 */

public class Suggestion{

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("imei")
    @Expose
    private String imei;

    @SerializedName("reason_to_learn")
    @Expose
    private String reason_to_learn;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    @SerializedName("updated_at")
    @Expose
    private String updated_at;

    public Suggestion(){}

    public Suggestion(String name, String description, String imei, String reason_to_learn, String username) {
        this.name = name;
        this.description = description;
        this.imei = imei;
        this.reason_to_learn = reason_to_learn;
        this.username = username;
    }

    public Suggestion(Integer id, String name, String description, String imei, String reason_to_learn, String username, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imei = imei;
        this.reason_to_learn = reason_to_learn;
        this.username = username;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getReason_to_learn() {
        return reason_to_learn;
    }

    public void setReason_to_learn(String reason_to_learn) {
        this.reason_to_learn = reason_to_learn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
