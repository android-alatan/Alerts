package io.androidalatan.dialog.assertion

import android.graphics.Color
import android.view.View
import androidx.annotation.ArrayRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import io.androidalatan.dialog.api.AlertDialogBuilder
import io.androidalatan.dialog.api.CommonAlertDialog

class MockAlertDialogBuilder() : AlertDialogBuilder {

    var canceledOnTouchOutside: Boolean = true

    var cancellable: Boolean = true

    var positiveButtonClickListener: CommonAlertDialog.ButtonClickListener? = null

    var positiveButtonText: String = ""

    @StringRes
    var positiveButtonTextRes: Int = View.NO_ID

    @ColorInt
    var positiveButtonColor: Int = Color.TRANSPARENT

    @ColorRes
    var positiveButtonColorRes: Int = View.NO_ID

    var negativeButtonClickListener: CommonAlertDialog.ButtonClickListener? = null

    var negativeButtonText: String = ""

    @StringRes
    var negativeButtonTextRes: Int = View.NO_ID

    @ColorInt
    var negativeButtonColor: Int = Color.TRANSPARENT

    @ColorRes
    var negativeButtonColorRes: Int = View.NO_ID

    var title: String = ""

    @StringRes
    var titleRes: Int = View.NO_ID

    @ColorInt
    var titleColor: Int = Color.TRANSPARENT

    @ColorRes
    var titleColorRes: Int = View.NO_ID

    var message: String = ""

    @StringRes
    var messageRes: Int = View.NO_ID

    @ColorInt
    var messageColor: Int = Color.TRANSPARENT

    @ColorRes
    var messageColorRes: Int = View.NO_ID

    var itemList: List<String> = emptyList()

    @ArrayRes
    var itemListRes: Int = View.NO_ID

    var itemClickListener: ((Int) -> Unit)? = null

    @ColorInt
    var backgroundColor: Int = Color.TRANSPARENT

    @ColorRes
    var backgroundColorRes: Int = View.NO_ID

    var contentView: View? = null

    @LayoutRes
    var contentViewRes: Int = View.NO_ID

    var lastBuiltDialog: MockCommonAlertDialog? = null
    var buildCount = 0
    var showCount = 0

    override fun setContentView(view: View): AlertDialogBuilder {
        this.contentView = view
        return this
    }

    override fun setContentView(layoutRes: Int): AlertDialogBuilder {
        this.contentViewRes = layoutRes
        return this
    }

    override fun setPositiveButton(buttonText: String, clickListener: CommonAlertDialog.ButtonClickListener?): AlertDialogBuilder {
        this.positiveButtonText = buttonText
        this.positiveButtonClickListener = clickListener
        return this
    }

    override fun setPositiveButton(textRes: Int, clickListener: CommonAlertDialog.ButtonClickListener?): AlertDialogBuilder {
        this.positiveButtonTextRes = textRes
        this.positiveButtonClickListener = clickListener
        return this
    }

    override fun setNegativeButton(buttonText: String, clickListener: CommonAlertDialog.ButtonClickListener?): AlertDialogBuilder {
        this.negativeButtonText = buttonText
        this.negativeButtonClickListener = clickListener
        return this
    }

    override fun setNegativeButton(textRes: Int, clickListener: CommonAlertDialog.ButtonClickListener?): AlertDialogBuilder {
        this.negativeButtonTextRes = textRes
        this.negativeButtonClickListener = clickListener
        return this
    }

    override fun setTitle(title: String): AlertDialogBuilder {
        this.title = title
        return this
    }

    override fun setTitle(titleRes: Int): AlertDialogBuilder {
        this.titleRes = titleRes
        return this
    }

    override fun setTitleColor(color: Int): AlertDialogBuilder {
        this.titleColor = color
        return this
    }

    override fun setTitleColorRes(colorRes: Int): AlertDialogBuilder {
        this.titleColorRes = colorRes
        return this
    }

    override fun setMessage(message: String): AlertDialogBuilder {
        this.message = message
        return this
    }

    override fun setMessage(messageRes: Int): AlertDialogBuilder {
        this.messageRes = messageRes
        return this
    }

    override fun setMessageColor(color: Int): AlertDialogBuilder {
        this.messageColor = color
        return this
    }

    override fun setMessageColorRes(colorRes: Int): AlertDialogBuilder {
        this.messageColorRes = colorRes
        return this
    }

    override fun setItemList(items: List<String>, itemClickListener: ((Int) -> Unit)?): AlertDialogBuilder {
        this.itemList = items
        this.itemClickListener = itemClickListener
        return this
    }

    override fun setItemListRes(itemsRes: Int, itemClickListener: ((Int) -> Unit)?): AlertDialogBuilder {
        this.itemListRes = itemsRes
        this.itemClickListener = itemClickListener
        return this
    }

    override fun setBackgroundColor(color: Int): AlertDialogBuilder {
        this.backgroundColor = color
        return this
    }

    override fun setBackgroundColorRes(colorRes: Int): AlertDialogBuilder {
        this.backgroundColorRes = colorRes
        return this
    }

    override fun setPositiveButtonColor(color: Int): AlertDialogBuilder {
        this.positiveButtonColor = color
        return this
    }

    override fun setPositiveButtonColorRes(colorRes: Int): AlertDialogBuilder {
        this.positiveButtonColorRes = colorRes
        return this
    }

    override fun setNegativeButtonColor(color: Int): AlertDialogBuilder {
        this.negativeButtonColor = color
        return this
    }

    override fun setNegativeButtonColorRes(colorRes: Int): AlertDialogBuilder {
        this.negativeButtonColorRes = colorRes
        return this
    }

    override fun setCancellable(cancellable: Boolean): AlertDialogBuilder {
        this.cancellable = cancellable
        return this
    }

    override fun setCanceledOnTouchOutside(canceledOnTouchOutside: Boolean): AlertDialogBuilder {
        this.canceledOnTouchOutside = canceledOnTouchOutside
        return this
    }

    override fun build(): CommonAlertDialog {
        buildCount++
        return MockCommonAlertDialog(positiveButtonClickListener, negativeButtonClickListener).apply {
            lastBuiltDialog = this
        }
    }

    override fun show(): CommonAlertDialog {
        showCount++
        return build().also { it.show() }
    }
}