package jp.satorufujiwara.mvvm.util.ext

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (T?) -> Unit) = observe(owner, Observer {
  observer.invoke(it)
})

fun <X, Y> LiveData<X>.switchMap(func: (X) -> LiveData<Y>)
    = Transformations.switchMap(this, func)

operator fun ViewModelProvider.Factory.get(activity: FragmentActivity)
    = ViewModelProviders.of(activity, this)

operator fun ViewModelProvider.Factory.get(fragment: Fragment)
    = ViewModelProviders.of(fragment, this)