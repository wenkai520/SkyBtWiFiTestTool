package com.tianci.btwifitesttool.ui;

import android.app.Activity;
import android.os.Bundle;

import com.tianci.btwifitesttool.Control;
import com.tianci.btwifitesttool.R;
import com.tianci.btwifitesttool.ui.base.BaseActivity;
import com.tianci.btwifitesttool.utils.L;

/**
 * MainActivity class
 *
 * @author pis
 * @date 2018/7/12
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        L.d("MainActivity onCreate");
        Control.getInstance().startDefaultActivity();
        finish();
    }
}
