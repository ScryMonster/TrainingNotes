package com.example.darkfox.trainingnotes.arch.ui.dialogs

import com.example.darkfox.trainingnotes.arch.base.ui.BaseBottomSheetDialog
import com.example.darkfox.trainingnotes.arch.ui.contracts.SearchSettingsContract
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import org.koin.android.ext.android.inject

class SearchSettingsDialog : BaseBottomSheetDialog<SearchSettingsContract.View,SearchSettingsContract.Presenter>(),SearchSettingsContract.View {
    override val presenter: SearchSettingsContract.Presenter by inject()
    override val scopeName: String = KoinScopes.SEARCH_SETTINGS.scopeName

}