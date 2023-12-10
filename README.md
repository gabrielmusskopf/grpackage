# gRPackage
![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)

Projeto de controle de entregas para demonstrar e aprender sobre protobuff, em especial, gRPC. Consiste em um servidor gRPC e um cliente, ambos escritos em Kotlin.
O cliente expõe alguns endpoints REST para a intereção com a aplicação, utilizando o framework Ktor, e integrage com o servidor através de protobuff.

## Tecnologias
- Kotlin
- Gradle (Groovy)
- gRPC, protobuff
- Ktor
- Exposed (ORM)
- Koin (injeção de dependência)

## Quickstart
Na raiz do projeto, buildar cliente e servidor
```shell
 ./gradlew clean installDist
```

Executar servidor e cliente
```shell
./server/build/install/server/bin/server
./client/build/install/client/bin/client
```
