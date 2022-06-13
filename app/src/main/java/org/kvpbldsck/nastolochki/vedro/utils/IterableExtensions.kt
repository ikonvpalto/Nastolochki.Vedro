package org.kvpbldsck.nastolochki.vedro.utils

fun <T> Iterable<T>.minus(indexToRemove: Int): List<T> {
    return this.filterIndexed { index, _ -> index != indexToRemove }
}
