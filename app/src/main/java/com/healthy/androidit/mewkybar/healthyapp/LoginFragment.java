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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {




    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button _loginBtn = getView().findViewById(R.id.login_login_btn);
        TextView _registerBtn = getView().findViewById(R.id.login_register_btn);

        _registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new RegisterFragment())
                        .commit();
                Log.d("User", "GO TO REGISTER");
            }
        });

        _loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText _userId = (EditText) getView().findViewById(R.id.login_user_id);
                EditText _password = (EditText) getView().findViewById(R.id.login_password);
                String _userIdStr = _userId.getText().toString();
                String _passwordStr = _password.getText().toString();



                if(_userIdStr.isEmpty() || _passwordStr.isEmpty()){
                    Toast.makeText(
                            getActivity(), "กรุณาระบุ User หรือ Password", Toast.LENGTH_SHORT
                    ).show();
                    Log.d("User", "USER OR PASSWORD IS EMPTY");
                } else if (_userIdStr.equals("admin") && _passwordStr.equals("admin")){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new MenuFragment())
                            .commit();
                    Log.d("User", "GO TO BMI");
                } else {
                    Toast.makeText(
                            getActivity(), "User หรือ Password ไม่ถูกต้อง", Toast.LENGTH_SHORT
                    ).show();
                    Log.d("User", "INVALID USER OR PASSWORD");
                }
            }
        });
    }
}
