package com.example.nybooks.presentation.books

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nybooks.R
import com.example.nybooks.databinding.ActivityBooksBinding
import com.example.nybooks.presentation.base.BaseActivity
import com.example.nybooks.presentation.details.BookDetailsActivity

class BooksActivity : BaseActivity() {
    private lateinit var binding: ActivityBooksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBooksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar(binding.includeToolbar.toolbarMain, R.string.books_title)

        val viewModel: BooksViewModel = ViewModelProvider(this).get(BooksViewModel::class.java)

        viewModel.booksLiveData.observe(this) {
            it?.let { books ->
                with(binding.recyclerBooks) {
                    layoutManager =
                        LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = BooksAdapter(books){ book ->
                        val intent = BookDetailsActivity.getStartIntent(this@BooksActivity, book.title, book.description)
                        startActivity(intent)
                    }
                }
            }
        }

        viewModel.getBooks()
    }
}