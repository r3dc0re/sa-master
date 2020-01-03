package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_redcash_set.*
import kotlinx.android.synthetic.main.activity_show__qr_code.*
import java.text.SimpleDateFormat
import java.util.*

class activity_redcash_set : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redcash_set)

        submit_give.setOnClickListener {
            var give_cash = redcash_amt.text.toString()

            var uid = FirebaseAuth.getInstance().uid?:""
            var ref = FirebaseDatabase.getInstance().getReference("/user/$uid")

            if(give_cash.isEmpty())
            {
                Toast.makeText(this,"請填入金額",Toast.LENGTH_SHORT).show()
            }
            else{
                val  user = User(give_cash.toInt()).MapCash()
                ref.updateChildren(user).addOnSuccessListener {
                    Log.d("ref","OK")
                }
                finish()
            }

//            openScanner()

//            var time = Calendar.getInstance()
//            var format = SimpleDateFormat ("yyyy-MM-dd hh:mm:ss")
//            var now_time =  format.format(time.time)
//            var have_cash=0
//            var current_user_name=""
//            ref = FirebaseDatabase.getInstance().getReference("/user/${data[2]}")
//            ref.addListenerForSingleValueEvent(object : ValueEventListener {
//                override fun onDataChange(dataSnapshot: DataSnapshot) {
//                    val snap = dataSnapshot.getValue(User::class.java)
//                    alert.setMessage("是否要送給${data[1]}${give_cash}的紅包?")
//                    alert.setPositiveButton("YES") { dialog, which ->
//                        val key =  FirebaseDatabase.getInstance().getReference("/record").push().key
//                        val cash =snap!!.cash.toInt()
//                        FirebaseDatabase.getInstance().getReference("/user/${uid}").child("cash").setValue(have_cash-give_cash)
//                        FirebaseDatabase.getInstance().getReference("/user/${data[2]}").child("cash").setValue(cash+give_cash)
//                        val record=record(data[2],data[1],uid,current_user_name,"cash",give_cash.to,now_time)
//                        FirebaseDatabase.getInstance().getReference("/record/${key}").setValue(record)
//                        FirebaseDatabase.getInstance().getReference("/user/${uid}/record/${key}").setValue("pay")
//                        FirebaseDatabase.getInstance().getReference("/user/${data[2]}/record/${key}").setValue("get")
//                        Toast.makeText(applicationContext, "交易完成", Toast.LENGTH_SHORT).show()
//                    }
//                    alert.show()
//                }
//                override fun onCancelled(databaseError: DatabaseError) {}
//            })
        }


    }

    fun openScanner(){
        val  integrator= IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES)
        integrator.setPrompt("Scan a barcode")
        integrator.setCameraId(0)  // 前鏡頭
        integrator.setBeepEnabled(false) // 拍照聲音
        integrator.setBarcodeImageEnabled(true)
        integrator.setOrientationLocked(false) // 部翻轉
        integrator.initiateScan()
    }

}

