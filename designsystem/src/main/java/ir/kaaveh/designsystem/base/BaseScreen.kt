package ir.kaaveh.designsystem.base

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import ir.kaaveh.designsystem.collectInLaunchedEffect
import ir.kaaveh.designsystem.useBase

@Composable
fun BaseScreen(
    baseViewModel: BaseViewModel,
    content: @Composable () -> Unit,
) {

    val (baseState, baseEffect, baseEvent) = useBase(viewModel = baseViewModel)

    val context = LocalContext.current
    val activity = context as? Activity

    baseEffect.collectInLaunchedEffect { effect ->
        when (effect) {
            BaseContract.BaseEffect.OnErrorBackPressed -> {
                activity?.onBackPressed()
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        if (baseState.isLoading) {
            // Show Loading view
        }

        baseState.errorState?.let {
            // Show Error view
        } ?: if (!baseState.isLoading) {
            content()
            if (baseState.isLoadingDialog) {
                // Show Loading dialog
            }
        }
    }
}