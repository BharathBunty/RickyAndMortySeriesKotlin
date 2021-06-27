package com.bharath.rickyandmortyseries.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import com.bharath.rickyandmortyseries.MainActivity
import com.bharath.rickyandmortyseries.viewmodel.GetCharacterDetailsViewModel
import com.bharath.rickyandmortyseries.viewmodel.GetCharacterListViewModel

abstract class BaseFragment: Fragment() {


    protected val mainActivity: MainActivity
       get() = activity as MainActivity

    protected val characterDetailsViewModel: GetCharacterDetailsViewModel by activityViewModels()

    protected val characterListViewModel: GetCharacterListViewModel by activityViewModels()

    protected fun navigate(actionId: Int){
        mainActivity.navController.navigate(actionId)
    }

    protected fun navigateWithData(navDirections: NavDirections){
        mainActivity.navController.navigate(navDirections)
    }


}