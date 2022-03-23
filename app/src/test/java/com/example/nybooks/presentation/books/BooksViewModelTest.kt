package com.example.nybooks.presentation.books

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.nybooks.R
import com.example.nybooks.data.BooksResult
import com.example.nybooks.data.model.Book
import com.example.nybooks.data.repository.BooksRepository
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class BooksViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var booksLiveDataObserver: Observer<List<Book>>

    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int, Int?>>

    private lateinit var viewModel: BooksViewModel

    @Test
    fun `when view model getBooks get success then sets booksLiveData`(){
        // Arrange
        val books = listOf(
            Book("Title 1", "Author 1", "Description 1")
        )

        val resultSuccess = MockRepository(BooksResult.Success(books))
        viewModel = BooksViewModel(resultSuccess)
        viewModel.booksLiveData.observeForever(booksLiveDataObserver)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(booksLiveDataObserver).onChanged(books)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1, null))
    }

    @Test
    fun `when view model getBooks get server error then sets booksLiveData`(){
        // Arrange
        val resultServerEror = MockRepository(BooksResult.ServerError)
        viewModel = BooksViewModel(resultServerEror)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.books_error_500_generic))
    }
}

class MockRepository(private val result: BooksResult): BooksRepository {
    override fun getBooks(booksResultCallback: (result: BooksResult) -> Unit) {
        booksResultCallback(result)
    }
}