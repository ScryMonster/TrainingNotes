package com.example.darkfox.trainingnotes.di.modules

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.darkfox.trainingnotes.arch.repository.local.AccountRepository
import com.example.darkfox.trainingnotes.arch.repository.local.PermissionsLocalRepository
import com.example.darkfox.trainingnotes.arch.repository.remote.FirebaseAuthRepository
import com.example.darkfox.trainingnotes.arch.repository.remote.IRemoteRepository
import com.example.darkfox.trainingnotes.arch.repository.remote.TrainingDaysRepository
import com.example.darkfox.trainingnotes.arch.repository.remote.UserFirestoreRepository
import com.example.darkfox.trainingnotes.database.TrainingsDatabase
import com.example.darkfox.trainingnotes.models.dto.Account
import com.example.darkfox.trainingnotes.models.objects.AccountManager
import com.example.darkfox.trainingnotes.models.objects.PermissionsManager
import com.example.darkfox.trainingnotes.models.objects.TokenManager
import com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs.IAccountManager
import com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs.IPermissionsManager
import com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs.ITokenManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object BaseModule {

    val module = module {

        single { provideSharedPrefs(get()) }

        single<IAccountManager> {
            AccountManager(sharedPreferences = get(), gson = get())
        }
        single {
            TrainingsDatabase.getInstance(context = androidContext())!!
        }

        single {
            TrainingsDatabase.getInstance(context = androidContext())?.getAccountsDao()!!
        }

        single {
            TrainingsDatabase.getInstance(context = androidContext())?.getTrainingDaysDao()!!
        }

        single {
            AccountRepository(accountManager = get(), accountDao = get())
        }
        single {
            TrainingDaysRepository(firestoreDatabase = get())
        }
        single{
            PermissionsLocalRepository(permissionsManager = get())
        }
        single<ITokenManager> {
            provideTokenManager(sharedPreferences = get())
        }
        single<IPermissionsManager> {
            providePermissionsManager(sharedPreferences = get(), gson = get())
        }

        single {
            FirebaseAuthRepository(auth = FirebaseAuth.getInstance())
        }

        single {
            FirebaseFirestore.getInstance()
        }

        single<IRemoteRepository<Account>> {
            UserFirestoreRepository(firestoreDatabase = get())
        }
    }

    private fun provideSharedPrefs(context: Context) = PreferenceManager.getDefaultSharedPreferences(context)

    private fun provideTokenManager(sharedPreferences: SharedPreferences) = TokenManager(sharedPreferences)

    private fun providePermissionsManager(sharedPreferences: SharedPreferences, gson: Gson) = PermissionsManager(sharedPreferences,gson)
}