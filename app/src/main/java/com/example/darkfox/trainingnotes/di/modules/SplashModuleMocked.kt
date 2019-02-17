package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.domain.splash.ISplashInteractor
import com.example.darkfox.trainingnotes.arch.domain.splash.SplashInteractor
import com.example.darkfox.trainingnotes.arch.mocked.ISplashMockedInteractor
import com.example.darkfox.trainingnotes.arch.mocked.MockedAccountRepository
import com.example.darkfox.trainingnotes.arch.mocked.MockedSplashInteractor
import com.example.darkfox.trainingnotes.arch.mocked.MockedSplashViewModel
import com.example.darkfox.trainingnotes.arch.repository.local.LocalRepository
import com.example.darkfox.trainingnotes.arch.repository.local.PermissionsLocalRepository
import com.example.darkfox.trainingnotes.arch.ui.splash.viewmodel.SplashViewModel
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.dto.ReadWriteStoragePermission
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import com.example.darkfox.trainingnotes.utils.permission.PermissionHelper
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object SplashModuleMocked {
    private val scopeName = KoinScopes.SPLASH.scopeName
    private val permissionsLocalRepositoryName = "PermissionsLocalRepositoryName"
    private val mockedAccountRepositoryName = "MockedAccountRepositoryName"

    val module = module {
        scope(scopeName) { PermissionHelper() }

        scope<LocalRepository<Account>>(
                scopeName,
                name = mockedAccountRepositoryName) {
            MockedAccountRepository(get())
        }
        scope<LocalRepository<ReadWriteStoragePermission>>(
                scopeName,
                name = permissionsLocalRepositoryName) {
            PermissionsLocalRepository(get())
        }

        scope<ISplashMockedInteractor>(scopeName) {
            MockedSplashInteractor(
                    get(name = mockedAccountRepositoryName),
                    get(name = permissionsLocalRepositoryName),
                    get()
            )
        }
        viewModel { MockedSplashViewModel(get()) }
//        scope<LocalRepository<Account>> (scopeName){ AccountRepository(get()) }
    }
}