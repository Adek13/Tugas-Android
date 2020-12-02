package com.example.pulsa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pulsa {

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("phone_number")
    @Expose
    private String phone_number;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
