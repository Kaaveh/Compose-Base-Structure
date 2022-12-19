package ir.kaaveh.designsystem.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

open class BaseViewModel : ViewModel(), BaseContract {

    private val _baseState = MutableStateFlow(BaseContract.BaseState())
    override val baseState: StateFlow<BaseContract.BaseState> = _baseState.asStateFlow()

    protected val baseEffectChannel = Channel<BaseContract.BaseEffect>(Channel.UNLIMITED)
    override val baseEffect: Flow<BaseContract.BaseEffect> = baseEffectChannel.receiveAsFlow()

    override fun baseEvent(event: BaseContract.BaseEvent) = when (event) {
        BaseContract.BaseEvent.OnErrorBackPressed -> onErrorBackPressed()
        BaseContract.BaseEvent.OnErrorRetryPressed -> onErrorRetryPressed()
        else -> {}
    }

    private fun onErrorBackPressed() {
        baseEffectChannel.trySend(BaseContract.BaseEffect.OnErrorBackPressed)
    }

    private fun onErrorRetryPressed() {
        _baseState.update {
            it.copy(
                isLoading = true,
                isLoadingDialog = false,
                errorState = null,
            )
        }
        // Do something
    }

    fun showLoading() {
        _baseState.update {
            it.copy(
                isLoading = true,
                isLoadingDialog = false,
                errorState = null,
            )
        }
    }

    fun showContent() {
        _baseState.update {
            it.copy(
                isLoading = false,
                isLoadingDialog = false,
                errorState = null,
            )
        }
    }

    fun showLoadingDialog() {
        _baseState.update {
            it.copy(
                isLoading = false,
                isLoadingDialog = true,
                errorState = null,
            )
        }
    }

}