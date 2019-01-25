package nl.jovmit.roboapp.login

import nl.jovmit.roboapp.login.data.LoginCredentials
import nl.jovmit.roboapp.login.data.LoginResult
import retrofit2.HttpException

class RemoteLoginRepository(
    private val loginApi: LoginApi
) : LoginRepository {

    override suspend fun performLogin(credentials: LoginCredentials): LoginResult {
        return try {
            val loggedInUser = loginApi.login(credentials).await()
            LoginResult.Success(loggedInUser)
        } catch (e: HttpException) {
            LoginResult.Failure("Incorrect Credentials")
        }
    }
}
