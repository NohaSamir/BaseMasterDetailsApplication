package com.example.basemasterdetailsapplication.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.basemasterdetailsapplication.R
import com.example.basemasterdetailsapplication.data.source.network.models.ResultWrapper
import com.example.basemasterdetailsapplication.data.source.repository.dataRepository
import com.example.basemasterdetailsapplication.databinding.ListFragmentBinding
import com.example.basemasterdetailsapplication.domain.DummyData
import com.google.android.material.snackbar.Snackbar

class ListFragment : Fragment() {

    private lateinit var adapter: ListAdapter
    private lateinit var binding: ListFragmentBinding

    /**
     * Lazily initialize our [ListViewModel].
     */
    private val viewModel: ListViewModel by viewModels {
        ListViewModel.Factory(dataRepository)
    }

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the ListViewModel
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ListFragmentBinding.inflate(inflater)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        /*Initialize adapter and handle on item click */
        adapter = ListAdapter(object : OnClickListener {
            override fun onClick(data: DummyData) {
                findNavController().navigate(
                    ListFragmentDirections.actionListFragmentToDetailsFragment(
                        data
                    )
                )
            }
        })

        binding.recycler.adapter = adapter

        return binding.root
    }

    /**
     * Observe view model
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.list.observe(viewLifecycleOwner,
            Observer<List<DummyData>> {
                if (it != null) {
                    adapter.submitList(it)
                }
            })


        viewModel.networkList.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    is ResultWrapper.Success<*> -> {
                        //Do What you want with your response
                        //val list = it.value as List<NetworkDummyData>
                        Log.d("ListFrag Success ", it.toString())
                    }

                    is ResultWrapper.GenericError -> {
                        Log.d("ListFrag ", it.error?.message ?: "Generic Error")
                        showError()
                    }

                    is ResultWrapper.NetworkError -> {
                        Log.d("ListFrag", " Network Error ")
                        showError()
                    }
                }
            }
        })
    }

    private fun showError() {
        Snackbar.make(
            binding.root,
            getString(R.string.couldnt_refresh_feeds),
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(getString(R.string.retry)) {
                viewModel.refresh()
            }
            .show()
    }
}

