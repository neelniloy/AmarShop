
import java.text.SimpleDateFormat
import java.util.*

fun getFormattedDateTime(millis: Long, format: String) =
    SimpleDateFormat(format, Locale.getDefault()).format(Date(millis))