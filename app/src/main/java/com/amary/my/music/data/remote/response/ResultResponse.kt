package com.amary.my.music.data.remote.response


import com.google.gson.annotations.SerializedName
import com.amary.my.music.domain.model.Result

data class ResultResponse(
    @SerializedName("artistId")
    val artistId: Int? = null,
    @SerializedName("artistName")
    val artistName: String? = null,
    @SerializedName("artistViewUrl")
    val artistViewUrl: String? = null,
    @SerializedName("artworkUrl100")
    val artworkUrl100: String? = null,
    @SerializedName("artworkUrl30")
    val artworkUrl30: String? = null,
    @SerializedName("artworkUrl60")
    val artworkUrl60: String? = null,
    @SerializedName("collectionArtistId")
    val collectionArtistId: Int? = null,
    @SerializedName("collectionArtistName")
    val collectionArtistName: String? = null,
    @SerializedName("collectionCensoredName")
    val collectionCensoredName: String? = null,
    @SerializedName("collectionExplicitness")
    val collectionExplicitness: String? = null,
    @SerializedName("collectionHdPrice")
    val collectionHdPrice: Double? = null,
    @SerializedName("collectionId")
    val collectionId: Int? = null,
    @SerializedName("collectionName")
    val collectionName: String? = null,
    @SerializedName("collectionPrice")
    val collectionPrice: Double? = null,
    @SerializedName("collectionViewUrl")
    val collectionViewUrl: String? = null,
    @SerializedName("contentAdvisoryRating")
    val contentAdvisoryRating: String? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("discCount")
    val discCount: Int? = null,
    @SerializedName("discNumber")
    val discNumber: Int? = null,
    @SerializedName("isStreamable")
    val isStreamable: Boolean? = null,
    @SerializedName("kind")
    val kind: String? = null,
    @SerializedName("longDescription")
    val longDescription: String? = null,
    @SerializedName("previewUrl")
    val previewUrl: String? = null,
    @SerializedName("primaryGenreName")
    val primaryGenreName: String? = null,
    @SerializedName("releaseDate")
    val releaseDate: String? = null,
    @SerializedName("trackCensoredName")
    val trackCensoredName: String? = null,
    @SerializedName("trackCount")
    val trackCount: Int? = null,
    @SerializedName("trackExplicitness")
    val trackExplicitness: String? = null,
    @SerializedName("trackHdPrice")
    val trackHdPrice: Double? = null,
    @SerializedName("trackHdRentalPrice")
    val trackHdRentalPrice: Double? = null,
    @SerializedName("trackId")
    val trackId: Int? = null,
    @SerializedName("trackName")
    val trackName: String? = null,
    @SerializedName("trackNumber")
    val trackNumber: Int? = null,
    @SerializedName("trackPrice")
    val trackPrice: Double? = null,
    @SerializedName("trackRentalPrice")
    val trackRentalPrice: Double? = null,
    @SerializedName("trackTimeMillis")
    val trackTimeMillis: Int? = null,
    @SerializedName("trackViewUrl")
    val trackViewUrl: String? = null,
    @SerializedName("wrapperType")
    val wrapperType: String? = null
) {
    fun map(): Result {
        return Result(
            artistId = artistId ?: 0,
            artistName = artistName ?: "",
            artistViewUrl = artistViewUrl ?: "",
            artworkUrl100 = artworkUrl100 ?: "",
            artworkUrl30 = artworkUrl30 ?: "",
            artworkUrl60 = artworkUrl60 ?: "",
            collectionArtistId = collectionArtistId ?: 0,
            collectionArtistName = collectionArtistName ?: "",
            collectionCensoredName = collectionCensoredName ?: "",
            collectionExplicitness = collectionExplicitness ?: "",
            collectionHdPrice = collectionHdPrice ?: 0.0,
            collectionId = collectionId ?: 0,
            collectionName = collectionName ?: "",
            collectionPrice = collectionPrice ?: 0.0,
            collectionViewUrl = collectionViewUrl ?: "",
            contentAdvisoryRating = contentAdvisoryRating ?: "",
            country = country ?: "",
            currency = currency ?: "",
            discCount = discCount ?: 0,
            discNumber = discNumber ?: 0,
            isStreamable = isStreamable ?: false,
            kind = kind ?: "",
            longDescription = longDescription ?: "",
            previewUrl = previewUrl ?: "",
            primaryGenreName = primaryGenreName ?: "",
            releaseDate = releaseDate ?: "",
            trackCensoredName = trackCensoredName ?: "",
            trackCount = trackCount ?: 0,
            trackExplicitness = trackExplicitness ?: "",
            trackHdPrice = trackHdPrice ?: 0.0,
            trackHdRentalPrice = trackHdRentalPrice ?: 0.0,
            trackId = trackId ?: 0,
            trackName = trackName ?: "",
            trackNumber = trackNumber ?: 0,
            trackPrice = trackPrice ?: 0.0,
            trackRentalPrice = trackRentalPrice ?: 0.0,
            trackTimeMillis = trackTimeMillis ?: 0,
            trackViewUrl = trackViewUrl ?: "",
            wrapperType = wrapperType ?: ""
        )
    }
}