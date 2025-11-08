package com.amary.my.music.data.remote.response

import com.amary.my.music.domain.model.Result
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultResponse(
    @SerialName("artistId")
    val artistId: Int? = null,
    @SerialName("artistName")
    val artistName: String? = null,
    @SerialName("artistViewUrl")
    val artistViewUrl: String? = null,
    @SerialName("artworkUrl100")
    val artworkUrl100: String? = null,
    @SerialName("artworkUrl30")
    val artworkUrl30: String? = null,
    @SerialName("artworkUrl60")
    val artworkUrl60: String? = null,
    @SerialName("collectionArtistId")
    val collectionArtistId: Int? = null,
    @SerialName("collectionArtistName")
    val collectionArtistName: String? = null,
    @SerialName("collectionCensoredName")
    val collectionCensoredName: String? = null,
    @SerialName("collectionExplicitness")
    val collectionExplicitness: String? = null,
    @SerialName("collectionHdPrice")
    val collectionHdPrice: Double? = null,
    @SerialName("collectionId")
    val collectionId: Int? = null,
    @SerialName("collectionName")
    val collectionName: String? = null,
    @SerialName("collectionPrice")
    val collectionPrice: Double? = null,
    @SerialName("collectionViewUrl")
    val collectionViewUrl: String? = null,
    @SerialName("contentAdvisoryRating")
    val contentAdvisoryRating: String? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("currency")
    val currency: String? = null,
    @SerialName("discCount")
    val discCount: Int? = null,
    @SerialName("discNumber")
    val discNumber: Int? = null,
    @SerialName("isStreamable")
    val isStreamable: Boolean? = null,
    @SerialName("kind")
    val kind: String? = null,
    @SerialName("longDescription")
    val longDescription: String? = null,
    @SerialName("previewUrl")
    val previewUrl: String? = null,
    @SerialName("primaryGenreName")
    val primaryGenreName: String? = null,
    @SerialName("releaseDate")
    val releaseDate: String? = null,
    @SerialName("trackCensoredName")
    val trackCensoredName: String? = null,
    @SerialName("trackCount")
    val trackCount: Int? = null,
    @SerialName("trackExplicitness")
    val trackExplicitness: String? = null,
    @SerialName("trackHdPrice")
    val trackHdPrice: Double? = null,
    @SerialName("trackHdRentalPrice")
    val trackHdRentalPrice: Double? = null,
    @SerialName("trackId")
    val trackId: Int? = null,
    @SerialName("trackName")
    val trackName: String? = null,
    @SerialName("trackNumber")
    val trackNumber: Int? = null,
    @SerialName("trackPrice")
    val trackPrice: Double? = null,
    @SerialName("trackRentalPrice")
    val trackRentalPrice: Double? = null,
    @SerialName("trackTimeMillis")
    val trackTimeMillis: Int? = null,
    @SerialName("trackViewUrl")
    val trackViewUrl: String? = null,
    @SerialName("wrapperType")
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