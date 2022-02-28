package io.androidalatan.app.toast

import android.widget.Toast
import io.androidalatan.app.toast.api.AppToast

class AppToastImpl(private val toast: Toast) : AppToast {

    override fun show() {
        toast.show()
    }
}