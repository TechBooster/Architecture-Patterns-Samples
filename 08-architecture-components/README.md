「8章 Architecture Components」のサンプルです
====


「Android Architecture Componentsが想定するアーキテクチャ」以降の節のコードが入っています。
節のタイトルとコードの対応は以下の表のとおりです。

| 節 | リポジトリ |
|---|---|
| Android Architecture ComponentsとMVVM| [arch-mvvm](arch-mvvm) |
| Architecture ComponentsとFlux | [arch-flux](arch-flux) |

また、それぞれのリポジトリ内でのパッケージ構成は次のようになっています。

| パッケージ | 主なクラス |
|---|---|
| .data | api/entity/repositoryとサブパッケージがあり、GitHubService,GitHubRepositoryクラスが入っている |
| .di | Dagger2のInjectionを行うためのクラス群、ViewModelFactoryなど |
| .flux | [arch-flux](arch-flux)のみで使用、Action/Dispatcher/StoreなどのFlux用のインタフェース |
| .ui.main | MainActivity/MainViewModel　など画面固有のクラス |
| .util | 拡張関数などのユーティリティクラス |
