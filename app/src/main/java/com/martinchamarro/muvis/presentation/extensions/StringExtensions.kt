@file:JvmName("Strings")
package com.comprea.client.presentation.extensions

fun String?.isNotNullOrEmpty(): Boolean = !isNullOrEmpty()

fun String.notEquals(other: Any?): Boolean = !equals(other)



