package io.androidalatan.dialog.api

interface CommonAlertDialog {
    fun show()
    fun isShowing(): Boolean
    fun dismiss()

    fun interface ButtonClickListener {
        fun onClick()
    }
}