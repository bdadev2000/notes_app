package com.bdadevfs.note_app.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bdadevfs.note_app.Models.Note
import com.bdadevfs.note_app.utilities.DATABASE_NAME

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase(){

    /** class khác kế thừa NoteDatabase sẽ được impliment abstract fun getNoteDao **/
    abstract fun getNoteDao() : NoteDao

    companion object{
        @Volatile
        private var INSTANCE : NoteDatabase? = null
        fun  getDatabase(context: Context) : NoteDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    DATABASE_NAME,
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }


}