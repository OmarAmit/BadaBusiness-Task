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

class UpdateNoteActivity : AppCompatActivity() {

    lateinit var noteTitle: TextInputEditText
    lateinit var noteText: TextInputEditText
    lateinit var updateButton: MaterialButton
    lateinit var deleteButton: MaterialButton
    var currentDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_note)

        noteTitle = findViewById(R.id.noteTitle)
        noteText = findViewById(R.id.noteText)
        updateButton = findViewById(R.id.updateNote)
        deleteButton = findViewById(R.id.deleteNote)

        val notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)


        var note: Note = intent.getSerializableExtra("note") as Note


        noteTitle.setText(note.noteTitle)
        noteText.setText(note.noteText)


        updateButton.setOnClickListener {
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm")

            currentDate = sdf.format(Date())
            note.noteTitle = noteTitle.text.toString()
            note.noteText = noteText.text.toString()
            note.noteEdit = "edited"
            note.dateTime=currentDate


            notesViewModel.updateNote(note)
            Toast.makeText(this, "Updated Note Successfully", Toast.LENGTH_SHORT).show()
            finish()

        }

        deleteButton.setOnClickListener {
            notesViewModel.deleteNote(note)
            Toast.makeText(this, "Deleted Note successfully", Toast.LENGTH_SHORT).show()
            finish()
        }


    }
}