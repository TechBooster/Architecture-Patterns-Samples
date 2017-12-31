package jp.satorufujiwara.flux.util.ext

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import jp.satorufujiwara.flux.data.entity.Repo


fun RecyclerView.Adapter<*>.createOnListChangedCallback() = object : ObservableList.OnListChangedCallback<ObservableList<Repo>>() {
  override fun onItemRangeMoved(list: ObservableList<Repo>?, from: Int, to: Int, count: Int) {
    notifyItemMoved(from, to)
  }

  override fun onItemRangeRemoved(list: ObservableList<Repo>?, start: Int, count: Int) {
    notifyItemRangeRemoved(start, count)
  }

  override fun onItemRangeChanged(list: ObservableList<Repo>?, start: Int, count: Int) {
    notifyItemRangeChanged(start, count)
  }

  override fun onItemRangeInserted(list: ObservableList<Repo>?, start: Int, count: Int) {
    notifyItemRangeInserted(start, count)
  }

  override fun onChanged(list: ObservableList<Repo>?) {
    notifyDataSetChanged()
  }
}