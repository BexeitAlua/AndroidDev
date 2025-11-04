package com.example.assignment4

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment4.R
import com.example.assignment4.NetworkModule
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
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                showLoading()
                val response = NetworkModule.api.getPopularMovies()
                adapter.submit(response.results)
                showContent()
            } catch (e: Exception) {
                showError("Failed to load: ${e.message}")
            }
        }
    }
}