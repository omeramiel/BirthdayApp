package com.omeram.birthday.vo;

public class AgeView {

    private @AgePeriod int agePeriod;
    private AgeCount ageCount;

    public @AgePeriod int getAgePeriod() {
        return agePeriod;
    }

    public void setAgePeriod(@AgePeriod int agePeriod) {
        this.agePeriod = agePeriod;
    }

    public AgeCount getAgeCount() {
        return ageCount;
    }

    public void setAgeCount(AgeCount ageCount) {
        this.ageCount = ageCount;
    }
}
