package com.assignment.pacewisdom.navcontroller

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.pacewisdom.R
import com.assignment.pacewisdom.adapter.InvestAdapter
import com.assignment.pacewisdom.adapter.RecommendationAdapter
import com.assignment.pacewisdom.models.RecommendationModel
import com.assignment.pacewisdom.models.WhyInvestModel

class DashboardFragment : Fragment() {

    private lateinit var rootView: View
    private lateinit var recyclerViewRecommendation: RecyclerView
    private lateinit var recycleInvest: RecyclerView
    private lateinit var imgProfile: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.dashboard_fragment, container, false);
        populateRecommendationList()
        populateInvestList()

        imgProfile = rootView.findViewById(R.id.imgUserProfile)

        return rootView
    }

    private fun populateRecommendationList() {
        val users = ArrayList<RecommendationModel>()
        users.add(
            RecommendationModel(
                getString(R.string.retire_with_maximum),
                getString(R.string.retire_with_five),
                R.drawable.ic_item_recommendation_chair
            )
        )
        users.add(
            RecommendationModel(
                getString(R.string.education_fund),
                getString(R.string.get_best_education),
                R.drawable.ic_item_recommendation_chair
            )
        )
        users.add(
            RecommendationModel(
                getString(R.string.marriage_fund),
                getString(R.string.dreaming_of_wedding),
                R.drawable.ic_item_recommendation_chair
            )
        )
        users.add(
            RecommendationModel(
                getString(R.string.vacation_fund),
                getString(R.string.male_your_next_vacation),
                R.drawable.ic_item_recommendation_chair
            )
        )
        users.add(
            RecommendationModel(
                getString(R.string.dream_vehicle),
                getString(R.string.owning_vehicle),
                R.drawable.ic_item_recommendation_chair
            )
        )

        val recommendationAdapter = RecommendationAdapter(users)

        recyclerViewRecommendation = rootView.findViewById(R.id.recycleRecommendation)

        recyclerViewRecommendation.isNestedScrollingEnabled = false;
        recyclerViewRecommendation.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerViewRecommendation.adapter = recommendationAdapter
    }

    private fun populateInvestList() {
        val investData = ArrayList<WhyInvestModel>()

        investData.add(
            WhyInvestModel(
                getString(R.string.thanks_to_appreciate),
                getString(R.string.user),
                getString(R.string.marketing_consultant),
                R.drawable.user_profile
            )
        )
        investData.add(
            WhyInvestModel(
                getString(R.string.thanks_to_appreciate),
                getString(R.string.user),
                getString(R.string.marketing_consultant),
                R.drawable.user_profile
            )
        )
        investData.add(
            WhyInvestModel(
                getString(R.string.thanks_to_appreciate),
                getString(R.string.user),
                getString(R.string.marketing_consultant),
                R.drawable.user_profile
            )
        )
        investData.add(
            WhyInvestModel(
                getString(R.string.thanks_to_appreciate),
                getString(R.string.user),
                getString(R.string.marketing_consultant),
                R.drawable.user_profile
            )
        )
        investData.add(
            WhyInvestModel(
                getString(R.string.thanks_to_appreciate),
                getString(R.string.user),
                getString(R.string.marketing_consultant),
                R.drawable.user_profile
            )
        )


        val investAdapter = InvestAdapter(investData)

        recycleInvest = rootView.findViewById(R.id.recycleInvest)

        recycleInvest.isNestedScrollingEnabled = false;
        recycleInvest.layoutManager =
            LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        recycleInvest.adapter = investAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgProfile.setOnClickListener {
            val intent = Intent(requireActivity(), PanCardActivity::class.java)
            startActivity(intent)
        }
    }
}