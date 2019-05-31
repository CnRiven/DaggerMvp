package com.riven.daggermvp.ui.activity.login;

import android.content.Intent;
import android.widget.EditText;

import com.riven.daggermvp.R;
import com.riven.daggermvp.base.BaseActivity;
import com.riven.daggermvp.ui.activity.main.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/27.
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.etUserName)
    EditText etUserName;
    @BindView(R.id.etPassWord)
    EditText etPassWord;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {

    }

    @OnClick(R.id.btnLogin)
    public void onViewClicked() {
        mPresenter.postLogin(etUserName.getText().toString(), etPassWord.getText().toString());
    }

    @Override
    public void goMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
