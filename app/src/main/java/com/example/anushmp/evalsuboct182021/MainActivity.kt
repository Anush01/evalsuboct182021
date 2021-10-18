package com.example.anushmp.evalsuboct182021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity(), EventClickLister {

    lateinit var searchbutton: MaterialButton
    lateinit var seeallbutton: MaterialButton
    lateinit var addevent: MaterialButton
    lateinit var rv: RecyclerView
    lateinit var dbh: DatabaseHelper
    lateinit var eventlist:MutableList<EventModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchbutton = findViewById(R.id.gotosearch)
        seeallbutton = findViewById(R.id.seeallevents)
        addevent = findViewById(R.id.addevent)
        rv = findViewById(R.id.rv)

        dbh = DatabaseHelper(this)

        buildlisthere()

        eventlist = dbh.getAllEvents()

        val eva = EventViewAdapter(this,eventlist,this)

        val llm = LinearLayoutManager(this)

        rv.adapter = eva
        rv.layoutManager = llm


        rv.visibility = View.GONE

        seeallbutton.setOnClickListener {

            rv.visibility = View.VISIBLE

        }

    }


    fun buildlisthere(){

        for(i in 1..20){

            dbh.insertevent("demoEvent"+i,"demo event","1.1.0000","Mars",500)

        }

    }

    override fun EditClick(Event: EventModel) {

        Toast.makeText(this,"editing",Toast.LENGTH_SHORT).show()



    }

    override fun DeleteClick(Event: EventModel) {
        Toast.makeText(this,"Deleting",Toast.LENGTH_SHORT).show()
    }
}