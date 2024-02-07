package com.example.shemajamebelin8.presentation.screen.home

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.shemajamebelin8.databinding.FragmentHomeBinding
import com.example.shemajamebelin8.presentation.adapter.HomeImageViewPagerAdapter
import com.example.shemajamebelin8.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.widget.ViewPager2
import com.example.shemajamebelin8.R
import java.lang.Math.abs

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()
    private val citiesAdapter = HomeImageViewPagerAdapter()

    override fun setupView() {
        setUpPager()
    }


    override fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.citiesStateFlow.collect {
                    handleState(it)
                }
            }
        }
    }


    private fun handleState(citiesState: CitiesState) {
        citiesState.cities?.let {
            citiesAdapter.submitList(it)
        }
    }

    private fun setUpPager() {
        with(binding.pager) {
            adapter = citiesAdapter
            offscreenPageLimit = 1

            val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
            val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
            val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
            val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
                page.translationX = -pageTranslationX * position
                page.scaleY = 1 - (0.25f * abs(position))
            }
            setPageTransformer(pageTransformer)

            val itemDecoration = HorizontalMarginItemDecoration(
                requireContext(),
                R.dimen.viewpager_current_item_horizontal_margin
            )
            addItemDecoration(itemDecoration)
        }

    }


}