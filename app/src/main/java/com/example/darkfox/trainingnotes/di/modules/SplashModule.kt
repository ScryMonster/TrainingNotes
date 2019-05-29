package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.domain.splash.ISplashInteractor
import com.example.darkfox.trainingnotes.arch.domain.splash.SplashInteractor
import com.example.darkfox.trainingnotes.arch.repository.local.LocalRepository
import com.example.darkfox.trainingnotes.arch.repository.local.AccountRepository
import com.example.darkfox.trainingnotes.arch.repository.local.PermissionsLocalRepository
import com.example.darkfox.trainingnotes.arch.ui.contracts.SplashContract
import com.example.darkfox.trainingnotes.arch.ui.splash.SplashPresenter
import com.example.darkfox.trainingnotes.di.modules.BaseModule.AccountRepositoryName
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.dto.ReadWriteStoragePermission
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import com.example.darkfox.trainingnotes.utils.permission.PermissionHelper
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object SplashModule {
    private val scopeName = KoinScopes.SPLASH.scopeName
    private val permissionsLocalRepositoryName = "PermissionsLocalRepositoryName"
//    private val AccountRepositoryName = "AccountRepositoryName"

    val module = module {
        scope(scopeName) { PermissionHelper() }

//        scope<LocalRepository<Account>>(
//                scopeName,
//                name = AccountRepositoryName) {
//            AccountRepository(get())
//        }
        scope<LocalRepository<ReadWriteStoragePermission>>(
                scopeName,
                name = permissionsLocalRepositoryName) {
            PermissionsLocalRepository(get())
        }

        scope<ISplashInteractor>(scopeName) {
            SplashInteractor(
                    get(name = AccountRepositoryName),
                    get(name = permissionsLocalRepositoryName),
                    get()
            )
        }

        scope<SplashContract.Presenter>(scopeName){
            SplashPresenter(get())
        }
//        viewModel { SplashViewModel(get()) }
//        scope<LocalRepository<Account>> (scopeName){ AccountRepository(get()) }
    }
}