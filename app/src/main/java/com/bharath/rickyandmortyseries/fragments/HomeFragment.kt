package com.bharath.rickyandmortyseries.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bharath.rickyandmortyseries.Controller.CharacterListController
import com.bharath.rickyandmortyseries.databinding.FragmentHomeBinding


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater ,container ,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listController = CharacterListController(::onCharacterSelected)
        characterListViewModel.characterListLiveData.observe(viewLifecycleOwner){ listOfCharacters ->
            listController.submitList(listOfCharacters)
        }
        binding.epoxyRecyclerView.setController(listController)

    }

    private fun onCharacterSelected(characterId: Int){
        val navDirections = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(characterId)
        navigateWithData(navDirections)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}