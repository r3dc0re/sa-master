package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_my_event.*
import kotlinx.android.synthetic.main.activity_my_good.*
import kotlinx.android.synthetic.main.activity_new_good.view.*
import kotlinx.android.synthetic.main.list_layout.view.*

class my_good : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_good)

        new_good.setOnClickListener {
            val intent = Intent(this, activity_new_good::class.java)
            startActivity(intent)
        }

        show_good()
    }


    private fun show_good(){
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid

        FirebaseDatabase.getInstance().getReference("/user/$uid/good").addValueEventListener(object :
            ValueEventListener {
            var adapter = GroupAdapter<GroupieViewHolder>()

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach{
                    Log.d("GoodList",it.toString())
                    val good = it.getValue(GD::class.java)
                    if(good!= null)
                    {
                        adapter.add(UserGood(good))
                    }

                    adapter.setOnItemLongClickListener{item, view ->
                        val gooditem = item as UserGood
                        val good_id = "${gooditem.good.goodid}"
                        showPopUp(view,good_id.toString())
                        true
                    }
                }



                good_list.adapter = adapter
                adapter= GroupAdapter<GroupieViewHolder>()
            }

            override fun onCancelled(p0: DatabaseError) {
                finish()
            }

        })
    }

    fun showPopUp(view: View,good_id:String) {
        val popupMenu = PopupMenu(this, view)
        val inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.popup_menu, popupMenu.menu)
        popupMenu.show()
        popupMenu.menu.findItem(R.id.item2).setVisible(false)
        popupMenu.menu.findItem(R.id.item3).setVisible(false)
        popupMenu.menu.findItem(R.id.item4).setVisible(false)

        popupMenu.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.item1 -> {

                    Log.d("delete","delete")
                    val uid = FirebaseAuth.getInstance().uid?:""
                    val ref = FirebaseDatabase.getInstance().getReference("/user/$uid/good/$good_id")
//                    val ref2 = FirebaseDatabase.getInstance().getReference("/good/$good_id")
                    ref.removeValue()
//                    ref2.removeValue()
                    Log.d("key","$good_id")
                    Toast.makeText(this, "已刪除", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }
}

class GD(val new_good_name:String, val new_good_price: String, val goodid:String){
    constructor():this("","","")
}



class UserGood(val good:GD): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.ev_name.text = good.new_good_name
        viewHolder.itemView.ev_date.text = "$"+ good.new_good_price
        viewHolder.itemView.evid.text = good.goodid
    }

    override fun getLayout(): Int {
        return R.layout.list_layout
    }
}