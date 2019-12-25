package com.example.sqlitemanager.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.sqlitemanager.bean.ActionBean;
import com.example.sqlitemanager.bean.ActionSpotBean;
import java.util.ArrayList;

public class UavActionDao {
    private static final String TAG = "UavActionDao";
    private final ActionSQLiteOpenHelper mHelper;
    private String routetype;
    private String inspectiontime;
    private String towername;
    private double towerlng;
    private double towerlat;
    private ArrayList<ActionSpotBean> spotList;
    private String routename;

    public UavActionDao(Context context) {
        mHelper = new ActionSQLiteOpenHelper(context);
    }

    /**
     * @param routename      航线名称
     * @param routetype      航线类型
     * @param inspectiontime 巡检时间
     * @param towername      塔名
     * @param towerlng       塔的经度
     * @param towerlat       塔的经度
     * @param longitude      经度
     * @param latitude       纬度
     * @param height         飞行高度
     * @param yawangle       偏航角
     * @param pitchangle     云台俯仰角
     * @param actionselect   动作的选择
     */
    public void add(String routename, String routetype, String inspectiontime, String towername,
                    double towerlng, double towerlat, double longitude, double latitude, float height,
                    double yawangle, double pitchangle, String actionselect) {
        SQLiteDatabase mDb = mHelper.getWritableDatabase();

        String sql = "insert into uavaction(routename,routetype,inspectiontime,towername," +
                "towerlng,towerlat,longitude,latitude,height,yawangle,pitchangle,actionselect)values" +
                "(?,?,?,?,?,?,?,?,?,?,?,?)";
        mDb.execSQL(sql, new Object[]{routename, routetype, inspectiontime,
                towername, towerlng, towerlat, longitude, latitude, height, yawangle, pitchangle,
                actionselect});
        mDb.close();
    }


    /**
     * 通过经纬度,修改经纬度
     *
     * @param ago_longitude   修改之前的经度
     * @param ago_latitude    修改之前的纬度
     * @param after_longitude 修改之后的经度
     * @param after_latitude  修改之后的纬度
     */
    public void revise(double ago_longitude, double ago_latitude, double after_longitude, double
            after_latitude) {
        SQLiteDatabase mDb = mHelper.getWritableDatabase();
        String sql = "update uavaction set longitude=?,latitude=? where longitude=? and latitude=?";
        mDb.execSQL(sql, new Object[]{after_longitude, after_latitude, ago_longitude, ago_latitude});

        mDb.close();
    }

    /**
     * 通过经纬度删除动作中一个点的数据
     *
     * @param longitude 经度
     * @param latitude  纬度
     */
    public void delSpot(double longitude, double latitude) {
        SQLiteDatabase mDb = mHelper.getWritableDatabase();
        String sql = "delete from uavaction where longitude=? and latitude=?";
        mDb.execSQL(sql, new Object[]
                {longitude, latitude});
        mDb.close();
    }

    /**
     * 所有动作分类之后的大集合
     *
     * @return
     */
    public ArrayList<ActionBean> findAction() {
        SQLiteDatabase mDb = mHelper.getReadableDatabase();
        ArrayList<ActionBean> mlist = new ArrayList<>();
        String sql = "select * from uavaction";
        Cursor curs = mDb.rawQuery(sql, null);
        ArrayList<String> mRoute = new ArrayList<>();

        while (curs.moveToNext()) {
            String routename = curs.getString(curs.getColumnIndex("routename"));
            mRoute.add(routename);
        }


        for (int i = 0; i < mRoute.size(); i++) {
            //需要查询两次,使用curs.moveToNext()查询结果是null
            Cursor cursor = mDb.rawQuery("select * from uavaction where routename=?",
                    new String[]{mRoute.get(i)});
            Log.d(TAG, "findAction: ___for");
            while (cursor.moveToNext()) {
                Log.d(TAG, "findAction: ___while");
                spotList = new ArrayList<>();

                routename = cursor.getString(cursor.getColumnIndex("routename"));
                routetype = cursor.getString(cursor.getColumnIndex("routetype"));
                inspectiontime = cursor.getString(cursor.getColumnIndex("inspectiontime"));
                towername = cursor.getString(cursor.getColumnIndex("towername"));
                towerlng = cursor.getDouble(cursor.getColumnIndex("towerlng"));
                towerlat = cursor.getDouble(cursor.getColumnIndex("towerlat"));
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                double longitude = cursor.getDouble(cursor.getColumnIndex("longitude"));
                double latitude = cursor.getDouble(cursor.getColumnIndex("latitude"));
                float height = cursor.getFloat(cursor.getColumnIndex("height"));
                double yawangle = cursor.getDouble(cursor.getColumnIndex("yawangle"));
                double pitchangle = cursor.getDouble(cursor.getColumnIndex("pitchangle"));
                String actionselect = cursor.getString(cursor.getColumnIndex("actionselect"));

                Log.d(TAG, "findAction:_____longitude： " + longitude + "______towerlat:" + towerlat);
                ActionSpotBean actionSpotBean = new ActionSpotBean();
                actionSpotBean.setId(id);
                actionSpotBean.setLongitude(longitude);
                actionSpotBean.setLatitude(latitude);
                actionSpotBean.setHeight(height);
                actionSpotBean.setYawangle(yawangle);
                actionSpotBean.setPitchangle(pitchangle);
                actionSpotBean.setActionselect(actionselect);
                spotList.add(actionSpotBean);

            }

            ActionBean actionBean = new ActionBean(spotList, routename, routetype,
                    inspectiontime, towername, towerlng, towerlat);
            mlist.add(actionBean);


        }

        for (int i = 0; i < mlist.size(); i++) {
            ArrayList<ActionSpotBean> mlist1 = mlist.get(i).getMlist();
            for (int j = 0; j < mlist1.size(); j++) {
                double longitude = mlist1.get(j).getLongitude();
                double latitude = mlist1.get(j).getLatitude();
                Log.d(TAG, "经度: " + longitude + "___纬度:" + latitude);
            }
        }

        return mlist;
    }

    /**
     * 查询所有航线名称
     * @return
     */
    public ArrayList<String> findRouteName(){

        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase mDb = mHelper.getReadableDatabase();
        String sql = "select * from uavaction";
        Cursor cursor = mDb.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("routename"));
            list.add(name);
        }

        return list;
    }
}
