package io.androidalatan.dialog

import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatDialog
import io.androidalatan.dialog.api.CommonAlertDialog

internal typealias CallbackBeforeShow = (CommonAlertDialog) -> Unit

class CommonAlertDialogImpl(
    lazyDialog: Lazy<AppCompatDialog>,
    private val callbackBeforeShow: CallbackBeforeShow
) : CommonAlertDialog {
    private val dialog by lazyDialog

    @VisibleForTesting
    internal var isShowing = false
    override fun show() {
        if (!isShowing()) {
            callbackBeforeShow.invoke(this)
            isShowing = true
            dialog.show()
        }
    }

    override fun isShowing(): Boolean = isShowing

    override fun dismiss() {
        if (isShowing()) {
            isShowing = false
            dialog.dismiss()
        }
    }
}