package org.kvpbldsck.nastolochki.vedro.ui.models

data class UserModel(
    val name: String
) {

    companion object {
        fun getTestUsers() = listOf(UserModel("Валера"), UserModel("Артем"), UserModel("Ира"), UserModel("Полина"), UserModel("Дикий"))
    }

}