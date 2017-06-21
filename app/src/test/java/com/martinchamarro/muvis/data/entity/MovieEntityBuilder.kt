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

package com.martinchamarro.muvis.data.entity

class MovieEntityBuilder {
    companion object {
        fun build(): MovieEntity {
            return MovieEntity(
                    1,
                    "La Guerra de las Galaxias - Una Nueva Esperanza",
                    "Star Wars - A New Hope",
                    "en",
                    "La trama describe la historia de un grupo de guerrilleros —conocidos como la Alianza Rebelde— cuyo objetivo es destruir la estación espacial Estrella de la Muerte, creada por el opresor Imperio Galáctico. Desde una perspectiva general, la historia se enfoca en un joven granjero llamado Luke Skywalker, quien, de forma repentina, se convertirá en un héroe conforme acompaña al Maestro Jedi Obi-Wan Kenobi en una misión que lo llevará a unirse a la Alianza Rebelde para ayudarles a destruir la estación espacial del Imperio.",
                    "1977-03-25",
                    false,
                    123.45f,
                    1000,
                    8.0f,
                    "/a1d3c5b6c4dc7d8a4cb3abc.png",
                    "/4f5e7af8aef68efec9ee53e.png",
                    null
            )
        }
    }
}
