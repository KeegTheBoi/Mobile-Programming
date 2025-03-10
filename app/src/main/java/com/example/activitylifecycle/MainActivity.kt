package com.example.activitylifecycle

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.example.activitylifecycle.ui.theme.ActivityLifeCycleTheme
import com.example.activitylifecycle.ui.theme.Purple80

private const val TAG = "MainActivity"
private const val TAG_COMPO = " @Composable"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "On Create")
        Toast.makeText(this, "On Create", Toast.LENGTH_SHORT).show()
        enableEdgeToEdge()
        setContent {
            ActivityLifeCycleTheme {
                Scaffold(
                    containerColor = Purple80,
                    modifier = Modifier.fillMaxSize()
                    ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private var startCounter = 0

    private fun logAndToast(msg: String) {
        Log.i(TAG, "Log on $msg")
        Toast.makeText(this, "On $msg", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        val state = "Started ${startCounter++} times"
        logAndToast(state)
    }

    override fun onStop() {
        super.onStop()
        val state = "Stop"
        logAndToast(state)
    }

    override fun onRestart() {
        super.onRestart()
        val state = "Restart"
        logAndToast(state)
    }

    override fun onResume() {
        super.onResume()
        val state = "Resume"
        logAndToast(state)
    }

    override fun onPause() {
        super.onPause()
        val msg = "Pause"
        logAndToast(msg)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )

    LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
        Log.i(TAG_COMPO, "on Create")
    }

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        Log.i(TAG_COMPO, "on Start")
    }

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        Log.i(TAG_COMPO, "on Resume")
    }

    LifecycleEventEffect(Lifecycle.Event.ON_PAUSE) {
        Log.i(TAG_COMPO, "on Pause")
    }

    LifecycleEventEffect(Lifecycle.Event.ON_STOP) {
        Log.i(TAG_COMPO, "on Stop")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ActivityLifeCycleTheme {
        Greeting("Android")
    }
}