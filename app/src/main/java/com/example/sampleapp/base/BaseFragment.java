package com.example.sampleapp.base;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.sampleapp.R;

public abstract class BaseFragment extends Fragment {

    private Toolbar toolbar;

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
