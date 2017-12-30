「6章 Fluxアーキテクチャ」のサンプルです
====

「プロダクトでの実装」以降の節のコードが入っています。
節のタイトルとコードの対応は以下の表のとおりです。

| 節 | リポジトリ |
|---|---|
| RepositoryおよびActionの実装</br>DispatcherとStoreの実装| [flux-normal](flux-normal) |
| EventBusとDataBindingを用いたDispacher、Storeの実装 | [flux-eventbus](flux-eventbus) |
| RxJava2を用いたDispatcher、Storeの実装| [flux-rxjava](flux-rxjava) |

また、それぞれのリポジトリ内でのパッケージ構成は次のようになっています。

| パッケージ | 主なクラス |
|---|---|
| .data | api/entity/repositoryとサブパッケージがあり、GitHubService,GitHubRepositoryクラスが入っている |
| .di | Dagger2のInjectionを行うためのクラス群 |
| .flux | 全画面共通で使うようなAction/Dispatcher/Storeインタフェース、BaseActivityなど |
| .ui.main | MainActivity/MainActionCreator/MainStore　など画面固有のクラス |
| .util | 拡張関数などのユーティリティクラス |




