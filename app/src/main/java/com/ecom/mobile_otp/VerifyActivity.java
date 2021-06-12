package com.ecom.mobile_otp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyActivity extends AppCompatActivity {


    TextView textView;
    EditText in1, in2, in3, in4, in5, in6;
    Button verify;
    TextView resend;
    String get_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        textView = findViewById(R.id.mobile_number);
        in1 = findViewById(R.id.input1);
        in2 = findViewById(R.id.input2);
        in3 = findViewById(R.id.input3);
        in4 = findViewById(R.id.input4);
        in5 = findViewById(R.id.input5);
        in6 = findViewById(R.id.input6);
        verify = findViewById(R.id.verify);
        resend = findViewById(R.id.resend);

        textView.setText(String.format("+91-%s", getIntent().getStringExtra("mobile")
        ));
        get_otp = getIntent().getStringExtra("otp");


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!in1.getText().toString().trim().isEmpty() && !in2.getText().toString().trim().isEmpty() && !in3.getText().toString().trim().isEmpty() && !in4.getText().toString().trim().isEmpty() && !in5.getText().toString().trim().isEmpty() && !in6.getText().toString().trim().isEmpty()) {
                    String code_otp = in1.getText().toString() + in2.getText().toString() + in3.getText().toString() +
                            in4.getText().toString() + in5.getText().toString() + in6.getText().toString();
                    if (get_otp != null) {
                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                get_otp, code_otp);

                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(VerifyActivity.this, "Enter the Correct OTP", Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });

                    } else {
                        Toast.makeText(VerifyActivity.this, "Please check Internet Connection", Toast.LENGTH_SHORT).show();
                    }


//                    Toast.makeText(VerifyActivity.this, "OTP Verified", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(VerifyActivity.this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });

        otp();

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + getIntent().getStringExtra("mobile"), 60, TimeUnit.SECONDS, VerifyActivity.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        Toast.makeText(VerifyActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(VerifyActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String new_otp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        get_otp = new_otp;
                    }
                });

            }
        });
    }

    private void otp() {
        in1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    in2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        in2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    in3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        in3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    in4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        in4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    in5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        in5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    in6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}