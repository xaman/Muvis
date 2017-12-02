/*
 * Copyright 2017 Martin Chamarro (@martinchamarro)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.martinchamarro.muvis.presentation.extensions

import android.content.Context
import android.support.annotation.*
import android.support.v4.content.ContextCompat


fun Context.string(@StringRes stringRes: Int): String = getString(stringRes)

fun Context.dimen(@DimenRes dimenRes: Int) = resources.getDimensionPixelSize(dimenRes)

fun Context.integer(@IntegerRes integerRes: Int) = resources.getInteger(integerRes)

fun Context.color(@ColorRes colorRes: Int): Int = ContextCompat.getColor(this, colorRes)
