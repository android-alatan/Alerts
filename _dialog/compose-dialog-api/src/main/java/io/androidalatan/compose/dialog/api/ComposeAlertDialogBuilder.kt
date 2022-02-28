package io.androidalatan.compose.dialog.api

import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable

interface ComposeAlertDialogBuilder {

    fun setContentView(content: @Composable () -> Unit): ComposeAlertDialogBuilder

    fun setPositiveButton(buttonText: String, clickListener: ComposeAlertDialog.ButtonClickListener?): ComposeAlertDialogBuilder
    fun setPositiveButton(@StringRes textRes: Int, clickListener: ComposeAlertDialog.ButtonClickListener?): ComposeAlertDialogBuilder

    fun setNegativeButton(buttonText: String, clickListener: ComposeAlertDialog.ButtonClickListener?): ComposeAlertDialogBuilder
    fun setNegativeButton(@StringRes textRes: Int, clickListener: ComposeAlertDialog.ButtonClickListener?): ComposeAlertDialogBuilder

    fun setTitle(title: String): ComposeAlertDialogBuilder
    fun setTitle(@StringRes titleRes: Int): ComposeAlertDialogBuilder

    fun setTitleColor(@ColorInt color: Int): ComposeAlertDialogBuilder
    fun setTitleColorRes(@ColorRes colorRes: Int): ComposeAlertDialogBuilder

    fun setMessage(message: String): ComposeAlertDialogBuilder
    fun setMessage(@StringRes messageRes: Int): ComposeAlertDialogBuilder

    fun setMessageColor(@ColorInt color: Int): ComposeAlertDialogBuilder
    fun setMessageColorRes(@ColorRes colorRes: Int): ComposeAlertDialogBuilder

    fun setItemList(items: List<String>, itemClickListener: ((Int) -> Unit)?): ComposeAlertDialogBuilder
    fun setItemListRes(itemsRes: Int, itemClickListener: ((Int) -> Unit)?): ComposeAlertDialogBuilder

    fun setPositiveButtonColor(@ColorInt color: Int): ComposeAlertDialogBuilder

    fun setPositiveButtonColorRes(@ColorRes colorRes: Int): ComposeAlertDialogBuilder
    fun setNegativeButtonColor(@ColorInt color: Int): ComposeAlertDialogBuilder

    fun setNegativeButtonColorRes(@ColorRes colorRes: Int): ComposeAlertDialogBuilder
    fun setCancellable(cancellable: Boolean): ComposeAlertDialogBuilder

    fun setCanceledOnTouchOutside(canceledOnTouchOutside: Boolean): ComposeAlertDialogBuilder
    fun build(): ComposeAlertDialog
    fun show(): ComposeAlertDialog
}