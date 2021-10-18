package com.example.anushmp.evalsuboct182021

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DatabaseHelper(val context:Context): SQLiteOpenHelper(context,"event_db",null,1) {

    companion object{

        val table_name = "eventinfo"

    }

    override fun onCreate(db: SQLiteDatabase?) {

        var tablecreationquery:String = "create table $table_name(" +
                "id integer primary key, event_name text, event_desc text, event_date text" +
                ",event_location text, event_price integer)"

        db?.execSQL(tablecreationquery)

    }

    fun insertevent(
        name:String,
    desc:String,
    date:String,
    location:String,
    price:Int){

        val db = writableDatabase

        val cv:ContentValues = ContentValues()

        cv.put("event_name",name)
        cv.put("event_desc",desc)
        cv.put("event_date",date)
        cv.put("event_location",location)
        cv.put("event_price",price)


        val response = db.insert(table_name,null,cv)


        if(response.toInt() == -1){

            Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show()


        }else{

           // Toast.makeText(context,"event added", Toast.LENGTH_SHORT).show()

        }


    }


    fun updateEvent(id:Int,
    fieldtoupdate:String,
    stringData:String,
    intdata:Int){

        if(intdata>0){}

        if(stringData.equals("none")){}else{}



        val db = writableDatabase

        val cv = ContentValues()

        if(fieldtoupdate.equals("event_name")){
            cv.put("event_name",stringData)
        }

        if(fieldtoupdate.equals("event_desc")){
            cv.put("event_desc",stringData)
        }

        if(fieldtoupdate.equals("event_date")){
            cv.put("event_date",stringData)
        }

        if(fieldtoupdate.equals("event_location")){
            cv.put("event_location",stringData)
        }

        if(fieldtoupdate.equals("event_price")){
            cv.put("event_price",intdata)
        }


        val affectedrows = db.update(table_name,cv,"id=$id",null)



        if(affectedrows>0){
            Toast.makeText(context,"updated successfully",Toast.LENGTH_SHORT).show()
        }


    }

    fun deleteEvent(id:Int){

        val db = writableDatabase

        val response = db.delete(table_name,"id=$id",null)


        if(response>0){

            Toast.makeText(context,"deleted event",Toast.LENGTH_SHORT).show()

        }




    }

    fun getAllEvents(): MutableList<EventModel>{

        var responselist:MutableList<EventModel> = mutableListOf<EventModel>()


        var rdb = readableDatabase

        var getAllQuery = "select * from $table_name"

        val response:Cursor = rdb.rawQuery(getAllQuery,null)




        if(response!=null && response.count>0){

            response.moveToFirst()

            val id = response.getColumnIndex("id")
            val event_name = response.getColumnIndex("event_name")
            val event_desc = response.getColumnIndex("event_desc")
            val event_date = response.getColumnIndex("event_date")
            val event_location = response.getColumnIndex("event_location")
            val event_price = response.getColumnIndex("event_price")


            do {


                val idval = response.getInt(id)
                val eventnameval = response.getString(event_name)
                val eventdescval = response.getString(event_desc)
                val eventdateval = response.getString(event_date)
                val eventlocationval = response.getString(event_location)
                val eventpriceval = response.getInt(event_price)

                val eventobj :EventModel = EventModel(idval,eventnameval
                ,eventdescval,
                eventdateval,
                eventlocationval,
                eventpriceval)

                responselist.add(eventobj)

            }while (response.moveToNext())

        }



        return responselist


    }




    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}