package com.example.androidlatihan15_firebasedbe

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.storage.StorageReference

class BukuAdapter : RecyclerView.Adapter<BukuAdapter.BukuViewHolder>{
    lateinit var mContext : Context
    lateinit var itemBuku : List<BukuModel>
    lateinit var listener : FirebaseDataListener
    lateinit var storage : StorageReference

    constructor(){ }
    constructor(mContext : Context, list: List<BukuModel>) {
        this.mContext = mContext
        this.itemBuku = list
        listener = mContext as HalamanDepan

        storage = mContext as StorageReference

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BukuViewHolder {
        val view : View = LayoutInflater.from(p0.context).inflate(
            R.layout.show_data, p0, false)
        val bukuViewHolder = BukuViewHolder(view)
        return bukuViewHolder

    }


    override fun getItemCount(): Int {
        return itemBuku.size
    }

    override fun onBindViewHolder(p0: BukuViewHolder, p1: Int) {
        val bukuModel : BukuModel = itemBuku.get(p1)
        p0.tv_nama.text = bukuModel.getNama()
        p0.tv_tanggal.text = bukuModel.getTanggal()
        p0.tv_judul.text = bukuModel.getJudulBuku()
        p0.ll_content.setOnClickListener {
            Toast.makeText(mContext, "contoh touch listener",
                Toast.LENGTH_SHORT).show()
        }
        p0.ll_content.setOnLongClickListener(object : View.OnLongClickListener{
            override fun onLongClick(v: View?): Boolean {
                val builder = AlertDialog.Builder(mContext)
                builder.setMessage("Pilih Operasi Data !!")

                builder.setPositiveButton("Update"){
                    dialog, i ->
//                        Toast.makeText(mContext, "halo saya update",
//                            Toast.LENGTH_SHORT).show()
                   listener.onUpdated(bukuModel, p1)
                }
                builder.setNegativeButton("Delete"){
                    dialog, i ->
                    listener.onDeletedData(bukuModel, p1)
                }

                val dialog : AlertDialog = builder.create()
                dialog.show()

                return true
            }
        })
    }

    inner class BukuViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var ll_content : LinearLayout
        var tv_nama : TextView
        var tv_tanggal : TextView
        var tv_judul : TextView
        init {
            ll_content = itemView.findViewById(R.id.ll_content)
            tv_nama = itemView.findViewById(R.id.tv_penulis)
            tv_tanggal = itemView.findViewById(R.id.tv_tittle)
            tv_judul = itemView.findViewById(R.id.tv_tgl)
        }
    }

    interface FirebaseDataListener {
        fun onDeletedData(buku : BukuModel, position : Int)
        fun onUpdated(buku : BukuModel, position: Int)
    }

}

