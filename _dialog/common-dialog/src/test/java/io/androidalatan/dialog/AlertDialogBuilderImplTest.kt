package io.androidalatan.dialog

import android.content.Context
import android.os.Build
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import io.androidalatan.dialog.api.CommonAlertDialog
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O])
@RunWith(RobolectricTestRunner::class)
class AlertDialogBuilderImplTest {

    private lateinit var builder: AlertDialogBuilderImpl
    private var showCount = 0

    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Before
    fun setUp() {
        builder = AlertDialogBuilderImpl(context) { showCount++ }
    }

    @Test
    fun `setContentView view`() {
        val view = TextView(context)
        builder.setContentView(view)
        Assertions.assertEquals(view, builder.contentView)
    }

    @Test
    fun `setContentView viewRes`() {
        val layoutRes = 14312
        builder.setContentView(layoutRes)
        Assertions.assertEquals(layoutRes, builder.contentViewRes)
    }

    @Test
    fun `setPositiveButton text`() {
        val clickListener = CommonAlertDialog.ButtonClickListener {}
        val buttonText = "hello"
        builder.setPositiveButton(buttonText, clickListener)
        Assertions.assertEquals(buttonText, builder.positiveButtonText)
        Assertions.assertEquals(clickListener, builder.positiveButtonClickListener)
    }

    @Test
    fun `setPositiveButton textRes`() {
        val clickListener = CommonAlertDialog.ButtonClickListener {}
        val buttonText = 1231
        builder.setPositiveButton(buttonText, clickListener)
        Assertions.assertEquals(buttonText, builder.positiveButtonTextRes)
        Assertions.assertEquals(clickListener, builder.positiveButtonClickListener)
    }

    @Test
    fun `setNegativeButton text`() {
        val clickListener = CommonAlertDialog.ButtonClickListener {}
        val buttonText = "1231"
        builder.setNegativeButton(buttonText, clickListener)
        Assertions.assertEquals(buttonText, builder.negativeButtonText)
        Assertions.assertEquals(clickListener, builder.negativeButtonClickListener)
    }

    @Test
    fun `setNegativeButton textRes`() {
        val clickListener = CommonAlertDialog.ButtonClickListener {}
        val buttonText = 1231
        builder.setNegativeButton(buttonText, clickListener)
        Assertions.assertEquals(buttonText, builder.negativeButtonTextRes)
        Assertions.assertEquals(clickListener, builder.negativeButtonClickListener)
    }

    @Test
    fun `setTitle text`() {
        val title = "hello"
        builder.setTitle(title)
        Assertions.assertEquals(title, builder.title)
    }

    @Test
    fun `setTitle textRes`() {
        val title = 1231
        builder.setTitle(title)
        Assertions.assertEquals(title, builder.titleRes)
    }

    @Test
    fun setTitleColor() {
        val color = 0xFF00FF
        builder.setTitleColor(color)
        Assertions.assertEquals(color, builder.titleColor)
    }

    @Test
    fun setTitleColorRes() {
        val color = 1234
        builder.setTitleColorRes(color)
        Assertions.assertEquals(color, builder.titleColorRes)
    }

    @Test
    fun `setMessage text`() {
        val message = "hello"
        builder.setMessage(message)
        Assertions.assertEquals(message, builder.message)
    }

    @Test
    fun `setMessage textRes`() {
        val message = 123
        builder.setMessage(message)
        Assertions.assertEquals(message, builder.messageRes)
    }

    @Test
    fun setMessageColor() {
        val color = 0xFF00FF
        builder.setMessageColor(color)
        Assertions.assertEquals(color, builder.messageColor)
    }

    @Test
    fun setMessageColorRes() {
        val colorRes = 124
        builder.setMessageColorRes(colorRes)
        Assertions.assertEquals(colorRes, builder.messageColorRes)
    }

    @Test
    fun backgroundColor() {
        val color = 0xFF00FF
        builder.setBackgroundColor(color)
        Assertions.assertEquals(color, builder.backgroundColor)
    }

    @Test
    fun backgroundColorRes() {
        val colorRes = 1234
        builder.setBackgroundColorRes(colorRes)
        Assertions.assertEquals(colorRes, builder.backgroundColorRes)
    }

    @Test
    fun positiveButtonColor() {
        val color = 0xFF00FF
        builder.setPositiveButtonColor(color)
        Assertions.assertEquals(color, builder.positiveButtonColor)
    }

    @Test
    fun positiveButtonColorRes() {
        val colorRes = 1234
        builder.setPositiveButtonColorRes(colorRes)
        Assertions.assertEquals(colorRes, builder.positiveButtonColorRes)
    }

    @Test
    fun negativeButtonColor() {
        val color = 0xFF00FF
        builder.setNegativeButtonColor(color)
        Assertions.assertEquals(color, builder.negativeButtonColor)
    }

    @Test
    fun negativeButtonColorRes() {
        val colorRes = 14213
        builder.setNegativeButtonColorRes(colorRes)
        Assertions.assertEquals(colorRes, builder.negativeButtonColorRes)
    }

    @Test
    fun setCancellable() {
        builder.setCancellable(true)
        Assertions.assertTrue(builder.cancellable)

        builder.setCancellable(false)
        Assertions.assertFalse(builder.cancellable)
    }

    @Test
    fun setCanceledOnTouchOutside() {
        builder.setCanceledOnTouchOutside(true)
        Assertions.assertTrue(builder.canceledOnTouchOutside)

        builder.setCanceledOnTouchOutside(false)
        Assertions.assertFalse(builder.canceledOnTouchOutside)
    }

    @Test
    fun setItemList() {
        val itemClickListener: (Int) -> Unit = {}
        val items = listOf("1", "2", "3")
        builder.setItemList(items, itemClickListener)
        Assertions.assertEquals(itemClickListener, builder.itemClickListener)
        Assertions.assertEquals(items, builder.itemList)
    }

    @Test
    fun setItemListRes() {
        val itemClickListener: (Int) -> Unit = {}
        val itemsRes = 321
        builder.setItemListRes(itemsRes, itemClickListener)
        Assertions.assertEquals(itemClickListener, builder.itemClickListener)
        Assertions.assertEquals(itemsRes, builder.itemListRes)
    }

    @Test
    fun build() {
        val commonAlertDialog = builder.setTitle("Hello")
            .setMessage("World~!")
            .setPositiveButton("OK") {}
            .build()

        Assertions.assertTrue(commonAlertDialog is CommonAlertDialogImpl)
    }

    @Ignore("Due to Roblectric doesn't support Material Theme")
    @Test
    fun show() {
        val commonAlertDialog = builder.setTitle("Hello")
            .setMessage("World~!")
            .setPositiveButton("OK") {}
            .show()

        Assertions.assertTrue(commonAlertDialog is CommonAlertDialogImpl)
        Assertions.assertEquals(1, showCount)
    }
}