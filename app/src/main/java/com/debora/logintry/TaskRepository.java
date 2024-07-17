package com.debora.logintry;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private DatabaseReference databaseReference;

    public TaskRepository() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("tasks"); // Sesuaikan dengan path yang Anda gunakan di Firebase
    }

    public void addTask(Task task) {
        String taskId = databaseReference.push().getKey();
        task.setId(taskId);
        databaseReference.child(taskId).setValue(task);
    }

    public void updateTask(Task task) {
        databaseReference.child(task.getId()).setValue(task);
    }

    public void deleteTask(String taskId) {
        databaseReference.child(taskId).removeValue();
    }

    public void getTasks(final TaskDataListener listener) {
        databaseReference.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Task> taskList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Task task = snapshot.getValue(Task.class);
                    if (task != null) {
                        taskList.add(task);
                    }
                }
                listener.onTaskDataLoaded(taskList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onError(databaseError);
            }
        });
    }

    public interface TaskDataListener {
        void onTaskDataLoaded(List<Task> tasks);
        void onError(DatabaseError databaseError);
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference; // Metode untuk mengembalikan referensi database
    }
}
