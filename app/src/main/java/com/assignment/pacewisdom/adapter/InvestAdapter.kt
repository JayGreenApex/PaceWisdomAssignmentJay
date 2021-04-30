package com.assignment.pacewisdom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.pacewisdom.R
import com.assignment.pacewisdom.models.RecommendationModel
import com.assignment.pacewisdom.models.WhyInvestModel
import kotlinx.android.synthetic.main.item_invest_list.view.*
import kotlinx.android.synthetic.main.item_recommendation_list.view.*

class InvestAdapter(private val investList: ArrayList<WhyInvestModel>) :
    RecyclerView.Adapter<InvestAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_invest_list, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(investList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return investList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: WhyInvestModel) {
            itemView.txvInvestTitle.text = user.investTitle
            itemView.txvInvestUserName.text = user.investUserName
            itemView.txvInvestUserType.text = user.investType
            itemView.imgUser.setImageResource(user.investUserImage)

        }
    }
}