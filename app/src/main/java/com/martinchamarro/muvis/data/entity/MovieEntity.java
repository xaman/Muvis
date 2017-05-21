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

package com.martinchamarro.muvis.data.entity;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MovieEntity {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    @SerializedName("id") private int id;
    @SerializedName("title") private String title;
    @SerializedName("original_title") private String originalTitle;
    @SerializedName("original_language") private String originalLanguage;
    @SerializedName("overview") private String overview;
    @SerializedName("release_date") private String releaseDate;
    @SerializedName("adult") private boolean forAdults;
    @SerializedName("popularity") private float popularity;
    @SerializedName("vote_count") private int votesCount;
    @SerializedName("vote_average") private float votesAverage;
    @SerializedName("poster_path") private String posterPath;
    @SerializedName("backdrop_path") private String backdropPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Calendar getReleaseCalendar() {
        Calendar calendar = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
            calendar = new GregorianCalendar();
            calendar.setTime(format.parse(releaseDate));
        } catch (ParseException e) {}
        return calendar;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isForAdults() {
        return forAdults;
    }

    public void setForAdults(boolean forAdults) {
        this.forAdults = forAdults;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public int getVotesCount() {
        return votesCount;
    }

    public void setVotesCount(int votesCount) {
        this.votesCount = votesCount;
    }

    public float getVotesAverage() {
        return votesAverage;
    }

    public void setVotesAverage(float votesAverage) {
        this.votesAverage = votesAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieEntity)) return false;
        MovieEntity that = (MovieEntity) o;
        return getId() == that.getId();
    }

    @Override public int hashCode() {
        return getId();
    }

    @Override public String toString() {
        final StringBuffer sb = new StringBuffer("MovieEntity{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", originalTitle='").append(originalTitle).append('\'');
        sb.append(", originalLanguage='").append(originalLanguage).append('\'');
        sb.append(", overview='").append(overview).append('\'');
        sb.append(", releaseDate='").append(releaseDate).append('\'');
        sb.append(", isForAdults=").append(forAdults);
        sb.append(", popularity=").append(popularity);
        sb.append(", votesCount=").append(votesCount);
        sb.append(", votesAverage=").append(votesAverage);
        sb.append(", posterPath='").append(posterPath).append('\'');
        sb.append(", backdropPath='").append(backdropPath).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
