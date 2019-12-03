package cmsc436.mobilesurvey.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import cmsc436.mobilesurvey.R
import cmsc436.mobilesurvey.models.Response
import cmsc436.mobilesurvey.utils.ResponseAdapter
import cmsc436.mobilesurvey.utils.getFirstWord
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_responses.*

class ResponseDetailActivity : AppCompatActivity() {
    private var surveyType: String? = null

    private var questionOne: String? = null
    private var questionTwo: String? = null
    private var questionThree: String? = null
    private var questionFour: String? = null
    private var questionFive: String? = null
    private var questionSix: String? = null
    private var questionSeven: String? = null

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_response_detail)

        val restaurant: String = getFirstWord(getString(R.string.restaurant))
        val retail: String = getFirstWord(getString(R.string.retail))
        val amusement: String = getFirstWord(getString(R.string.amusement))
        val id = intent.getStringExtra("id")
        val mLayoutManager: LinearLayout = findViewById(R.id.activity_response_detail)

        val docRef = db.collection("responses").document(id)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    var resp = Response(document)
                    val type: String = resp!!.type.toString()

                    when (type) {
                        restaurant -> {
                            surveyType = restaurant
                            questionOne = getString(R.string.restaurant_one)
                            questionTwo = getString(R.string.restaurant_two)
                            questionThree = getString(R.string.restaurant_three)
                            questionFour = getString(R.string.restaurant_four)
                            questionFive = getString(R.string.restaurant_five)
                            questionSix = getString(R.string.restaurant_six)
                        }

                        retail -> {
                            surveyType = retail
                            questionOne = getString(R.string.retail_one)
                            questionTwo = getString(R.string.retail_two)
                            questionThree = getString(R.string.retail_three)
                            questionFour = getString(R.string.retail_four)
                            questionFive = getString(R.string.retail_five)
                            questionSix = getString(R.string.retail_six)
                        }

                        amusement -> {
                            surveyType = amusement
                            questionOne = getString(R.string.amusement_one)
                            questionTwo = getString(R.string.amusement_two)
                            questionThree = getString(R.string.amusement_three)
                            questionFour = getString(R.string.amusement_four)
                            questionFive = getString(R.string.amusement_five)
                            questionSix = getString(R.string.amusement_six)
                        }

                        else -> {
                        }
                    }

                    mLayoutManager.findViewById<TextView>(R.id.question_one)?.text = questionOne
                    mLayoutManager.findViewById<TextView>(R.id.question_two)?.text = questionTwo
                    mLayoutManager.findViewById<TextView>(R.id.question_three)?.text = questionThree
                    mLayoutManager.findViewById<TextView>(R.id.question_four)?.text = questionFour
                    mLayoutManager.findViewById<TextView>(R.id.question_five)?.text = questionFive
                    mLayoutManager.findViewById<TextView>(R.id.question_six)?.text = questionSix
                    mLayoutManager.findViewById<TextView>(R.id.question_seven)?.text = questionSeven

                    mLayoutManager.findViewById<TextView>(R.id.answer_one)?.text =
                        resp.answerOne.toString()
                    mLayoutManager.findViewById<TextView>(R.id.answer_two)?.text =
                        resp.answerTwo.toString()
                    mLayoutManager.findViewById<TextView>(R.id.answer_three)?.text =
                        resp.answerThree.toString()
                    mLayoutManager.findViewById<TextView>(R.id.answer_four)?.text =
                        resp.answerFour.toString()
                    mLayoutManager.findViewById<TextView>(R.id.answer_five)?.text =
                        resp.answerFive.toString()
                    mLayoutManager.findViewById<TextView>(R.id.answer_six)?.text =
                        resp.answerSix.toString()
                    mLayoutManager.findViewById<TextView>(R.id.answer_seven)?.text =
                        resp.answerSeven.toString()

                } else {
                    Toast.makeText(
                        applicationContext,
                        "Response no longer exists",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            }
            .addOnFailureListener { exception ->
            }

        questionSeven = getString(R.string.comments)
    }
}
