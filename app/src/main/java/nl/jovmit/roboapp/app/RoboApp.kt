package nl.jovmit.roboapp.app

import android.app.Application
import nl.jovmit.roboapp.login.loginModule
import org.koin.core.context.startKoin

class RoboApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(loginModule)
        }
    }
}