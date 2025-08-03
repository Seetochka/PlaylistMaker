package com.practicum.playlistmaker

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.core.net.toUri

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activitySettings)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(top = systemBars.top)
            insets
        }

        findViewById<ImageView>(R.id.backToMain).setOnClickListener { finish() }
        findViewById<FrameLayout>(R.id.shareButton).setOnClickListener { shareContent() }
        findViewById<FrameLayout>(R.id.supportButton).setOnClickListener { sendEmail() }
        findViewById<FrameLayout>(R.id.agreementButton).setOnClickListener { openLink() }
    }

    private fun shareContent() {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, getString(R.string.share_link))
        }
        startActivity(Intent.createChooser(intent, getString(R.string.share_title)))
    }

    private fun sendEmail() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = "mailto:".toUri()
            putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.support_email)))
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.support_subject))
            putExtra(Intent.EXTRA_TEXT, getString(R.string.support_text))
        }
        startActivity(intent)
    }

    private fun openLink() {
        val url = getString(R.string.agreement_link).toUri()
        val intent = Intent(Intent.ACTION_VIEW, url)
        startActivity(intent)
    }
}