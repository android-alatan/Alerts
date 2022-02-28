package io.androidalatan.dialog

import androidx.appcompat.app.AppCompatDialog
import io.androidalatan.dialog.api.CommonAlertDialog
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify

class CommonAlertDialogImplTest {

    private val actualDialog = mock<AppCompatDialog>()

    private var callbackCount = 0
    private val callback: (CommonAlertDialog) -> Unit = { callbackCount++ }

    private val commonDialog = CommonAlertDialogImpl(lazy { actualDialog }, callback)

    @AfterEach
    fun tearDown() {
        callbackCount = 0
    }

    @Test
    fun `show() already done`() {
        commonDialog.isShowing = true
        commonDialog.show()
        Assertions.assertEquals(0, callbackCount)
        Assertions.assertTrue(commonDialog.isShowing())
        verify(actualDialog, never()).show()
    }

    @Test
    fun `show() not yet`() {
        commonDialog.isShowing = false
        commonDialog.show()
        Assertions.assertEquals(1, callbackCount)
        Assertions.assertTrue(commonDialog.isShowing())
        verify(actualDialog).show()
    }

    @Test
    fun isShowing() {
        commonDialog.isShowing = false
        Assertions.assertFalse(commonDialog.isShowing())

        commonDialog.isShowing = true
        Assertions.assertTrue(commonDialog.isShowing())
    }

    @Test
    fun `dismiss() while showing`() {
        commonDialog.isShowing = true
        commonDialog.dismiss()
        Assertions.assertFalse(commonDialog.isShowing())
        verify(actualDialog).dismiss()
    }

    @Test
    fun `dismiss() not showing yet`() {
        commonDialog.isShowing = false
        commonDialog.dismiss()
        Assertions.assertFalse(commonDialog.isShowing())
        verify(actualDialog, never()).dismiss()
    }
}