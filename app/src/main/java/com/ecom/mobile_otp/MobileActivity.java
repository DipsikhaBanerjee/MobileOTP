package com.ecom.mobile_otp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class MobileActivity extends AppCompatActivity {

    EditText mobile;
    Button button;
    CountryCodePicker ccp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);

        mobile = findViewById(R.id.number);
        button = findViewById(R.id.cont);
        ccp = findViewById(R.id.ccp);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mobile.getText().toString().trim().isEmpty()) {
                    if ((mobile.getText().toString().trim()).length() == 10) {

                        PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + mobile.getText().toString(), 60, TimeUnit.SECONDS, MobileActivity.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                Toast.makeText(MobileActivity.this, "OTP Sent Successfully", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(MobileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String otp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                Intent intent = new Intent(getApplicationContext(), VerifyActivity.class);
                                intent.putExtra("mobile", mobile.getText().toString());
                                intent.putExtra("otp", otp);
                                startActivity(intent);
                            }
                        });

//                        Intent intent = new Intent(getApplicationContext(), VerifyActivity.class);
//                        intent.putExtra("mobile", mobile.getText().toString());
//                        startActivity(intent);
                    } else {
                        Toast.makeText(MobileActivity.this, "Please enter correct number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MobileActivity.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}