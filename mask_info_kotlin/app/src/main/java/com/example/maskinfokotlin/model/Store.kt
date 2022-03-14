package com.example.maskinfokotlin.model

data class Store(
    var addr: String,
    var code: String,
    var created_at: String,
    var lat: Double,
    var lng: Double,
    var name: String,
    var remainStat: String,
    var stockAt: String,
    var type: String,
)
