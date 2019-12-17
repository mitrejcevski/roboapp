package nl.jovmit.roboapp.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_login.*
import nl.jovmit.roboapp.R
import nl.jovmit.roboapp.login.data.LoginCredentials
import nl.jovmit.roboapp.login.data.LoginResult
import nl.jovmit.roboapp.login.data.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val loginViewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupUI()
        observeLoginViewModel()
    }

    private fun setupUI() {
        loginButton.setOnClickListener {
            triggerLogin()
        }
    }

    private fun observeLoginViewModel() {
        loginViewModel.loginLiveData().observe(this, Observer {
            when (it) {
                is LoginResult.Loading -> showLoading(it.value)
                is LoginResult.Success -> onLoginSuccess(it.user)
                is LoginResult.Failure -> onLoginFailure(it.reason)
            }
        })
    }

    private fun showLoading(value: Boolean) {

    }

    private fun onLoginSuccess(user: User) {

    }

    private fun onLoginFailure(reason: String) {
        textInfoLabel.text = reason
    }

    private fun triggerLogin() {
        val usernameValue = loginUsername.text.toString()
        val passwordValue = loginPassword.text.toString()
        loginViewModel.login(LoginCredentials(usernameValue, passwordValue))
    }
}