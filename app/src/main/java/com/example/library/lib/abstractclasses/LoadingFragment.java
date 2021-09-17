package com.example.library.lib.abstractclasses;

import static com.example.library.lib.utils.Constants.KEY_MESSAGE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.library.R;


public class LoadingFragment extends BaseDialogFragment {

    public LoadingFragment() {
        // Required empty public constructor
    }

    public static LoadingFragment newInstance(String message) {

        Bundle args = new Bundle();
        args.putString(KEY_MESSAGE, message);
        LoadingFragment fragment = new LoadingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loading, container, false);
    }

    TextView msgTV;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCancelable(false);

        msgTV = view.findViewById(R.id.msgTV);
        msgTV.setText(getArguments().getString(KEY_MESSAGE));
    }

    public void updateMessage(String msg) {
        msgTV.setText(msg);
    }
}
