package com.omeram.birthday.model;

public class Baby {

    private String name;
    private long birthdayTimestamp;
    private String pictureUri;

    public Baby(String name, long birthday) {
        this.name = name;
        this.birthdayTimestamp = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBirthdayTimestamp() {
        return birthdayTimestamp;
    }

    public void setBirthdayTimestamp(long birthdayTimestamp) {
        this.birthdayTimestamp = birthdayTimestamp;
    }

    public String getPictureUri() {
        return pictureUri;
    }

    public void setPictureUri(String pictureUri) {
        this.pictureUri = pictureUri;
    }
}
