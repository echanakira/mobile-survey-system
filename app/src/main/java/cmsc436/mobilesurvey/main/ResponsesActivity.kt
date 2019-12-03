package cmsc436.mobilesurvey.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import cmsc436.mobilesurvey.main.ResponseDetailActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import cmsc436.mobilesurvey.R
import cmsc436.mobilesurvey.models.Response
import cmsc436.mobilesurvey.utils.ResponseAdapter
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_responses.*

class ResponsesActivity : AppCompatActivity() {
    private var mAdapter: ResponseAdapter? = null
    //    private lateinit var recyclerView: RecyclerView
//    private lateinit var viewAdapter: RecyclerView.Adapter<*>
//    private lateinit var viewManager: RecyclerView.LayoutManager
    private var firestoreListener: ListenerRegistration? = null

    var userId: String? = null
    val db = FirebaseFirestore.getInstance()
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_responses)
        mAuth = FirebaseAuth.getInstance()
        userId = mAuth!!.currentUser?.uid


        loadResponsesList()

        firestoreListener = db!!.collection("responses")
            .whereEqualTo("place_id", userId)
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener(EventListener { snaps, e ->
                if (e != null) {
//                    Log.e(TAG, "Listen failed!", e)
                    return@EventListener
                }

                val responseList = mutableListOf<Response>()

                for (doc in snaps!!.documents) {
                    var resp = Response(doc)
                    responseList.add(resp)
                }


                mAdapter = ResponseAdapter(responseList) { response: Response, _: Int ->
                    val intent =
                        Intent(
                            this,
                            ResponseDetailActivity::class.java
                        )

                    intent.putExtra("id", response.id.toString())

                    startActivity(intent)
                }

                responses_view.adapter = mAdapter
            })
    }

    private fun loadResponsesList() {
        db!!.collection("responses")
            .whereEqualTo("place_id", userId)
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .get()
            .addOnCompleteListener { docs ->
                if (docs.isSuccessful) {
                    val responseList = mutableListOf<Response>()

                    for (doc in docs.result!!.documents) {
                        var resp = Response(doc)
                        responseList.add(resp)
                    }

                    mAdapter = ResponseAdapter(responseList) { response: Response, _: Int ->
                        val intent =
                            Intent(
                                this,
                                ResponseDetailActivity::class.java
                            )


                        intent.putExtra("id", response.id.toString())

                        startActivity(intent)
                    }

                    val mLayoutManager = LinearLayoutManager(applicationContext)
                    responses_view.layoutManager = mLayoutManager
                    responses_view.adapter = mAdapter
                } else {
//                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            }
    }
}
