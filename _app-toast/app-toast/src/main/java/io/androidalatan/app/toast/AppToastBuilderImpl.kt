package io.androidalatan.app.toast

import android.content.Context
import android.view.View
import android.widget.Toast
import io.androidalatan.app.toast.api.AppToast
import io.androidalatan.app.toast.api.AppToastBuilder

class AppToastBuilderImpl(private val context: Context) : AppToastBuilder {

    internal var text: String = ""
    internal var textResId: Int = View.NO_ID
    internal var duration: Int = AppToastBuilder.Duration.Long

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
        check(duration == AppToastBuilder.Duration.Long || duration == AppToastBuilder.Duration.Short) {
            "Duration should be AppToastBuilder.Duration.Long or AppToastBuilder.Duration.Short"
        }

        check(text.isNotEmpty() || textResId != 0 && textResId != View.NO_ID) {
            "Text or Text ResId should be assigned at least"
        }

        val toastDuration = when (duration) {
            AppToastBuilder.Duration.Long -> Toast.LENGTH_LONG
            else -> Toast.LENGTH_SHORT
        }
        val toast = if (text.isNotEmpty()) {
            Toast.makeText(context, text, toastDuration)
        } else {
            Toast.makeText(context, textResId, toastDuration)
        }

        return AppToastImpl(toast)
    }

    override fun show(): AppToast {
        return create().apply {
            show()
        }
    }
}