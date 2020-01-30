package com.example.basemasterdetailsapplication.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.basemasterdetailsapplication.databinding.DetailsFragmentBinding

class DetailsFragment : Fragment() {

    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DetailsFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val dummyData = DetailsFragmentArgs.fromBundle(arguments!!).dummyData
        val viewModelFactory = DetailViewModelFactory(dummyData)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailsViewModel::class.java)
        binding.viewModel = viewModel

        return binding.root
    }
}
