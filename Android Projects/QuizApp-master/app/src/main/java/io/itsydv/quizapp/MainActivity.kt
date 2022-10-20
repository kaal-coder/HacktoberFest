package io.itsydv.quizapp

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import io.itsydv.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

// converting latex to WebView using MathJax Script
fun loadData(webView: WebView, text: String) {
    webView.loadDataWithBaseURL("",  text, "text/html", "utf-8", null)
}