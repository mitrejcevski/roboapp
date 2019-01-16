package nl.jovmit.roboapp.login

import nl.jovmit.roboapp.login.data.LoginCredentials
import nl.jovmit.roboapp.login.data.LoginResult

interface LoginRepository {

    fun performLogin(credentials: LoginCredentials): LoginResult
}
