package com.omeram.birthday.vo;

public class ScreenView {

    private int background;
    private int defaultPicture;

    public ScreenView(int background, int defaultPicture) {
        this.background = background;
        this.defaultPicture = defaultPicture;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getDefaultPicture() {
        return defaultPicture;
    }

    public void setDefaultPicture(int defaultPicture) {
        this.defaultPicture = defaultPicture;
    }
}
