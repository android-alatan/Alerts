package io.androidalatan.compose.dialog.assertion

import io.androidalatan.compose.dialog.api.ComposeAlertDialogBuilder
import io.androidalatan.compose.dialog.api.ComposeAlertDialogBuilderFactory

class MockAlertDialogBuilderFactory : ComposeAlertDialogBuilderFactory {
    var createCount = 0
    var lastDialogBuilder: MockAlertDialogBuilder? = null
    override fun create(): ComposeAlertDialogBuilder {
        createCount++
        return MockAlertDialogBuilder().apply { lastDialogBuilder = this }
    }
}