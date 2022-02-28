package io.androidalatan.compose.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import io.androidalatan.compose.dialog.api.ComposeAlertDialogBuilder
import io.androidalatan.compose.dialog.api.ComposeAlertDialogBuilderFactory

class ComposeAlertDialogBuilderFactoryImpl : ComposeAlertDialogBuilderFactory {

    private val showState = mutableStateOf(false)
    private var contentState = mutableStateOf<@Composable () -> Unit>(@Composable {})

    @Composable
    fun activate() {
        val show by remember { showState }
        if (show) {
            val content by remember { contentState }
            content.invoke()
        }
    }

    override fun create(): ComposeAlertDialogBuilder {
        return ComposeAlertDialogBuilderImpl(showState, contentState)
    }
}