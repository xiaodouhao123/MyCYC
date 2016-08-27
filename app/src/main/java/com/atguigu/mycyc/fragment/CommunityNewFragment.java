package com.atguigu.mycyc.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.mycyc.R;

/**
 * Created by 徐达
 * on 2016/8/27 on 11:51.
 * 作用:
 */
public class CommunityNewFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.fragment_community_new,null);
        return view;

    }
}
