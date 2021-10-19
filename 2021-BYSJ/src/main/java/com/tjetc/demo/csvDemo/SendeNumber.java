package com.tjetc.demo.csvDemo;

public class SendeNumber {
    private String code;
    private String Period;
    private String resourceUsedNum;
    private String BreachUsedNum;
    private String type;
    private String result;//结果
    private String reason;//原因

    public SendeNumber(String code, String period, String resourceUsedNum, String breachUsedNum, String type, String result, String reason) {
        this.code = code;
        Period = period;
        this.resourceUsedNum = resourceUsedNum;
        BreachUsedNum = breachUsedNum;
        this.type = type;
        this.result = result;
        this.reason = reason;
    }

    public SendeNumber() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPeriod() {
        return Period;
    }

    public void setPeriod(String period) {
        Period = period;
    }

    public String getResourceUsedNum() {
        return resourceUsedNum;
    }

    public void setResourceUsedNum(String resourceUsedNum) {
        this.resourceUsedNum = resourceUsedNum;
    }

    public String getBreachUsedNum() {
        return BreachUsedNum;
    }

    public void setBreachUsedNum(String breachUsedNum) {
        BreachUsedNum = breachUsedNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
