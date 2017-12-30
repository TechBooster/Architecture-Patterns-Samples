package jp.satorufujiwara.flux.flux

class ActivityLifecycleCallback {

  companion object {

    private val NONE = 0
    private val CREATE = 1
    private val START = 1 shl 1
    private val RESUME = 1 shl 2
    private val PAUSE = 1 shl 3
    private val STOP = 1 shl 4
    private val DESTROY = 1 shl 5

    private val ON_CREATE = CREATE
    private val ON_START = CREATE or START
    private val ON_RESUME = CREATE or START or RESUME
    private val ON_PAUSE = PAUSE
    private val ON_STOP = PAUSE or STOP
    private val ON_DESTROY = PAUSE or STOP or DESTROY
  }

  private var state = NONE

  private val onCreateCallbacks = HashSet<() -> Unit>()
  private val onDestroyCallbacks = HashSet<() -> Unit>()

  fun dispatchOnCreate() {
    state = ON_CREATE
    onCreateCallbacks.forEach { it() }
  }

  fun dispatchOnDestroy() {
    state = ON_DESTROY
    onDestroyCallbacks.forEach { it() }
  }

  fun clear() {
    onCreateCallbacks.clear()
    onDestroyCallbacks.clear()
  }

  fun on(onCreate: (() -> Unit)? = null, onDestroy: (() -> Unit)? = null) {
    onCreate?.let { addOnCreate(it) }
    onDestroy?.let { addOnDestroy(it) }
  }

  fun addOnCreate(onCreate: () -> Unit) {
    onCreateCallbacks.add(onCreate)
    if (state and ON_CREATE > 0) {
      onCreate()
    }
  }

  fun addOnDestroy(onDestroy: () -> Unit) {
    onDestroyCallbacks.add(onDestroy)
    if (state and ON_DESTROY > 0) {
      onDestroy()
    }
  }

}
