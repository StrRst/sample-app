package com.example.sampleapp.activity.base;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sampleapp.R;

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    public void initToolbar(String title) {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        initToolbarMenu();
    }

    public void initToolbarWithNavigation(String title) {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        initToolbarMenu();
    }

    private void initToolbarMenu() {
        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_show_activity_name:
                        showToast(getString(R.string.help_button_toast) +
                                BaseActivity.this.getClass().getSimpleName());
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    public void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }
}