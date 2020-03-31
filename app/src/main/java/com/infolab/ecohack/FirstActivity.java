package com.infolab.ecohack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Стартовая активити для волонтёра.
 * @author Глеб Новиков
 */
public class FirstActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        /* Инициализация активити. */
        initializeActivity();
    }

    /**
     * Метод инициализации необходимых компонентов.
     */
    private void initializeActivity() {
        /* Кнопка добавления сотрудника. */
        Button buttonRegistration = findViewById(R.id.buttonCollaboratorRegistration);
        buttonRegistration.setOnClickListener(onClickCollaboratorRegistration);
    }

    private View.OnClickListener onClickCollaboratorRegistration = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Переход на активити добавления сотрудника. */
            Intent intent = new Intent(FirstActivity.this,
                    RegistrationActivity.class);
            startActivity(intent);
        }
    };


}
