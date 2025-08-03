package com.practicum.playlistmaker

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.core.widget.addTextChangedListener

class SearchActivity : AppCompatActivity() {
    private var searchValue: String = SEARCH_DEF

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SEARCH_VALUE, searchValue)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        searchValue = savedInstanceState.getString(SEARCH_VALUE, SEARCH_DEF)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activitySearch)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(top = systemBars.top)
            insets
        }

        findViewById<ImageView>(R.id.backToMain).setOnClickListener { finish() }

        val clearButton = findViewById<ImageView>(R.id.clearButton)
        val searchInput = findViewById<EditText>(R.id.searchInput)

        clearButton.setOnClickListener {
            searchInput.setText("")
            hideKeyboard(searchInput)
        }

        searchInput.setText(searchValue)

        searchInput.addTextChangedListener(
            onTextChanged = { s, _, _, _ ->
                clearButton.isVisible = !s.isNullOrEmpty()
            },
            afterTextChanged = { s ->
                searchValue = s.toString()
            }
        )
    }

    private fun hideKeyboard(searchInput: EditText) {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(searchInput.windowToken, 0)
    }

    companion object {
        const val SEARCH_VALUE = "SEARCH_VALUE"
        const val SEARCH_DEF = ""
    }
}