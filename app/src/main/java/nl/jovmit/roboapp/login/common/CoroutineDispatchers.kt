package nl.jovmit.roboapp.login.common

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatchers {

    val ui: CoroutineDispatcher

    val background: CoroutineDispatcher
}
