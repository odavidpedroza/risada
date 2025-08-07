package com.risadadobola

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

class ShareManager(private val context: Context, private val filesDir: File, private val fileFactory: (File, String) -> File = ::File) {
    fun shareAudioFile(
        audioResourceId: Int,
        message: String,
    ) {
        val file = fileFactory(filesDir, "risada.mp3")
        context.resources.openRawResource(audioResourceId).use { inputStream ->
            file.outputStream().use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }

        val uri: Uri =
            FileProvider.getUriForFile(
                context,
                context.applicationContext.packageName + ".fileprovider",
                file,
            )

        val shareIntent: Intent =
            Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, uri)
                putExtra(Intent.EXTRA_TEXT, message)
                type = "audio/mpeg"
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
        context.startActivity(Intent.createChooser(shareIntent, null))
    }
}
