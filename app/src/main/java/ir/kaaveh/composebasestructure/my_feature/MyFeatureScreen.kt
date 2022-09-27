package ir.kaaveh.composebasestructure.my_feature

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ir.kaaveh.designsystem.collectInLaunchedEffect
import ir.kaaveh.designsystem.use

@Composable
fun MyFeatureScreen(
    viewModel: MyFeatureViewModel
) {
    val (state, effect, event) = use(viewModel = viewModel)
    val activity = LocalContext.current as? Activity

    effect.collectInLaunchedEffect {
        when (it) {
            MyFeatureContract.Effect.OnBackPressed -> {
                activity?.onBackPressed()
            }
            is MyFeatureContract.Effect.ShowToast -> {
                Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(text = state.userMessage)

        Button(onClick = {
            event.invoke(MyFeatureContract.Event.OnBackPressed)
        }) {
            Text(text = "onBackPressed")
        }

        Spacer(modifier = Modifier.height(56.dp))

        Button(onClick = {
            event.invoke(MyFeatureContract.Event.ShowToast("Hiiiiiii!"))
        }) {
            Text(text = "Show Toast")
        }

    }
}