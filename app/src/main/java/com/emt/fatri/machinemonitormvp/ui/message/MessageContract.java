package com.emt.fatri.machinemonitormvp.ui.message;

/**
 * description:
 * Created by kingkong on 2018/10/24 0024.
 * changed by kingkong on 2018/10/24 0024.
 */

public class MessageContract {
    /**
     * V视图层
     */
    interface IMessageView {

        void showAlarmMessage();


    }

    /**
     * P视图与逻辑处理的连接层
     */
    interface IMessagePresenter {
        void getAlarmMessage();
    }

    /**
     * 逻辑处理层
     */
    interface IMessageModel {
    }
}
