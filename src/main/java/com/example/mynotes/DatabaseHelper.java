package com.example.mynotes;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

@Database(entities=Note.class,exportSchema = false,version = 1)
public  abstract class DatabaseHelper extends RoomDatabase {

    private static final String DB_NAME="notes_db";
    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getInstance(MainActivity context){
        if(instance==null){
            instance=Room.databaseBuilder(context.getApplicationContext(),
                    DatabaseHelper.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract NoteDao noteDao();
}
