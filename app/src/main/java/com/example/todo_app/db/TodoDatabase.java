package com.example.todo_app.db;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {TaskEntry.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class TodoDatabase extends RoomDatabase {

    private static final String LOG_TAG = TodoDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "db";
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(1);

    private static TodoDatabase instance;

    public static TodoDatabase getInstance(Context context){
        if(instance == null){
            synchronized (LOCK){
                Log.d(LOG_TAG, "Creating a new database instance");
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        TodoDatabase.class, TodoDatabase.DATABASE_NAME)
                        //.allowMainThreadQueries()
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return instance;
    }

    public abstract TaskDao taskDao();


}
