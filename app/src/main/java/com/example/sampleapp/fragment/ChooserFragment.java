package com.example.sampleapp.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp.R;
import com.example.sampleapp.adapter.CountryRecyclerAdapter;
import com.example.sampleapp.api.ApiCallback;
import com.example.sampleapp.api.RestClient;
import com.example.sampleapp.app.App;
import com.example.sampleapp.base.BaseFragment;
import com.example.sampleapp.database.AppDatabase;
import com.example.sampleapp.listener.OnCountrySelectListener;
import com.example.sampleapp.model.CountryErrorItem;
import com.example.sampleapp.model.CountryItem;
import com.example.sampleapp.model.HistoryItems;
import com.example.sampleapp.utils.HistorySharedPrefsUtils;
import com.example.sampleapp.utils.KeyboardUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class ChooserFragment extends BaseFragment {

    private static final String TAG = ChooserFragment.class.getSimpleName();

    private RecyclerView recyclerView;
    private AppCompatEditText countryInputField;
    private AppCompatButton searchButton;
    private View loaderBlock;

    private List<CountryItem> items;
    private CountryRecyclerAdapter adapter;
    private OnCountrySelectListener listener;

    private HistoryItems historyItems;

    public ChooserFragment() {

    }

    public void setCountrySelectListener(OnCountrySelectListener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chooser, container, false);

        recyclerView = view.findViewById(R.id.country_list);
        countryInputField = view.findViewById(R.id.country_name_input_field);
        searchButton = view.findViewById(R.id.search_button);
        loaderBlock = view.findViewById(R.id.loader_block);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        items = new ArrayList<>();

        adapter = new CountryRecyclerAdapter(items, position -> {
            CountryItem selected = null;
            try {
                selected = adapter.getItems().get(position);
            } catch (IndexOutOfBoundsException e) {
                Log.e(TAG, "Retrieving country info failed", e);
            }
            if (selected == null) {
                return;
            }

            if (listener != null) {
                listener.onCountrySelect(selected);
            }
        });

        recyclerView.setAdapter(adapter);

        historyItems = HistorySharedPrefsUtils.retrieveHistoryItems(App.getInstance());

        searchButton.setOnClickListener(v -> handleSearchAction());

        countryInputField.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_GO) {
                handleSearchAction();
                return true;
            }
            return false;
        });

        //TODO: attach observer outside fragment/activity to remove lifecycle dependency
        getDatabase().countryItemDao().getAll().observe(this, countryItems -> {
            items.clear();
            if (countryItems != null && !countryItems.isEmpty()) {
                items.addAll(countryItems);
            }
            adapter.notifyDataSetChanged();
        });
    }

    private void handleSearchAction() {
        String input = countryInputField.getText().toString();

        if (TextUtils.isEmpty(input)) {
            countryInputField.requestFocus();
            return;
        }

        KeyboardUtils.hide(countryInputField);
        recyclerView.requestFocus();

        historyItems.addToStart(input);
        HistorySharedPrefsUtils.saveHistoryItems(App.getInstance(), historyItems);

        searchCountries(input);
    }

    public void handleExternalSearchRequest(String searchString) {
        if (TextUtils.isEmpty(searchString)) {
            countryInputField.requestFocus();
            return;
        }

        recyclerView.requestFocus();

        clearList();

        countryInputField.setText(searchString);
        searchCountries(searchString);
    }

    public void searchCountries(String countryName) {
        showProgressBlock();
        RestClient.getInstance().getService().getCountries(countryName)
                .enqueue(new ApiCallback<List<CountryItem>>() {
                    @Override
                    public void success(@NotNull Response<List<CountryItem>> response) {
                        replaceList(response.body());
                        hideProgressBlock();
                    }

                    @Override
                    public void failure(CountryErrorItem countryError) {
                        String errorMessage = countryError.getMessage();
                        Log.e(TAG, errorMessage);
                        showErrorToast(errorMessage);

                        hideProgressBlock();
                    }
                });
    }

    private void clearList() {
        getDatabase().countryItemDao().deleteAll();
    }

    private void replaceList(List<CountryItem> newList) {
        getDatabase().countryItemDao().deleteAll();
        getDatabase().countryItemDao().insert(newList);
    }

    private void showErrorToast(String errorMessage) {
        Toast.makeText(App.getInstance(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    private void showProgressBlock() {
        if (loaderBlock != null) {
            loaderBlock.setVisibility(View.VISIBLE);
        }
    }

    private void hideProgressBlock() {
        if (loaderBlock != null) {
            loaderBlock.setVisibility(View.GONE);
        }
    }

    private AppDatabase getDatabase() {
        return App.getInstance().getDatabase();
    }
}
