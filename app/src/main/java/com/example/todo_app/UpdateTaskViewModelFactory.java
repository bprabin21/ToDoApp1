package com.example.todo_app;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class UpdateTaskViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    Application application;
    int taskId;

    public UpdateTaskViewModelFactory(Application application, int taskId){
        this.application = application;
        this.taskId = taskId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return  (T) new UpdateTaskViewModel(application, taskId);
    }
}