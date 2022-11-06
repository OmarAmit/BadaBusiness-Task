package com.example.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.task.ViewModel.NotesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var floatingActionButton: FloatingActionButton
    lateinit var notesRecyclerView: RecyclerView
    lateinit var notesViewModel: NotesViewModel
    lateinit var notesAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        floatingActionButton = findViewById(R.id.addNote)
        notesRecyclerView = findViewById(R.id.myNotesList)

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }

        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)


        notesAdapter = NoteAdapter(this)

        notesRecyclerView.adapter = notesAdapter
        notesRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


        notesViewModel.allNotes.observe(this, Observer { notes ->
            notesAdapter.setData(notes)
        })

    }
}