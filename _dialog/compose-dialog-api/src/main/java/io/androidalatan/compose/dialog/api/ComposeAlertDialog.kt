package io.androidalatan.compose.dialog.api

interface ComposeAlertDialog {
    fun show()
    fun isShowing(): Boolean
    fun dismiss()

    fun interface ButtonClickListener {
        fun onClick()
    }
}