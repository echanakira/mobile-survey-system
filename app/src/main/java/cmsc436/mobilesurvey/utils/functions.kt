package cmsc436.mobilesurvey.utils

fun getFirstWord(text: String): String {
    val value = if (text!!.contains(" ")) text!!.split(" ")[0] else text
    return value.toLowerCase()
}