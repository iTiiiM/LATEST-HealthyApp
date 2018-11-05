package com.healthy.androidit.mewkybar.healthyapp.Sleep;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Toast;

import com.healthy.androidit.mewkybar.healthyapp.R;


public class AddSleepFragment extends Fragment {
    SQLiteDatabase myDB;
    Sleep sleep;
    String status;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDB = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS sleep (id INTEGER PRIMARY KEY AUTOINCREMENT, date VARCHAR(10), toBedTime VARCHAR(5), awakeTime VARCHAR(5))");
        Bundle bundle = getArguments();
        try
        {
            sleep = (Sleep) bundle.getSerializable("sleep object");
            status = "edit";
        }
        catch (NullPointerException e)
        {
            if (sleep == null)
            {
                status = "new";
            }
            else
            {
                Log.d("test", "null pointer : " + e.getMessage());
            }
        }
        Log.d("test", "status : " + status);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_sleep, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(status.equals("edit"))
        {
            setValue();
        }
        initBackBtn();
        initAddBtn();
    }
    public void setValue()
    {
        EditText date = getView().findViewById(R.id.add_sleep_date);
        EditText toBedTime = getView().findViewById(R.id.add_sleep_toBedTime);
        EditText awakeTime = getView().findViewById(R.id.add_sleep_awakeTime);
        date.setText(sleep.getDate());
        toBedTime.setText(sleep.getToBedTime());
        awakeTime.setText(sleep.getAwakeTime());
        Button addButton = getView().findViewById(R.id.add_sleep_add_button);
        addButton.setText("edit");
    }

    public void initBackBtn(){
        Button backBtn = getView().findViewById(R.id.add_sleep_back_button);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
    }

    public void initAddBtn(){
        Button btn = getView().findViewById(R.id.add_sleep_add_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText date = getView().findViewById(R.id.add_sleep_date);
                EditText toBedTime = getView().findViewById(R.id.add_sleep_toBedTime);
                EditText awakeTime = getView().findViewById(R.id.add_sleep_awakeTime);
                String dateStr = date.getText().toString();
                String toBedTimeStr = toBedTime.getText().toString();
                String awakeTimeStr = awakeTime.getText().toString();

                ContentValues contentValues = new ContentValues();
                contentValues.put("date",dateStr);
                contentValues.put("toBedTime", toBedTimeStr);
                contentValues.put("awakeTime", awakeTimeStr);

                if (status.equals("new")){
                    myDB.insert("sleep", null, contentValues);
                }

                else if (status.equals("edit")){
                    myDB.update("sleep", contentValues, "id=" + sleep.getId(), null);
                }
                Toast.makeText(getContext(), "add new sleep", Toast.LENGTH_SHORT).show();
                getFragmentManager().popBackStack();

            }
        });
    }
}
