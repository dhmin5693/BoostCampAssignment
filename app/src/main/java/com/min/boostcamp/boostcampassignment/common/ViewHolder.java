package com.min.boostcamp.boostcampassignment.common;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ViewHolder<BindingType extends ViewDataBinding> extends RecyclerView.ViewHolder {

    // 앱 내에서 공통으로 사용할 ViewHolder 클래스
    private BindingType binding;

    public ViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public BindingType getBinding() {
        return binding;
    }
}