package nl.jovmit.roboapp.login

import nl.jovmit.roboapp.login.common.AppCoroutineDispatchers
import nl.jovmit.roboapp.login.common.CoroutineDispatchers
import nl.jovmit.roboapp.login.validation.CredentialsValidator
import nl.jovmit.roboapp.login.validation.LoginCredentialsValidator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val loginModule = module {

    single<CoroutineDispatchers> { AppCoroutineDispatchers() }
    single<CredentialsValidator> { LoginCredentialsValidator() }
    factory<LoginRepository> { RemoteLoginRepository(get()) }
    viewModel { LoginViewModel(get(), get(), get()) }

    single<LoginApi> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://my.api.nl/")
            .build()
        retrofit.create(LoginApi::class.java)
    }
}