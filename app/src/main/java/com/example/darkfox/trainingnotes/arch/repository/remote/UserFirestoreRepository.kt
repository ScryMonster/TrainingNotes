package com.example.darkfox.trainingnotes.arch.repository.remote

import com.example.darkfox.trainingnotes.models.dto.Account
import com.example.darkfox.trainingnotes.utils.enums.AccountPropertyType
import com.example.darkfox.trainingnotes.utils.extensions.account
import com.example.darkfox.trainingnotes.utils.extensions.check
import com.example.darkfox.trainingnotes.utils.extensions.users
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class UserFirestoreRepository(private val firestoreDatabase: FirebaseFirestore) : IRemoteRepository<Account> {

    override suspend fun create(data: Account, success: () -> Unit, error: (Exception) -> Unit) {
        firestoreDatabase.users().account(data.fireBaseId).set(data).check(success, error)
    }


    override fun get(id: String, error: (Exception) -> Unit): Task<DocumentSnapshot> = firestoreDatabase.users().document(id).get()
//    {
//        try {
//            val snap = db.users().document(id).get().await()
//            val account = snap.data?.toAccount()
//            success(account!!)
//        } catch (e:Exception){
//            error(e)
//        }
//        db.users().document(id).get().checkSusp(
//                {
//                    val toAccount = it.data?.toAccount()
//                    if (toAccount != null) {
//                        success(toAccount)
//                    }else error(NullPointerException())
//                },
//                {
//                    error(it)
//                })
//    }

    override suspend fun <O : Any> overwriteProperty(documentName: String,
                                             propertyType: AccountPropertyType,
                                             newValue: O,
                                             success: () -> Unit,
                                             error: (Exception) -> Unit) {
        firestoreDatabase.users().document(documentName).apply {
            when (propertyType) {
                AccountPropertyType.FIREBASE_ID -> update(AccountPropertyType.FIREBASE_ID.propName, newValue)
                AccountPropertyType.FIRST_NAME -> update(AccountPropertyType.FIRST_NAME.propName, newValue)
                AccountPropertyType.LAST_NAME -> update(AccountPropertyType.LAST_NAME.propName, newValue)
                AccountPropertyType.EMAIL -> update(AccountPropertyType.EMAIL.propName, newValue)
                AccountPropertyType.IMAGE_URL -> update(AccountPropertyType.IMAGE_URL.propName, newValue)
                AccountPropertyType.PROPERTIES -> update(AccountPropertyType.PROPERTIES.propName, newValue)
            }.check(success,error)
        }
    }
}