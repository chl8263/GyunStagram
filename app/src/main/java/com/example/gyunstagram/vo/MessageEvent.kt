package com.example.gyunstagram.vo

data class MessageEvent(
    var eventName : String,
    var destinationUid : String,
    var userId : String
)