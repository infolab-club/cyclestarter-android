package club.infolab.recyclingstarter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import club.infolab.recyclingstarter.registration.RegistrationCallback;
import club.infolab.recyclingstarter.retrofit.Collaborator;
import club.infolab.recyclingstarter.retrofit.RetrofitTransactions;

/**
 * Активити для входа или регистрации нового сотрудника офиса.
 */
public class LoginActivity extends AppCompatActivity implements RegistrationCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /* Инициализация активити. */
        initializeActivity();
    }

    /**
     * Метод инициализации необходимых компонентов.
     */
    private void initializeActivity() {
        /* Кнопка регистрации сотрудника. */
        Button buttonRegistration = findViewById(R.id.buttonRegister);
        buttonRegistration.setOnClickListener(onClickRegister);
    }

    private View.OnClickListener onClickRegister = v -> {
        /* Получаем данные о сотруднике. */
        Collaborator collaborator = getInputData();
        /* Отправляем данные на сервер. */
        postData(collaborator);
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
    private void postData(Collaborator collaborator) {
        RetrofitTransactions.getInstance().addCollaborator(collaborator, this);
        // TODO: remove
        onRegistrationResult(true);
    }

    /**
     * Метод перехода на активность с результатом регистрации сотрудника.
     * @param isSuccess успешна ли прошла регистрация
     */
    @Override
    public void onRegistrationResult(boolean isSuccess) {
        Intent intent = new Intent(this, UserActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
