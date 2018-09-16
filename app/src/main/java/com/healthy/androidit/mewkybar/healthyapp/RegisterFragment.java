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
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterFragment extends Fragment {
    private  FirebaseAuth mAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button _registerBtn = getView().findViewById(R.id.register_register_btn);
        _registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText _email = getView().findViewById(R.id.register_email);
                EditText _password = getView().findViewById(R.id.register_password);
                EditText _rePassword = getView().findViewById(R.id.register_repassword);

                String _emailStr = _email.getText().toString();
                String _passwordStr = _password.getText().toString();
                String _rePasswordStr = _rePassword.getText().toString();


                if (_emailStr.isEmpty()) {
                    Toast.makeText(
                            getActivity(), "กรุณาระบุข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT
                    ).show();
                    Log.d("Register", "FIELD NAME IS EMPTY");
                } else if (_emailStr.equals("admin")) {
                    Toast.makeText(
                            getActivity(), "User นี้มีอยู่ในระบบแล้ว", Toast.LENGTH_SHORT
                    ).show();
                    Log.d("Register", "USER ALREADY EXIST");
                } else {
                    Log.d("Register", "GO TO BMI");
                    mAuth.createUserWithEmailAndPassword(_emailStr, _passwordStr).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Log.d("Register", "Complete");
                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.main_view, new LoginFragment());
                        }
                    });
                }
            }
        });
    }
}
