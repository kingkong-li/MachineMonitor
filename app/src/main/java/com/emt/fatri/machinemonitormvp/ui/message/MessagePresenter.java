package com.emt.fatri.machinemonitormvp.ui.message;

/**
 * description:
 * Created by kingkong on 2018/10/24 0024.
 * changed by kingkong on 2018/10/24 0024.
 */

public class MessagePresenter implements MessageContract.IMessagePresenter {
    private MessageContract.IMessageView mMessageView;
    private MessageContract.IMessageModel mMessageModel;

    public MessagePresenter(MessageContract.IMessageView messageView)
    {
        mMessageView=messageView;
        mMessageModel=new MessageModel();

    }

    @Override
    public void getAlarmMessage() {

    }
}
