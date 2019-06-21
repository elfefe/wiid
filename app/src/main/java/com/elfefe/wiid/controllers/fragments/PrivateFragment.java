package com.elfefe.wiid.controllers.fragments;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.elfefe.wiid.R;

public class PrivateFragment extends Fragment {

    private ImageButton imageButton;
    private TextView textView;

    public static PrivateFragment newInstance() {

        Bundle args = new Bundle();

        PrivateFragment fragment = new PrivateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_private, container, false);

        imageButton = (ImageButton) view.findViewById(R.id.fragment_private_sendImage);
        textView = (TextView) view.findViewById(R.id.fragment_private_sendText);

        textView.setText("Test texte");

        return view;
    }
}

