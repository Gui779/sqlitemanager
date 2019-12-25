package com.example.sqlitemanager.bean;

public class ActionSpotBean {

    private int id;//id
    private double longitude;//经度
    private double latitude;//纬度
    private float height;//飞行高度
    private double yawangle;//偏航角
    private double pitchangle;//云台俯仰角
    private String actionselect;//动作的选择
    public ActionSpotBean(){

    }
    public ActionSpotBean(double longitude, double latitude, float height, double yawangle, double pitchangle, String actionselect) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
        this.yawangle = yawangle;
        this.pitchangle = pitchangle;
        this.actionselect = actionselect;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public double getYawangle() {
        return yawangle;
    }

    public void setYawangle(double yawangle) {
        this.yawangle = yawangle;
    }

    public double getPitchangle() {
        return pitchangle;
    }

    public void setPitchangle(double pitchangle) {
        this.pitchangle = pitchangle;
    }

    public String getActionselect() {
        return actionselect;
    }

    public void setActionselect(String actionselect) {
        this.actionselect = actionselect;
    }
}
