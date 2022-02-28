package io.androidalatan.app.toast

import android.content.Context
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class AppToastBuilderFactoryImplTest {

    private val context = Mockito.mock(Context::class.java)
    private val factory = AppToastBuilderFactoryImpl(context)

    @Test
    fun create() {
        Assertions.assertNotNull(factory.create())
    }
}