package com.example.darkfox.trainingnotes.models.dto

class EnterUserState(var emailState:Boolean = false,
                     var passwordState:Boolean = false ){

    private var successBlock:()->Unit = {}

    private var errorBlock:(String)->Unit = {}

    infix fun setErrorListener(code:(String)->Unit) = apply {
        this.errorBlock = code
    }

    infix fun setSuccessListener(code:()->Unit) = apply {
        this.successBlock = code
    }

    fun checkState(){
        if (emailState && passwordState) successBlock()
        when{
            emailState && passwordState -> successBlock()
            !emailState-> errorBlock("Invalid Email")
            !passwordState -> errorBlock("Invalid Password")
        }
    }
}