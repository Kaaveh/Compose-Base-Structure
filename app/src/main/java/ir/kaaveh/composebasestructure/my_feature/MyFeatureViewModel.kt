package ir.kaaveh.composebasestructure.my_feature

import ir.kaaveh.designsystem.base.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

class MyFeatureViewModel : BaseViewModel(), MyFeatureContract {

    private val effectChannel = Channel<MyFeatureContract.Effect>(Channel.UNLIMITED)
    private val mutableState = MutableStateFlow(MyFeatureContract.State(userMessage = "Helllo!!!"))

    override val state: StateFlow<MyFeatureContract.State> = mutableState.asStateFlow()
    override val effect: Flow<MyFeatureContract.Effect> = effectChannel.receiveAsFlow()

    override fun event(event: MyFeatureContract.Event) = when (event) {
        MyFeatureContract.Event.OnBackPressed -> onBackPressed()
        is MyFeatureContract.Event.ShowToast -> showToast(event.message)
    }

    private fun showToast(message: String) {
        /**
         * I like to eliminate effectChannel and use baseEffectChannel instead of
         *
         * baseEffectChannel.trySend(MyFeatureContract.Effect.ShowToast(message = message))
         */
        effectChannel.trySend(MyFeatureContract.Effect.ShowToast(message = message))
    }

    private fun onBackPressed() {
        effectChannel.trySend(MyFeatureContract.Effect.OnBackPressed)
    }

}