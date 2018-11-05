package com.healthy.androidit.mewkybar.healthyapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import com.healthy.androidit.mewkybar.healthyapp.Sleep.SleepFragment;
import com.healthy.androidit.mewkybar.healthyapp.Weight.WeightFragment;

import java.util.ArrayList;

public class MenuFragment extends Fragment {
    FirebaseAuth mAuth;
    ArrayList<String> _menu = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _menu.add("BMI");
        _menu.add("Weight");
        _menu.add("Sleep");
        _menu.add("Logout");



        ArrayAdapter<String> _menuAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                _menu
        );

        ListView _menuList = (ListView) getView().findViewById(R.id.menu_list);
        _menuList.setAdapter(_menuAdapter);
        _menuList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("MENU", "Click on menu =" + _menu.get(i));
                if (_menu.get(i) == "BMI"){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction().replace(R.id.main_view, new BMIFragment())
                            .commit();
                }
                else if(_menu.get(i) == "Weight"){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction().replace(R.id.main_view, new WeightFragment())
                            .commit();
                }else if(_menu.get(i) == "Logout"){
                    mAuth = FirebaseAuth.getInstance();
                    mAuth.signOut();

                    getActivity().getSupportFragmentManager()
                            .beginTransaction().replace(R.id.main_view, new LoginFragment())
                            .commit();
                }else if(_menu.get(i) == "Sleep"){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction().replace(R.id.main_view, new SleepFragment())
                            .commit();
                }
            }
        });


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }
}
