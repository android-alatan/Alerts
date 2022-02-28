package io.androidalatan.dialog.assertion

import io.androidalatan.dialog.api.AlertDialogBuilder
import io.androidalatan.dialog.api.AlertDialogBuilderFactory

class MockAlertDialogBuilderFactory : AlertDialogBuilderFactory {
    var createCount = 0
    var lastDialogBuilder: MockAlertDialogBuilder? = null
    override fun create(): AlertDialogBuilder {
        createCount++
        return MockAlertDialogBuilder().apply { lastDialogBuilder = this }
    }
}