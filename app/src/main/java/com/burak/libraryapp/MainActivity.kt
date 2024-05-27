package com.burak.libraryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.burak.libraryapp.ui.theme.LibraryAppTheme

class MainActivity : ComponentActivity() {
    private val bookViewModel by viewModels<BookViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibraryAppTheme {
                BookLibraryApp(bookViewModel)
            }
        }
    }
}

@Composable
fun BookLibraryApp(bookViewModel: BookViewModel) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Kitap Ekle", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = title,
            onValueChange = { title = it },
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                if (title.isEmpty()) {
                    Text("Kitap Başlığı")
                }
                innerTextField()
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = author,
            onValueChange = { author = it },
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                if (author.isEmpty()) {
                    Text("Yazar")
                }
                innerTextField()
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            bookViewModel.addBook(title, author)
            title = ""
            author = ""
        }) {
            Text("Ekle")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Kitap Listesi", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        BookList(books = bookViewModel.books, onRemove = { bookViewModel.removeBook(it) })
    }
}

@Composable
fun BookList(books: List<Book>, onRemove: (Book) -> Unit) {
    Column {
        books.forEach { book ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("${book.title} - ${book.author}")
                Button(onClick = { onRemove(book) }) {
                    Text("Sil")
                }
            }
        }
    }
}
