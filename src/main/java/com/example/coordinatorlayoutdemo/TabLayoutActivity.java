package com.example.coordinatorlayoutdemo;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * CoordinatorLayout 嵌套TabLayout的使用
 */
public class TabLayoutActivity extends AppCompatActivity {

    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    /**
     * 标题列表
     */
    private List<String> mTitles = new ArrayList<>();

    /**
     * Fragment列表
     */
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        ButterKnife.bind(this);
        toolbar.setTitle("TabLayout");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initData();

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initData() {

        mTitles.add("新闻");
        mTitles.add("段子");
        mTitles.add("图片");

        mFragments.add(DemoFragment.newInstance());
        mFragments.add(DemoFragment.newInstance());
        mFragments.add(DemoFragment.newInstance());

        tabs.addTab(tabs.newTab().setText(mTitles.get(0)));
        tabs.addTab(tabs.newTab().setText(mTitles.get(1)));
        tabs.addTab(tabs.newTab().setText(mTitles.get(2)));

        //设置TabLayout的模式
//        tabs.setTabMode(TabLayout.MODE_FIXED);

        viewPager.setAdapter(new ViewpagerAdapter(getSupportFragmentManager()));
        tabs.setupWithViewPager(viewPager);
        tabs.setTabsFromPagerAdapter(new ViewpagerAdapter(getSupportFragmentManager()));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    class ViewpagerAdapter extends FragmentPagerAdapter {

        public ViewpagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        //此方法用来显示tab上的名字，此方法必须重写，否则将无法显示tab上的名字
        @Override
        public CharSequence getPageTitle(int position) {

            return mTitles.get(position % mTitles.size());
        }
    }

}
