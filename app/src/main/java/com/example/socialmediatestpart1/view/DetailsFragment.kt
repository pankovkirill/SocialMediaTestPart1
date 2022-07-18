package com.example.socialmediatestpart1.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import com.example.socialmediatestpart1.R
import com.example.socialmediatestpart1.databinding.FragmentDetailsBinding
import com.example.socialmediatestpart1.model.data.AppState
import com.example.socialmediatestpart1.model.data.BestSellerModel
import com.example.socialmediatestpart1.view.recyclerview.SimilarAdapter
import com.example.socialmediatestpart1.viewmodel.DetailsViewModel

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding

    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this)[DetailsViewModel::class.java]
    }

    private val adapter: SimilarAdapter by lazy { SimilarAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        val data = arguments?.getParcelable<BestSellerModel>(KEY)

        data?.let { renderDetails(it) }

        initViewModel()
        initView()

        binding.close.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun renderDetails(data: BestSellerModel) {
        with(binding) {
            image.load(data.image)
            title.text = data.title
            author.text = data.author
            amount.text = "(${data.rate.amount})"
            score.text = data.rate.score.toString()
            price.text = "${data.price}€"
        }
    }

    private fun initView() {
        binding.recyclerViewSimilar.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewSimilar.adapter = adapter
    }

    private fun initViewModel() {
        viewModel.liveData.observe(viewLifecycleOwner) { renderData(it) }
        viewModel.getData()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessSimilar -> {
                appState.data?.let {
                    if (it.isEmpty())
                        showLog("No data available")
                    else
                        adapter.setData(it)
                }
            }
            is AppState.Loading -> {
                showLog("Loading")
            }
            is AppState.Error -> {
                appState.error.message?.let {
                    showLog(it)
                }
            }
        }
    }

    private fun showLog(message: String) {
        Log.d("TAG", message)
    }

    companion object {
        private const val KEY = "key"

        fun newInstance(data: BestSellerModel) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY, data)
            }
        }
    }
}