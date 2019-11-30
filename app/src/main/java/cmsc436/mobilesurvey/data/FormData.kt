package cmsc436.mobilesurvey.data

import androidx.annotation.Keep
import com.google.firebase.database.IgnoreExtraProperties

@Keep
@IgnoreExtraProperties
    data class FormData(
        val name: String = "",
        val content: String = "",
        val qrcode: String ="",
        val id: String = ""
    )

    data class DecodedFormData(
        val name: String = "",
        val content: String = "",
        val qrcode: ByteArray = ByteArray(8192),
        val id: String = ""
    )