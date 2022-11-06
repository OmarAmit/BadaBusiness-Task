package com.example.task.ViewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.task.database.NoteDatabase
import com.example.task.model.Note
import com.example.task.repository.NotesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var allNotes:LiveData<List<Note>>
    lateinit var notesRepo: NotesRepo
    lateinit var searchNotes : LiveData<List<Note>>

    init {

        val notesDao = NoteDatabase.getDatabase(application).getNotesDao()
        notesRepo = NotesRepo(notesDao)
        allNotes = notesRepo.allNotes
    }



    fun addNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            notesRepo.addNote(note)
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch(Dispatchers.IO){
            notesRepo.updateNote(note)
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            notesRepo.deleteNote(note)
        }
    }

    fun getSearchResult(query:String) : LiveData<List<Note>> {
       return notesRepo.searchNotes(query).asLiveData()

    }




}