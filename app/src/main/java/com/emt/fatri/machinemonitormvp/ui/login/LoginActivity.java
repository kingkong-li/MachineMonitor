package com.emt.fatri.machinemonitormvp.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.emt.fatri.machinemonitormvp.R;
import com.emt.fatri.machinemonitormvp.data.model.UserInfo;
import com.emt.fatri.machinemonitormvp.ui.main.MainActivity;
import com.emt.fatri.machinemonitormvp.utils.MainApplication;
import com.emt.fatri.machinemonitormvp.utils.SharedPreferenceUtil;
import com.emt.fatri.machinemonitormvp.utils.ToastUtils;


/**
 * 登录页面
 * (看例子之前看一遍下面直白的解释,看完之后再看一遍就更明白MVP模式了)
 * --------M层   对P层传递过来的userInfo进行登录(网络请求)判断,处理完成之后将处理结果回调给P层
 * --------P层   传递完数据给M层处理之后,实例化回调对象,成功了就通知V层登录成功,失败了就通知V层显示错误信息
 * --------V层   负责响应用户的交互(获取数据---->提示操作结果)
 * @author Administrator
 */
public class LoginActivity extends Activity implements LoginContract.ILoginView {


    private  EditText mEtLoginUserName;

    private EditText mEtLoginPwd;

    private Button mBtnLogin;

    private ProgressBar mPbProgress;

    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        mLoginPresenter = new LoginPresenter(this);
        initView();

    }

    /**
     * 初始化View
     */
    private void initView() {

        mBtnLogin=findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenter.login();
            }
        });

        mPbProgress=findViewById(R.id.pb_progress);
        mEtLoginUserName=findViewById(R.id.et_login_userName);
        mEtLoginPwd=findViewById(R.id.et_login_pwd);
        mEtLoginUserName.setText(SharedPreferenceUtil.getString(MainApplication.getInstance(),
                UserInfo.USER_NAME,""));
        mEtLoginPwd.setText(SharedPreferenceUtil.getString(MainApplication.getInstance(),
                UserInfo.USER_PASSWORD,""));

    }



    @Override
    public void showProgress() {
        mPbProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mPbProgress.setVisibility(View.GONE);
    }

    @Override
    public void showInfo(String info) {

//        ToastUtils.getInstance().show(info);
    }

    @Override
    public void showErrorMsg(String msg) {
        ToastUtils.getInstance().show(msg);
    }

    @Override
    public void toMain() {
        Intent intent=new Intent();
        intent.setClass(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void toRegister() {

//        startActivity(new Intent(mActivity, RegisterActivity.class));
    }

    @Override
    public UserInfo getUserLoginInfo() {
        return new UserInfo(mEtLoginUserName.getText().toString().trim(), mEtLoginPwd.getText().toString().trim());
    }


    public void onRegister(View view) {
        toRegister();
    }


}
