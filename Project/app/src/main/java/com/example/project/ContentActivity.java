package com.example.project;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.drawerlayout.widget.DrawerLayout;


public class ContentActivity extends Activity implements AdapterView.OnItemClickListener, DrawerLayout.DrawerListener, View.OnClickListener {
    public static final int LEVEL_MENU = 0;
    public static final int LEVEL_BACK = 1;
    private Fragment fragment;
    private int mPosition;
    private DrawerLayout mDrawer;
    private ListView lvMenu;
    private ImageView ivMenu;
    private TitleAdapter titleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
        Intent intent = getIntent();
        mPosition = intent.getIntExtra(MyActivity.KEY_POSITION, 0);
        initView();
    }

    private void initView() {
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //viewPager = (ViewPager) findViewById(R.id.view_pager);
        lvMenu = (ListView) findViewById(R.id.lv_menu);
        ivMenu = (ImageView) findViewById(R.id.iv_menu);

        titleAdapter = new TitleAdapter(this);
        lvMenu.setAdapter(titleAdapter);

        lvMenu.setOnItemClickListener(this);
        mDrawer.setDrawerListener(this);
        ivMenu.setOnClickListener(this);

        displayView(mPosition);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (mPosition == position) {
            mDrawer.closeDrawers();
        } else {
            mPosition = position;
            displayView(mPosition);
        }

        mDrawer.closeDrawers();
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    private void displayView(int position) {
        fragment = new ContentFragment(position);

        getFragmentManager().beginTransaction().replace(R.id.frame_content, fragment).commit();

    }

    @Override
    public void onDrawerSlide(View view, float v) {

    }

    @Override
    public void onDrawerOpened(View view) {
        ivMenu.setImageLevel(LEVEL_BACK);
    }

    @Override
    public void onDrawerClosed(View view) {
        ivMenu.setImageLevel(LEVEL_MENU);
    }

    @Override
    public void onDrawerStateChanged(int i) {

    }


    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_menu:
                if (mDrawer.isDrawerOpen(Gravity.START)) {
                    mDrawer.closeDrawers();
                    ivMenu.setImageLevel(LEVEL_MENU);
                } else {
                    mDrawer.openDrawer(Gravity.START);
                    ivMenu.setImageLevel(LEVEL_BACK);
                }
                break;
            default:
                break;
        }
    }
}
