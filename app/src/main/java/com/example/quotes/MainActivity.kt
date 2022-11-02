package com.example.quotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel

    private val quoteText : TextView
    get() = findViewById(R.id.quoteText)
    private val quoteAuthor : TextView
    get()= findViewById(R.id.quoteAuthor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
                     // here we are using context of application below so that change of conf. will not be able to destroy the object.
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModel::class.java)  // ViewModel object

        setQuote(mainViewModel.getQuote())  // accessing quotes from ViewModel


    }

    private fun setQuote(quote: Quote) {
        quoteText.text = quote.text
        quoteAuthor.text = quote.author
    }

    // function formed from xmls onclick attribute
    fun onPrevious(view: View) {
        setQuote(mainViewModel.previousQuote() )

    }
    fun onNext(view: View) {
        setQuote(mainViewModel.nextQuote())
    }
    fun share(view: View) {  // for sharing we use implicit intent
        val intent  = Intent(Intent.ACTION_SEND)  // intent for sending simple message
        intent.type = "text/plain"   // telling the intent about the type
        intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text) // this code is for getting the data from the viewModel
        intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuote().author)
        startActivity(intent)
    }
}