package io.androidalatan.app.toast.api

import androidx.annotation.UiThread

interface AppToast {
    @UiThread
    fun show()
}