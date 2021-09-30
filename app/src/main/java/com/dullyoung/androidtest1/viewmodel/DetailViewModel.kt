package com.dullyoung.androidtest1.viewmodel

import android.graphics.Color
import android.text.Html
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coorchice.library.SuperTextView
import com.dullyoung.androidtest1.model.bean.CarInfo
import com.dullyoung.androidtest1.model.bean.DetailInfo
import com.dullyoung.androidtest1.model.bean.SubInfo
import com.dullyoung.androidtest1.model.engine.DetailInfoEngine
import com.dullyoung.androidtest1.utils.CommonUtils
import com.dullyoung.androidtest1.view.acvitity.CarDetailActivity
import com.dullyoung.androidtest1.view.adapter.CarInfoAdapter
import com.dullyoung.androidtest1.view.adapter.SubAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


/**
 * @author Dullyoung   2021/9/28
 */
open class DetailViewModel : ViewModel() {
    var detailInfo: MutableLiveData<DetailInfo> = MutableLiveData()

    open fun loadData() {
        MainScope().launch(Dispatchers.Main) {
            val engine = DetailInfoEngine()
            val info = engine.getInfo()
            detailInfo.value = info
 
        }
    }

}

@BindingAdapter("leftDate")
fun getLeftDate(textView: TextView, detailInfo: DetailInfo?) {
    if (detailInfo == null) {
        return
    }
    val text =
        "${(if (detailInfo.daysLeft == null) 0 else detailInfo.daysLeft).toString()} days left"
    textView.text = text
}

@BindingAdapter("mProgress")
fun getProgress(progressBar: ProgressBar, detailInfo: DetailInfo?) {
    if (detailInfo == null) {
        return
    }
    val startTime = detailInfo.startTime!!
    val endTime = detailInfo.endTime!!
    val nowTime: Long = System.currentTimeMillis() / 1000
    if (nowTime > endTime) {
        progressBar.progress = 100
    } else {
        progressBar.progress = ((nowTime - startTime) / (endTime - startTime)).toInt()
    }
}

@BindingAdapter("drivenKM")
fun getDrivenDistance(textView: TextView, detailInfo: DetailInfo?) {
    if (detailInfo == null) {
        return
    }
    val distance: Int = if (detailInfo.drivenThisMonth == null) 0 else detailInfo.drivenThisMonth!!
    val text = "$distance <small>km</small>"
    textView.text = Html.fromHtml(text)
}

@BindingAdapter("usageFee")
fun getUsageFee(textView: TextView, detailInfo: DetailInfo?) {
    if (detailInfo == null) {
        return
    }
    val fee: Int =
        if (detailInfo.usageDueThisMonth == null) 0 else detailInfo.usageDueThisMonth!!
    val text = "<small>$</small> $fee"
    textView.text = Html.fromHtml(text)
}

@BindingAdapter("updateAt")
fun getUpdateAt(textView: TextView, detailInfo: DetailInfo?) {
    if (detailInfo == null) {
        return
    }
    val time: Long =
        if (detailInfo.updatedAt == null) 0L else detailInfo.updatedAt!!
    val text = "last update: ${CommonUtils.formatDate(time * 1000)}"
    textView.text = text
}

@BindingAdapter("bindAdapter")
fun bindRVAdapter(recyclerView: RecyclerView, detailInfo: DetailInfo?) {
    if (detailInfo == null) {
        return
    }
    val carInfos = ArrayList<CarInfo>()

    val price = if (detailInfo.basePrice == null) 0 else detailInfo.basePrice
    carInfos.add(CarInfo("Base Price", "$ ${price}/month"))

    val roadTax = if (detailInfo.roadTax == null) 0 else detailInfo.roadTax
    carInfos.add(CarInfo("Road Tax", "$ $roadTax"))

    if (detailInfo.country == CarDetailActivity.CountryType.Thailand.type) {
        carInfos.add(CarInfo("Total Fines", "$ 1245"))
        carInfos.add(CarInfo("Total Fines Amount", "$ 2245"))
    } else {
        val baseUsageInsurance = "$ 0.09 /km"
        carInfos.add(CarInfo("Usage Based Insurance", baseUsageInsurance))

        val stringBuilder = StringBuilder()
        detailInfo.drivers?.forEachIndexed { index, driver ->
            run {
                stringBuilder.append(driver.name)
                if (index + 1 != detailInfo.drivers?.size) {
                    stringBuilder.append("\n")
                }
            }
        }
        carInfos.add(CarInfo("Named Drivers", stringBuilder.toString()))

    }

    val insuranceExcess = if (detailInfo.insuranceExcess == null) 0 else detailInfo.insuranceExcess
    carInfos.add(CarInfo("Insurance Excess", "$ $insuranceExcess"))

    val carInfoAdapter = CarInfoAdapter(carInfos)
    recyclerView.layoutManager = object : LinearLayoutManager(recyclerView.context) {
        override fun canScrollVertically(): Boolean {
            return false
        }
    }
    recyclerView.addItemDecoration(
        DividerItemDecoration(
            recyclerView.context,
            RecyclerView.VERTICAL
        )
    )
    recyclerView.adapter = carInfoAdapter

}

@BindingAdapter("bindSubAdapter")
fun bindSubAdapter(recyclerView: RecyclerView, type: String?) {
    val subList = ArrayList<SubInfo>()
    if (type == CarDetailActivity.CountryType.Singapore.type) {
        subList.add(SubInfo("Get Help", Color.parseColor("#ff9b27")))
        subList.add(SubInfo("View Docs", Color.parseColor("#426ab3")))
        subList.add(SubInfo("Payments", Color.parseColor("#426ab3")))
        subList.add(SubInfo("Cancel Sub", Color.parseColor("#f05b72")))
    } else {
        subList.add(SubInfo("Get Help", Color.parseColor("#ff9b27")))
        subList.add(SubInfo("Payments", Color.parseColor("#426ab3")))
        subList.add(SubInfo("Cancel Sub", Color.parseColor("#f05b72")))
    }
    val subAdapter = SubAdapter(subList)
    recyclerView.adapter = subAdapter
    recyclerView.layoutManager =
        LinearLayoutManager(recyclerView.context, RecyclerView.HORIZONTAL, false)
}

@BindingAdapter("isGone")
fun isGone(stv: SuperTextView, string: String?) {
    if (string == CarDetailActivity.CountryType.Thailand.type) {
        stv.visibility = View.GONE
    } else {
        stv.visibility = View.VISIBLE
    }
}

@BindingAdapter("bindData")
fun bindData(stv: SuperTextView, subInfo: SubInfo?) {
    if (subInfo == null) {
        return
    }
    stv.text = subInfo.name
    stv.solid = subInfo.color
}
