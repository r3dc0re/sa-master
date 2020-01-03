package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_new_event.*
import kotlinx.android.synthetic.main.activity_new_good.*
import kotlinx.android.synthetic.main.activity_set_goods_money.*
import kotlinx.android.synthetic.main.activity_set_goods_money.submit

class activity_new_good : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_good)

        submit.setOnClickListener {
            val new_good_name = new_good_name.text.toString()
            val new_good_price = new_good_price.text.toString()


            if(new_good_name.isEmpty() || new_good_price.isEmpty() )
            {
                Toast.makeText(this,"有格子未填寫", Toast.LENGTH_SHORT).show()

            }
            else{
                val uid = FirebaseAuth.getInstance().uid?:""
                val good_id = FirebaseDatabase.getInstance().getReference("/good/").push().key
//                val ref = FirebaseDatabase.getInstance().getReference("/good/${good_id}")
                val ref2 = FirebaseDatabase.getInstance().getReference("/user/$uid/good/${good_id}")
                val user = User(new_good_name,new_good_price,good_id).Map3()
//                ref.updateChildren(user).addOnSuccessListener {
//                    Log.d("setgood","real time database access success")
//                }

                ref2.updateChildren(user).addOnSuccessListener {
                    Log.d("setgood","real time database access success")
                    Toast.makeText(this,"新增商品成功！", Toast.LENGTH_SHORT).show()
                }




                finish()
            }
        }
    }
}
