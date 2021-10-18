package com.example.anushmp.evalsuboct182021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.android.material.button.MaterialButton

class UpdateEventActivity : AppCompatActivity() {

    lateinit var updatefield:EditText
    lateinit var updatedata:EditText
    lateinit var mainact:MaterialButton
    lateinit var updatebutton:MaterialButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_event)


        updatefield = findViewById(R.id.etupdatefield)
        updatedata = findViewById(R.id.etupdatedata)

        mainact = findViewById(R.id.mainfromupdatelink)
        updatebutton = findViewById(R.id.updatebutton)

      //  val obj = intent.extras

        val id:Int = intent.getIntExtra("id",0)



    }
}