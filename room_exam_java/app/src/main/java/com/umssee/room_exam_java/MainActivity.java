package com.umssee.room_exam_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mTodoEditText;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTodoEditText = findViewById(R.id.todo_edit);
        mResultTextView = findViewById(R.id.result_text);

        // Database 는 Background에서 작동되어야 함. 임시로 메인쓰레드에서 동작하게 하는 allowMainThreadQueries()
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "todo-db")
                .allowMainThreadQueries()
                .build();
        mResultTextView.setText(db.todoDao().getAll().toString());

        findViewById(R.id.add_button).setOnClickListener(view -> {
            db.todoDao().insert(new Todo(mTodoEditText.getText().toString()));
            mResultTextView.setText(db.todoDao().getAll().toString());
        });
    }
}