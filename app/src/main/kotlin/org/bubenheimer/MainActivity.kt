package org.bubenheimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                var dialogSelection by remember { mutableStateOf(0) }

                when (dialogSelection) {
                    1 -> FullScreenDialog { dialogSelection = 0 }
                    2 -> Material3AlertDialog { dialogSelection = 0 }
                }

                Column(Modifier.fillMaxSize().background(Red)) {
                    Spacer(Modifier.height(100.dp))

                    TextButton(onClick = { dialogSelection = 1 }) {
                        Text("Unthemed full screen dialog")
                    }

                    TextButton(onClick = { dialogSelection = 2 }) {
                        Text("Material3 AlertDialog")
                    }
                }
            }
        }
    }
}

@Composable
fun FullScreenDialog(onDismiss: () -> Unit) {
    Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = false,
                    usePlatformDefaultWidth = false,
                    decorFitsSystemWindows = true
            )
    ) {
        Surface(Modifier.fillMaxSize().background(Gray)) {
            Box(
                    Modifier.fillMaxSize().background(Green),
                    contentAlignment = Center
            ) { Text("Full screen dialog", Modifier.background(Yellow)) }
        }
    }
}

@Composable
fun Material3AlertDialog(onDismiss: () -> Unit) = AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = { TextButton(onClick = onDismiss) { Text("Ok") } },
        modifier = Modifier.background(Gray),
        text = {
            Text(
                    text = "Material 3 AlertDialog  with some really long text that means nothing. We want to fill things up mainly, so I'll type it up myself.",
                    modifier = Modifier.background(Yellow),
                    fontSize = TextUnit(30f, TextUnitType.Sp),
                    lineHeight = TextUnit(30f, TextUnitType.Sp)
            )
        },
        properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false,
                usePlatformDefaultWidth = false,
                decorFitsSystemWindows = false
        )
)
