package com.natiqhaciyef.worldapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class CoroutinesViewModel(app: Application): AndroidViewModel(app),CoroutineScope {
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main      // evvel prosesi icra ele sonra Main Thread-a qayit

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}