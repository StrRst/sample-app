package com.example.sampleapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp.R;
import com.example.sampleapp.adapter.CountryRecyclerAdapter;
import com.example.sampleapp.app.App;
import com.example.sampleapp.base.BaseFragment;
import com.example.sampleapp.contract.ChooserContract;
import com.example.sampleapp.listener.OnCountrySelectListener;
import com.example.sampleapp.listener.OnProgressStateChangeListener;
import com.example.sampleapp.model.CountryItem;
import com.example.sampleapp.util.KeyboardUtils;

import java.util.ArrayList;
import java.util.List;

public class ChooserFragment extends BaseFragment implements ChooserContract.View {

    private ChooserContract.Presenter presenter;

    private String searchString;

    private RecyclerView recyclerView;
    private AppCompatEditText countryInputField;
    private AppCompatButton searchButton;

    private List<CountryItem> items;
    private CountryRecyclerAdapter adapter;

    private OnCountrySelectListener countrySelectListener;
    private OnProgressStateChangeListener progressStateChangeListener;

    public ChooserFragment() {

    }

    @Override
    public void setPresenter(ChooserContract.Presenter presenter) {
        this.presenter = presenter;
    }

    // Regular setter is used because value of the field is not intended
    // to be preserved after fragment recreation
    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public void setCountrySelectListener(OnCountrySelectListener listener) {
        this.countrySelectListener = listener;
    }

    public void setProgressStateChangeListener(OnProgressStateChangeListener progressStateChangeListener) {
        this.progressStateChangeListener = progressStateChangeListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chooser, container, false);

        recyclerView = view.findViewById(R.id.country_list);
        countryInputField = view.findViewById(R.id.country_name_input_field);
        searchButton = view.findViewById(R.id.search_button);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        items = new ArrayList<>();

        adapter = new CountryRecyclerAdapter(items, position -> {
            CountryItem selected = adapter.getItems().get(position);

            if (countrySelectListener != null) {
                countrySelectListener.onCountrySelect(selected);
            }
        });

        recyclerView.setAdapter(adapter);

        searchButton.setOnClickListener(v -> handleSearchAction());

        countryInputField.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_GO) {
                handleSearchAction();
                return true;
            }
            return false;
        });

        presenter.takeView(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (searchString != null) {
            // Needs to be executed just once after non-null search string has been set
            presenter.handleRestoreSearchFromHistory(searchString);
            searchString = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

    private void handleSearchAction() {
        String input = countryInputField.getText().toString();

        presenter.handleInitiateSearch(input);
    }

    @Override
    public void showProgress() {
        if (progressStateChangeListener != null) {
            progressStateChangeListener.onProgressStateChanged(true);
        }
    }

    @Override
    public void hideProgress() {
        if (progressStateChangeListener != null) {
            progressStateChangeListener.onProgressStateChanged(false);
        }
    }

    @Override
    public void hideKeyboard() {
        KeyboardUtils.hide(countryInputField);
    }

    @Override
    public void setInputFieldText(String text) {
        countryInputField.setText(text);
    }

    @Override
    public void setFocusOnContent() {
        recyclerView.requestFocus();
    }

    @Override
    public void displayInputError() {
        //TODO: show something to user to indicate the error
        countryInputField.requestFocus();
    }

    @Override
    public void displayRequestError(@NonNull String message) {
        Toast.makeText(App.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void observeData(LiveData<List<CountryItem>> countryList) {
        countryList.observe(this, countryItems -> {
            items.clear();
            if (countryItems != null && !countryItems.isEmpty()) {
                items.addAll(countryItems);
            }
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void stopObserving(LiveData<List<CountryItem>> countryList) {
        countryList.removeObservers(this);
    }
}
