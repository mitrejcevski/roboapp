package nl.jovmit.roboapp.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nl.jovmit.roboapp.login.common.CoroutineDispatchers
import nl.jovmit.roboapp.login.common.CoroutineViewModel
import nl.jovmit.roboapp.login.data.LoginCredentials
import nl.jovmit.roboapp.login.data.LoginResult
import nl.jovmit.roboapp.login.validation.CredentialsValidator

class LoginViewModel(
    private val dispatchers: CoroutineDispatchers,
    private val validator: CredentialsValidator,
    private val loginRepository: LoginRepository
) : CoroutineViewModel(dispatchers) {

    private val loginLiveData = MutableLiveData<LoginResult>()

    fun loginLiveData(): LiveData<LoginResult> {
        return loginLiveData
    }

    fun login(credentials: LoginCredentials) {
        loginLiveData.value = LoginResult.Loading(true)
        if (validator.validate(credentials)) {
            triggerLogin(credentials)
        }
    }

    private fun triggerLogin(credentials: LoginCredentials) {
        launch(dispatchers.background) {
            val loginResult = loginRepository.performLogin(credentials)
            withContext(dispatchers.ui) {
                loginLiveData.value = loginResult
                loginLiveData.value = LoginResult.Loading(false)
            }
        }
    }
}
