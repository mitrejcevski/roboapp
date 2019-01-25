package nl.jovmit.roboapp.login

import kotlinx.coroutines.Deferred
import nl.jovmit.roboapp.login.data.LoginCredentials
import nl.jovmit.roboapp.login.data.User

interface LoginApi {

    fun login(credentials: LoginCredentials): Deferred<User>
}
