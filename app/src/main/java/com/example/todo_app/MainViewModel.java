package com.example.todo_app;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.todo_app.db.TodoDatabase;
import com.example.todo_app.db.Repository;
import com.example.todo_app.db.TaskEntry;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    Repository repository;

    private final LiveData<List<TaskEntry>> tasks;

    private final MutableLiveData<Boolean> _showSnackBarEvent = new MutableLiveData<>();

    public LiveData<Boolean> showSnackBarEvent() {
        return _showSnackBarEvent;
    }

    public void doneShowSnackBarEvent() {
        _showSnackBarEvent.setValue(false);
    }

    public MainViewModel(Application application) {
        super(application);
        TodoDatabase database = TodoDatabase.getInstance(application);
        repository = new Repository(database);
        tasks = repository.getTasks();
    }

    public LiveData<List<TaskEntry>> getTasks() {
        return tasks;
    }

    public void deleteTask(TaskEntry task) {
        repository.deleteTask(task);
        _showSnackBarEvent.setValue(true);
    }

    public void update(TaskEntry task) {
        repository.updateTask(task);
        _showSnackBarEvent.setValue(true);
    }
}