package com.bdadevfs.note_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bdadevfs.note_app.Models.Note
import com.bdadevfs.note_app.databinding.ActivityAddNoteBinding
import com.bdadevfs.note_app.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding:  ActivityAddNoteBinding
    private lateinit var note: Note
    private lateinit var oldNote : Note
    var isUpdate = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try{
            oldNote = intent.getSerializableExtra("current_note")as Note
            binding.edtTitle.setText(oldNote.title)
            binding.edtNote.setText(oldNote.note)
            isUpdate=true
        }catch (e:Exception){
            e.printStackTrace()
        }
        binding.imageViewDone.setOnClickListener {
            val title = binding.edtTitle.text.toString()
            val note_desc = binding.edtNote.text.toString()
            if (title.isNotEmpty() || note_desc.isNotEmpty()){
                val formatter = SimpleDateFormat("EEE, d MMM yyyy HH:mm a")
                if(isUpdate){
                    note = Note(
                        oldNote.id,title,note_desc,formatter.format(Date())
                    )
                }else{
                    note = Note(
                        null,title,note_desc,formatter.format(Date())
                    )
                }
                val intent = Intent()
                intent.putExtra("note",note)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }else{
                Toast.makeText(this@AddNoteActivity,"Please enter some data",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

        binding.imageViewBack.setOnClickListener {
            onBackPressed()
        }
    }
}