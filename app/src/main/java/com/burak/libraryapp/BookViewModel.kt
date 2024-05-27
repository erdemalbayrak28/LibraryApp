package com.burak.libraryapp

import android.icu.text.CaseMap.Title
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.launch

class BookViewModel(application: Application) : AndroidViewModel(application) {
    private val bookRepository: BookRepository
    val allBooks: LiveData<List<Book>>
    var books = mutableStateListOf<Book>()
        private set

    init {
        val database = AppDatabase.getDatabase(application, viewModelScope)
        val bookDao = database.bookDao()
        bookRepository = BookRepository(bookDao)
        allBooks = bookRepository.allBooks
    }

    fun addBook(title: String, author: String) {
        viewModelScope.launch {
            bookRepository.insert(Book(title = title, author = author))
        }
    }

    fun removeBook(book: Book) {
        viewModelScope.launch {
            bookRepository.delete(book)
        }
    }
}