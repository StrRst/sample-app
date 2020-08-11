package com.example.sampleapp.base;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.sampleapp.R;

public abstract class BaseFragment extends Fragment {

    protected boolean inLandscapeMode;

    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inLandscapeMode = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void initToolbar(View container, String title) {
        toolbar = container.findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle(title);
        }
    }

    public void initToolbarWithBackButton(View container, String title) {
        toolbar = container.findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle(title);
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
            toolbar.setNavigationOnClickListener(view -> {
                getActivity().onBackPressed();
            });
        }
    }
}
