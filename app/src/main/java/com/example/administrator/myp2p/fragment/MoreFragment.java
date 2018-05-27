package com.example.administrator.myp2p.fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myp2p.R;

import static com.example.administrator.myp2p.util.UIUtils.getXmlView;

public class MoreFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getXmlView(R.layout.fragment_more);
        return view;
    }
}
