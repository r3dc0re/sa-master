package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_event_redcash.*
import kotlinx.android.synthetic.main.activity_my_event.*
import kotlinx.android.synthetic.main.activity_my_event.event_list
import kotlinx.android.synthetic.main.activity_new_event.*
import kotlinx.android.synthetic.main.list_layout.view.*

class activity_event_redcash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_redcash)
        show_event()
    }


    private fun show_event(){
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid

        FirebaseDatabase.getInstance().getReference("/user/$uid/event").addValueEventListener(object :
            ValueEventListener {
            var adapter = GroupAdapter<GroupieViewHolder>()

            override fun onDataChange(p2: DataSnapshot) {
                var refx ="0"
                p2.children.forEach{
                    Log.d("EventList",it.toString())
                    val event = it.getValue(EVr::class.java)
                    if(event!= null)
                    {
                        adapter.add(RItem(event))
                    }
                    val key = it.key
                    adapter.setOnItemClickListener{item, view ->
                        val ritem = item as RItem
                        val RC = "${ritem.event.event_m}".toInt()
                        val RN = "${ritem.event.event_name}"
                        Log.d("ID","$RC")

                        val ref = FirebaseDatabase.getInstance().getReference("/user/$uid")
                        val upd = User(RC).MapCash()

                        ref.updateChildren(upd).addOnSuccessListener {
                            Log.d("upd","$upd")
                            Toast.makeText(this@activity_event_redcash,"已選擇"+"$RN",Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                event_list.adapter = adapter
                adapter= GroupAdapter<GroupieViewHolder>()
            }

            override fun onCancelled(p0: DatabaseError) {
                finish()
            }

        })
    }


}


class EVr(val event_name:String, val event_date:String, val event_m:Int){
    constructor():this("","", 0)
}


class RItem(val event:EVr): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.ev_name.text = event.event_name
        viewHolder.itemView.ev_date.text = event.event_date
        viewHolder.itemView.ev_m.text = "紅包$"+event.event_m.toString()
    }

    override fun getLayout(): Int {
        return R.layout.list_layout
    }
}






