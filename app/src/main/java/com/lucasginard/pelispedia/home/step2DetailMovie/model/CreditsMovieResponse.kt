package com.lucasginard.pelispedia.home.step2DetailMovie.model

import com.google.gson.annotations.SerializedName

data class CreditsMovieResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("cast") var cast: ArrayList<Cast> = arrayListOf(),
    @SerializedName("crew") var crew: ArrayList<Crew> = arrayListOf()
)

data class Cast(
    @SerializedName("adult") var adult: Boolean? = null,
    @SerializedName("gender") var gender: Int? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("known_for_department") var knownForDepartment: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("original_name") var originalName: String? = null,
    @SerializedName("popularity") var popularity: Double? = null,
    @SerializedName("profile_path") var profilePath: String? = null,
    @SerializedName("cast_id") var castId: Int? = null,
    @SerializedName("character") var character: String? = null,
    @SerializedName("credit_id") var creditId: String? = null,
    @SerializedName("order") var order: Int? = null
)

data class Crew(
    @SerializedName("adult") var adult: Boolean? = null,
    @SerializedName("gender") var gender: Int? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("known_for_department") var knownForDepartment: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("original_name") var originalName: String? = null,
    @SerializedName("popularity") var popularity: Double? = null,
    @SerializedName("profile_path") var profilePath: String? = null,
    @SerializedName("credit_id") var creditId: String? = null,
    @SerializedName("department") var department: String? = null,
    @SerializedName("job") var job: String? = null
)