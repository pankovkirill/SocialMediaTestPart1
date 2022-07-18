package com.example.socialmediatestpart1.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.socialmediatestpart1.R
import com.example.socialmediatestpart1.databinding.ActivityMainBinding
import com.example.socialmediatestpart1.model.data.AppState
import com.example.socialmediatestpart1.model.data.BestSellerModel
import com.example.socialmediatestpart1.view.recyclerview.BestSellerAdapter
import com.example.socialmediatestpart1.view.recyclerview.CarouselAdapter
import com.example.socialmediatestpart1.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val bestAdapter: BestSellerAdapter by lazy { BestSellerAdapter(onListItemClickListener) }
    private val carouselAdapter: CarouselAdapter by lazy { CarouselAdapter() }

    private val onListItemClickListener: BestSellerAdapter.OnListItemClickListener =
        object : BestSellerAdapter.OnListItemClickListener {
            override fun onItemClick(data: BestSellerModel) {
                supportFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.container, DetailsFragment.newInstance(data))
                    .commit()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }


        initViewModel()
        initView()
    }

    private fun initView() {
        binding.carouselRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.carouselRecyclerView.adapter = carouselAdapter

        binding.bestSellerRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.bestSellerRecyclerView.adapter = bestAdapter
    }

    private fun initViewModel() {
        viewModel.carouselLiveData.observe(this) { renderBestData(it) }
        viewModel.bestLiveData.observe(this) { renderBestData(it) }
        viewModel.getData()
    }

    private fun renderBestData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                appState.error.message?.let {
                    showLog(it)
                }
            }
            is AppState.Loading -> {
                showLog("Loading")
            }
            is AppState.SuccessCarousel -> {
                appState.data?.let {
                    if (it.isEmpty())
                        showLog("No data available")
                    else
                        carouselAdapter.setData(it)
                }
            }

            is AppState.SuccessBest -> {
                appState.data?.let {
                    if (it.isEmpty())
                        showLog("No data available")
                    else
                        bestAdapter.setData(it)
                }
            }
        }
    }

    private fun showLog(message: String) {
        Log.d("TAG", message)
    }
}