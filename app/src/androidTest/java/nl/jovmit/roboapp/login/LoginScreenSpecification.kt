package nl.jovmit.roboapp.login

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import nl.jovmit.roboapp.R
import nl.jovmit.roboapp.login.common.AppCoroutineDispatchers
import nl.jovmit.roboapp.login.data.LoginCredentials
import nl.jovmit.roboapp.login.data.User
import nl.jovmit.roboapp.login.validation.LoginCredentialsValidator
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module
import retrofit2.HttpException
import retrofit2.Response

@RunWith(AndroidJUnit4::class)
class LoginScreenSpecification {

    @get:Rule
    val rule = ActivityTestRule(LoginActivity::class.java, true, false)

    private val loginModule = module {
        val dispatchers = AppCoroutineDispatchers()
        val credentialsValidator = LoginCredentialsValidator()
        val loginApi = InMemoryLoginApi()
        val loginRepository = RemoteLoginRepository(loginApi)
        viewModel {
            LoginViewModel(dispatchers, credentialsValidator, loginRepository)
        }
    }

    @Before
    fun setUp() {
        loadKoinModules(loginModule)
        rule.launchActivity(Intent())
    }

    @Test
    fun should_display_incorrect_credentials_error() {
        onView(withId(R.id.loginUsername)).perform(typeText("username"))
        onView(withId(R.id.loginPassword)).perform(typeText("password"))

        onView(withId(R.id.loginButton)).perform(click())

        onView(withId(R.id.textInfoLabel)).check(matches(withText("Incorrect Credentials")))
    }

    @After
    fun tearDown() {
        unloadKoinModules(loginModule)
    }

    inner class InMemoryLoginApi : LoginApi {

        override suspend fun login(credentials: LoginCredentials): User {
            if (credentials.username == "username" && credentials.password == "password") {
                val responseBody = ResponseBody.create(null, "Incorrect Credentials")
                val response = Response.error<User>(400, responseBody)
                throw HttpException(response)
            }
            return with(credentials) { User(username, password, "about") }
        }
    }
}
