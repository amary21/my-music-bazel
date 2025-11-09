package com.amary.my.music.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amary.my.music.domain.model.Result

@Entity(tableName = "result")
data class ResultEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("artistId")
    val artistId: Int? = null,
    @ColumnInfo("artistName")
    val artistName: String? = null,
    @ColumnInfo("artistViewUrl")
    val artistViewUrl: String? = null,
    @ColumnInfo("artworkUrl100")
    val artworkUrl100: String? = null,
    @ColumnInfo("artworkUrl30")
    val artworkUrl30: String? = null,
    @ColumnInfo("artworkUrl60")
    val artworkUrl60: String? = null,
    @ColumnInfo("collectionArtistId")
    val collectionArtistId: Int? = null,
    @ColumnInfo("collectionArtistName")
    val collectionArtistName: String? = null,
    @ColumnInfo("collectionCensoredName")
    val collectionCensoredName: String? = null,
    @ColumnInfo("collectionExplicitness")
    val collectionExplicitness: String? = null,
    @ColumnInfo("collectionHdPrice")
    val collectionHdPrice: Double? = null,
    @ColumnInfo("collectionId")
    val collectionId: Int? = null,
    @ColumnInfo("collectionName")
    val collectionName: String? = null,
    @ColumnInfo("collectionPrice")
    val collectionPrice: Double? = null,
    @ColumnInfo("collectionViewUrl")
    val collectionViewUrl: String? = null,
    @ColumnInfo("contentAdvisoryRating")
    val contentAdvisoryRating: String? = null,
    @ColumnInfo("country")
    val country: String? = null,
    @ColumnInfo("currency")
    val currency: String? = null,
    @ColumnInfo("discCount")
    val discCount: Int? = null,
    @ColumnInfo("discNumber")
    val discNumber: Int? = null,
    @ColumnInfo("isStreamable")
    val isStreamable: Boolean? = null,
    @ColumnInfo("kind")
    val kind: String? = null,
    @ColumnInfo("longDescription")
    val longDescription: String? = null,
    @ColumnInfo("previewUrl")
    val previewUrl: String? = null,
    @ColumnInfo("primaryGenreName")
    val primaryGenreName: String? = null,
    @ColumnInfo("releaseDate")
    val releaseDate: String? = null,
    @ColumnInfo("trackCensoredName")
    val trackCensoredName: String? = null,
    @ColumnInfo("trackCount")
    val trackCount: Int? = null,
    @ColumnInfo("trackExplicitness")
    val trackExplicitness: String? = null,
    @ColumnInfo("trackHdPrice")
    val trackHdPrice: Double? = null,
    @ColumnInfo("trackHdRentalPrice")
    val trackHdRentalPrice: Double? = null,
    @ColumnInfo("trackId")
    val trackId: Int? = null,
    @ColumnInfo("trackName")
    val trackName: String? = null,
    @ColumnInfo("trackNumber")
    val trackNumber: Int? = null,
    @ColumnInfo("trackPrice")
    val trackPrice: Double? = null,
    @ColumnInfo("trackRentalPrice")
    val trackRentalPrice: Double? = null,
    @ColumnInfo("trackTimeMillis")
    val trackTimeMillis: Int? = null,
    @ColumnInfo("trackViewUrl")
    val trackViewUrl: String? = null,
    @ColumnInfo("wrapperType")
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

    companion object {
        fun from(result: Result): ResultEntity {
            return ResultEntity(
                artistId = result.artistId,
                artistName = result.artistName,
                artistViewUrl = result.artistViewUrl,
                artworkUrl100 = result.artworkUrl100,
                artworkUrl30 = result.artworkUrl30,
                artworkUrl60 = result.artworkUrl60,
                collectionArtistId = result.collectionArtistId,
                collectionArtistName = result.collectionArtistName,
                collectionCensoredName = result.collectionCensoredName,
                collectionExplicitness = result.collectionExplicitness,
                collectionHdPrice = result.collectionHdPrice,
                collectionId = result.collectionId,
                collectionName = result.collectionName,
                collectionPrice = result.collectionPrice,
                collectionViewUrl = result.collectionViewUrl,
                contentAdvisoryRating = result.contentAdvisoryRating,
                country = result.country,
                currency = result.currency,
                discCount = result.discCount,
                discNumber = result.discNumber,
                isStreamable = result.isStreamable,
                kind = result.kind,
                longDescription = result.longDescription,
                previewUrl = result.previewUrl,
                primaryGenreName = result.primaryGenreName,
                releaseDate = result.releaseDate,
                trackCensoredName = result.trackCensoredName,
                trackCount = result.trackCount,
                trackExplicitness = result.trackExplicitness,
                trackHdPrice = result.trackHdPrice,
                trackHdRentalPrice = result.trackHdRentalPrice,
                trackId = result.trackId,
                trackName = result.trackName,
                trackNumber = result.trackNumber,
                trackPrice = result.trackPrice,
                trackRentalPrice = result.trackRentalPrice,
                trackTimeMillis = result.trackTimeMillis,
                trackViewUrl = result.trackViewUrl,
                wrapperType = result.wrapperType
            )
        }
    }
}