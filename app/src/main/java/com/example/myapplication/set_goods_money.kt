package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_set_goods_money.*

class set_goods_money : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_goods_money)
        submit.setOnClickListener {
            val pay_cash=pay_cash.text.toString()
            val goods_name = goods_name.text.toString()
            val goods_cash = goods_cash.text.toString()
            val new_good_name = goods_name
            val new_good_price = goods_cash

            if(pay_cash.isEmpty() || goods_name.isEmpty() || goods_cash.isEmpty())
            {
                Toast.makeText(this,"有格子未填寫", Toast.LENGTH_SHORT).show()

            }
            else{
                val uid = FirebaseAuth.getInstance().uid?:""
                val ref = FirebaseDatabase.getInstance().getReference("/user/$uid")
                val user = User(pay_cash.toInt(),goods_name,goods_cash.toInt()).toMap()

                ref.updateChildren(user).addOnSuccessListener {
                    Log.d("setmoney","real time database access success")
                }
                val good_id = FirebaseDatabase.getInstance().getReference("/good/").push().key
                val ref2 = FirebaseDatabase.getInstance().getReference("/user/$uid/good/${good_id}")
                val nuser = User(new_good_name,new_good_price,good_id).Map3()

                ref2.updateChildren(nuser).addOnSuccessListener {
                    Log.d("setmoney","real time database access success")
                }

                val intent = Intent(this,main_page::class.java)
                intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK.or(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }
        }
    }
}
