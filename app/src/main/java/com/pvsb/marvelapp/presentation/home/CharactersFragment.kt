package com.pvsb.marvelapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.pvsb.marvelapp.databinding.FragmentCharactersBinding
import com.pvsb.marvelapp.presentation.model.CharacterDetailNavArg
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!
    private val mAdapter: MainAdapter by lazy {
        MainAdapter() {

            findNavController().navigate(
                CharactersFragmentDirections.actionCharactersFragmentToCharacterDetail(
                    CharacterDetailNavArg(it.id, it.name, it.image)
                )
            )
        }
    }
    private val viewModel: CharactersVIewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onEnter()
    }

    private fun onEnter() {

        initAdapter()
        handleState()
        fetchData()

        binding.includeError.swipeError.setOnRefreshListener {
            fetchData()
        }
    }

    private fun fetchData() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.charactersPagingData("")
                    .collect { pagingData ->
                        mAdapter.submitData(pagingData)
                    }
            }
        }
    }

    private fun initAdapter() {

        binding.homeRecycler.apply {

            val loadFooter = LoadingStateAdapter { mAdapter.retry() }

            val gridLayout = GridLayoutManager(
                requireContext(),
                2, GridLayoutManager.VERTICAL,
                false
            )

            gridLayout.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val viewType = mAdapter.getItemViewType(position)
                    return if (viewType == 1) 1 else 2
                }
            }

            val footerAdapter = mAdapter.withLoadStateFooter(loadFooter)
            layoutManager = gridLayout
            adapter = footerAdapter
            scrollToPosition(0)
            setHasFixedSize(true)
        }
    }

    private fun handleState() {

        lifecycleScope.launch {
            mAdapter.loadStateFlow.collectLatest { state ->
                binding.viewFlipperCHaracters.displayedChild =

                    when (state.refresh) {
                        is LoadState.Loading -> {
                            setUpShimmer(true)
                            FLIPPER_LOAD
                        }
                        is LoadState.NotLoading -> {
                            setUpShimmer(false)
                            FLIPPER_DATA
                        }
                        is LoadState.Error -> {
                            setUpShimmer(false)
                            FLIPPER_ERROR
                        }
                    }
            }
        }
    }

    private fun setUpShimmer(visible: Boolean) {
        binding.includeError.swipeError.isRefreshing = visible
        binding.includeLoadingState.shimmerCharacters.apply {
            isVisible = visible
            if (visible) startShimmer()
            else stopShimmer()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val FLIPPER_LOAD = 0
        const val FLIPPER_DATA = 1
        const val FLIPPER_ERROR = 2
    }
}