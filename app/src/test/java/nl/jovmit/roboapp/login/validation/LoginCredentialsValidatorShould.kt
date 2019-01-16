package nl.jovmit.roboapp.login.validation

import nl.jovmit.roboapp.login.data.LoginCredentials
import org.junit.Assert.*
import org.junit.Test

class LoginCredentialsValidatorShould {

    private val validator: CredentialsValidator = LoginCredentialsValidator()

    @Test
    fun return_true_for_valid_credentials() {
        val validCredentials = LoginCredentials("user", "pass")

        assertTrue(validator.validate(validCredentials))
    }

    @Test
    fun return_false_for_invalid_credentials() {
        val invalidCredentials = LoginCredentials("", "")

        assertFalse(validator.validate(invalidCredentials))
    }
}