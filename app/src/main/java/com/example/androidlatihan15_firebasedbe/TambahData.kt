package com.example.androidlatihan15_firebasedbe

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.register_act.*
import kotlinx.android.synthetic.main.tambah_data.*
import kotlinx.android.synthetic.main.upload_image.*

class TambahData : AppCompatActivity(){

    lateinit var dbRef :DatabaseReference
    lateinit var helperPref : PrefsHelper
    var datax : String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tambah_data)

        datax = intent.getStringExtra("kode")
        helperPref = PrefsHelper(this)

        if (datax != null){
            showdataFromDB()
        }

        btn_simpan.setOnClickListener {
            val nama = et_namaPenulis.text.toString()
            val judul = et_judulBuku.text.toString()
            val tgl = et_tanggal.text.toString()
            val desc = et_description.text.toString()

            if (nama.isNotEmpty() || judul.isNotEmpty() || tgl.isNotEmpty() ||
                    desc.isNotEmpty()){
                simpanToFirebase(nama, judul, tgl, desc)
            }else{
                Toast.makeText(this, "inputan tidak boleh kosong",
                    Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun simpanToFirebase(nama : String, judul : String, tgl : String, desc : String){
        val uidUser = helperPref.getUID()
        val counterID = helperPref.getCounterId()

        dbRef = FirebaseDatabase.getInstance().getReference("dataBuku/$uidUser")
        dbRef.child("$counterID/id").setValue(uidUser)
        dbRef.child("$counterID/nama").setValue(nama)
        dbRef.child("$counterID/judulBuku").setValue(judul)
        dbRef.child("$counterID/tanggal").setValue(tgl)
        dbRef.child("$counterID/description").setValue(desc)

        Toast.makeText(this, "Data Berhasil Ditambahkan",
            Toast.LENGTH_SHORT).show()

        if (datax == null) {
            helperPref.saveCounterId(counterID+1)
        }
        onBackPressed()
    }

    fun showdataFromDB(){
        dbRef = FirebaseDatabase.getInstance()
            .getReference("dataBuku/${helperPref.getUID()}/${datax}/")
        dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
               val buku = p0.getValue(BukuModel::class.java)
                    et_namaPenulis.setText(buku!!.getNama())
                    et_judulBuku.setText(buku.getJudulBuku())
                    et_tanggal.setText(buku.getTanggal())
                    et_description.setText(buku.getDescription())
                }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.upload_image)

        btn_kirim.setOnClickListener {

        }
    }


}