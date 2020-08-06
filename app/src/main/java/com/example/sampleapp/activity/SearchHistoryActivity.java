package com.example.sampleapp.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp.R;
import com.example.sampleapp.activity.base.BaseActivity;
import com.example.sampleapp.adapter.SearchHistoryRecyclerAdapter;
import com.example.sampleapp.model.HistoryItems;
import com.example.sampleapp.utils.Constants;
import com.example.sampleapp.utils.HistorySharedPrefsUtils;

public class SearchHistoryActivity extends BaseActivity {

    private RecyclerView recyclerView;

    private SearchHistoryRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_history);

        initToolbarWithBackButton(getString(R.string.search_history_activity_title));

        HistoryItems historyItems = HistorySharedPrefsUtils.retrieveHistoryItems(this);

        adapter = new SearchHistoryRecyclerAdapter(historyItems, position -> {
            String searchString = adapter.getItems().get(position);
            startMainActivity(searchString);
        });

        recyclerView = findViewById(R.id.search_history_list);
        recyclerView.setAdapter(adapter);
    }

    private void startMainActivity(String searchString) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Constants.SEARCH_STRING, searchString);
        startActivity(intent);
    }
}