package com.example.jialian.bottomnavigationbardemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.jialian.bottomnavigationbardemo.observable.BadgeItemObservable;
import com.example.jialian.bottomnavigationbardemo.observable.EventBadgeItem;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment {


    private View mLayout;

    public ItemFragment() {
        // Required empty public constructor
    }


    public static ItemFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title",title);
        ItemFragment fragment = new ItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mLayout = inflater.inflate(R.layout.fragment_item, container, false);
        Button titleView = (Button) mLayout.findViewById(R.id.tv_title);
        final String title = this.getArguments().getString("title");
        titleView.setText("f:"+title);
        titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBadgeItem.getInstance().post("f:"+title);
            }
        });
        return mLayout;
    }

}
