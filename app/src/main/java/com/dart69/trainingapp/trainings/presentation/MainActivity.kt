package com.dart69.trainingapp.trainings.presentation

import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dart69.mvvm.screens.Screen
import com.dart69.mvvm.viewmodels.repeatOnStarted
import com.dart69.trainingapp.R
import com.dart69.trainingapp.databinding.ActivityMainBinding
import com.dart69.trainingapp.trainings.presentation.recyclerview.MarginItemDecoration
import com.dart69.trainingapp.trainings.presentation.recyclerview.SchedulesAdapter
import com.faltenreich.skeletonlayout.applySkeleton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Screen<ActivityMainBinding, MainViewModel> {
    override val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override val viewModel: MainViewModel by viewModels()

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            viewModel.refresh()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val schedulesAdapter = SchedulesAdapter()
        val swipeRefreshLayout = binding.root.apply { setOnRefreshListener(viewModel::refresh) }
        val skeleton = binding.recyclerView.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = schedulesAdapter
            addItemDecoration(MarginItemDecoration())
            applySkeleton(R.layout.item_schedule, SKELETON_COUNT)
        }

        repeatOnStarted(this) {
            viewModel.collectStates { state ->
                skeleton.isSkeletonVisible = state.isLoading
                swipeRefreshLayout.isRefreshing = state.isLoading
                schedulesAdapter.submitList(state.items)
            }
        }

        repeatOnStarted(this) {
            viewModel.collectEvents { event ->
                when (event) {
                    is MainEvents.DisplayErrorMessage -> event.applyOn(binding.root)
                    is MainEvents.DisplayNetworkErrorDialog -> event.applyOn(this)
                    is MainEvents.OpenNetworkSettings -> event.applyOn(launcher)
                }
            }
        }
    }

    private companion object {
        const val SKELETON_COUNT = 20
    }
}