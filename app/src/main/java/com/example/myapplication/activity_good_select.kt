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
import kotlinx.android.synthetic.main.activity_my_event.*
import kotlinx.android.synthetic.main.list_layout.view.*

class activity_good_select : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_good_select)

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
                    Log.d("EventList",it.toString())
                    val good = it.getValue(EVg::class.java)
                    if(good!= null)
                    {
                        adapter.add(GItem(good))
                    }
                    val key = it.key

                    adapter.setOnItemClickListener{item, view ->
                        val gitem = item as GItem
                        val GN = "${gitem.good.new_good_name}"
                        val GP = "${gitem.good.new_good_price}".toInt()
                        Log.d("ID","$GN,$GP")

                        val ref = FirebaseDatabase.getInstance().getReference("/user/$uid/")
                        val upd = User(GN,GP).MapGood()

                        ref.updateChildren(upd).addOnSuccessListener {
                            Log.d("upd","$upd")
                            Toast.makeText(this@activity_good_select,"已選擇"+"$GN",Toast.LENGTH_SHORT).show()
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


class EVg(val new_good_name:String, val new_good_price:String){
    constructor():this("","")
}



class GItem(val good:EVg): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.ev_name.text = good.new_good_name
        viewHolder.itemView.ev_date.text = "$"+good.new_good_price
    }

    override fun getLayout(): Int {
        return R.layout.list_layout
    }
}

