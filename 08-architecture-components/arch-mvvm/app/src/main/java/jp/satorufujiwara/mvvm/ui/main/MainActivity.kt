package jp.satorufujiwara.mvvm.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import jp.satorufujiwara.mvvm.R
import jp.satorufujiwara.mvvm.databinding.MainActivityBinding
import jp.satorufujiwara.mvvm.util.ext.get
import jp.satorufujiwara.mvvm.util.ext.observe
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

  @Inject lateinit var androidInjector: DispatchingAndroidInjector<Fragment>
  @Inject lateinit var providerFactory: ViewModelProvider.Factory
  private val viewModel by lazy { providerFactory[this][MainViewModel::class.java] }
  private val binding by lazy { DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity) }
  private val adapter = MainAdapter()
  private val ownerName = "satorufujiwara"

  override fun supportFragmentInjector() = androidInjector

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding.recyclerView.adapter = adapter
    binding.recyclerView.layoutManager = LinearLayoutManager(this)
    adapter.onItemClicked = {
      RepoDetailDialogFragment.newInstance(ownerName, it.name)
          .show(supportFragmentManager, "RepoDetailDialog")
    }

    viewModel.repos.observe(this) {
      it ?: return@observe
      adapter.run {
        items.clear()
        items.addAll(it)
        notifyDataSetChanged()
      }
    }
    viewModel.fetchRepo(ownerName)
  }
}


