package com.jagteshwar.spotifycloneapp

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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
                    val state = mainViewModel.status.collectAsState()
                    if(state.value.isNotEmpty()){
                        Text(
                            text = state.value,
                            modifier = Modifier.padding(innerPadding),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }else{
                        Text(
                            text = "Loading...",
                            modifier = Modifier.padding(innerPadding),
                            style = MaterialTheme.typography.bodyLarge
                        )
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpotifyCloneAppTheme {
        Greeting("Android")
    }
}