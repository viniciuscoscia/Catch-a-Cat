package com.viniciuscoscia.catchacat.presenter.util

fun <T> MutableList<T>.addAll(items: List<T>?) {
    if (items != null) addAll(items)
}