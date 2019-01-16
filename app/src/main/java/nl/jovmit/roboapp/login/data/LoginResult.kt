package nl.jovmit.roboapp.login.data

sealed class LoginResult {

    data class Loading(val value: Boolean) : LoginResult()

    data class Success(val user: User) : LoginResult()

    data class Failure(val reason: String) : LoginResult()
}
