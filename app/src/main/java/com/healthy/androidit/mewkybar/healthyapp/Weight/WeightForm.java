package com.healthy.androidit.mewkybar.healthyapp.Weight;



import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.healthy.androidit.mewkybar.healthyapp.R;

import java.util.ArrayList;
import java.util.Calendar;

public class WeightForm extends Fragment {
    ArrayList<Weight> weightStores = new ArrayList();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight_form, container, false);

    }
    TextView dateField, weight;
    Calendar mCurrentDate;
    String dayStr, monthStr;
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    String uid, dateStr, weightStr;
    int mYear, mMonth, mDay;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        dateField = (TextView) getView().findViewById(R.id.date_for_weight_page);
        weight = (TextView) getView().findViewById(R.id.weight_for_weight_page);
        mCurrentDate = Calendar.getInstance();
        mYear = mCurrentDate.get(Calendar.YEAR);
        mMonth = mCurrentDate.get(Calendar.MONTH);
        mDay = mCurrentDate.get(Calendar.DAY_OF_MONTH);





        super.onActivityCreated(savedInstanceState);

        dateField.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                datePickerPopup(dateField);
            }
        });
        Button saveWeightButton = (Button) getView().findViewById(R.id.save_weight_form);
        uid = currentFirebaseUser.getUid().toString();

        saveWeightButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dateStr = dateField.getText().toString();
                weightStr = weight.getText().toString();

                Log.d("WeightForm", weight + "" + dateStr);

                if(dateStr.isEmpty() || weightStr.isEmpty()){
                    Toast.makeText(getActivity(), "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
                }else{
                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    final Weight weightStore = new Weight(Float.parseFloat(weight.getText().toString()), dateField.getText().toString(),  "-");
                    db.collection("myfitness")
                            .document(uid)
                            .collection("weight")
                            .document(dateField.getText().toString())
                            .set(weightStore)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getActivity(), "เพิ่มข้อมูล", Toast.LENGTH_LONG).show();
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFragment()).commit();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });

    }



    private void datePickerPopup(final TextView field){

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                if(month < 10){
                    monthStr = "0" + (month + 1);
                }else{
                    monthStr = (month + 1) + "";
                }

                if(day < 10){
                    dayStr = "0" + day;

                }else{
                    dayStr = day +"";
                }

                mYear = year;
                mMonth = month;
                mDay = day;
                field.setText(year + "-" + monthStr + "-" + dayStr);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }


}
