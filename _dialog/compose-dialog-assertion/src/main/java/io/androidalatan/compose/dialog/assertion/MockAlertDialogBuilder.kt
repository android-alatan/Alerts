package io.androidalatan.compose.dialog.assertion

import android.graphics.Color
import android.view.View
import androidx.annotation.ArrayRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import io.androidalatan.compose.dialog.api.ComposeAlertDialog
import io.androidalatan.compose.dialog.api.ComposeAlertDialogBuilder

class MockAlertDialogBuilder() : ComposeAlertDialogBuilder {

    var canceledOnTouchOutside: Boolean = true

    var cancellable: Boolean = true

    var positiveButtonClickListener: ComposeAlertDialog.ButtonClickListener? = null

    var positiveButtonText: String = ""

    @StringRes
    var positiveButtonTextRes: Int = View.NO_ID

    @ColorInt
    var positiveButtonColor: Int = Color.TRANSPARENT

    @ColorRes
    var positiveButtonColorRes: Int = View.NO_ID

    var negativeButtonClickListener: ComposeAlertDialog.ButtonClickListener? = null

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

    var contentView: (@Composable () -> Unit)? = null

    var lastBuiltDialog: MockComposeAlertDialog? = null
    var buildCount = 0
    var showCount = 0

    override fun setContentView(content: @Composable () -> Unit): ComposeAlertDialogBuilder {
        this.contentView = content
        return this
    }

    override fun setPositiveButton(buttonText: String, clickListener: ComposeAlertDialog.ButtonClickListener?): ComposeAlertDialogBuilder {
        this.positiveButtonText = buttonText
        this.positiveButtonClickListener = clickListener
        return this
    }

    override fun setPositiveButton(textRes: Int, clickListener: ComposeAlertDialog.ButtonClickListener?): ComposeAlertDialogBuilder {
        this.positiveButtonTextRes = textRes
        this.positiveButtonClickListener = clickListener
        return this
    }

    override fun setNegativeButton(buttonText: String, clickListener: ComposeAlertDialog.ButtonClickListener?): ComposeAlertDialogBuilder {
        this.negativeButtonText = buttonText
        this.negativeButtonClickListener = clickListener
        return this
    }

    override fun setNegativeButton(textRes: Int, clickListener: ComposeAlertDialog.ButtonClickListener?): ComposeAlertDialogBuilder {
        this.negativeButtonTextRes = textRes
        this.negativeButtonClickListener = clickListener
        return this
    }

    override fun setTitle(title: String): ComposeAlertDialogBuilder {
        this.title = title
        return this
    }

    override fun setTitle(titleRes: Int): ComposeAlertDialogBuilder {
        this.titleRes = titleRes
        return this
    }

    override fun setTitleColor(color: Int): ComposeAlertDialogBuilder {
        this.titleColor = color
        return this
    }

    override fun setTitleColorRes(colorRes: Int): ComposeAlertDialogBuilder {
        this.titleColorRes = colorRes
        return this
    }

    override fun setMessage(message: String): ComposeAlertDialogBuilder {
        this.message = message
        return this
    }

    override fun setMessage(messageRes: Int): ComposeAlertDialogBuilder {
        this.messageRes = messageRes
        return this
    }

    override fun setMessageColor(color: Int): ComposeAlertDialogBuilder {
        this.messageColor = color
        return this
    }

    override fun setMessageColorRes(colorRes: Int): ComposeAlertDialogBuilder {
        this.messageColorRes = colorRes
        return this
    }

    override fun setItemList(items: List<String>, itemClickListener: ((Int) -> Unit)?): ComposeAlertDialogBuilder {
        this.itemList = items
        this.itemClickListener = itemClickListener
        return this
    }

    override fun setItemListRes(itemsRes: Int, itemClickListener: ((Int) -> Unit)?): ComposeAlertDialogBuilder {
        this.itemListRes = itemsRes
        this.itemClickListener = itemClickListener
        return this
    }

    override fun setPositiveButtonColor(color: Int): ComposeAlertDialogBuilder {
        this.positiveButtonColor = color
        return this
    }

    override fun setPositiveButtonColorRes(colorRes: Int): ComposeAlertDialogBuilder {
        this.positiveButtonColorRes = colorRes
        return this
    }

    override fun setNegativeButtonColor(color: Int): ComposeAlertDialogBuilder {
        this.negativeButtonColor = color
        return this
    }

    override fun setNegativeButtonColorRes(colorRes: Int): ComposeAlertDialogBuilder {
        this.negativeButtonColorRes = colorRes
        return this
    }

    override fun setCancellable(cancellable: Boolean): ComposeAlertDialogBuilder {
        this.cancellable = cancellable
        return this
    }

    override fun setCanceledOnTouchOutside(canceledOnTouchOutside: Boolean): ComposeAlertDialogBuilder {
        this.canceledOnTouchOutside = canceledOnTouchOutside
        return this
    }

    override fun build(): ComposeAlertDialog {
        buildCount++
        return MockComposeAlertDialog(positiveButtonClickListener, negativeButtonClickListener).apply {
            lastBuiltDialog = this
        }
    }

    override fun show(): ComposeAlertDialog {
        showCount++
        return build().also { it.show() }
    }
}