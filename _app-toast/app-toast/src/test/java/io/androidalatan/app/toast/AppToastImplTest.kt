package io.androidalatan.app.toast

import android.widget.Toast
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class AppToastImplTest {

    private val toast = Mockito.mock(Toast::class.java)
    private val appToast = AppToastImpl(toast)

    @Test
    fun show() {
        appToast.show()
        Mockito.verify(toast)
            .show()
    }
}