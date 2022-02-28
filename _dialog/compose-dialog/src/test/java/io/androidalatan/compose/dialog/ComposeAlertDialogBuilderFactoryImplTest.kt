package io.androidalatan.compose.dialog

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ComposeAlertDialogBuilderFactoryImplTest {

    @Test
    fun create() {
        val factory = ComposeAlertDialogBuilderFactoryImpl()
        val builder = factory.create()
        Assertions.assertTrue(builder is ComposeAlertDialogBuilderImpl)
    }
}