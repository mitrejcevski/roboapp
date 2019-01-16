package nl.jovmit.roboapp.login

import nl.jovmit.roboapp.login.data.LoginCredentials
import nl.jovmit.roboapp.login.data.User

interface LoginApi {

    fun login(credentials: LoginCredentials): User
}
