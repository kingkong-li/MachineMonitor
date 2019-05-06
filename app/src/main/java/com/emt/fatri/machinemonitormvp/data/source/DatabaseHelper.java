package com.emt.fatri.machinemonitormvp.data.source;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.emt.fatri.machinemonitormvp.data.model.AreaInfo;
import com.emt.fatri.machinemonitormvp.utils.MainApplication;

import java.util.ArrayList;

/**
 * description:数据库处理帮助类
 * Created by kingkong on 2018/9/7 0007.
 * changed by kingkong on 2018/9/7 0007.
 */

public class DatabaseHelper {

    /**
     *
     * 根据区域id 删除该区域
     * @author lijg4
     */
    public static void deleteAreaUseId(int areaId) {

        ContentResolver contentResolver = MainApplication.getInstance().getContentResolver();
        Uri uri = MachineInfoProvider.CONTENT_URI_AREA_LIST;
        contentResolver.delete(uri, AreaInfo.AREA_ID+"=?",
                new String[]{String.valueOf(areaId)});


    }

    /**
     *插入一条数据到数据库
     */
    public static void insertNewItemToDb(final AreaInfo info) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ContentValues values = new ContentValues();
                values.put(AreaInfo.AREA_ID, info.mAreaId);
                values.put(AreaInfo.AREA_NAME, info.mAreaName);
                values.put(AreaInfo.AREA_DESCRIPTION, info.mAreaDescription);
                values.put(AreaInfo.AREA_STATE, info.mAreaState);
                MainApplication
                        .getInstance()
                        .getApplicationContext()
                        .getContentResolver()
                        .insert(MachineInfoProvider.CONTENT_URI_AREA_LIST,
                                values);
            }
        }).start();

    }

    /**
     *
     * @return
     */
    public static ArrayList<AreaInfo> getAreaInfoList(){
        ArrayList<AreaInfo> dataList = new ArrayList<AreaInfo>();
        ContentResolver contentResolver = MainApplication.getInstance().getContentResolver();
        Uri uri = MachineInfoProvider.CONTENT_URI_AREA_LIST;
        Cursor cursor = contentResolver.query(uri, null, null, null,null);
        try {
            if (null != cursor && cursor.moveToFirst()) {
                do {
                    AreaInfo data=new AreaInfo();
                    data.mAreaId=cursor.getInt(cursor.getColumnIndex(AreaInfo.AREA_ID));
                    data.mAreaName=cursor.getString(cursor.getColumnIndex(AreaInfo.AREA_NAME));
                    data.mAreaDescription=cursor.getString(cursor.getColumnIndex(AreaInfo.AREA_DESCRIPTION));
                    data.mAreaState=cursor.getInt(cursor.getColumnIndex(AreaInfo.AREA_STATE));
                    dataList.add(data);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return dataList;

    }

}
