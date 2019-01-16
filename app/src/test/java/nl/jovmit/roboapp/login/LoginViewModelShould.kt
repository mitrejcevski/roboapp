package nl.jovmit.roboapp.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import nl.jovmit.roboapp.login.data.LoginCredentials
import nl.jovmit.roboapp.login.validation.CredentialsValidator
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

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
        loginViewModel = LoginViewModel(validator, loginRepository)
    }

    @Test
    fun perform_login() {
        given(validator.validate(credentials)).willReturn(true)

        loginViewModel.login(credentials)

        verify(loginRepository).performLogin(credentials)
    }

    @Test
    fun not_perform_login_with_invalid_credentials() {
        given(validator.validate(credentials)).willReturn(false)

        loginViewModel.login(credentials)

        verify(loginRepository, never()).performLogin(credentials)
    }
}