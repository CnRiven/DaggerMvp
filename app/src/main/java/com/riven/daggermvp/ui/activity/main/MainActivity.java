package com.riven.daggermvp.ui.activity.main;

import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.riven.daggermvp.R;
import com.riven.daggermvp.base.BaseActivity;
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
    @BindView(R.id.rdMine)
    RadioButton rdMine;

    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private MineFragment mineFragment;
    //第一次进入默认为Home
    private int currentTablIndex = R.id.rdHome;

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
        mineFragment = (MineFragment) fragmentManager.findFragmentByTag(MineFragment.class.getName());

        //第一次进入，初始化页面
        changeTabView(currentTablIndex);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        changeTabView(checkedId);
    }

    private void changeTabView(int checkedId) {
        currentTablIndex = checkedId;
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
        if (mineFragment != null) {
            fragmentManager.beginTransaction().hide(mineFragment).commit();
        }
    }

}
