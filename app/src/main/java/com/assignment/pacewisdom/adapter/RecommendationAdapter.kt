package com.assignment.pacewisdom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.pacewisdom.R
import com.assignment.pacewisdom.models.RecommendationModel
import kotlinx.android.synthetic.main.item_recommendation_list.view.*

class RecommendationAdapter(private val recommendationList: ArrayList<RecommendationModel>) :
    RecyclerView.Adapter<RecommendationAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recommendation_list, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(recommendationList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return recommendationList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: RecommendationModel) {
            itemView.txvRecommendationTitle.text = user.recommendationTitle
            itemView.txvRecommendationDescription.text = user.recommendationDescription
            itemView.imgRecommendation.setImageResource(user.recommendationImage)

        }
    }
}