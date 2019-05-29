package com.example.darkfox.trainingnotes.arch.repository.remote

import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.utils.enums.AccountPropertyType
import com.example.darkfox.trainingnotes.utils.extensions.convertIntoDocument
import com.example.darkfox.trainingnotes.utils.extensions.newAccount
import com.example.darkfox.trainingnotes.utils.extensions.toAccount
import com.example.darkfox.trainingnotes.utils.extensions.users
import com.google.firebase.firestore.FirebaseFirestore

class UserFirestoreRepository(override val db: FirebaseFirestore) : BaseFirestoreRepository<Account>() {
    override fun create(data: Account, success: () -> Unit, error: (Exception) -> Unit) {
        db.users().newAccount(data.fireBaseId).set(data.convertIntoDocument())
                .addOnSuccessListener {
                    success()
                }
                .addOnFailureListener {
                    error(it)
                }
    }

    override fun get(success: (Account) -> Unit, error: (Exception) -> Unit) {
        db.users().get()
                .addOnSuccessListener {
                    it.documents.forEach { document ->
                        document.data?.toAccount()
                    }
                }
                .addOnFailureListener {
                    error(it)
                }
    }

    override fun <O : Any> overwriteProperty(documentName: String,
                                             propertyType: AccountPropertyType,
                                             newValue: O,
                                             success: () -> Unit,
                                             error: (Exception) -> Unit) {
        db.users().document(documentName).apply {
            when (propertyType) {
                AccountPropertyType.FIREBASE_ID -> update(AccountPropertyType.FIREBASE_ID.propName, newValue)
                AccountPropertyType.FIRST_NAME -> update(AccountPropertyType.FIRST_NAME.propName, newValue)
                AccountPropertyType.LAST_NAME -> update(AccountPropertyType.LAST_NAME.propName, newValue)
                AccountPropertyType.EMAIL -> update(AccountPropertyType.EMAIL.propName, newValue)
                AccountPropertyType.IMAGE_URL -> update(AccountPropertyType.IMAGE_URL.propName, newValue)
                AccountPropertyType.PROPERTIES -> update(AccountPropertyType.PROPERTIES.propName, newValue)
            }
                    .addOnSuccessListener {
                        success()
                    }
                    .addOnFailureListener {
                        error(it)
                    }
        }
    }
}