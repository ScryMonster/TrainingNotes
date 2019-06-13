package com.example.darkfox.trainingnotes.arch.repository

import com.example.darkfox.trainingnotes.arch.repository.local.AccountRepository
import com.example.darkfox.trainingnotes.arch.repository.local.PermissionsLocalRepository
import com.example.darkfox.trainingnotes.arch.repository.remote.FirebaseAuthRepository
import com.example.darkfox.trainingnotes.arch.repository.remote.IRemoteRepository
import com.example.darkfox.trainingnotes.arch.repository.remote.TrainingDaysRepository
import com.example.darkfox.trainingnotes.database.dao.TrainingDaysDao
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.dto.ReadWriteStoragePermission
import com.example.darkfox.trainingnotes.dto.Result
import com.example.darkfox.trainingnotes.dto.gym.TrainingDay
import com.example.darkfox.trainingnotes.utils.enums.AccountPropertyType
import com.example.darkfox.trainingnotes.utils.extensions.awaitWithNull
import com.example.darkfox.trainingnotes.utils.extensions.getResults
import com.example.darkfox.trainingnotes.utils.extensions.toAccount
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

object DataProvider : KoinComponent{

    private val accountRepository:AccountRepository by inject()
    private val permissionsLocalRepository:PermissionsLocalRepository by inject()
    private val firebaseAuthRepository:FirebaseAuthRepository by inject()
    private val firestoreRepository:IRemoteRepository<Account> by inject()
    private val trainingDaysDao: TrainingDaysDao by inject()
    private val traininDaysFirestoreRepo : TrainingDaysRepository by inject()

    private val dbScope = CoroutineScope(Dispatchers.IO)

    private suspend inline fun <reified T : Any?> makeDbRequest(crossinline operation: () -> T): T? {
        return withContext(dbScope.coroutineContext) { operation() }
    }




    fun getCurrentAccount():Account? = accountRepository.getFromSharedPrefs()

    suspend fun getAccountById(id:String) = makeDbRequest {
        accountRepository.getAccount(id)
    }

    fun saveCurrentAccount(account: Account) {
        accountRepository.saveToSharedPrefs(account)
    }

    suspend fun saveAccount(account: Account) = makeDbRequest {
        accountRepository.saveToDatabase(account)
    }

    suspend fun updateAccount(account: Account) = makeDbRequest {
        accountRepository.updateAccount(account)
    }

    fun savePermissions(permissions: ReadWriteStoragePermission) {
        permissionsLocalRepository.save(permissions)
    }

    fun getPermissions() = permissionsLocalRepository.restore()

//    suspend fun register(email:String,password:String):FirebaseUser? = firebaseAuthRepository.register(email, password).awaitWithNull()?.user
    suspend fun register(email:String,password:String):Result<AuthResult> = firebaseAuthRepository.register(email, password).getResults()

//    suspend fun login(email:String,password:String):FirebaseUser? = firebaseAuthRepository.signIn(email, password).awaitWithNull()?.user
    suspend fun login(email:String,password:String):Result<AuthResult> = firebaseAuthRepository.signIn(email, password).getResults()

    suspend fun createAccountDocument(account: Account,success:()->Unit,fail:(Exception)->Unit){
        firestoreRepository.create(account,success,fail)
    }

    suspend fun getAccountDocument(id:String):Account? = firestoreRepository.get(id).awaitWithNull()?.data?.toAccount()

    suspend fun <O:Any> overwriteProperty(documentName: String,
                                      propertyType: AccountPropertyType,
                                      newValue: O,
                                      success: () -> Unit,
                                      error: (Exception) -> Unit){
        firestoreRepository.overwriteProperty(documentName, propertyType, newValue, success, error)
    }

    suspend fun getTrainingDaysById(id:String) = makeDbRequest {
        trainingDaysDao.getAllByAccountId(id)
    }

    suspend fun saveTrainingDay(day:TrainingDay) = makeDbRequest {
        trainingDaysDao.insert(day)
    }

    suspend fun updateTraining(day:TrainingDay) = makeDbRequest {
        trainingDaysDao.updateTrainingById(day)
    }

    suspend fun getTodaysTrainingDay(id:Long) = makeDbRequest {
        trainingDaysDao.getTodaysTrainingDay(id)
    }

    suspend fun  saveDayToServer(day:TrainingDay,success:()->Unit,fail:(Exception)->Unit){
        traininDaysFirestoreRepo.saveTrainingDay(day,success, fail)
    }

    suspend fun getDaysFromServer() = traininDaysFirestoreRepo.getTrainings().await().documents.map { it.toObject(TrainingDay::class.java) }

    suspend fun updateTrainingDayServer(day: TrainingDay,success: () -> Unit,fail: (Exception) -> Unit){
        traininDaysFirestoreRepo.updateDay(day,success,fail)
    }


}