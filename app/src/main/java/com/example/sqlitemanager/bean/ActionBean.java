package com.example.sqlitemanager.bean;

import java.util.ArrayList;

public class ActionBean {

    private ArrayList<ActionSpotBean> mlist;//动作集合
    private String routename = "";//航线名称
    private String routetype = "";//航线类型
    private String inspectiontime = "";//巡检时间
    private String towername = "";//塔名
    private double towerlng;//塔的经度
    private double towerlat;//塔的经度

    public ActionBean(ArrayList<ActionSpotBean> mlist, String routename,
                      String routetype, String inspectiontime, String towername, double towerlng,
                      double towerlat) {
        this.mlist = mlist;
        this.routename = routename;
        this.routetype = routetype;
        this.inspectiontime = inspectiontime;
        this.towername = towername;
        this.towerlng = towerlng;
        this.towerlat = towerlat;
    }


    public ArrayList<ActionSpotBean> getMlist() {
        return mlist;
    }

    public void setMlist(ArrayList<ActionSpotBean> mlist) {
        this.mlist = mlist;
    }

    public String getRoutename() {
        return routename;
    }

    public void setRoutename(String routename) {
        this.routename = routename;
    }

    public String getRoutetype() {
        return routetype;
    }

    public void setRoutetype(String routetype) {
        this.routetype = routetype;
    }

    public String getInspectiontime() {
        return inspectiontime;
    }

    public void setInspectiontime(String inspectiontime) {
        this.inspectiontime = inspectiontime;
    }

    public String getTowername() {
        return towername;
    }

    public void setTowername(String towername) {
        this.towername = towername;
    }

    public double getTowerlng() {
        return towerlng;
    }

    public void setTowerlng(double towerlng) {
        this.towerlng = towerlng;
    }

    public double getTowerlat() {
        return towerlat;
    }

    public void setTowerlat(double towerlat) {
        this.towerlat = towerlat;
    }
}
