package com.example.smubuoy;

public class HydrologyInfo {
    private String time;      //时间点
    private String wind;         //风力
    private String temp;         //水温
    private String dip;          //倾角
    private String power;       //电量
    private String number;      //浮标编号


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String column) {
        this.temp = temp;
    }

    public String getDip() {
        return dip;
    }

    public void setDip(String dip) {
        this.dip = dip;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
