package ir.kaaveh.composebasestructure

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import ir.kaaveh.composebasestructure.my_feature.MyFeatureScreen
import ir.kaaveh.composebasestructure.my_feature.MyFeatureViewModel
import ir.kaaveh.composebasestructure.ui.theme.ComposeBaseStructureTheme
import ir.kaaveh.designsystem.base.BaseScreen
import ir.kaaveh.designsystem.base.BaseViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val baseViewModel by viewModels<BaseViewModel>()
        val myFeatureViewModel by viewModels<MyFeatureViewModel>()

        setContent {
            ComposeBaseStructureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BaseScreen(baseViewModel = baseViewModel) {
                        MyFeatureScreen(viewModel = myFeatureViewModel)
                    }
                }
            }
        }
    }
}