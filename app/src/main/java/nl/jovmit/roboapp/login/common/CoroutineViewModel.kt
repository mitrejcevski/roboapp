package nl.jovmit.roboapp.login.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class CoroutineViewModel(
    private val dispatchers: CoroutineDispatchers
) : ViewModel(), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = dispatchers.ui + job

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}
