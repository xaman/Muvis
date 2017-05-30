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

import com.martinchamarro.muvis.domain.interactor.Interactor

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Executor of interactors implemented using a ThreadPoolExecutor
 */
@Singleton class InteractorExecutor @Inject constructor() : Executor {

    companion object {
        private val CORE_POOL_SIZE = 3
        private val MAX_POOL_SIZE = 5
        private val KEEP_ALIVE_TIME = 60L
        private val TIME_UNIT = TimeUnit.SECONDS
        private val WORK_QUEUE = LinkedBlockingQueue<Runnable>()
    }

    private val threadExecutor: ThreadPoolExecutor

    init {
        threadExecutor = ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TIME_UNIT, WORK_QUEUE)
    }

    override fun execute(interactor: Interactor?) {
        if (interactor == null) {
            throw IllegalArgumentException("Interactor to execute can't be null")
        }
        threadExecutor.submit(interactor)
    }
}