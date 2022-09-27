package ir.kaaveh.designsystem.base

import androidx.lifecycle.ViewModel
import ir.kaaveh.designsystem.BaseUnidirectionalViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

data class BaseState(
    val isLoading: Boolean = false,
    val isLoadingDialog: Boolean = false,
    val errorState: String? = null,
)

sealed class BaseEffect {
    object OnErrorBackPressed : BaseEffect()
}

sealed class BaseEvent {
    object OnErrorBackPressed : BaseEvent()
    object OnErrorRetryPressed : BaseEvent()
}

open class BaseViewModel : ViewModel(),
    BaseUnidirectionalViewModel<BaseEvent, BaseEffect, BaseState> {

    private val _baseState = MutableStateFlow(BaseState())
    override val baseState: StateFlow<BaseState> = _baseState.asStateFlow()

    protected val baseEffectChannel = Channel<BaseEffect>(Channel.UNLIMITED)
    override val baseEffect: Flow<BaseEffect> = baseEffectChannel.receiveAsFlow()

    override fun baseEvent(event: BaseEvent) = when (event) {
        BaseEvent.OnErrorBackPressed -> onErrorBackPressed()
        BaseEvent.OnErrorRetryPressed -> onErrorRetryPressed()
    }

    private fun onErrorBackPressed() {
        baseEffectChannel.trySend(BaseEffect.OnErrorBackPressed)
    }

    private fun onErrorRetryPressed(){
        _baseState.update {
            it.copy(
                isLoading = true,
                isLoadingDialog = false,
                errorState = null,
            )
        }
        // Do something
    }
}