package com.omeram.birthday.vo;

import com.omeram.birthday.R;


public enum AgeCount {

    zero(0, R.drawable.n0),
    one(1, R.drawable.n1),
    two(2, R.drawable.n2),
    three(3, R.drawable.n3),
    four(4, R.drawable.n4),
    five(5, R.drawable.n5),
    six(6, R.drawable.n6),
    seven(7, R.drawable.n7),
    eight(8, R.drawable.n8),
    nine(9, R.drawable.n9),
    ten(10, R.drawable.n10),
    eleven(11, R.drawable.n11),
    twelve(12, R.drawable.n12);

    private int number;
    private int resource;

    AgeCount(int number, int resource) {
        this.number = number;
        this.resource = resource;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public static AgeCount findByNumber(int number) {
        for (AgeCount v : values()) {
            if (v.getNumber() == number) {
                return v;
            }
        }
        return null;
    }
}
