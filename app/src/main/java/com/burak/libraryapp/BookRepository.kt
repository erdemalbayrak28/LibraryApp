package com.burak.libraryapp

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookRepository(private val bookDao: BookDao) {

    val allBooks: LiveData<List<Book>> = bookDao.getAllBooks()

    suspend fun insert(book: Book) {
        withContext(Dispatchers.IO) {
            bookDao.insertBook(book)
        }
    }

    suspend fun delete(book: Book) {
        withContext(Dispatchers.IO) {
            bookDao.deleteBook(book)
        }
    }
}