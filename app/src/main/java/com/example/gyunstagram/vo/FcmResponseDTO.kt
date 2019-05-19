package com.example.gyunstagram.vo

data class FcmResponseDTO (

     var multicast_id : String ? = null ,
     var success : String ? = null ,
     var failure : String ? = null ,
     var canonical_ids : String ? = null
){
    data class results(
        var message_id : String ? = null
    )
}