package com.bharath.rickyandmortyseries.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bharath.rickyandmortyseries.Controller.CharacterDetailController
import com.bharath.rickyandmortyseries.databinding.FragmentDetailsBinding


/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : BaseFragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val safeArgs: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater ,container ,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterDetailsViewModel.getCharacterDetails(safeArgs.characterId)
        val detailsController  =  CharacterDetailController()
        characterDetailsViewModel.characterByIdLiveData.observe(viewLifecycleOwner){ response ->
            detailsController.characterDetailResponse = response
        }
        binding.epoxyRecyclerView.setControllerAndBuildModels(detailsController)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}