package com.example.a22_10_cdan_spring

data class StudentBean(var name: String = "", var note: Int=0)
data class UserBean(var login: String = "", var password: String="", var sessionId:String ="")

data class PlaneBean(var name: String?, var id:String?)