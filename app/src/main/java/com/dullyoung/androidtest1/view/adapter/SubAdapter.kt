package com.dullyoung.androidtest1.view.adapter

import androidx.databinding.BindingAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.coorchice.library.SuperTextView
import com.dullyoung.androidtest1.BR
import com.dullyoung.androidtest1.R
import com.dullyoung.androidtest1.databinding.ItemSubBinding
import com.dullyoung.androidtest1.model.bean.SubInfo


/**
 * @author Dullyoung   2021/9/28
 */
class SubAdapter(data: MutableList<SubInfo>?) :
    BaseQuickAdapter<SubInfo, BaseDataBindingHolder<ItemSubBinding>>(R.layout.item_sub, data) {
    override fun convert(holder: BaseDataBindingHolder<ItemSubBinding>, item: SubInfo) {
        holder.dataBinding?.setVariable(BR.subInfo, item)
    }
}
