package io.androidalatan.dialog.api

import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes

interface AlertDialogBuilder {
    fun setContentView(view: View): AlertDialogBuilder
    fun setContentView(@LayoutRes layoutRes: Int): AlertDialogBuilder

    fun setPositiveButton(buttonText: String, clickListener: CommonAlertDialog.ButtonClickListener?): AlertDialogBuilder
    fun setPositiveButton(@StringRes textRes: Int, clickListener: CommonAlertDialog.ButtonClickListener?): AlertDialogBuilder

    fun setNegativeButton(buttonText: String, clickListener: CommonAlertDialog.ButtonClickListener?): AlertDialogBuilder
    fun setNegativeButton(@StringRes textRes: Int, clickListener: CommonAlertDialog.ButtonClickListener?): AlertDialogBuilder

    fun setTitle(title: String): AlertDialogBuilder
    fun setTitle(@StringRes titleRes: Int): AlertDialogBuilder

    fun setTitleColor(@ColorInt color: Int): AlertDialogBuilder
    fun setTitleColorRes(@ColorRes colorRes: Int): AlertDialogBuilder

    fun setMessage(message: String): AlertDialogBuilder
    fun setMessage(@StringRes messageRes: Int): AlertDialogBuilder

    fun setMessageColor(@ColorInt color: Int): AlertDialogBuilder
    fun setMessageColorRes(@ColorRes colorRes: Int): AlertDialogBuilder

    fun setItemList(items: List<String>, itemClickListener: ((Int) -> Unit)?): AlertDialogBuilder
    fun setItemListRes(itemsRes: Int, itemClickListener: ((Int) -> Unit)?): AlertDialogBuilder

    fun setBackgroundColor(@ColorInt color: Int): AlertDialogBuilder
    fun setBackgroundColorRes(@ColorRes colorRes: Int): AlertDialogBuilder

    fun setPositiveButtonColor(@ColorInt color: Int): AlertDialogBuilder

    fun setPositiveButtonColorRes(@ColorRes colorRes: Int): AlertDialogBuilder
    fun setNegativeButtonColor(@ColorInt color: Int): AlertDialogBuilder

    fun setNegativeButtonColorRes(@ColorRes colorRes: Int): AlertDialogBuilder
    fun setCancellable(cancellable: Boolean): AlertDialogBuilder

    fun setCanceledOnTouchOutside(canceledOnTouchOutside: Boolean): AlertDialogBuilder
    fun build(): CommonAlertDialog
    fun show(): CommonAlertDialog
}