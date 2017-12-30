package jp.satorufujiwara.flux.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import kotlin.reflect.KClass

interface StoreProvider : ViewModelProvider.Factory {

  fun <T : ViewModel> get(activity: FragmentActivity, storeClass: KClass<T>): T

  fun <T : ViewModel> get(fragment: Fragment, storeClass: KClass<T>): T

}