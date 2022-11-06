package com.example.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.task.ViewModel.NotesViewModel
import com.example.task.model.Note
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class AddNoteActivity : AppCompatActivity() {

    lateinit var addNote: MaterialButton
    lateinit var  noteTitle : TextInputEditText
    lateinit var noteText : TextInputEditText
    var currentDate:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        addNote = findViewById(R.id.addNote)
        noteTitle = findViewById(R.id.noteTitle)
        noteText = findViewById(R.id.noteText)

        var notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        addNote.setOnClickListener {
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm")

            currentDate = sdf.format(Date())
            val nTitle = noteTitle.text.toString()
            val nText = noteText.text.toString()

//        create a new note from the class
            val newNote : Note = Note(0,nTitle,nText, currentDate,"")
            notesViewModel.addNote(newNote)
            Toast.makeText(this,"Succsesfully added new Note",Toast.LENGTH_SHORT).show()
            finish()

        }


    }
}