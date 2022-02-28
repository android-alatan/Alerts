package io.androidalatan.compose.dialog

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ComposeAlertDialogImplTest {

    private var showInvoker = false
    private val dialog = ComposeAlertDialogImpl { showInvoker = true }

    @Test
    fun show() {
        dialog.show()
        Assertions.assertTrue(showInvoker)
        Assertions.assertTrue(dialog.isShowing)
        Assertions.assertTrue(dialog.isShowing())
    }

    @Test
    fun dismiss() {
        dialog.dismiss()
        Assertions.assertTrue(showInvoker)
        Assertions.assertFalse(dialog.isShowing)
        Assertions.assertFalse(dialog.isShowing())

    }
}