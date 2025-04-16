package com.jagteshwar.spotifycloneapp

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.jagteshwar.spotifycloneapp.ui.feature.login.LoginScreen
import com.jagteshwar.spotifycloneapp.ui.theme.SpotifyCloneAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    var isSplashScreenVisible = true
    val mainViewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition{ isSplashScreenVisible }
            setOnExitAnimationListener{splashScreenViewProvider->
                val zoomX = ObjectAnimator.ofFloat(
                    splashScreenViewProvider.iconView,
                    "scaleX",
                    0.4f,
                    0f
                )
                val zoomY = ObjectAnimator.ofFloat(
                    splashScreenViewProvider.iconView,
                    "scaleY",
                    0.4f,
                    0f
                )

                zoomX.duration = 500
                zoomY.duration = 500
                zoomY.doOnEnd {
                    splashScreenViewProvider.remove()
                }
                zoomX.doOnEnd {
                    splashScreenViewProvider.remove()
                }

                zoomX.start()
                zoomY.start()


            }
        }
        enableEdgeToEdge()
        setContent {
            SpotifyCloneAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   Box(modifier = Modifier.padding(innerPadding)){
                       LoginScreen(navController = rememberNavController())
                   }

                }
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            isSplashScreenVisible = false
        }
    }
}
