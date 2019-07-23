package com.example.androidlatihan15_firebasedbe

class BukuModel {

    private var nama : String? = null
    private var tanggal : String? = null
    private var judulBuku : String? = null
    private var id : String? = null
    private var description : String? = null
    private var image : String? = null
    private var key : String? = null // untuk menyimpan key value dari firebase db

    constructor(){}
    constructor(nama : String, tanggal : String, judul : String, description : String, image : String){
        this.nama = nama
        this.tanggal = tanggal
        this.judulBuku = judul
        this.description = description
        this.image = image
    }

    fun getNama() : String{ return nama!! }
    fun getTanggal() :String{ return tanggal!! }
    fun getJudulBuku() : String{ return judulBuku!! }
    fun getId() :String{return  id!! }
    fun setId(id : String){this.id = id }
    fun getDescription() : String{return description!! }
    fun setDesc(desc : String){this.description = desc}
    fun setNama(nama: String){this.nama = nama}
    fun setTanggal(tanggal: String){this.tanggal = tanggal}
    fun setJudul(judul: String){this.judulBuku = judul}

    fun getKey() : String{return key!!}
    fun setKey(key : String){this.key = key }
}