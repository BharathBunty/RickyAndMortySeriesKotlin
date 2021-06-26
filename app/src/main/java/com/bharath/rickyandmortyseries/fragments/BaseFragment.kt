package com.bharath.rickyandmortyseries.fragments

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import com.bharath.rickyandmortyseries.MainActivity

abstract class BaseFragment: Fragment() {


    protected val mainActivity: MainActivity
       get() = activity as MainActivity

    protected fun navigate(actionId: Int){
        mainActivity.navController.navigate(actionId)
    }

    protected fun navigateWithData(navDirections: NavDirections){
        mainActivity.navController.navigate(navDirections)
    }


}