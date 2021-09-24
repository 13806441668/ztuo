package com.bizzan.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.bizzan.R;
import com.bizzan.app.Injection;
import com.bizzan.base.BaseTransFragmentActivity;
import com.bizzan.entity.Favorite;
import com.bizzan.ui.home.presenter.FourPresenter;
import com.bizzan.ui.home.presenter.MainPresenter;
import com.bizzan.utils.WonderfulCodeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OTCActivity extends BaseTransFragmentActivity implements MainContract.View {

    @BindView(R.id.flContainer)
    FrameLayout flContainer;

    private FourFragment fourFragment;

    private MainContract.Presenter presenter;


    public static void actionStart(Context context) {
        Intent intent = new Intent(context, OTCActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }


    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_otc;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        new MainPresenter(Injection.provideTasksRepository(getApplicationContext()), this);
        if (fragments.size() == 0) recoverFragment();
        new FourPresenter(Injection.provideTasksRepository(getApplicationContext()), fourFragment);

        selecte(0);
    }


    public void selecte(int page) {
        showFragment(fragments.get(page));
    }

    @Override
    protected void obtainData() {
    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {
        presenter.allCurrency();
    }

    @Override
    protected void initFragments() {
        if (fourFragment == null) fragments.add(fourFragment = new FourFragment());
    }

    @Override
    protected void recoverFragment() {
        fourFragment = (FourFragment) getSupportFragmentManager().findFragmentByTag(FourFragment.TAG);
        if (fourFragment == null) fragments.add(fourFragment = new FourFragment());
        else fragments.add(fourFragment);
    }

    @Override
    public int getContainerId() {
        return R.id.flContainer;
    }


    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void allCurrencySuccess(Object obj) {
    }


    @Override
    public void allCurrencyFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }


    @Override
    public void findSuccess(List<Favorite> obj) {
    }

    @Override
    public void findFail(Integer code, String toastMessage) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
