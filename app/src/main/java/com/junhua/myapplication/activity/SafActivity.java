package com.junhua.myapplication.activity;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.junhua.storage.SafCallback;
import com.junhua.storage.SafOpenType;
import com.junhua.storage.SafParams;
import com.junhua.storage.SafExtensionKt;
import com.junhua.myapplication.databinding.ActivitySafBinding;
import com.junhua.myapplication.frame.BaseActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SafActivity extends BaseActivity<ActivitySafBinding> {

    public SafActivity() {
        super(ActivitySafBinding::inflate);
    }

    @Override
    public void initViews(@NonNull ActivitySafBinding binding) {
        SafParams safParams = new SafParams(Uri.EMPTY, 1, SafOpenType.ACTION_OPEN_DOCUMENT, false);
        binding.OpenSaf.setOnClickListener(v -> SafExtensionKt.openSafForCallback(this, safParams, new SafCallback()));
    }

    @Override
    public void initListener(@NonNull ActivitySafBinding $this$initListener) {
        
    }

    @Override
    public void initConfig(@NonNull ActivitySafBinding $this$initConfig) {

    }
}
