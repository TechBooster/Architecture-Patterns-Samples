package jp.satorufujiwara.flux.ui.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import jp.satorufujiwara.flux.R
import jp.satorufujiwara.flux.databinding.MainActivityBinding
import jp.satorufujiwara.flux.flux.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector {

  @Inject lateinit var androidInjector: DispatchingAndroidInjector<Fragment>
  @Inject lateinit var actionCreator: MainActionCreator
  @Inject lateinit var store: MainStore
  private val binding by lazy { DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity) }
  private val adapter = MainAdapter()
  private val ownerName = "satorufujiwara"

  override fun supportFragmentInjector() = androidInjector

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding.recyclerView.adapter = adapter
    binding.recyclerView.layoutManager = LinearLayoutManager(this)
    adapter.onItemClicked = {
      actionCreator.showRepoDetailDialog(this, ownerName, it.name)
    }
    store.repos
        .subscribe {
          adapter.run {
            items.clear()
            items.addAll(it)
            notifyDataSetChanged()
          }
        }
        .disposeWhenDestroy()
    actionCreator.fetchRepo(ownerName)
  }
}


