package io.androidalatan.compose.dialog

import androidx.compose.runtime.Composable
import io.androidalatan.compose.dialog.api.ComposeAlertDialog
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

class ComposeAlertDialogBuilderImplTest {

    private val dialogBuilder = ComposeAlertDialogBuilderImpl(mock(), mock())

    @Test
    fun setContentView() {
        val content: @Composable () -> Unit = { }
        dialogBuilder.setContentView(content)
        Assertions.assertEquals(dialogBuilder.content, content)
    }

    @Test
    fun `setPositiveButton string`() {
        val buttonText = "positive-button"
        val clickListener = ComposeAlertDialog.ButtonClickListener {}
        dialogBuilder.setPositiveButton(buttonText, clickListener)
        Assertions.assertEquals(dialogBuilder.positiveButtonText, buttonText)
        Assertions.assertEquals(dialogBuilder.positiveButtonClickListener, clickListener)
    }

    @Test
    fun `setPositiveButton res`() {
        val textRes = 123
        val clickListener = ComposeAlertDialog.ButtonClickListener {}
        dialogBuilder.setPositiveButton(textRes, clickListener)
        Assertions.assertEquals(dialogBuilder.positiveButtonTextRes, textRes)
        Assertions.assertEquals(dialogBuilder.positiveButtonClickListener, clickListener)
    }

    @Test
    fun `setNegativeButton string`() {
        val buttonText = "positive-button"
        val clickListener = ComposeAlertDialog.ButtonClickListener {}
        dialogBuilder.setNegativeButton(buttonText, clickListener)
        Assertions.assertEquals(dialogBuilder.negativeButtonText, buttonText)
        Assertions.assertEquals(dialogBuilder.negativeButtonClickListener, clickListener)
    }

    @Test
    fun `setNegativeButton res`() {
        val buttonTextRes = 123
        val clickListener = ComposeAlertDialog.ButtonClickListener {}
        dialogBuilder.setNegativeButton(buttonTextRes, clickListener)
        Assertions.assertEquals(dialogBuilder.negativeButtonTextRes, buttonTextRes)
        Assertions.assertEquals(dialogBuilder.negativeButtonClickListener, clickListener)
    }

    @Test
    fun `setTitle string`() {
        val title = "positive-button"
        dialogBuilder.setTitle(title)
        Assertions.assertEquals(dialogBuilder.title, title)
    }

    @Test
    fun `setTitle res`() {
        val titleRes = 1234
        dialogBuilder.setTitle(titleRes)
        Assertions.assertEquals(dialogBuilder.titleRes, titleRes)
    }

    @Test
    fun setTitleColor() {
        val color = 123
        dialogBuilder.setTitleColor(color)
        Assertions.assertEquals(dialogBuilder.titleColor, color)
    }

    @Test
    fun setTitleColorRes() {
        val color = 123
        dialogBuilder.setTitleColorRes(color)
        Assertions.assertEquals(dialogBuilder.titleColorRes, color)
    }

    @Test
    fun `setMessage string`() {
        val message = "hello"
        dialogBuilder.setMessage(message)
        Assertions.assertEquals(dialogBuilder.message, message)
    }

    @Test
    fun `setMessage res`() {
        val message = 1234
        dialogBuilder.setMessage(message)
        Assertions.assertEquals(dialogBuilder.messageRes, message)
    }

    @Test
    fun setMessageColor() {
        val msgColor = 1234
        dialogBuilder.setMessageColor(msgColor)
        Assertions.assertEquals(dialogBuilder.messageColor, msgColor)
    }

    @Test
    fun setMessageColorRes() {
        val msgColor = 1234
        dialogBuilder.setMessageColorRes(msgColor)
        Assertions.assertEquals(dialogBuilder.messageColorRes, msgColor)
    }

    @Test
    fun setItemList() {
        val items = listOf("1234", "312", "dhasd")
        val itemClickListener: (Int) -> Unit = {}
        dialogBuilder.setItemList(items, itemClickListener)
        Assertions.assertEquals(dialogBuilder.itemList, items)
        Assertions.assertEquals(dialogBuilder.itemClickListener, itemClickListener)
    }

    @Test
    fun setItemListRes() {
        val items = 123
        val itemClickListener: (Int) -> Unit = {}
        dialogBuilder.setItemListRes(items, itemClickListener)
        Assertions.assertEquals(dialogBuilder.itemListRes, items)
        Assertions.assertEquals(dialogBuilder.itemClickListener, itemClickListener)
    }

    @Test
    fun setPositiveButtonColor() {
        val buttonColor = 3123
        dialogBuilder.setPositiveButtonColor(buttonColor)
        Assertions.assertEquals(dialogBuilder.positiveButtonColor, buttonColor)
    }

    @Test
    fun setPositiveButtonColorRes() {
        val buttonColor = 3123
        dialogBuilder.setPositiveButtonColorRes(buttonColor)
        Assertions.assertEquals(dialogBuilder.positiveButtonColorRes, buttonColor)
    }

    @Test
    fun setNegativeButtonColor() {
        val buttonColor = 3123
        dialogBuilder.setNegativeButtonColor(buttonColor)
        Assertions.assertEquals(dialogBuilder.negativeButtonColor, buttonColor)
    }

    @Test
    fun setNegativeButtonColorRes() {
        val buttonColor = 3123
        dialogBuilder.setNegativeButtonColorRes(buttonColor)
        Assertions.assertEquals(dialogBuilder.negativeButtonColorRes, buttonColor)
    }

    @Test
    fun setCancellable() {
        val cancellable = false
        dialogBuilder.setCancellable(cancellable)
        Assertions.assertEquals(dialogBuilder.cancellable, cancellable)
    }

    @Test
    fun setCanceledOnTouchOutside() {
        val cancellable = false
        dialogBuilder.setCanceledOnTouchOutside(cancellable)
        Assertions.assertEquals(dialogBuilder.canceledOnTouchOutside, cancellable)
    }

    @Test
    fun build() {
        val clickListener = ComposeAlertDialog.ButtonClickListener {}
        val alertDialog = dialogBuilder.setTitle("Hello")
            .setMessage("World")
            .setPositiveButton("Confirm", clickListener)
            .build()

        Assertions.assertTrue(alertDialog is ComposeAlertDialogImpl)
    }
}