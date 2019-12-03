package cmsc436.mobilesurvey.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cmsc436.mobilesurvey.R
import cmsc436.mobilesurvey.models.Response
import com.google.firebase.Timestamp

class ResponseAdapter(
    private val responseList: List<Response>,
    val clickListener: (Response, Int) -> Unit
) :
    RecyclerView.Adapter<ResponseAdapter.ViewHolder>() {

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var title: TextView
        internal var content: TextView

        init {
            title = view.findViewById(R.id.title)
            content = view.findViewById(R.id.content)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ResponseAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.response_card, parent, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = responseList[position]

        holder.title.text = "Submitted survey"
        var timestamp: Timestamp? = note.timestamp as Timestamp
        holder.content.text = timestamp!!.toDate().toString()
        holder.itemView.setOnClickListener { clickListener(note, position) }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = responseList.size
}