package com.infolab.ecohack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.infolab.ecohack.retrofit.ApiService;
import com.infolab.ecohack.retrofit.Collaborator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Активити добавления нового сотрудника офиса.
 * @author Глеб Новиков
 */
public class RegistrationActivity extends AppCompatActivity {
    /** Ссылка на сервер. */
    private static final String BASE_URL = "https://daniilor.pythonanywhere.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        /* Инициализация активити. */
        initializeActivity();
    }

    /**
     * Метод инициализации необходимых компонентов.
     */
    private void initializeActivity() {
        /* Кнопка добавления сотрудника. */
        Button buttonRegistration = findViewById(R.id.buttonRegister);
        buttonRegistration.setOnClickListener(onClickRegister);
    }

    private View.OnClickListener onClickRegister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Получаем данные о сотруднике. */
            Collaborator collaborator = getInputData();
            /* Отправляем данные на сервер. */
            postData(collaborator);
        }
    };

    /**
     * Метод получения данных о новом сотруднике офиса.
     * @return сотрудник
     */
    private Collaborator getInputData() {
        /* ФИО. */
        EditText inputFullName = findViewById(R.id.inputFullName);
        String fullName = inputFullName.getText().toString();
        /* E-mail. */
        EditText inputEmail = findViewById(R.id.inputEmail);
        String email = inputEmail.getText().toString();
        /* Номер телефона. */
        EditText inputPhoneNumber = findViewById(R.id.inputPhoneNumber);
        String phoneNumber = inputPhoneNumber.getText().toString();
        /* Адресс корпуса. */
        EditText inputAddress = findViewById(R.id.inputAddress);
        String address = inputAddress.getText().toString();
        /* Номер офиса. */
        EditText inputOffice = findViewById(R.id.inputOffice);
        String office = inputOffice.getText().toString();

        return new Collaborator(fullName, email, phoneNumber, address, office);
    }

    /**
     * Метод отправки данных о новом сотруднике на сервер.
     * @param collaborator сотрудник офиса
     */
    private void postData(Collaborator collaborator){
        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);
        Call<Collaborator> call = service.addCollaborator(collaborator);

        call.enqueue(new Callback<Collaborator>() {
            /* Метод, вызываемый при успешной отправки данных. */
            @Override
            public void onResponse(Call<Collaborator> call, Response<Collaborator> response) {
                goToResult(true);
            }
            /* Метод, вызываемый при неудачной отправки данных. */
            @Override
            public void onFailure(Call<Collaborator> call, Throwable t) {
                Log.d("RETROFIT_ERROR", t.getMessage());
                goToResult(false);
            }
        });
    }

    /**
     * Метод перехода на активность с результатом регистрации сотрудника.
     * @param isSuccess успешна ли прошла регистрация
     */
    private void goToResult(boolean isSuccess) {
        Intent intent = new Intent(this, ResultActivity.class);
        /* Отправка данных с результатом добавления. */
        intent.putExtra("result", isSuccess);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
