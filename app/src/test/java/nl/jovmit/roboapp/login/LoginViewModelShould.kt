package nl.jovmit.roboapp.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verifyBlocking
import kotlinx.coroutines.runBlocking
import nl.jovmit.roboapp.login.common.TestCoroutineDispatchers
import nl.jovmit.roboapp.login.data.LoginCredentials
import nl.jovmit.roboapp.login.validation.CredentialsValidator
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.verification.VerificationMode

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelShould {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var loginRepository: LoginRepository
    @Mock
    private lateinit var validator: CredentialsValidator

    private val credentials = LoginCredentials("user", "pass")

    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun initialize() {
        val dispatchers = TestCoroutineDispatchers()
        loginViewModel = LoginViewModel(dispatchers, validator, loginRepository)
    }

    @Test
    fun perform_login() {
        given(validator.validate(credentials)).willReturn(true)

        loginViewModel.login(credentials)

        verifyBlocking(loginRepository) { performLogin(credentials) }
    }

    @Test
    fun not_perform_login_with_invalid_credentials() {
        given(validator.validate(credentials)).willReturn(false)

        loginViewModel.login(credentials)

        verifyBlocking(loginRepository, never()) { performLogin(credentials) }
    }

    private fun <T> verifyBlocking(
        mock: T,
        mode: VerificationMode,
        f: suspend T.() -> Unit
    ) {
        val m = Mockito.verify(mock, mode)
        runBlocking { m.f() }
    }
}