package com.example.flagquiz.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.flagquiz.R
import com.example.flagquiz.data.locale_storage.room.enitities.LevelsEntity


class LevelsAdapter : RecyclerView.Adapter<LevelsAdapter.Holder>() {
    private val data = ArrayList<LevelsEntity>()
    private var listener: ((LevelsEntity) -> Unit)? = null
    var listenerMessage = MutableLiveData<String>()

    fun onClick(l: (LevelsEntity) -> Unit) {
        listener = l

    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitItems(newItems: List<LevelsEntity>) {
        data.clear()
        data.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {

        private val textLevel = view.findViewById<TextView>(R.id.level_txt)
        private val procent = view.findViewById<TextView>(R.id.image_level_item)
        private val item_level = view.findViewById<ConstraintLayout>(R.id.item_l)
        private val image = view.findViewById<ImageView>(R.id.image_view_block)

        @SuppressLint("SetTextI18n")
        fun bind() {
            val item = data[adapterPosition]
            textLevel.text = item.levels
            if (absoluteAdapterPosition == 0 || data[absoluteAdapterPosition - 1].correct.toDouble() > 0.8 * data[absoluteAdapterPosition - 1].allQuestions.toDouble()) {
                image.visibility = View.GONE
                procent.visibility = View.VISIBLE
                procent.text =
                    "${(item.correct.toDouble() / item.allQuestions.toDouble() * 100).toInt()} %"
            } else {
                image.visibility = View.VISIBLE
                procent.visibility = View.GONE
            }


        }

        init {
            item_level.setOnClickListener {

                if (absoluteAdapterPosition == 0) {
                    listener?.invoke(data[adapterPosition])
                } else {
                    if (data[absoluteAdapterPosition - 1].correct.toDouble() > 0.8 * data[absoluteAdapterPosition - 1].allQuestions.toDouble())
                        listener?.invoke(data[adapterPosition])
                    else {
                        listenerMessage.value = "This level is not unlocked"
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_levels, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = data.size
}