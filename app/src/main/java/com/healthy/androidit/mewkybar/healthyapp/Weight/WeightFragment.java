package com.healthy.androidit.mewkybar.healthyapp.Weight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.healthy.androidit.mewkybar.healthyapp.R;

import java.util.ArrayList;

public class WeightFragment extends Fragment {

    ArrayList<Weight> weights = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        weights.add(new Weight("01 Jan 2018", 63, "UP"));
        weights.add(new Weight("08 Jul 2008", 70 , "UP"));
        weights.add(new Weight("09 Aug 2009", 80 ,"UP"));

        ListView _weightList = getView().findViewById(R.id.weight_list);
        WeightAdapter _weightAdapter = new WeightAdapter(
                getActivity(),
                R.layout.fragment_weight_item,
                weights
        );
        _weightList.setAdapter(_weightAdapter);


//        _weight.add("64");
//        _weight.add("80");
//        _weight.add("75");
//
//        ArrayAdapter<String> _weightAdapter = new ArrayAdapter<>(
//                getActivity(),
//                android.R.layout.simple_list_item_1,
//                _weight
//        );
//
//        ListView _weightList = (ListView) getView().findViewById(R.id.weight_list);
//        _weightList.setAdapter(_weightAdapter);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight
                , container, false);
    }
}
