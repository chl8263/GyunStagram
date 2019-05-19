package com.example.gyunstagram.vo



data class FcmDTO (
    var token : String ? = null,
    var notification : Notification? = Notification()
){
    data class Notification(
        var body : String ? = null,
        var title : String ? = null
    )
}