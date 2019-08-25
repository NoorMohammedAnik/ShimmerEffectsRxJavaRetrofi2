package com.app.shimmereffects;

import com.google.gson.annotations.SerializedName;

public class Contacts {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String massage;

    public String getId() {
        return id;
    }

    public void setName(String name) {

        this.name=name;
    }


    public String getName() {
        return name;
    }


    public void setEmail(String email) {

        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public String getValue() {
        return value;
    }

    public String getMassage() {
        return massage;
    }
}
