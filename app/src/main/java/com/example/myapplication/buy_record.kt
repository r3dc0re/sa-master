package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*

class buy_record : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout
            .activity_buy_record)
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        var array = ArrayList<String>()

        FirebaseDatabase.getInstance().getReference("/user/${uid}/record").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataSnapshot.children.forEach {
                    Log.d("text","${it.key}")
                    val type=it.value

                    FirebaseDatabase.getInstance().getReference("/record/${it.key}").addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {

                            Log.d("data","${dataSnapshot}")
                            val data = dataSnapshot.getValue(record::class.java)
                            Log.d("data","${data}")

                            if (type=="get")
                            {
                                if(data?.states=="cash")
                                {
                                    Log.d("data1","${data?.money}")
                                    array.add("從${data?.payname}"+"   "+"獲得${data?.money}元紅包")

                                }
                                else{
                                    Log.d("data2","${data?.money}")
                                    array.add("販賣${data?.good_name}給${data?.payname}獲得${data?.money}元")
                                }
                            }
                            else
                            {
                                if(data?.states=="cash")
                                {
                                    Log.d("data3","${data?.money}")
                                    array.add("付給${data?.getname}"+"    "+"${data?.money}元紅包")
                                }
                                else{
                                    Log.d("data4","${data?.money}")
                                    array.add("購買${data?.good_name}付給${data?.getname}${data?.money}元")
                                }
                            }

                        val adapter = ArrayAdapter(this@buy_record,
                    R.layout.list_item, array)

                    val listView:ListView = findViewById(R.id.list)
                    listView.setAdapter(adapter)
                }



                        override fun onCancelled(databaseError: DatabaseError) {}

                    })
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}

        })

    }
}
