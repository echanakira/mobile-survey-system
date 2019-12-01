package cmsc436.mobilesurvey.models

import com.google.firebase.firestore.DocumentSnapshot

data class Response(val snap: DocumentSnapshot) {
    var data = snap.data

    var id: Any? = snap.id
    var placeId: Any? = data!!["place_id"]
    var placeName: Any? = data!!["place_name"]
    var timestamp: Any? = data!!["timestamp"]
    var answerOne: Any? = data!!["1"]
    var answerTwo: Any? = data!!["2"]
    var answerThree: Any? = data!!["3"]
    var answerFour: Any? = data!!["4"]
    var answerFive: Any? = data!!["5"]
    var answerSix: Any? = data!!["6"]
    var answerSeven: Any? = data!!["7"]
}