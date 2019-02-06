package com.example.darkfox.trainingnotes.dto.errors

class UserNotExist(val name:String = "There isn't any saved user") : Exception(name)