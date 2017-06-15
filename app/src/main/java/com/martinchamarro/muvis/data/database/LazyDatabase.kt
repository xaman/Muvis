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

package com.martinchamarro.muvis.data.database

import android.content.Context
import com.martinchamarro.lazystorage.LazyStorage
import com.martinchamarro.muvis.data.entity.MovieEntity
import javax.inject.Inject

class LazyDatabase @Inject constructor(context: Context) : Database {

    private val db = LazyStorage(context)
    private val movieClass = MovieEntity::class.java

    override fun save(movie: MovieEntity) = db.save(movie)

    override fun load(id: Int): MovieEntity? = db.load(id, movieClass)

    override fun loadAll(): List<MovieEntity> = db.loadAll(movieClass)

    override fun contains(id: Int) = load(id) != null

    override fun delete(id: Int) = db.delete(id, movieClass)

    override fun invalidate() = db.invalidate()
}