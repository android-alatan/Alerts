package io.androidalatan.dialog

import android.app.Activity
import io.androidalatan.dialog.api.CommonAlertDialog
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class AlertDialogBuilderFactoryImplTest {

    private val activity = mock<Activity>()
    private val factory = AlertDialogBuilderFactoryImpl(lazy { activity })

    @Test
    fun create() {
        val dialogBuilder = factory.create()
        Assertions.assertNotNull(dialogBuilder)
        Assertions.assertTrue(dialogBuilder is AlertDialogBuilderImpl)
    }

    @Test
    fun `dismissPreviousAndSet() lastDialog is null`() {
        factory.lastDialog = null
        val dialog = mock<CommonAlertDialog>()
        factory.dismissPreviousAndSet(dialog)
        Assertions.assertEquals(dialog, factory.lastDialog)
    }

    @Test
    fun `dismissPreviousAndSet() lastDialog is not showing`() {
        val lastDialog = mock<CommonAlertDialog>()
        factory.lastDialog = lastDialog
        whenever(lastDialog.isShowing()).thenReturn(false)
        val dialog = mock<CommonAlertDialog>()
        factory.dismissPreviousAndSet(dialog)
        verify(lastDialog).isShowing()
        verify(lastDialog, never()).dismiss()
        Assertions.assertEquals(dialog, factory.lastDialog)
    }

    @Test
    fun `dismissPreviousAndSet() lastDialog is showing`() {
        val lastDialog = mock<CommonAlertDialog>()
        factory.lastDialog = lastDialog
        whenever(lastDialog.isShowing()).thenReturn(true)
        val dialog = mock<CommonAlertDialog>()
        factory.dismissPreviousAndSet(dialog)
        verify(lastDialog).isShowing()
        verify(lastDialog).dismiss()
        Assertions.assertEquals(dialog, factory.lastDialog)
    }
}