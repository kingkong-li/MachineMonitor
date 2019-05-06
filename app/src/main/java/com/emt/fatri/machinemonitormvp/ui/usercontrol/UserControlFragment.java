package com.emt.fatri.machinemonitormvp.ui.usercontrol;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emt.fatri.machinemonitormvp.R;

/**
 * description:
 * Created by kingkong on 2018/10/22 0022.
 * changed by kingkong on 2018/10/22 0022.
 */

public class UserControlFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_control, container, false);
        Toolbar toolbar = view.findViewById(R.id.fragment_toolbar);
        setHasOptionsMenu(true);
        if ((getActivity()) != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        return view;
    }
}
