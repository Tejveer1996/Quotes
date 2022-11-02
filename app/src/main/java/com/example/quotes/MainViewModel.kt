package com.example.quotes

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.nio.charset.Charset

class MainViewModel( val context: Context) : ViewModel() {
    private var quotesList: Array<Quote> = emptyArray()
    private var index = 0
    private var LastIndex =0

    init {
        quotesList = loadArrayOfQuotes()
        LastIndex = quotesList.lastIndex
    }

    private fun loadArrayOfQuotes(): Array<Quote> {
        // loading of quotes taken place from assets folder
        // Below code shows that how we read a asset file
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        // now converting above json into String
        val json = String(buffer, Charsets.UTF_8)  // encoding of json is Charsets.UTF_8
        val gson = Gson()   // converting json into java code through gson
        return gson.fromJson(json, Array<Quote>::class.java)


    }

    fun getQuote() = quotesList[index]
    fun nextQuote() = if (index == LastIndex){quotesList[index]}else{quotesList[++index]}
    fun previousQuote() =
        if (index == 0) {
            quotesList[index]
        } else {
            quotesList[--index]
        }



}