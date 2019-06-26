package com.riven.daggermvp.ui.activity.main;

import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.riven.daggermvp.MyApp;
import com.riven.daggermvp.R;
import com.riven.daggermvp.base.BaseActivity;
import com.riven.daggermvp.ui.fragment.article.ArticleFragment;
import com.riven.daggermvp.ui.fragment.home.HomeFragment;
import com.riven.daggermvp.ui.fragment.mine.MineFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.rdHome)
    RadioButton rdHome;
    @BindView(R.id.rdArticle)
    RadioButton rdArticle;
    @BindView(R.id.rdMine)
    RadioButton rdMine;

    /** 记录点击返回时间 **/
    private long exitTime = 0;

    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private ArticleFragment articleFragment;
    private MineFragment mineFragment;
    //第一次进入默认为Home
    private int currentTabIndex = R.id.rdHome;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                showToast("再按一次，退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        radioGroup.setOnCheckedChangeListener(this);
        fragmentManager = getSupportFragmentManager();
        homeFragment = (HomeFragment) fragmentManager.findFragmentByTag(HomeFragment.class.getName());
        articleFragment = (ArticleFragment) fragmentManager.findFragmentByTag(ArticleFragment.class.getName());
        mineFragment = (MineFragment) fragmentManager.findFragmentByTag(MineFragment.class.getName());

        //第一次进入，初始化页面
        changeTabView(currentTabIndex);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        changeTabView(checkedId);
    }

    private void changeTabView(int checkedId) {
        currentTabIndex = checkedId;
        //无论点击哪个，先把所有的都隐藏
        hideAll();
        switch (checkedId) {
            case R.id.rdHome:
                rdHome.setChecked(true);
                if (homeFragment != null && homeFragment.isAdded()) {
                    fragmentManager.beginTransaction().show(homeFragment).commit();
                } else {
                    homeFragment = new HomeFragment();
                    fragmentManager.beginTransaction().add(R.id.frameLayout, homeFragment, HomeFragment.class.getName()).commit();
                }
                break;
            case R.id.rdArticle:
                rdArticle.setChecked(true);
                if (articleFragment != null && articleFragment.isAdded()) {
                    fragmentManager.beginTransaction().show(articleFragment).commit();
                } else {
                    articleFragment = new ArticleFragment();
                    fragmentManager.beginTransaction().add(R.id.frameLayout, articleFragment, ArticleFragment.class.getName()).commit();
                }
                break;
            case R.id.rdMine:
                rdMine.setChecked(true);
                if (mineFragment != null && mineFragment.isAdded()) {
                    fragmentManager.beginTransaction().show(mineFragment).commit();
                } else {
                    mineFragment = new MineFragment();
                    fragmentManager.beginTransaction().add(R.id.frameLayout, mineFragment, MineFragment.class.getName()).commit();
                }
                break;
            default:
                break;
        }
    }

    /**
     * fragment全部隐藏
     */
    public void hideAll() {
        if (homeFragment != null) {
            fragmentManager.beginTransaction().hide(homeFragment).commit();
        }
        if (articleFragment != null) {
            fragmentManager.beginTransaction().hide(articleFragment).commit();
        }
        if (mineFragment != null) {
            fragmentManager.beginTransaction().hide(mineFragment).commit();
        }
    }

}
