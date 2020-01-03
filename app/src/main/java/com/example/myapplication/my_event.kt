package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_my_event.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.list_layout.view.*
import java.util.ArrayList
import javax.xml.validation.ValidatorHandler

class my_event : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_event)

        new_event.setOnClickListener {
            val intent = Intent(this, activity_new_event::class.java)
            startActivity(intent)
        }

        show_event()
    }


    private fun show_event(){
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid

        FirebaseDatabase.getInstance().getReference("/user/$uid/event").addValueEventListener(object : ValueEventListener {
            var adapter = GroupAdapter<GroupieViewHolder>()

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach{
                    Log.d("EventList",it.toString())
                    val event = it.getValue(EV::class.java)
                    if(event!= null)
                    {
                        adapter.add(UserItem(event))
                    }

                    
                    adapter.setOnItemLongClickListener{item, view ->
                        val kitem = item as UserItem
                        val KN = "${kitem.event.event_name}"
                        val KD = "${kitem.event.event_date}"
                        val KM = "${kitem.event.event_m}"
                        val event_id = "${kitem.event.eventid}"
                        showPopUp(view,event_id,KN, KD, KM)
                        true
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


    fun showPopUp(view: View,event_id:String,KN:String,KD:String,KM:String) {
        val popupMenu = PopupMenu(this, view)
        val inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.popup_menu, popupMenu.menu)
        popupMenu.show()
        popupMenu.menu.findItem(R.id.item3).setVisible(false)
        popupMenu.menu.findItem(R.id.item4).setVisible(false)

        popupMenu.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.item2 -> {
                    val uid = FirebaseAuth.getInstance().uid?:""
                    val ref2 = FirebaseDatabase.getInstance().getReference("/event/$event_id")
                    val ref = FirebaseDatabase.getInstance().getReference("/user/${uid}/event/$event_id")
                    val upd = User(KN+"(已結束)",KD,KM.toInt(),event_id).Map2()

                    ref.updateChildren(upd)
                    ref2.removeValue()
                    Toast.makeText(this, "結束活動", Toast.LENGTH_SHORT).show();
                }
                R.id.item1 -> {

                    Log.d("delete","delete")
                    val uid = FirebaseAuth.getInstance().uid?:""
                    val ref = FirebaseDatabase.getInstance().getReference("/user/${uid}/event/$event_id")
                    val ref2 = FirebaseDatabase.getInstance().getReference("/event/$event_id")
                    ref.removeValue()
                    ref2.removeValue()
                    Toast.makeText(this, "已刪除", Toast.LENGTH_SHORT).show();
                }
            }
            true
        }
    }


}


class EV(val event_name:String, val event_date:String, val event_m:Int, val eventid:String){
    constructor():this("","", 0,"")
}



class UserItem(val event:EV): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.ev_name.text = event.event_name
        viewHolder.itemView.ev_date.text = event.event_date
        viewHolder.itemView.ev_m.text = "紅包$"+event.event_m.toString()
        viewHolder.itemView.evid.text = event.eventid
    }

    override fun getLayout(): Int {
        return R.layout.list_layout
    }
}





