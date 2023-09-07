package com.valentinerutto.anime.ui.home

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.valentinerutto.anime.databinding.FragmentHomeBinding
import com.valentinerutto.anime.ui.AnimeListAdapter
import com.valentinerutto.anime.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val viewmodel: MainViewModel by sharedViewModel<MainViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var page = 1
    var last_visible_page = 200
    val adapter = AnimeListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cm =
            requireActivity().getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting

        if (isConnected) {
        } else {
        }

        getDataFromApi(page)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.animeRecyclerView.layoutManager = GridLayoutManager(requireActivity(), 2)

        binding.animeProgressBar.isVisible = false

        setObservers()

        binding.fabRefresh.setOnClickListener {
            viewmodel._isLoading.value = true
            page++
            getDataFromApi(page)
        }

    }

  private  fun setObservers() {

        viewmodel.successfulAnimeListResponse.observe(viewLifecycleOwner) { animeList ->
            animeList?.map { last_visible_page = it.lastVisiblePage!! }
            binding.animeRecyclerView.adapter = adapter.apply {
                submitList(animeList)
            }
        }
        viewmodel.isLoading.observe(viewLifecycleOwner) { showLoading ->
            binding.animeProgressBar.isVisible = showLoading

        }
        viewmodel.errorAnimeListResponse.observe(viewLifecycleOwner) {
            binding.animeErrorTextView.text = it
        }
    }

    private fun getDataFromApi(page: Int) {

        if (page > last_visible_page) {
            binding.animeErrorTextView.text = "That is all the data"
            return
        }

        viewmodel.fetchAnimeList(page)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}