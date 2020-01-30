package com.example.basemasterdetailsapplication.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.basemasterdetailsapplication.databinding.ListFragmentBinding
import com.example.basemasterdetailsapplication.domain.DummyData
import com.example.basemasterdetailsapplication.data.source.repository.dataRepository

class ListFragment : Fragment() {

    private lateinit var adapter: ListAdapter

    /**
     * Lazily initialize our [ListViewModel].
     */
    private val viewModel: ListViewModel by lazy {
        ViewModelProviders.of(this, ListViewModel.Factory(dataRepository))
            .get(ListViewModel::class.java)
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

        val binding = ListFragmentBinding.inflate(inflater)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        /*Initialize adapter and handle on item click */
        adapter = ListAdapter(object : OnClickListener {
            override fun onClick(data: DummyData) {
                findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailsFragment(data))
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
        viewModel.list.observe(this,
            Observer<List<DummyData>> {
                if (it != null) {
                    adapter.submitList(it)
                }
            })
    }
}

