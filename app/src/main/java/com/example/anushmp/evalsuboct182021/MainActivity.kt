package com.example.anushmp.evalsuboct182021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), EventClickLister {

    lateinit var searchbutton: MaterialButton
    lateinit var seeallbutton: MaterialButton
    lateinit var addevent: MaterialButton
    lateinit var refreshlist: MaterialButton
    lateinit var rv: RecyclerView
    lateinit var dbh: DatabaseHelper
    lateinit var eventlist:MutableList<EventModel>
    lateinit var eva:EventViewAdapter
    lateinit var llm:LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchbutton = findViewById(R.id.gotosearch)
        seeallbutton = findViewById(R.id.seeallevents)
        addevent = findViewById(R.id.addevent)
        refreshlist = findViewById(R.id.refreshlist)
        rv = findViewById(R.id.rv)

        dbh = DatabaseHelper(this)

        buildlisthere()

        CoroutineScope(Default).launch {

            val eventlistco = dbh.getAllEvents()

            launch(Main) {

                eventlist = eventlistco
              eva = EventViewAdapter(this@MainActivity,eventlist,this@MainActivity)
                llm = LinearLayoutManager(this@MainActivity)

                rv.adapter = eva
                rv.layoutManager = llm

            }

        }

        //eventlist = dbh.getAllEvents()


       // val eva = EventViewAdapter(this,eventlist,this)

        //val llm = LinearLayoutManager(this)

        //rv.adapter = eva
        //rv.layoutManager = llm


        rv.visibility = View.GONE

        seeallbutton.setOnClickListener {

            rv.visibility = View.VISIBLE

        }


        val updated: String? = intent.getStringExtra("updated")

        if(updated.equals("yes")){

            rv.adapter?.notifyDataSetChanged()

        }

        addevent.setOnClickListener {

            var i = Intent(this,RegisterEvent::class.java)
            startActivity(i)

        }


        refreshlist.setOnClickListener {


            eventlist = dbh.getAllEvents()

            eva = EventViewAdapter(this,eventlist,this)
            llm = LinearLayoutManager(this)

            rv.adapter = eva
            rv.layoutManager = llm

            eva.notifyDataSetChanged()


        }



    }


    fun buildlisthere(){

        for(i in 1..2){

            dbh.insertevent("demoEvent"+i,"demo event","1.1.0000","Mars",500)

        }

    }

    override fun EditClick(Event: EventModel) {

        Toast.makeText(this,"editing",Toast.LENGTH_SHORT).show()







    }

    override fun DeleteClick(Event: EventModel) {
        Toast.makeText(this,"Deleting",Toast.LENGTH_SHORT).show()

        dbh.deleteEvent(Event.id)
    }
}