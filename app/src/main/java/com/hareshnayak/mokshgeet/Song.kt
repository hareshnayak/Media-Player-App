package com.hareshnayak.mokshgeet

data class Song(
    val id:String, val title: String,val album: String,val artist: String, val duration: Long = 0, val path: String,
)