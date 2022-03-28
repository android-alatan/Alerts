package io.androidalatan.compose.dialog.assertion

import io.androidalatan.compose.dialog.api.ComposeAlertDialog

class MockComposeAlertDialog(
    private val positiveButtonClickListener: ComposeAlertDialog.ButtonClickListener?,
    private val negativeButtonClickListener: ComposeAlertDialog.ButtonClickListener?
) : ComposeAlertDialog {
    var showCount = 0
    var dismissCount = 0
    private var isShowingFlag = false
    override fun show() {
        if (!isShowingFlag) {
            showCount++
            isShowingFlag = true
        }
    }

    override fun isShowing(): Boolean {
        return isShowingFlag
    }

    override fun dismiss() {
        if (isShowingFlag) {
            dismissCount++
        }
    }

    fun clickPositiveButton() {
        if (isShowing()) {
            dismiss()
            positiveButtonClickListener?.onClick()
        }
    }

    fun clickNegativeButton() {
        if (isShowing()) {
            dismiss()
            negativeButtonClickListener?.onClick()
        }
    }
}