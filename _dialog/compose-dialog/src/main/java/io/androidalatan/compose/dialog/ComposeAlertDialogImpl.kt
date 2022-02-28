package io.androidalatan.compose.dialog

import androidx.annotation.VisibleForTesting
import io.androidalatan.compose.dialog.api.ComposeAlertDialog

class ComposeAlertDialogImpl(
    private val showInvoker: (Boolean) -> Unit,
) : ComposeAlertDialog {

    @VisibleForTesting
    internal var isShowing = false

    override fun show() {
        isShowing = true
        showInvoker.invoke(true)
    }

    override fun isShowing(): Boolean {
        return isShowing
    }

    override fun dismiss() {
        isShowing = false
        showInvoker.invoke(false)
    }
}