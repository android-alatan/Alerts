package io.androidalatan.app.toast.assertion

import android.view.View
import io.androidalatan.app.toast.api.AppToast
import io.androidalatan.app.toast.api.AppToastBuilder

class MockAppToastBuilder : AppToastBuilder {
    var text: String = ""
    var textResId: Int = View.NO_ID
    var duration: Int = AppToastBuilder.Duration.Long
    var created = false
    var createdToast: MockAppToast? = null

    override fun text(text: String): AppToastBuilder {
        this.text = text
        return this
    }

    override fun textResId(textResId: Int): AppToastBuilder {
        this.textResId = textResId
        return this
    }

    override fun duration(duration: Int): AppToastBuilder {
        this.duration = duration
        return this
    }

    override fun create(): AppToast {
        created = true
        return MockAppToast().apply { createdToast = this }
    }

    override fun show(): AppToast {
        return create().apply {
            show()
        }
    }
}