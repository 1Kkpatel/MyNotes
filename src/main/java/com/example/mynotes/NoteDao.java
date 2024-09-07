package com.example.mynotes;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("select * from note")
    List<Note> getNotes();

    @Insert
    void addNote(Note note);

    @Delete
    void deleteNote(Note note);

}
