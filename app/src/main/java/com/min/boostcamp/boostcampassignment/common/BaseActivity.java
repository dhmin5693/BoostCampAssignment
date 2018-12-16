package com.min.boostcamp.boostcampassignment.common;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public abstract class BaseActivity<BindingType extends ViewDataBinding> extends AppCompatActivity {

    protected final String TAG = "LOG/" + getClass().getSimpleName();
    private BindingType binding;

    /**
     * layout ID를 반환하여 xml을 화면에 띄움(R.id.layoutId)
     * @return R.id.layoutId
     */
    protected abstract int getLayoutId();

    protected BindingType getBinding() {
        return binding;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        binding = DataBindingUtil.setContentView(this, getLayoutId());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
