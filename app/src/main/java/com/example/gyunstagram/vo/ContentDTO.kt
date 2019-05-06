package com.example.gyunstagram.vo

data class ContentDTO (
    var explain : String ? = null,
    var imageUrl : String ? = null,
    var uid : String ? = null,
    var userId : String ? = null,
    var timestamp :  Long ? = null,
    var favoriteCount : Int = 0,
    var favorite : MutableMap<String , Boolean>  = HashMap()


){
    data class Cooment(
        var uid : String ? = null,
        var userId : String ? = null,
        var comment : String ? = null,
        var timestamp : Long? = null
    )
}