package com.infolab.ecohack;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;

import com.infolab.ecohack.retrofit.RetrofitTransactions;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class VolunteerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        ButterKnife.bind(this);
    }



    @OnClick(R.id.btnCallServices)
    public void onClickCallService() {
        RetrofitTransactions.getInstance().fillBox(1, this);
    }

    @OnClick(R.id.btnReady)
    public void onClickBoxCleared() {
        RetrofitTransactions.getInstance().clearBox(1, this);
    }
}
