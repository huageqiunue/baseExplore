package com.junhua.myapplication.activity;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.storage.SafCallback;
import com.example.storage.SafExtensionKt;
import com.example.storage.SafOpenType;
import com.example.storage.SafParams;
import com.junhua.myapplication.databinding.ActivitySafBinding;
import com.junhua.myapplication.frame.BaseActivity;

public class SafActivity extends BaseActivity<ActivitySafBinding> {

    public SafActivity() {
        super(ActivitySafBinding::inflate);
    }

    @Override
    public void initViews(@NonNull ActivitySafBinding binding) {
        SafParams safParams = new SafParams(Uri.EMPTY, 1, SafOpenType.ACTION_OPEN_DOCUMENT, false);
        binding.OpenSaf.setOnClickListener(v -> SafExtensionKt.openSafForCallback(this, safParams, new SafCallback()));
    }
}
