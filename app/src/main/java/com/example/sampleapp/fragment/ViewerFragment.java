package com.example.sampleapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.example.sampleapp.R;

public class ViewerFragment extends Fragment {

    private AppCompatTextView laptopInfo;

    public ViewerFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewer, container, false);
        laptopInfo = view.findViewById(R.id.laptop_info);
        return view;
    }

    public void displayLaptopInfo(String info) {
        laptopInfo.setText(info);
    }
}
