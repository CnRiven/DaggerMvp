package com.riven.daggermvp.ui.activity.login;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.riven.daggermvp.R;
import com.riven.daggermvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/27.
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.llBack)
    LinearLayout llBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
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
        llBack.setVisibility(View.GONE);
        tvTitle.setText("登录");
    }

    @OnClick({R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                mPresenter.postLogin(etUserName.getText().toString(), etPassWord.getText().toString());
                break;
        }

    }
}
