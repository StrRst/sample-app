package com.example.sampleapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.sampleapp.R;
import com.example.sampleapp.listener.LaptopSelectListener;
import com.example.sampleapp.model.Laptop;
import com.example.sampleapp.utils.Constants;

import java.util.List;

public class ChooserFragment extends Fragment {

    private List<Laptop> laptopList;

    private LinearLayout buttonsBlock;

    private LaptopSelectListener laptopSelectListener;

    public ChooserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chooser, container, false);

        buttonsBlock = view.findViewById(R.id.buttons_block);

        Bundle bundle = getArguments();
        if (bundle == null) {
            return view;
        }
        laptopList = bundle.getParcelableArrayList(Constants.LAPTOP_LIST_FRAGMENT_PARAMETER);
        if (laptopList == null) {
            return view;
        }

        for (int index = 0; index < laptopList.size(); index++) {
            AppCompatButton button = (AppCompatButton) inflater.inflate(R.layout.button, buttonsBlock, false);
            button.setText(laptopList.get(index).getName());
            final int selectedIndex = index;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (laptopSelectListener != null) {
                        laptopSelectListener.onLaptopSelect(selectedIndex);
                    }
                }
            });
            buttonsBlock.addView(button);
        }

        return view;
    }

    public void setLaptopSelectListener(LaptopSelectListener laptopSelectListener) {
        this.laptopSelectListener = laptopSelectListener;
    }
}