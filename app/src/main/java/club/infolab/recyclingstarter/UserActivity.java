package club.infolab.recyclingstarter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import club.infolab.recyclingstarter.retrofit.RetrofitTransactions;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        /* Инициализация активити. */
        initializeActivity();
    }

    /**
     * Метод инициализации необходимых компонентов.
     */
    private void initializeActivity() {
        /* Кнопка для очистки контейнера. */
        Button buttonClear = findViewById(R.id.buttonClearBox);
        buttonClear.setOnClickListener(onClickClear);
        /* Кнопка для подтверждения очистки контейнера. */
        Button buttonDone = findViewById(R.id.buttonDoneBox);
        buttonDone.setOnClickListener(onClickDone);
    }

    private View.OnClickListener onClickClear = view ->
            RetrofitTransactions.getInstance().fillBox(1, this);

    private View.OnClickListener onClickDone = view ->
            RetrofitTransactions.getInstance().clearBox(1, this);
}
