package ir.kaaveh.designsystem.base

import ir.kaaveh.designsystem.BaseUnidirectionalViewModel

interface BaseContract :
    BaseUnidirectionalViewModel<BaseContract.BaseEvent, BaseContract.BaseEffect, BaseContract.BaseState> {

    data class BaseState(
        val isLoading: Boolean = false,
        val isLoadingDialog: Boolean = false,
        val errorState: String? = null,
    )

    interface NewBaseState {
        object OnLoading : NewBaseState
        object OnLoadingDialog : NewBaseState
        object OnErrorState : NewBaseState
        object OnSuccess : NewBaseState
    }

    interface BaseEffect {
        object OnErrorBackPressed : BaseEffect
    }

    interface BaseEvent {
        object OnErrorBackPressed : BaseEvent
        object OnErrorRetryPressed : BaseEvent
    }

}