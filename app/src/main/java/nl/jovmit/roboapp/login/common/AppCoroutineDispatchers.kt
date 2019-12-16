package nl.jovmit.roboapp.login.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AppCoroutineDispatchers : CoroutineDispatchers {

    override val ui: CoroutineDispatcher = Dispatchers.Main

    override val background: CoroutineDispatcher = Dispatchers.IO
}