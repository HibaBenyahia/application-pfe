package com.app.model;

/**
 * Created by Oussama on 16/05/2016.
 */
public class Emoticone {

    private String description;
    private String code;

    public Emoticone(String description, String code) {
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Emoticone{" +
                "description='" + description + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
