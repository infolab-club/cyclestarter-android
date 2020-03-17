package com.infolab.ecohack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.infolab.ecohack.retrofit.Collaborator;

/**
 * Активити с результатом процесса добавления сотрудника офиса.
 * @author Глеб Новиков
 */
public class ResultActivity extends AppCompatActivity {
    private boolean isSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initializeActivity();
    }

    /**
     * Метод инициализации необходимых компонентов.
     */
    private void initializeActivity() {
        /* Получение данных о результате добавления. */
        Intent intent = getIntent();
        isSuccess = intent.getBooleanExtra("result", false);
        showResult();

        /* Кнопка принятия результата. */
        Button buttonOk = findViewById(R.id.buttonOkResult);
        buttonOk.setOnClickListener(onClickOkResult);
    }

    private View.OnClickListener onClickOkResult = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            goToVolunteer();
        }
    };

    /**
     * Метод отображения результата добавления сотрудника офиса.
     */
    private void showResult() {
        TextView textHeader = findViewById(R.id.textHeaderResult);
        ImageView imageResult = findViewById(R.id.imageResult);

        if (isSuccess) {
            /* Успешное добавление. */
            textHeader.setText(R.string.success);
            imageResult.setImageResource(R.drawable.ic_success);
        }
        else {
            /* Неудачное добавление. */
            textHeader.setText(R.string.fail);
            imageResult.setImageResource(R.drawable.ic_error);
        }
    }

    /**
     * Метод возврата на главную активити для волонтёра.
     */
    private void goToVolunteer() {
        Intent intent = new Intent(this, VolunteerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
