package io.androidalatan.compose.dialog

import android.view.View
import androidx.annotation.ArrayRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import io.androidalatan.compose.dialog.api.ComposeAlertDialog
import io.androidalatan.compose.dialog.api.ComposeAlertDialogBuilder
import io.androidalatan.compose.dialog.ComposeAlertDialogBuilderImpl.Companion.COLOR_TRANSPARENT

class ComposeAlertDialogBuilderImpl(
    private val showState: MutableState<Boolean>,
    private val contentState: MutableState<@Composable () -> Unit>,
) : ComposeAlertDialogBuilder {
    @VisibleForTesting
    internal var canceledOnTouchOutside: Boolean = true

    @VisibleForTesting
    internal var cancellable: Boolean = true

    @VisibleForTesting
    internal var positiveButtonClickListener: ComposeAlertDialog.ButtonClickListener? = null

    @VisibleForTesting
    internal var positiveButtonText: String = ""

    @StringRes
    @VisibleForTesting
    internal var positiveButtonTextRes: Int = View.NO_ID

    @ColorInt
    @VisibleForTesting
    internal var positiveButtonColor: Int = COLOR_TRANSPARENT

    @ColorRes
    @VisibleForTesting
    internal var positiveButtonColorRes: Int = View.NO_ID

    @VisibleForTesting
    internal var negativeButtonClickListener: ComposeAlertDialog.ButtonClickListener? = null

    @VisibleForTesting
    internal var negativeButtonText: String = ""

    @StringRes
    @VisibleForTesting
    internal var negativeButtonTextRes: Int = View.NO_ID

    @ColorInt
    @VisibleForTesting
    internal var negativeButtonColor: Int = COLOR_TRANSPARENT

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
    internal var titleColor: Int = COLOR_TRANSPARENT

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
    internal var messageColor: Int = COLOR_TRANSPARENT

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

    internal var content: (@Composable () -> Unit)? = null

    override fun setContentView(content: @Composable () -> Unit): ComposeAlertDialogBuilder {
        this.content = content
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

        val content = @Composable {
            Dialog(
                onDismissRequest = { showState.value = false },
                properties = DialogProperties(
                    dismissOnBackPress = cancellable,
                    dismissOnClickOutside = canceledOnTouchOutside
                )
            ) {
                val colorOnTheme = MaterialTheme.colors
                Column(
                    modifier = Modifier
                        .padding(start = 32.dp, end = 32.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(colorOnTheme.primary)
                ) {

                    Title(colorOnTheme, title, titleRes, titleColor, titleColorRes)

                    if (content != null) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 8.dp)
                        ) {
                            content?.invoke()
                        }
                    } else {
                        Message(colorOnTheme, message, messageRes, messageColor, messageColorRes)
                        Items(colorOnTheme, itemList, itemListRes, itemClickListener, showState)
                    }

                    Buttons(
                        colorOnTheme,
                        positiveButtonText,
                        positiveButtonTextRes,
                        positiveButtonColor,
                        positiveButtonColorRes,
                        positiveButtonClickListener,
                        negativeButtonText,
                        negativeButtonTextRes,
                        negativeButtonColor,
                        negativeButtonColorRes,
                        negativeButtonClickListener,
                        showState
                    )

                }
            }
        }

        return ComposeAlertDialogImpl(showInvoker = { show ->
            contentState.value = content
            showState.value = show
        })
    }

    override fun show(): ComposeAlertDialog {
        return build().apply { show() }
    }

    companion object {
        const val COLOR_TRANSPARENT = 0x00000000 // AARRGGBB = 0
    }
}

@Composable
fun Title(colorOnTheme: Colors, title: String, titleRes: Int, titleColor: Int, titleColorRes: Int) {
    val titleText = when {
        title.isNotEmpty() -> title
        titleRes != View.NO_ID -> stringResource(id = titleRes)
        else -> ""
    }

    if (titleText.isNotEmpty()) {
        val titleTextColor = when {
            titleColor != COLOR_TRANSPARENT -> Color(titleColor)
            titleColorRes != View.NO_ID -> colorResource(id = titleColorRes)
            else -> colorOnTheme.onPrimary
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
                .height(56.dp)
        ) {
            Text(
                text = titleText,
                color = titleTextColor,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Center)
            )

        }
    }
}

@Composable
fun Message(colorOnTheme: Colors, message: String, messageRes: Int, messageColor: Int, messageColorRes: Int) {
    val messageText = when {
        message.isNotEmpty() -> message
        messageRes != View.NO_ID -> stringResource(id = messageRes)
        else -> ""
    }
    if (messageText.isNotEmpty()) {
        val msgTextColor = when {
            messageColor != COLOR_TRANSPARENT -> Color(messageColor)
            messageColorRes != View.NO_ID -> colorResource(id = messageColorRes)
            else -> colorOnTheme.onPrimary
        }
        Text(
            text = messageText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, 8.dp),
            fontSize = 20.sp,
            color = msgTextColor
        )
    }
}

@Composable
fun Items(
    colorOnTheme: Colors,
    itemList: List<String>,
    itemListRes: Int,
    itemClickListener: ((Int) -> Unit)?,
    showState: MutableState<Boolean>
) {
    val itemTexts = when {
        itemList.isNotEmpty() -> itemList.toTypedArray()
        itemListRes != View.NO_ID -> stringArrayResource(id = itemListRes)
        else -> emptyArray()
    }

    if (itemTexts.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp)
        ) {
            itemsIndexed(itemTexts) { index, text ->
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        showState.value = false
                        itemClickListener?.invoke(index)
                    }
                ) {
                    Text(
                        text = text,
                        color = colorOnTheme.onPrimary,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 8.dp,
                                end = 8.dp,
                                top = 8.dp,
                                bottom = 8.dp
                            )
                    )

                }
            }
        }
    }
}

@Composable
fun Buttons(
    colorOnTheme: Colors,
    positiveButtonText: String,
    positiveButtonTextRes: Int,
    positiveButtonColor: Int,
    positiveButtonColorRes: Int,
    positiveButtonClickListener: ComposeAlertDialog.ButtonClickListener?,
    negativeButtonText: String,
    negativeButtonTextRes: Int,
    negativeButtonColor: Int,
    negativeButtonColorRes: Int,
    negativeButtonClickListener: ComposeAlertDialog.ButtonClickListener?,
    showState: MutableState<Boolean>
) {
    val positiveBtnText = when {
        positiveButtonText.isNotEmpty() -> positiveButtonText
        positiveButtonTextRes != View.NO_ID -> stringResource(id = positiveButtonTextRes)
        else -> ""
    }

    val negativeBtnText = when {
        negativeButtonText.isNotEmpty() -> negativeButtonText
        negativeButtonTextRes != View.NO_ID -> stringResource(id = negativeButtonTextRes)
        else -> ""
    }

    if (positiveBtnText.isNotEmpty() or negativeBtnText.isNotEmpty()) {

        Column(
            modifier = Modifier
                .padding(top = 36.dp, bottom = 8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (positiveBtnText.isNotEmpty()) {
                val positiveTextColor = when {
                    positiveButtonColor != COLOR_TRANSPARENT -> Color(positiveButtonColor)
                    positiveButtonColorRes != View.NO_ID -> colorResource(id = positiveButtonColorRes)
                    else -> colorOnTheme.primary
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .clip(RoundedCornerShape(24.dp))
                        .background(colorOnTheme.onPrimary)
                        .defaultMinSize(minWidth = 198.dp, minHeight = 36.dp)
                        .clickable {
                            showState.value = false
                            positiveButtonClickListener?.onClick()
                        }
                ) {
                    Text(
                        text = positiveBtnText,
                        color = positiveTextColor,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            if (negativeBtnText.isNotEmpty()) {
                val negativeTextColor = when {
                    negativeButtonColor != COLOR_TRANSPARENT -> Color(negativeButtonColor)
                    negativeButtonColorRes != View.NO_ID -> colorResource(id = negativeButtonColorRes)
                    else -> colorOnTheme.onPrimary
                }
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            showState.value = false
                            negativeButtonClickListener?.onClick()
                        }
                ) {
                    Text(
                        text = negativeBtnText,
                        color = negativeTextColor,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                    )
                }
            }
        }

    }
}
