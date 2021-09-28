package com.dullyoung.androidtest1.view.adapter

import androidx.databinding.library.baseAdapters.BR
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dullyoung.androidtest1.R
import com.dullyoung.androidtest1.databinding.ItemCarInfoBinding
import com.dullyoung.androidtest1.model.bean.CarInfo


/**
 * @author Dullyoung   2021/9/28
 */
class CarInfoAdapter(data: MutableList<CarInfo>?) :
    BaseQuickAdapter<CarInfo, BaseDataBindingHolder<ItemCarInfoBinding>>(
        R.layout.item_car_info,
        data
    ) {
    override fun convert(holder: BaseDataBindingHolder<ItemCarInfoBinding>, item: CarInfo) {
        holder.dataBinding?.setVariable(BR.info,item)
    }

}