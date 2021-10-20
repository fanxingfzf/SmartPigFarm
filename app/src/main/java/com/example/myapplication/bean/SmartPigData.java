package com.example.myapplication.bean;

/**
 * 数据实体
 */
public class SmartPigData {
    private String Temperature;
    private String Shidu;

    @Override
    public String toString() {
        return "SmartPigData{" +
                "Temperature='" + Temperature + '\'' +
                ", Shidu='" + Shidu + '\'' +
                ", Anqi='" + Anqi + '\'' +
                ", Guangzhao='" + Guangzhao + '\'' +
                '}';
    }

    private String Anqi;

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }

    public String getShidu() {
        return Shidu;
    }

    public void setShidu(String shidu) {
        Shidu = shidu;
    }

    public String getAnqi() {
        return Anqi;
    }

    public void setAnqi(String anqi) {
        Anqi = anqi;
    }

    public String getGuangzhao() {
        return Guangzhao;
    }

    public void setGuangzhao(String guangzhao) {
        Guangzhao = guangzhao;
    }

    private String Guangzhao;
}
