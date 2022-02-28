package io.androidalatan.dialog

import android.app.Activity
import androidx.annotation.VisibleForTesting
import io.androidalatan.dialog.api.AlertDialogBuilder
import io.androidalatan.dialog.api.AlertDialogBuilderFactory
import io.androidalatan.dialog.api.CommonAlertDialog

class AlertDialogBuilderFactoryImpl(private val activity: Lazy<Activity>) : AlertDialogBuilderFactory {

    @VisibleForTesting
    internal var lastDialog: CommonAlertDialog? = null

    override fun create(): AlertDialogBuilder {
        return AlertDialogBuilderImpl(activity.value, this::dismissPreviousAndSet)
    }

    @VisibleForTesting
    internal fun dismissPreviousAndSet(dialog: CommonAlertDialog) {
        if (lastDialog?.isShowing() == true) {
            lastDialog?.dismiss()
        }
        lastDialog = dialog
    }
}