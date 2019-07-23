package com.example.androidlatihan15_firebasedbe

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log.e
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.halaman_depan.*
import java.util.*

class HalamanDepan : AppCompatActivity(), BukuAdapter.FirebaseDataListener {


    override fun onDeletedData(buku: BukuModel, position: Int) {
        dbref = FirebaseDatabase.getInstance()
            .getReference("dataBuku/${helperPrefs.getUID()}")
        if (dbref != null) {
            dbref.child(buku.getKey()).removeValue().addOnSuccessListener{
                Toast.makeText(this, "data berhasil dihapus",
                    Toast.LENGTH_SHORT).show()
                bukuAdapter!!.notifyDataSetChanged()
            }
        }
    }

    override fun onUpdated(buku: BukuModel, position: Int) {
        dbref = FirebaseDatabase.getInstance()
            .getReference("dataBuku/${helperPrefs.getUID()}")
        if (dbref !=null){

            var datax = dbref.child(buku.getKey()).key
            val intent = Intent(this, TambahData::class.java)
            intent.putExtra("kode", datax.toString())
            startActivity(intent)
        }
    }

    private var bukuAdapter : BukuAdapter? = null
    private var rcView : RecyclerView? = null
    private var list : MutableList<BukuModel> = ArrayList<BukuModel>()
    lateinit var dbref : DatabaseReference
    lateinit var helperPrefs : PrefsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.halaman_depan)
        helperPrefs = PrefsHelper(this)
        rcView = findViewById(R.id.rc_view)
        rcView!!.layoutManager = LinearLayoutManager(applicationContext)
        rcView!!.setHasFixedSize(true)

        dbref = FirebaseDatabase.getInstance()
            .getReference("dataBuku/${helperPrefs.getUID()}")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                list = ArrayList<BukuModel>()
                for (dataSnapshot in p0.children){
                        val addDataAll = dataSnapshot.getValue(BukuModel::class.java)
                        addDataAll!!.setKey(dataSnapshot.key!!)
                        list.add(addDataAll!!)
                        bukuAdapter = BukuAdapter(this@HalamanDepan,
                            list!!
                        )
                        rcView!!.adapter = bukuAdapter
                    }
            }

            override fun onCancelled(p0: DatabaseError) {
                e("TAGERROR", p0.message)
            }
        })

        fab_.setOnClickListener {
            //let'do something
            startActivity(Intent(this, TambahData::class.java))
        }

        upload_storage.setOnClickListener {
            startActivity(Intent(this, UploadFireStorage::class.java))
        }
    }

}