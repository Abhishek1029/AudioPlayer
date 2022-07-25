package com.mvvm.audioplayer.ui.onboarding.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.audioplayer.data.SliderModel
import com.mvvm.audioplayer.databinding.SingleItemOnboardingPagerBinding

private const val TAG = "OnBoardingPagerAdapter"

class OnBoardingPagerAdapter(
    private val context: Context,
    private val sliderModelList: List<SliderModel>
) :
    RecyclerView.Adapter<OnBoardingPagerAdapter.PagerViewHolder>() {

    inner class PagerViewHolder(val mBinding: SingleItemOnboardingPagerBinding) :
        RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val mBinding = SingleItemOnboardingPagerBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return PagerViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val sliderModel = sliderModelList[0]
        bindData(holder.mBinding, sliderModel,position)
    }

    private fun bindData(mBinding: SingleItemOnboardingPagerBinding, sliderModel: SliderModel,position:Int) {
        mBinding.apply {
            with(sliderModel){
                sliderImage = this.image[position]
                sliderHeading = context?.getString(this.heading[position])
                sliderSubHeading = context?.getString(this.subHeading[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return sliderModelList[0].image.size
    }
}