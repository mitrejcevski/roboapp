package nl.jovmit.roboapp.login.validation

import nl.jovmit.roboapp.login.data.LoginCredentials

class LoginCredentialsValidator : CredentialsValidator {

    override fun validate(credentials: LoginCredentials): Boolean {
        return with(credentials) {
            username.isNotBlank() && password.isNotBlank()
        }
    }
}
