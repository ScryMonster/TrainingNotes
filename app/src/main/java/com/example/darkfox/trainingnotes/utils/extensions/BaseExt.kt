package com.example.darkfox.trainingnotes.utils.extensions

import com.example.darkfox.trainingnotes.dto.Result

fun <T:Any> Result<T>.checkResult(success:(T)->Unit,
                        fail:(Throwable)->Unit){
    if (this is Result.Success){
        success(this.data)
    }
    else{
       fail((this as Result.Fail).cause)
    }
}


suspend fun <T:Any> Result<T>.checkResultSusp(success:suspend (T)->Unit,
                        fail:(Throwable)->Unit){
    if (this is Result.Success){
        success(this.data)
    }
    else{
       fail((this as Result.Fail).cause)
    }
}