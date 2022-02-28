package io.androidalatan.dialog.assertion

import io.androidalatan.dialog.api.CommonAlertDialog

class MockCommonAlertDialog(
    private val positiveButtonClickListener: CommonAlertDialog.ButtonClickListener?,
    private val negativeButtonClickListener: CommonAlertDialog.ButtonClickListener?
) : CommonAlertDialog {
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