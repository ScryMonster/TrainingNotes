package com.example.darkfox.trainingnotes.utils.extensions

import com.example.darkfox.trainingnotes.models.dto.Account
import com.example.darkfox.trainingnotes.models.dto.UserProperties
import com.example.darkfox.trainingnotes.utils.enums.AccountPropertyType


fun Account.convertIntoDocument(): Map<String,Any>{
    val doc = hashMapOf<String,Any>()
    doc.apply {
        put("firstName",firstName ?: "")
        put("lastName",lastName ?: "")
        put("fireBaseId",fireBaseId)
        put("email",email ?: "")
        put("imageUrl",imageUrl ?: "")
        put("properties",properties?.convertIntoDocument() ?: hashMapOf<String,Number>())
    }

    return doc
}

fun UserProperties.convertIntoDocument():HashMap<String,Number> = hashMapOf<String,Number>().apply {
    put("age",age ?: 0)
    put("weight",weight ?: 0.0)
}

fun Map<String,Any>.toAccount() : Account{
   val firstName =  get(AccountPropertyType.FIRST_NAME.propName) as String
   val lastName =  get(AccountPropertyType.LAST_NAME.propName) as String
   val email =  get(AccountPropertyType.EMAIL.propName) as String
   val firebaseId =  get(AccountPropertyType.FIREBASE_ID.propName) as String
   val imageUrl: String? =  (get(AccountPropertyType.IMAGE_URL.propName) ?: "") as String
   val properties =  get(AccountPropertyType.PROPERTIES.propName) as Map<String,Number>

    return Account(firebaseId,email, firstName, lastName,imageUrl,properties.toProperties())
}

fun Map<String,Number?>.toProperties(): UserProperties{
    val age = get("age") as Long ?: 0
    val weight = get("weight") as Double ?: 0.0
    return UserProperties(age, weight)
}

