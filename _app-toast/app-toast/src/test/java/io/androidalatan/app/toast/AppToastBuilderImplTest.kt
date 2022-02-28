package io.androidalatan.app.toast

import android.content.Context
import android.widget.Toast
import io.androidalatan.app.toast.api.AppToastBuilder
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(value = [Toast::class])
class AppToastBuilderImplTest {

    private val context = Mockito.mock(Context::class.java)
    private val builder = AppToastBuilderImpl(context)

    @Test
    fun text() {
        val text = "Text"
        builder.text(text)
        Assertions.assertEquals(text, builder.text)
    }

    @Test
    fun textRes() {
        val textResId = 112
        builder.textResId(textResId)
        Assertions.assertEquals(textResId, builder.textResId)
    }

    @Test
    fun duration() {
        val duration = AppToastBuilder.Duration.Short
        builder.duration(duration)
        Assertions.assertEquals(duration, builder.duration)
    }

    @Test
    fun create() {
        PowerMockito.mockStatic(Toast::class.java)
        val text = "toast-1"
        val toast = mock<Toast>()
        whenever(Toast.makeText(context, text, Toast.LENGTH_LONG)).thenReturn(toast)
        val appToast = builder
            .text(text)
            .duration(AppToastBuilder.Duration.Long)
            .create()

        Assertions.assertTrue(appToast is AppToastImpl)

    }

    @Test
    fun show() {

        PowerMockito.mockStatic(Toast::class.java)
        val text = "toast-1"
        val toast = mock<Toast>()
        whenever(Toast.makeText(context, text, AppToastBuilder.Duration.Short)).thenReturn(toast)
        val appToast = builder
            .text(text)
            .duration(AppToastBuilder.Duration.Short)
            .show()

        Assertions.assertTrue(appToast is AppToastImpl)
        verify(toast).show()
    }
}