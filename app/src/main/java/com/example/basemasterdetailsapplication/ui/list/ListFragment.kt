package com.example.basemasterdetailsapplication.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.basemasterdetailsapplication.R
import com.example.basemasterdetailsapplication.database.AppDatabase
import com.example.basemasterdetailsapplication.domain.DummyData
import com.example.basemasterdetailsapplication.network.apiServices
import com.example.basemasterdetailsapplication.repository.DataRepository
import com.example.basemasterdetailsapplication.repository.dataRepository
import kotlinx.android.synthetic.main.list_fragment.view.*

class ListFragment : Fragment() {

    private lateinit var adapter: ListAdapter
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_fragment, container, false)

        adapter = ListAdapter(object : OnClickListener {
            override fun onClick(data: DummyData) {
                Navigation.findNavController(view)
                    .navigate(R.id.action_listFragment_to_detailsFragment)
            }

        })

        view.recycler.adapter = adapter

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        context?.let {

            viewModel =
                ViewModelProviders.of(this, ListViewModel.Factory(dataRepository)).get(ListViewModel::class.java)


            viewModel.list.observe(this,
                Observer<List<DummyData>> {
                    if (it != null) {
                        adapter.submitList(it)
                    }
                })
        }

    }

}
