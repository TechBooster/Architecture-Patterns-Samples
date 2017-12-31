package jp.satorufujiwara.flux.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class ViewModelFactory @Inject
constructor(private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>)
  : StoreProvider {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    var creator: Provider<ViewModel>? = creators[modelClass]
    if (creator == null) {
      for ((key, value) in creators) {
        if (modelClass.isAssignableFrom(key)) {
          creator = value
          break
        }
      }
    }
    if (creator == null) throw IllegalArgumentException("unknown model class " + modelClass)
    try {
      return creator.get() as T
    } catch (e: Exception) {
      throw RuntimeException(e)
    }
  }

  override fun <T : ViewModel> get(activity: FragmentActivity, storeClass: KClass<T>) =
      ViewModelProviders.of(activity, this).get(storeClass.java)

  override fun <T : ViewModel> get(fragment: Fragment, storeClass: KClass<T>) =
      ViewModelProviders.of(fragment, this).get(storeClass.java)
}