package com.example.todo_app;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todo_app.db.TodoDatabase;
import com.example.todo_app.db.Repository;
import com.example.todo_app.db.TaskEntry;

public class UpdateTaskViewModel extends AndroidViewModel {

    Repository repository;
    LiveData<TaskEntry> task;

    UpdateTaskViewModel(Application application, int taskId){
        super(application);
        TodoDatabase database = TodoDatabase.getInstance(application);
        repository = new Repository(database);
        if(taskId != -1)
            task = repository.getTaskById(taskId);
    }


    public LiveData<TaskEntry> getTask(){
        return task;
    }

    public void insertTask(TaskEntry task){
        repository.insertTask(task);
    }

    public void updateTask(TaskEntry task){
        repository.insertTask(task);
    }


}