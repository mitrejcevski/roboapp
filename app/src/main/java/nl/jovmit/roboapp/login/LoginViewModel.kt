package nl.jovmit.roboapp.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nl.jovmit.roboapp.login.data.LoginCredentials
import nl.jovmit.roboapp.login.data.LoginResult
import nl.jovmit.roboapp.login.validation.CredentialsValidator

class LoginViewModel(
    private val validator: CredentialsValidator,
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val loginLiveData = MutableLiveData<LoginResult>()

    fun loginLiveData(): LiveData<LoginResult> {
        return loginLiveData
    }

    fun login(credentials: LoginCredentials) {
        if (validator.validate(credentials)) {
            loginLiveData.value = LoginResult.Loading(true)

            val loginResult = loginRepository.performLogin(credentials)
            loginLiveData.value = loginResult

            loginLiveData.value = LoginResult.Loading(false)
        }
    }
}
