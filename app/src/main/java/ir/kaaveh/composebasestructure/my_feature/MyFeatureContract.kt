package ir.kaaveh.composebasestructure.my_feature

import ir.kaaveh.designsystem.UnidirectionalViewModel
import ir.kaaveh.designsystem.base.BaseContract

interface MyFeatureContract :
    UnidirectionalViewModel<MyFeatureContract.Event, MyFeatureContract.Effect, MyFeatureContract.State> {

    data class State(
        val userMessage: String = "",
    )

    sealed class Effect {
        object OnBackPressed : Effect()
        data class ShowToast(val message: String) : Effect()
    }

    sealed class Event {
        object OnBackPressed : Event()
        data class ShowToast(val message: String) : Event()
    }

}