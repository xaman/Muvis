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

package com.martinchamarro.muvis.domain.model;

import com.google.auto.value.AutoValue;
import com.martinchamarro.muvis.Config;

@AutoValue public abstract class Movie {

    public abstract int id();
    public abstract String title();
    public abstract String originalTitle();
    public abstract String originalLanguage();
    public abstract String overview();
    public abstract String releaseDate();
    public abstract boolean forAdults();
    public abstract float popularity();
    public abstract int votesCount();
    public abstract float votesAverage();
    public abstract String posterPath();
    public abstract String backdropPath();

    public String posterFullPath() {
        return Config.PICTURE_DOMAIN + posterPath();
    }

    @Override public int hashCode() {
        return id();
    }

    @Override
    public boolean equals(Object o) {
        return o != null && o instanceof Movie && this.id() == ((Movie) o).id();
    }

    public static Builder builder() {
        return new AutoValue_Movie.Builder();
    }

    @AutoValue.Builder public abstract static class Builder {
        public abstract Builder setId(int value);
        public abstract Builder setTitle(String value);
        public abstract Builder setOriginalTitle(String value);
        public abstract Builder setOriginalLanguage(String value);
        public abstract Builder setOverview(String value);
        public abstract Builder setReleaseDate(String value);
        public abstract Builder setForAdults(boolean value);
        public abstract Builder setPopularity(float value);
        public abstract Builder setVotesCount(int value);
        public abstract Builder setVotesAverage(float value);
        public abstract Builder setPosterPath(String value);
        public abstract Builder setBackdropPath(String value);
        public abstract Movie build();
    }

}
