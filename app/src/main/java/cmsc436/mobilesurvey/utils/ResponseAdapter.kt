package cmsc436.mobilesurvey.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import cmsc436.mobilesurvey.R
import cmsc436.mobilesurvey.models.Response
import kotlinx.android.synthetic.main.card_view.view.*
import org.w3c.dom.Text

class ResponseAdapter(private val responseList: List<Response>) :
    RecyclerView.Adapter<ResponseAdapter.ViewHolder>() {

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var title: TextView
        internal var content: TextView

        init {
            title = view.findViewById(R.id.tvTitle)
            content = view.findViewById(R.id.tvContent)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ResponseAdapter.ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_note, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = responseList[position]

        holder!!.title.text = note.placeName as String
        holder.content.text = note.placeId as String
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = responseList.size
}