package com.bizzan.ui.common;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.bizzan.R;
import com.bizzan.base.BaseActivity;
import com.bizzan.serivce.MyTextService;
import com.bizzan.serivce.MyTextService_contract;
import com.bizzan.ui.home.MainActivity;
import com.bizzan.ui.lock.LockActivity;
import com.bizzan.utils.WonderfulLogUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.List;

public class StartActivity extends BaseActivity {

    //    int n = 3;
    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {

        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {


        }
    };
    int duration = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).fullScreen(true).init();
        ImmersionBar.with(this).fitsSystemWindows(true);
        startService(new Intent(StartActivity.this, MyTextService.class));
        startService(new Intent(StartActivity.this, MyTextService_contract.class));
        checkPermission(100);
        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int memoryClass = mActivityManager.getMemoryClass();
        int largeMemoryClass = mActivityManager.getLargeMemoryClass();
        WonderfulLogUtils.logi("miao", "多大" + memoryClass + "--" + largeMemoryClass);
    }

    private void checkPermission(int requestCode) {
        AndPermission.with(this).requestCode(requestCode).permission(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.REQUEST_INSTALL_PACKAGES).callback(listener).start();
    }


    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_start2;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {
        if (!isNeedShowLockActivity()) {
            MainActivity.actionStart(StartActivity.this);
            finish();

        }
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LockActivity.RETURN_LOCK) {
            MainActivity.actionStart(StartActivity.this);
        }
    }


}
