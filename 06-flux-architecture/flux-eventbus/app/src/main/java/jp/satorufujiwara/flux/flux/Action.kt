package jp.satorufujiwara.flux.flux

interface Action<out T> {
  val data: T
}