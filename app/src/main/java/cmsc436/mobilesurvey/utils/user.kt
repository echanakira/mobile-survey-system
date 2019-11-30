package cmsc436.mobilesurvey.utils

import com.google.firebase.firestore.DocumentSnapshot

data class User(val snap: DocumentSnapshot) {
    var data = snap.data

    var id: Any? = snap.id
    var email: Any? = data!!["email"]
    var name: Any? = data!!["name"]
    var type: Any? = data!!["type"]
}