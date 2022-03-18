package com.example.nybooks.presentation.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nybooks.data.model.Book
import com.example.nybooks.databinding.ItemBookBinding

class BooksAdapter(
    private val books: List<Book>
): RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val view = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BooksViewHolder(view)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bindView(books[position])
    }

    override fun getItemCount() = books.count()

    class BooksViewHolder(binding: ItemBookBinding): RecyclerView.ViewHolder(binding.root){

        private val title = binding.textTitle
        private val author = binding.textAuthor

        fun bindView(book: Book){
            title.text = book.title
            author.text = book.author
        }
    }
}