package nl.jovmit.roboapp.login

import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.runBlocking
import nl.jovmit.roboapp.login.data.LoginCredentials
import nl.jovmit.roboapp.login.data.LoginResult
import nl.jovmit.roboapp.login.data.User
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class RemoteLoginRepositoryShould {

    @Mock
    private lateinit var loginApi: LoginApi

    private val loggedInUser = User("username", "full name", "about")
    private val success = LoginResult.Success(loggedInUser)
    private val failure = LoginResult.Failure("Incorrect Credentials")
    private val credentials = LoginCredentials("username", "pass")

    private lateinit var loginRepository: RemoteLoginRepository

    @Before
    fun initialize() {
        loginRepository = RemoteLoginRepository(loginApi)
    }

    @Test
    fun return_successful_result_when_login_successes() = runBlocking {
        given(loginApi.login(credentials)).willReturn(loggedInUser)

        val result = loginRepository.performLogin(credentials)

        assertEquals(success, result)
    }

    @Test
    fun return_failure_result_when_login_fails() = runBlocking {
        val response = Response.error<User>(401, ResponseBody.create(null, ""))
        given(loginApi.login(credentials)).willThrow(HttpException(response))

        val result = loginRepository.performLogin(credentials)

        assertEquals(failure, result)
    }
}