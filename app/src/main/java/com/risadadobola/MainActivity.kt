package com.risadadobola

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.risadadobola.ui.theme.RisadaDoBolaTheme
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import androidx.compose.ui.viewinterop.AndroidView
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RisadaDoBolaTheme {
                val context = LocalContext.current
                val soundPlayer = SoundPlayer(context)
                val mainViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(soundPlayer))
                val shareManager = ShareManager(context, context.filesDir, ::File)
                val rateAppManager = RateAppManager(context)

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(innerPadding),
                        contentAlignment = Alignment.Center,
                    ) {
                        Card(
                            modifier =
                                Modifier
                                    .fillMaxSize(0.8f)
                                    .aspectRatio(1f)
                                    .padding(bottom = 64.dp),
                        ) {
                            Image(
                                painter = painterResource(id = if (mainViewModel.isPlaying) R.drawable.rindo else R.drawable.serio),
                                contentDescription = stringResource(id = R.string.image_content_description),
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize(),
                            )
                        }

                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                IconButton(
                                    onClick = { rateAppManager.rateApp() },
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = stringResource(id = R.string.rate_app_button_content_description),
                                        modifier = Modifier.size(48.dp),
                                        tint = MaterialTheme.colorScheme.primary,
                                    )
                                }

                                IconButton(
                                    onClick = { mainViewModel.onPlayPauseClick() },
                                ) {
                                    Icon(
                                        imageVector = if (mainViewModel.isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                        contentDescription =
                                            if (mainViewModel.isPlaying) {
                                                stringResource(
                                                    id = R.string.pause_button_content_description,
                                                )
                                            } else {
                                                stringResource(id = R.string.play_button_content_description)
                                            },
                                        modifier = Modifier.size(128.dp),
                                        tint = MaterialTheme.colorScheme.primary,
                                    )
                                }

                                IconButton(
                                    onClick = {
                                        shareManager.shareAudioFile(R.raw.risada, context.getString(R.string.share_message))
                                    },
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Share,
                                        contentDescription = stringResource(id = R.string.share_button_content_description),
                                        modifier = Modifier.size(48.dp),
                                        tint = MaterialTheme.colorScheme.primary,
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp)) // Space between buttons and AdView

                            AndroidView(
                                factory = {
                                    AdView(it).apply {
                                        setAdSize(AdSize.BANNER)
                                        adUnitId = "ca-app-pub-3940256099942544/6300978111"
                                        loadAd(AdRequest.Builder().build())
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
