package com.riven.daggermvp.ui.activity.web;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebView;
import com.riven.daggermvp.R;
import com.riven.daggermvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:
 * Author:
 * Date:
 */

public class WebActivity extends BaseActivity<WebPresenter> implements WebContract.View {

    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    private AgentWeb mAgentWeb;

    @Override
    protected int getLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        Bundle bundle = getIntent().getBundleExtra("bundle");
        String title = bundle.getString("title");
        String link = bundle.getString("link");
        tvTitle.setText(title);

        if (!TextUtils.isEmpty(link)){
            mAgentWeb = AgentWeb.with(this)
                    .setAgentWebParent(linearLayout, new LinearLayout.LayoutParams(-1, -1))
                    .useDefaultIndicator()
                    .createAgentWeb()
                    .ready()
                    .go(link);
        }

    }

    @OnClick(R.id.llBack)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁AgentWeb
        mAgentWeb.getWebLifeCycle().onDestroy();
    }
}
