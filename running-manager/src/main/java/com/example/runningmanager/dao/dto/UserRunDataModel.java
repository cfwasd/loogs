package com.example.runningmanager.dao.dto;

/**
 * @author wzh
 */
public class UserRunDataModel {
    private String userId;
    private String maxStepCount;
    private String exchangeCount;
    private String useStepCount;
    private String totalMileage;
    private String day;

    public String getMaxStepCount() {
        return maxStepCount;
    }

    public void setMaxStepCount(String maxStepCount) {
        this.maxStepCount = maxStepCount;
    }

    public String getExchangeCount() {
        return exchangeCount;
    }

    public void setExchangeCount(String exchangeCount) {
        this.exchangeCount = exchangeCount;
    }

    public String getUseStepCount() {
        return useStepCount;
    }

    public void setUseStepCount(String useStepCount) {
        this.useStepCount = useStepCount;
    }

    public String getTotalMileage() {
        return totalMileage;
    }

    public void setTotalMileage(String totalMileage) {
        this.totalMileage = totalMileage;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserRunDataModel{" +
                "userId='" + userId + '\'' +
                "maxStepCount='" + maxStepCount + '\'' +
                ", exchangeCount='" + exchangeCount + '\'' +
                ", useStepCount='" + useStepCount + '\'' +
                ", totalMileage='" + totalMileage + '\'' +
                ", day='" + day + '\'' +
                '}';
    }
}
