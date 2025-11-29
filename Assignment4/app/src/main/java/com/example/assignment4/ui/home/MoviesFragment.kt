package com.example.assignment4.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment4.R
import com.example.assignment4.data.api.NetworkModule
import com.example.assignment4.data.database.AppDatabase
import com.example.assignment4.data.database.entity.toEntity
import com.example.assignment4.data.database.entity.toMovie
import com.example.assignment4.ui.adapter.MoviesAdapter
import kotlinx.coroutines.launch

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private lateinit var progress: ProgressBar
    private lateinit var errorText: TextView
    private lateinit var list: RecyclerView
    private val adapter = MoviesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progress = view.findViewById(R.id.progress)
        errorText = view.findViewById(R.id.errorText)
        list = view.findViewById(R.id.moviesList)

        list.layoutManager = LinearLayoutManager(requireContext())
        list.adapter = adapter

        loadMovies()
    }

    private fun showLoading() {
        progress.visibility = View.VISIBLE
        errorText.visibility = View.GONE
        list.visibility = View.GONE
    }

    private fun showError(msg: String) {
        progress.visibility = View.GONE
        errorText.visibility = View.VISIBLE
        list.visibility = View.GONE
        errorText.text = msg
    }

    private fun showContent() {
        progress.visibility = View.GONE
        errorText.visibility = View.GONE
        list.visibility = View.VISIBLE
    }

    private fun loadMovies() {
        val db = AppDatabase.getInstance(requireContext())
        val dao = db.movieDao()

        viewLifecycleOwner.lifecycleScope.launch {
            showLoading()

            try {

                val response = NetworkModule.api.getPopularMovies()

                val entities = response.results.map { it.toEntity() }
                dao.clearAll()
                dao.insertAll(entities)

                val cachedMovies = dao.getAll().map { it.toMovie() }
                adapter.submit(cachedMovies)
                showContent()

            } catch (e: Exception) {
                val cachedMovies = dao.getAll().map { it.toMovie() }

                if (cachedMovies.isNotEmpty()) {
                    adapter.submit(cachedMovies)
                    showContent()
                } else {
                    showError("No internet and no cached data.")
                }
            }
        }
    }
}
