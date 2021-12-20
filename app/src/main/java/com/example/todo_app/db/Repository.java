package com.example.todo_app.db;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {

    TaskDao dao;

    public Repository(TodoDatabase todoDatabase){
        dao = todoDatabase.taskDao();
    }

    public LiveData<List<TaskEntry>> getTasks(){
        return dao.loadAllTasks();
    }

    public LiveData<TaskEntry> getTaskById(int taskId){
        return dao.loadTAskById(taskId);
    }

    public void updateTask(final TaskEntry task){
        TodoDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.update(task);
            }
        });
    }

    public void deleteTask(final TaskEntry task){
        TodoDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.deleteTask(task);
            }
        });
    }

    public  void  insertTask(final TaskEntry task){
        TodoDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insertTask(task);
            }
        });
    }
}