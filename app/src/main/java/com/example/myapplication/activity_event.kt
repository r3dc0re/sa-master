@file:Suppress("ClassName")

package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.journeyapps.barcodescanner.camera.FitCenterStrategy
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_main_page.*
import kotlinx.android.synthetic.main.activity_my_event.*
import kotlinx.android.synthetic.main.list_layout.view.*

class activity_event : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)


        show_event()
    }

    private fun show_event(){



        FirebaseDatabase.getInstance().getReference("/event").addValueEventListener(object : ValueEventListener {
            var adapter = GroupAdapter<GroupieViewHolder>()

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    Log.d("text", "${it.key}")
                    val event = it.getValue(EVc::class.java)
                    if (event != null) {
                        adapter.add(Eitem(event))
                    }


                }


                event_list.adapter = adapter
                adapter=GroupAdapter<GroupieViewHolder>()
            }


            override fun onCancelled(p0: DatabaseError) {
                finish()
            }
        })
    }

}

class EventAuth(val uid:String) {
    constructor():this("")
}


class Eitem(val event:EVc): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.ev_name.text = event.event_name
        viewHolder.itemView.ev_date.text = event.event_date
        viewHolder.itemView.ev_m.text = "紅包$"+event.event_m.toString()
    }
    override fun getLayout(): Int {
        return R.layout.list_layout
    }
}

class EVc(val event_name:String, val event_date:String, val event_m:Int){
    constructor():this("","",0)
}


