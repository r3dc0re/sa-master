package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_new_event.*
import kotlinx.android.synthetic.main.activity_set_goods_money.*
import kotlinx.android.synthetic.main.activity_set_goods_money.submit

class activity_new_event : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_event)



        submit.setOnClickListener {
            val event_name = event_name.text.toString()
            var event_date = event_date.text.toString()
            val event_m = event_m.text.toString()
            event_date = event_date.replace("/","-")

            if(event_name.isEmpty() || event_date.isEmpty() || event_m.isEmpty())
            {
                Toast.makeText(this,"有格子未填寫", Toast.LENGTH_SHORT).show()

            }
            else{
                val uid = FirebaseAuth.getInstance().uid?:""
                val event_id = FirebaseDatabase.getInstance().getReference("/event/").push().key
                val ref = FirebaseDatabase.getInstance().getReference("/event/${event_id}")
                val ref2 = FirebaseDatabase.getInstance().getReference("/user/$uid/event/${event_id}")
                val user = User(event_name,event_date,event_m.toInt(),event_id).Map2()
                ref.updateChildren(user).addOnSuccessListener {
                    Log.d("setevent","real time database access success")
                    Toast.makeText(this,"新增活動成功！", Toast.LENGTH_SHORT).show()
                }

                ref2.updateChildren(user).addOnSuccessListener {
                    Log.d("setevent","real time database access success")
                    Toast.makeText(this,"新增活動成功！", Toast.LENGTH_SHORT).show()
                }




                finish()
            }
        }
    }
}
