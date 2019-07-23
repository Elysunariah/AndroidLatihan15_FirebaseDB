package com.example.androidlatihan15_firebasedbe

class FriendlyMessage {
    private var id : String? = null
    private var text : String? = null
    private var name : String? = null
    private var photoUrl : String? = null
    private var imageUrl : String? = null
    constructor(){}
    constructor(text: String?, name: String?, photoUrl: String?, imageUrl: String?){
        this.text = text
        this.name = name
        this.photoUrl = photoUrl
        this.imageUrl = imageUrl
    }

    //setter getter for id
    fun getId() : String?{return id }
    fun setId(id : String?){ this.id = id }
    //setter getter for text
    fun getText() : String?{return text}
    fun setText(text: String?){this.text = text}
    //setter getter for name
    fun getName() : String?{return name}
    fun getName(name: String?){this.name = name}
    //setter getter for photoUrl
    fun getPhotoUrl() : String?{return photoUrl}
    fun setPhotoUrl(photoUrl: String?){this.photoUrl = photoUrl}
    //setter getter for imageUrl
    fun getImageUrl() : String?{return imageUrl}
    fun setImageUrl(imageUrl: String?){this.imageUrl = imageUrl}
}