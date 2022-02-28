package io.androidalatan.app.toast

import android.content.Context
import io.androidalatan.app.toast.api.AppToastBuilder
import io.androidalatan.app.toast.api.AppToastBuilderFactory

class AppToastBuilderFactoryImpl(private val context: Context) : AppToastBuilderFactory {
    override fun create(): AppToastBuilder {
        return AppToastBuilderImpl(context)
    }
}