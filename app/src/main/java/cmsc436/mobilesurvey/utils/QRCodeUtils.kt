package cmsc436.mobilesurvey.utils

import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.util.Base64
import android.util.Log
import android.widget.Toast
import cmsc436.mobilesurvey.R
import cmsc436.mobilesurvey.forms.QRGeneratorFragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.nio.ByteBuffer


class QRCodeUtils{

    private var mQRGeneratorFragment: QRGeneratorFragment? = null
    private val directory = "/QRcodeDemonuts"

    fun convertBitmapToByteArray(bitmap: Bitmap): ByteArray {
        var baos: ByteArrayOutputStream? = null
        try {
            baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
            return baos!!.toByteArray()
        } finally {
            if (baos != null) {
                try {
                    baos!!.close()
                } catch (e: IOException) {
                    Log.e(
                        QRCodeUtils::class.java!!.getSimpleName(),
                        "ByteArrayOutputStream was not closed"
                    )
                }

            }
        }
    }

    /**
     * Converts bitmap to the byte array without compression
     * @param bitmap source bitmap
     * @return result byte array
     */
    fun convertBitmapToByteArrayUncompressed(bitmap: Bitmap): ByteArray {
        val byteBuffer = ByteBuffer.allocate(bitmap.byteCount)
        bitmap.copyPixelsToBuffer(byteBuffer)
        byteBuffer.rewind()
        return byteBuffer.array()
    }

    /**
     * Converts compressed byte array to bitmap
     * @param src source array
     * @return result bitmap
     */
    fun convertCompressedByteArrayToBitmap(src: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(src, 0, src.size)
    }

    //generateQRCode will use inoput info to store code.
    fun generateQRCode(form: String): Bitmap?{
        try {
            val bitmap = TextToImageEncode(form)
            return bitmap
        } catch (e: WriterException) {
            e.printStackTrace()
            return null
        }
    }

    fun stringToBitmap(str: String): Bitmap{
        val imageBytes = Base64.decode(str, 0)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }

    fun TextToImageEncode(Value: String): Bitmap? {
        val bitMatrix: BitMatrix
        try {
            bitMatrix = MultiFormatWriter().encode(
                Value,
                BarcodeFormat.QR_CODE,
                QRGeneratorFragment.QRcodeWidth, QRGeneratorFragment.QRcodeWidth, null
            )

        } catch (Illegalargumentexception: IllegalArgumentException) {

            return null
        }

        val bitMatrixWidth = bitMatrix.getWidth()

        val bitMatrixHeight = bitMatrix.getHeight()

        val pixels = IntArray(bitMatrixWidth * bitMatrixHeight)

        for (y in 0 until bitMatrixHeight) {
            val offset = y * bitMatrixWidth

            for (x in 0 until bitMatrixWidth) {
                //TODO(elijah): Fix?
                pixels[offset + x] = if (bitMatrix.get(x, y))
                   R.color.black
                else
                    R.color.white
            }
        }
        val bitmap =
            Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444)

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight)
        return bitmap
    }
}