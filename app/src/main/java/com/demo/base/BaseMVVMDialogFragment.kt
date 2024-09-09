package com.demo.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment

abstract class BaseMVVMDialogFragment<T : BaseMVVMViewModel<*, *, *, *>> : DialogFragment() {
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        observerState()
        observerEffect()
    }

    abstract fun observerState()

    abstract fun observerEffect()
}
