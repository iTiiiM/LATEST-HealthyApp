package com.healthy.androidit.mewkybar.healthyapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class    BMIFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bmi, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button _calculateBtn = getView().findViewById(R.id.bmi_calculate_btn);
        _calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText _weight = getView().findViewById(R.id.bmi_weight);
                EditText _height = getView().findViewById(R.id.bmi_height);
                TextView _bmiLabel = getView().findViewById(R.id.bmi_bmi_label);
                TextView _bmiResult = getView().findViewById(R.id.bmi_result);
                String _weightStr = _weight.getText().toString();
                String _heightStr = _height.getText().toString();

                if (_weightStr.isEmpty() || _heightStr.isEmpty()){
                    Toast.makeText(
                            getActivity(), "กรุณาระบุข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT
                    ).show();
                    Log.d("BMI", "FIELD NAME IS EMPTY");
                } else {
                    Double _bmi = Double.parseDouble(_weightStr)/Math.pow((Float.parseFloat(_heightStr))/100, 2);
                    String _bmiStr = String.format("%.2f", _bmi);
                    _bmiLabel.setText("Your BMI");
                    _bmiResult.setText(_bmiStr);
                    Log.d("BMI", "BMI IS VALUE");
                }
            }
        });
    }
}