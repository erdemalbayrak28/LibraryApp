package com.burak.libraryapp
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookDao{
    @Query("SELECT * FROM books")
    fun getAllBooks(): LiveData<List<Book>>

    @Insert
    suspend fun insertBook(book: Book)
    @Delete
    suspend fun deleteBook(book: Book)
}