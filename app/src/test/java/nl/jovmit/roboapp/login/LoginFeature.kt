package nl.jovmit.roboapp.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.inOrder
import kotlinx.coroutines.runBlocking
import nl.jovmit.roboapp.login.common.TestCoroutineDispatchers
import nl.jovmit.roboapp.login.data.LoginCredentials
import nl.jovmit.roboapp.login.data.LoginResult
import nl.jovmit.roboapp.login.data.User
import nl.jovmit.roboapp.login.validation.LoginCredentialsValidator
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginFeature {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var loginLiveDataObserver: Observer<LoginResult>
    @Mock
    private lateinit var loginApi: LoginApi

    private val loggedInUser = User("username", "Full Name", "about")
    private val loginResults = LoginResult.Success(loggedInUser)
    private val enableLoading = LoginResult.Loading(true)
    private val disableLoading = LoginResult.Loading(false)
    private val loginCredentials = LoginCredentials("username", "password")

    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun initialize() {
        val loginRepository = RemoteLoginRepository(loginApi)
        val validator = LoginCredentialsValidator()
        val dispatchers = TestCoroutineDispatchers()
        loginViewModel = LoginViewModel(dispatchers, validator, loginRepository)
    }

    @Test
    fun perform_login() = runBlocking {
        given(loginApi.login(loginCredentials)).willReturn(loggedInUser)

        loginViewModel.loginLiveData().observeForever(loginLiveDataObserver)
        loginViewModel.login(loginCredentials)

        val inOrder = inOrder(loginLiveDataObserver)
        inOrder.verify(loginLiveDataObserver).onChanged(enableLoading)
        inOrder.verify(loginLiveDataObserver).onChanged(loginResults)
        inOrder.verify(loginLiveDataObserver).onChanged(disableLoading)
    }
}