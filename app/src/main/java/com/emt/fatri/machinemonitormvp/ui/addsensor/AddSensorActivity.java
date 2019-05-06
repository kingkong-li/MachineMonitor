package com.emt.fatri.machinemonitormvp.ui.addsensor;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.emt.fatri.machinemonitormvp.R;
import com.emt.fatri.machinemonitormvp.data.model.SensorSetInfo;

/**
 * create by kingkong on 2018/9/20 0020.
 * 添加传感器Activity
 */
public class AddSensorActivity extends Activity implements AddSensorContract.IAddSensorView{

    private AddSensorPresenter mAddSensorPresenter;
    /**传感器Id*/
    private TextView mTvSensorId;
    /**传感器名称*/
    private EditText mEtSensorName;
    /**传感器类型*/
    private EditText mEtSensorType;
    private EditText mEtMachineId;
    private EditText mEtModel;
    private EditText mEtNumber;
    private EditText mEtLocation;
    private EditText mEtDevType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sensor);
        mAddSensorPresenter=new AddSensorPresenter(this);

        initView();

        Button btAdd=findViewById(R.id.add);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SensorSetInfo sensorInfo=new SensorSetInfo();

                sensorInfo.sensorType = Integer.parseInt(mEtSensorType.getText().toString());

                sensorInfo.sensorName=mEtSensorName.getText().toString();
                sensorInfo.sensorVersion= Integer.parseInt(mEtModel.getText().toString());
                sensorInfo.serialnumber =mEtNumber.getText().toString();
                sensorInfo.location = mEtLocation.getText().toString();
                sensorInfo.devType = Integer.parseInt(mEtDevType.getText().toString());
                sensorInfo.devId = Integer.parseInt(mEtMachineId.getText().toString());

                mAddSensorPresenter.registerSensor(sensorInfo);
            }
        });

    }

    private void initView() {
        mEtSensorName=findViewById(R.id.sensor_name);
        mEtSensorType=findViewById(R.id.sensor_type);
        mEtMachineId=findViewById(R.id.dev_id);
        mEtDevType=findViewById(R.id.dev_Type);
        mEtModel=findViewById(R.id.model);
        mEtNumber=findViewById(R.id.number);
        mEtLocation=findViewById(R.id.location);
        mTvSensorId=findViewById(R.id.sensor_Id);
    }

    @Override
    public void showSensorId(int id) {

        mTvSensorId.setText(String.valueOf(id));
    }
}
