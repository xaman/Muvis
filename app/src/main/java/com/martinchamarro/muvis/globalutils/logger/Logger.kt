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

package com.martinchamarro.muvis.globalutils.logger

import android.util.Log
import com.martinchamarro.muvis.Config

object Logger {

    private val MESSAGE_LIMIT = 3 * 1024

    fun i(tag: String? = null, msg: String? = null) {
        if (Config.DEBUG) Log.i(Config.APP_NAME, getText(tag, msg))
    }

    fun e(tag: String? = null, msg: String? = null) {
        Log.e(Config.APP_NAME, getText(tag, msg))
    }

    fun d(tag: String? = null, msg: String? = null) {
        if (Config.DEBUG) Log.d(Config.APP_NAME, getText(tag, msg))
    }

    fun v(tag: String? = null, msg: String? = null) {
        if (Config.DEBUG) Log.v(Config.APP_NAME, getText(tag, msg))
    }

    fun w(tag: String? = null, msg: String? = null) {
        if (Config.DEBUG) Log.w(Config.APP_NAME, getText(tag, msg))
    }

    fun wtf(tag: String? = null, msg: String? = null) {
        if (Config.DEBUG) Log.wtf(Config.APP_NAME, getText(tag, msg))
    }

    fun l(tag: String? = null, msg: String? = null) {
        if (!Config.DEBUG) return
        var msg = msg
        while (!msg.isNullOrEmpty() && msg!!.length > MESSAGE_LIMIT) {
            d(tag, msg.substring(0, MESSAGE_LIMIT))
            msg = msg.substring(MESSAGE_LIMIT)
        }
        d(tag, msg)
    }

    private fun getText(tag: String?, msg: String?): String {
        return if (tag.isNullOrEmpty()) "$msg" else "[$tag] $msg"
    }

}