package com.elfefe.wiid.controllers.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.elfefe.wiid.R;
import com.elfefe.wiid.utility.ConnectionManager;
import com.elfefe.wiid.utility.PreferenceUtils;
import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.User;

public class CommunityFragment extends Fragment {

    public static Fragment newInstance(){
        return new CommunityFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) container.findViewById(R.id.fragment_community_recyclerView);


        return inflater.inflate(R.layout.fragment_community, container, false);
    }
}