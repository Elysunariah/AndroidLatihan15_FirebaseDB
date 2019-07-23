package com.example.androidlatihan15_firebasedbe

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.register_act.*

class RegisterAct : AppCompatActivity(){

    lateinit var dbRef : DatabaseReference
    lateinit var helperPrefs : PrefsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_act)

        helperPrefs = PrefsHelper(this)
        btn_regis.setOnClickListener {
            val nama = et_nama.text.toString()
            val email = et_email.text.toString()
            val psw = et_password.text.toString()

            if (nama.isNotEmpty() || email.isNotEmpty() || psw.isNotEmpty()){
                simpanToFireBase(nama, email, psw)
            }else{
                Toast.makeText(this, "inputan tidak boleh kosong",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun simpanToFireBase(nama : String, email : String, psw : String){
        val uidUser = helperPrefs.getUID()
        val counterID = helperPrefs.getCounterId()

        dbRef = FirebaseDatabase.getInstance().getReference("dataBuku/$uidUser")
        dbRef.child("$counterID/id").setValue(uidUser)
        dbRef.child("$counterID/nama").setValue(nama)
        dbRef.child("$counterID/email").setValue(email)
        dbRef.child("$counterID/password").setValue(psw)

        Toast.makeText(this, "Data Berhasil Ditambahkan",
            Toast.LENGTH_SHORT).show()

        helperPrefs.saveCounterId(counterID+1 )
        onBackPressed()
    }
}