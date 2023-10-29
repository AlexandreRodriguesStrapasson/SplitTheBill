package br.edu.scl.ifsp.ads.contatospdm.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @NonNull
    var name: String,
    @NonNull
    var amountToPaid: String,
    var receive: String,
    var pays: String,
): Parcelable