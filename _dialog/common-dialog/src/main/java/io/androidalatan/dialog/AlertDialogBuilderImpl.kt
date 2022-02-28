package io.androidalatan.dialog

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.TextView
import androidx.annotation.ArrayRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.androidalatan.alertdialog.R
import io.androidalatan.dialog.adapter.SimpleTextItemAdapter
import io.androidalatan.dialog.api.AlertDialogBuilder
import io.androidalatan.dialog.api.CommonAlertDialog

class AlertDialogBuilderImpl(
    private val context: Context,
    private val callbackBeforeShow: CallbackBeforeShow
) : AlertDialogBuilder {

    @VisibleForTesting
    internal var canceledOnTouchOutside: Boolean = true

    @VisibleForTesting
    internal var cancellable: Boolean = true

    @VisibleForTesting
    internal var positiveButtonClickListener: CommonAlertDialog.ButtonClickListener? = null

    @VisibleForTesting
    internal var positiveButtonText: String = ""

    @StringRes
    @VisibleForTesting
    internal var positiveButtonTextRes: Int = View.NO_ID

    @ColorInt
    @VisibleForTesting
    internal var positiveButtonColor: Int = Color.TRANSPARENT

    @ColorRes
    @VisibleForTesting
    internal var positiveButtonColorRes: Int = View.NO_ID

    @VisibleForTesting
    internal var negativeButtonClickListener: CommonAlertDialog.ButtonClickListener? = null

    @VisibleForTesting
    internal var negativeButtonText: String = ""

    @StringRes
    @VisibleForTesting
    internal var negativeButtonTextRes: Int = View.NO_ID

    @ColorInt
    @VisibleForTesting
    internal var negativeButtonColor: Int = Color.TRANSPARENT

    @ColorRes
    @VisibleForTesting
    internal var negativeButtonColorRes: Int = View.NO_ID

    @VisibleForTesting
    internal var title: String = ""

    @StringRes
    @VisibleForTesting
    internal var titleRes: Int = View.NO_ID

    @ColorInt
    @VisibleForTesting
    internal var titleColor: Int = Color.TRANSPARENT

    @ColorRes
    @VisibleForTesting
    internal var titleColorRes: Int = View.NO_ID

    @VisibleForTesting
    internal var message: String = ""

    @StringRes
    @VisibleForTesting
    internal var messageRes: Int = View.NO_ID

    @ColorInt
    @VisibleForTesting
    internal var messageColor: Int = Color.TRANSPARENT

    @ColorRes
    @VisibleForTesting
    internal var messageColorRes: Int = View.NO_ID

    @VisibleForTesting
    internal var itemList: List<String> = emptyList()

    @ArrayRes
    @VisibleForTesting
    internal var itemListRes: Int = View.NO_ID

    @VisibleForTesting
    internal var itemClickListener: ((Int) -> Unit)? = null

    @ColorInt
    @VisibleForTesting
    internal var backgroundColor: Int = Color.TRANSPARENT

    @ColorRes
    @VisibleForTesting
    internal var backgroundColorRes: Int = View.NO_ID

    @VisibleForTesting
    internal var contentView: View? = null

    @LayoutRes
    @VisibleForTesting
    internal var contentViewRes: Int = View.NO_ID

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

    override fun setBackgroundColor(color: Int): AlertDialogBuilder {
        this.backgroundColor = color
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
        return CommonAlertDialogImpl(lazy {
            val layoutInflater = LayoutInflater.from(context)
            val dialogRootView = layoutInflater.inflate(R.layout.dialog_common_dialog, null)
            val dialog = MaterialAlertDialogBuilder(context).setView(dialogRootView)
                .create()

            // title
            val titleViewStub = dialogRootView.findViewById<ViewStub>(R.id.viewstub_title)
            when {
                titleRes != View.NO_ID -> {
                    titleViewStub.visibility = View.VISIBLE
                    dialogRootView.findViewById<TextView>(R.id.text_dialog_title)
                        .also { it.setText(titleRes) }
                }
                title.isNotEmpty() -> {
                    titleViewStub.visibility = View.VISIBLE
                    dialogRootView.findViewById<TextView>(R.id.text_dialog_title)
                        .also { it.text = title }
                }
                else -> null
            }?.let { titleTextView ->
                if (titleColor != Color.TRANSPARENT) {
                    titleTextView.setTextColor(titleColor)
                } else if (titleColorRes != View.NO_ID) {
                    titleTextView.setTextColor(ContextCompat.getColor(context, titleColorRes))
                }
            }

            // content
            val dialogContentView = dialogRootView.findViewById<ViewGroup>(R.id.layout_content)
            when {
                contentView != null -> {
                    dialogContentView.addView(contentView)
                }
                contentViewRes != View.NO_ID -> {
                    layoutInflater.inflate(contentViewRes, dialogContentView, true)
                }
                else -> {
                    if (message.isNotEmpty() || messageRes != View.NO_ID) {
                        dialogRootView.findViewById<ViewStub>(R.id.viewstub_content)
                            .inflate()

                        val messageTextView = dialogRootView.findViewById<TextView>(R.id.text_dialog_message)
                        if (messageRes != View.NO_ID) {
                            messageTextView.setText(messageRes)
                        } else {
                            messageTextView.text = message
                        }

                        if (messageColor != Color.TRANSPARENT) {
                            messageTextView.setTextColor(messageColor)
                        } else if (messageColorRes != View.NO_ID) {
                            messageTextView.setTextColor(ContextCompat.getColor(context, messageColorRes))
                        }
                    }

                    if (itemList.isNotEmpty() || itemListRes != View.NO_ID) {
                        dialogRootView.findViewById<ViewStub>(R.id.viewstub_content_list)
                            .inflate()
                        val listView = dialogRootView.findViewById<RecyclerView>(R.id.rv_dialog_content_list)

                        val actualItems = when {
                            itemList.isNotEmpty() -> itemList
                            itemListRes != View.NO_ID -> listView.resources.getStringArray(itemListRes)
                                .toList()
                            else -> emptyList()
                        }

                        val textItemAdapter = SimpleTextItemAdapter(actualItems) { position ->
                            itemClickListener?.invoke(position)
                            dialog.dismiss()
                        }
                        listView.adapter = textItemAdapter
                    }
                }
            }

            // confirm/cancel buttons
            // confirm buttons
            val buttonViewStub = dialogRootView.findViewById<ViewStub>(R.id.viewstub_button)
            when {
                positiveButtonTextRes != View.NO_ID -> {
                    buttonViewStub.visibility = View.VISIBLE
                    dialogRootView.findViewById<TextView>(R.id.button_dialog_confirm)
                        .also { positiveBtn ->
                            positiveBtn.setText(positiveButtonTextRes)
                        }
                }
                positiveButtonText.isNotEmpty() -> {
                    buttonViewStub.visibility = View.VISIBLE
                    dialogRootView.findViewById<TextView>(R.id.button_dialog_confirm)
                        .also { positiveBtn ->
                            positiveBtn.text = positiveButtonText
                        }
                }
                else -> null
            }?.let { positiveBtn ->
                positiveBtn.visibility = View.VISIBLE
                positiveBtn.setOnClickListener {
                    dialog.dismiss()
                    positiveButtonClickListener?.onClick()
                }
                if (positiveButtonColor != Color.TRANSPARENT) {
                    positiveBtn.setTextColor(positiveButtonColor)
                } else if (positiveButtonColorRes != View.NO_ID) {
                    positiveBtn.setTextColor(ContextCompat.getColor(context, positiveButtonColorRes))
                }
            }

            // cancel buttons
            when {
                negativeButtonTextRes != View.NO_ID -> {
                    buttonViewStub.visibility = View.VISIBLE
                    dialogRootView.findViewById<TextView>(R.id.button_dialog_cancel)
                        .also { negativeBtn ->
                            negativeBtn.setText(negativeButtonTextRes)
                        }
                }
                negativeButtonText.isNotEmpty() -> {
                    buttonViewStub.visibility = View.VISIBLE
                    dialogRootView.findViewById<TextView>(R.id.button_dialog_cancel)
                        .also { negativeBtn ->
                            negativeBtn.text = negativeButtonText
                        }
                }
                else -> null
            }?.let { negativeBtn ->
                negativeBtn.visibility = View.VISIBLE
                negativeBtn.setOnClickListener {
                    dialog.dismiss()
                    negativeButtonClickListener?.onClick()
                }
                if (negativeButtonColor != Color.TRANSPARENT) {
                    negativeBtn.setTextColor(negativeButtonColor)
                } else if (negativeButtonColorRes != View.NO_ID) {
                    negativeBtn.setTextColor(ContextCompat.getColor(context, negativeButtonColorRes))
                }
            }

            dialog.setCancelable(cancellable)
            dialog.setCanceledOnTouchOutside(canceledOnTouchOutside)

            dialog
        }, callbackBeforeShow)
    }

    override fun show(): CommonAlertDialog {
        return build().also { it.show() }
    }
}