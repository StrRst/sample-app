package com.example.sampleapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp.R;
import com.example.sampleapp.activity.MainActivity;
import com.example.sampleapp.adapter.SearchHistoryRecyclerAdapter;
import com.example.sampleapp.base.BaseFragment;
import com.example.sampleapp.model.HistoryItems;
import com.example.sampleapp.utils.Constants;
import com.example.sampleapp.utils.HistorySharedPrefsUtils;

public class SearchHistoryContainerFragment extends BaseFragment {

    private RecyclerView recyclerView;

    private SearchHistoryRecyclerAdapter adapter;

    public SearchHistoryContainerFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_history_container, container, false);

        recyclerView = view.findViewById(R.id.search_history_list);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initToolbarWithBackButton(view, getString(R.string.search_history_activity_title));

        HistoryItems historyItems = HistorySharedPrefsUtils.retrieveHistoryItems(getContext());

        adapter = new SearchHistoryRecyclerAdapter(historyItems, position -> {
            String searchString = adapter.getItems().get(position);
            startMainActivity(searchString);
        });

        recyclerView.setAdapter(adapter);
    }

    private void startMainActivity(String searchString) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra(Constants.SEARCH_STRING, searchString);
        startActivity(intent);
    }
}
