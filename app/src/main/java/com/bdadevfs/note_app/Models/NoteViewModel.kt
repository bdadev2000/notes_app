package com.bdadevfs.note_app.Models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bdadevfs.note_app.Database.NoteDatabase
import com.bdadevfs.note_app.Database.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : NoteRepository
    val allNotes : LiveData<List<Note>>
    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun insertNote(note: Note) = viewModelScope.launch (Dispatchers.IO){
        repository.insert(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch (Dispatchers.IO){
        repository.delete(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch (Dispatchers.IO){
        repository.update(note)
    }
}