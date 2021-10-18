package com.example.anushmp.evalsuboct182021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.button.MaterialButton

class RegisterEvent : AppCompatActivity() {

    lateinit var dbh:DatabaseHelper
    lateinit var etname:EditText
    lateinit var etdesc:EditText
    lateinit var etdate:EditText
    lateinit var etlocation:EditText
    lateinit var etprice:EditText
    lateinit var mainact:MaterialButton
    lateinit var register:MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_event)

        etname = findViewById(R.id.eventNameEnter)
        etdesc = findViewById(R.id.descEnter)
        etdate = findViewById(R.id.dateEnter)
        etlocation = findViewById(R.id.locationEnter)
        etprice = findViewById(R.id.priceEnter)
        mainact = findViewById(R.id.mainactlink)
        register = findViewById(R.id.register)


        dbh = DatabaseHelper(this)


        register.setOnClickListener {

            dbh.insertevent(etname.text.toString(),
            etdesc.text.toString(),
            etdate.text.toString(),
            etlocation.text.toString(),
            etprice.text.toString().toInt())

            Toast.makeText(this,"Event Registered",Toast.LENGTH_SHORT).show()

        }

        mainact.setOnClickListener {

            val i = Intent(this,MainActivity::class.java)
            i.putExtra("updated","yes")
            startActivity(i)

        }





    }
}