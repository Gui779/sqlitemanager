package com.example.sqlitemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlitemanager.bean.ActionBean;
import com.example.sqlitemanager.bean.ActionSpotBean;
import com.example.sqlitemanager.db.UavActionDao;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mLngEt;
    EditText mLatEt;
    Button mAddBtn;
    Button mDelBtn;
    Button mReviseBtn;
    Button mQueryBtn;
    Button mUpgrade;
    TextView mShowData;
    private UavActionDao mUavActionDao;
    private static final String TAG = "UavActionDao";
    private double mLngD;
    private double mLatD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();


    }

    private void initView() {
        mLngEt = findViewById(R.id.lng_et);
        mLatEt = findViewById(R.id.lat_et);
        mAddBtn = findViewById(R.id.add_btn);
        mDelBtn = findViewById(R.id.del_btn);
        mReviseBtn = findViewById(R.id.revise_btn);
        mQueryBtn = findViewById(R.id.query_btn);
        mUpgrade = findViewById(R.id.upgrade_btn);
        mShowData = findViewById(R.id.show_data);

        mAddBtn.setOnClickListener(this);
        mDelBtn.setOnClickListener(this);
        mReviseBtn.setOnClickListener(this);
        mQueryBtn.setOnClickListener(this);
        mUpgrade.setOnClickListener(this);

        mUavActionDao = new UavActionDao(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.add_btn:
                String mLng = mLngEt.getText().toString().trim();
                String mLat = mLatEt.getText().toString().trim();

                mLngD = Double.parseDouble(mLng);
                mLatD = Double.parseDouble(mLat);

                if (mLngEt.getText().toString().trim().equals("") &&
                        mLatEt.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "请输入经纬度", Toast.LENGTH_SHORT).show();
                    return;
                }
                String routename = "动作四";
                ArrayList<String> routeList = mUavActionDao.findRouteName();
                if (routeList.contains(routename)){
                    Toast.makeText(this, "已经存在该航线,请重新添加", Toast.LENGTH_SHORT).show();
                }else{
                    mUavActionDao.add("routename", " 检测", "2019年12月24日",
                            "塔一", 116.035667, 34.645622, mLngD,
                            mLatD, 30, 0.6, 0.3, "无动作");
                }

                break;

            case R.id.del_btn:

                mUavActionDao.delSpot(Double.parseDouble(mLngEt.getText().toString().trim()),
                        Double.parseDouble(mLatEt.getText().toString().trim()));
                break;

            case R.id.revise_btn:
                if (mLngEt.getText().toString().trim().equals("") &&
                        mLatEt.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "请输入要修改的经纬度", Toast.LENGTH_SHORT).show();
                    return;
                }
                mUavActionDao.revise(Double.parseDouble(mLngEt.getText().toString().trim()),
                        Double.parseDouble(mLatEt.getText().toString().trim()),
                        100.0, 200.0);

                break;
            case R.id.query_btn:

                ArrayList<ActionBean> mActionLists = mUavActionDao.findAction();
                if (mActionLists.size() == 0) {
                    Toast.makeText(this, "未查询到结果", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (int i = 0; i < mActionLists.size(); i++) {
                    ActionBean actionBean = mActionLists.get(i);
                    String name = actionBean.getRoutename();
                    String inspectiontime = actionBean.getInspectiontime();
                    String routetype = actionBean.getRoutetype();
                    String towername = actionBean.getTowername();


                    ArrayList<ActionSpotBean> mlist = actionBean.getMlist();
                    Log.d(TAG, "mlist: " + mlist.size());
                    for (int j = 0; j < mlist.size(); j++) {
                        ActionSpotBean actionSpotBean = mlist.get(j);
                        double longitude = actionSpotBean.getLongitude();
                        double latitude = actionSpotBean.getLatitude();
                        String actionselect = actionSpotBean.getActionselect();
                        int id = actionSpotBean.getId();
                        mShowData.setText("动作名称:" + name + "\n" + "动作类型:" + routetype + "\n"
                                + "巡检时间" + inspectiontime + "\n" + "塔名称:" + towername + "\n"
                                + "经度:" + longitude + "\n" + "纬度:" + latitude + "\n" + "动作:" + actionselect);

                        Log.d(TAG, "findAction:数据： " + "___动作名称:" + name + "___动作类型:" + routetype
                                + "___巡检时间" + inspectiontime + "___塔名称:" + towername
                                + "___经度:" + longitude + "___纬度:" + latitude + "___动作:" + actionselect
                                + "___id:" + id);
                    }

                }
                Log.d(TAG, "mActionLists: " + mActionLists.size());

                break;

            case R.id.upgrade_btn:

                break;
        }
    }
}
