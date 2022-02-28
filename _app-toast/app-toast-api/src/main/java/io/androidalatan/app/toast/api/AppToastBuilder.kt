package io.androidalatan.app.toast.api

import androidx.annotation.IntDef
import androidx.annotation.StringRes
import androidx.annotation.UiThread

interface AppToastBuilder {
    fun text(text: String): AppToastBuilder
    fun textResId(@StringRes textResId: Int): AppToastBuilder
    fun duration(@Duration duration: Int): AppToastBuilder
    fun create(): AppToast

    @UiThread
    fun show(): AppToast

    @IntDef(value = [Duration.Short, Duration.Long])
    @Retention(AnnotationRetention.SOURCE)
    annotation class Duration {
        companion object {
            const val Short = 0
            const val Long = 1
        }
    }
}
