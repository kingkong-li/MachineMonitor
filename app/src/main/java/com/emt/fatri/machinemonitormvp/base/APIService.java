package com.emt.fatri.machinemonitormvp.base;


import com.emt.fatri.machinemonitormvp.data.model.AreaInfo;
import com.emt.fatri.machinemonitormvp.data.model.HttpResult;
import com.emt.fatri.machinemonitormvp.data.model.LoginResponseData;
import com.emt.fatri.machinemonitormvp.data.model.MachineInfo;
import com.emt.fatri.machinemonitormvp.data.model.SensorInfo;
import com.emt.fatri.machinemonitormvp.data.model.SensorSetInfo;
import com.emt.fatri.machinemonitormvp.data.model.SetSensorResponse;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * API--接口  服务[这里处理的是同一的返回格式 resultCode  resultInfo Data<T> --->这里的data才是返回的结果,T就是泛型 具体返回的been对象或集合]
 * Created by Kingkong
 * on 2018/9/3.
 */
public interface APIService {
    /**
     * 用户登录的接口
     *
     * @param username 用户名
     * @param pwd      密码
     */
    @FormUrlEncoded
    @POST("index/user_log_in")
    Observable<HttpResult<LoginResponseData>> userLogin(@Field("UserName") String username, @Field("UserPasswd") String pwd);

    @FormUrlEncoded
    @POST("user/get_zone_list")
    Observable<HttpResult<List<AreaInfo>>> getAreaList(@Field("UserId") int userId);

    @FormUrlEncoded
    @POST("zone/get_dev_list")
    Observable<HttpResult<List<MachineInfo>>> getMachineInfo(@Field("ZoneId") int zoneId,@Field("DevType") int devType);

    @FormUrlEncoded
    @POST("equip/get_conf")
    Observable<HttpResult<List<SensorInfo>>> getSensorList(@Field("zone_id") int machineId);

    @POST("sensor/set_conf")
    Observable<HttpResult<SetSensorResponse>> setSensor(@Body SensorSetInfo requestBody);

    @FormUrlEncoded
    @POST("equip/get_sensor_list_data")
    Observable<HttpResult<List<SensorInfo>>> getSensorListData(@Field("DevType") int devType,
                                                         @Field("DevId") int devId,@Field("SensorType") int sensorType);

}
