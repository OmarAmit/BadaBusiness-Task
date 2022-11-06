package com.example.task.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.task.Dao.NotesDao
import com.example.task.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNotesDao() : NotesDao

    companion object{
        @Volatile
        private var INSTANCE : NoteDatabase? = null


        fun getDatabase(context: Context):NoteDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "notes_database").build()

                INSTANCE = instance
                return instance
            }

        }
    }

}