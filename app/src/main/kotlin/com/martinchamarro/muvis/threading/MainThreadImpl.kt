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

package com.martinchamarro.muvis.threading

import android.os.Handler
import android.os.Looper

import javax.inject.Inject
import javax.inject.Singleton

/**

 * Class used to notify to the callback of the interactor in the main thread.
 * It is not possible to modify views from another thread.

 */
@Singleton class MainThreadImpl @Inject constructor() : MainThread {

    private val handler: Handler = Handler(Looper.getMainLooper())

    override fun post(runnable: () -> Unit) {
        this.handler.post(runnable)
    }

}
