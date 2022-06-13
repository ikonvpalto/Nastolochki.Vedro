package org.kvpbldsck.nastolochki.vedro.models

data class User(val name: String) {
    companion object {
        fun getTestUsers() = listOf(User("Валера"), User("Артем"), User("Ира"), User("Полина"), User("Дикий"))
    }
}