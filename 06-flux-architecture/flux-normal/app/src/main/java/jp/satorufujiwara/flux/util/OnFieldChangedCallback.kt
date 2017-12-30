package jp.satorufujiwara.flux.util

import android.databinding.Observable
import android.databinding.ObservableField


class OnFieldChangedCallback<in T>(private val onChanged: (T) -> Unit) : Observable.OnPropertyChangedCallback() {

  override fun onPropertyChanged(sender: Observable, propertyId: Int) {
    (sender as? ObservableField<T>)?.run {
      onChanged(get())
    }
  }
}