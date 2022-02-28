package io.androidalatan.app.toast.assertion

import io.androidalatan.app.toast.api.AppToast

class MockAppToast : AppToast {
    var showCount = 0
    override fun show() {
        showCount++
    }
}