package jp.satorufujiwara.flux.flux

interface Action<out T> {
  val type: String
  val data: T
}