package com.yinli.yinli_ylscrollview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yinli.ylscrollview.YLScrollView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Sample2Fragment extends Fragment {


    public Sample2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_sample2, container, false);

        YLScrollView ylScrollView = (YLScrollView) rootView.findViewById(R.id.sampleScrollView);
        ylScrollView.setTextColor(getResources().getColor(R.color.custom_color));
        ylScrollView.setText("Scroll Me");
        ylScrollView.setTextSize(16f);

        return rootView;
    }


}
