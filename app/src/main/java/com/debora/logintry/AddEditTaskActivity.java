package com.debora.logintry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddEditTaskActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.todo.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.example.todo.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.todo.EXTRA_DESCRIPTION";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private TaskRepository taskRepository;
    private String taskId; // Declare taskId as a class-level variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        taskRepository = new TaskRepository();

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Task");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            taskId = intent.getStringExtra(EXTRA_ID); // Set taskId from intent extra
        } else {
            setTitle("Add Task");
            taskId = null; // Initialize taskId if it's a new task
        }

        Button buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });
    }

    private void saveTask() {
        String title = editTextTitle.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);

        if (taskId != null) {
            // Update existing task
            Task task = new Task(taskId, title, description);
            taskRepository.updateTask(task);
        } else {
            // Add new task
            taskId = taskRepository.getDatabaseReference().push().getKey();
            Task task = new Task(taskId, title, description);
            taskRepository.addTask(task);
        }

        setResult(RESULT_OK, data);
        finish();

        // Navigasi ke halaman TaskItemActivity untuk menampilkan detail tugas
        Intent intent = new Intent(AddEditTaskActivity.this, CrudActivity.class);
        intent.putExtra(EXTRA_ID, taskId);  // Kirim ID tugas untuk menampilkan detailnya jika diperlukan
        startActivity(intent);
    }
}
