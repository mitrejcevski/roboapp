package nl.jovmit.roboapp.login.validation

import nl.jovmit.roboapp.login.data.LoginCredentials

interface CredentialsValidator {

    fun validate(credentials: LoginCredentials): Boolean
}
