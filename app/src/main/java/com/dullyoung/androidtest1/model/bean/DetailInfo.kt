package com.dullyoung.androidtest1.model.bean

import com.google.gson.annotations.SerializedName


/**
 * @author Dullyoung   2021/9/28
 */
class DetailInfo {

    var country: String? = null

    //车型
    var model: String? = null

    //车牌号
    @SerializedName("carplate_number")
    var carPlateNumber: String? = null

    var price: String? = null

    @SerializedName("start_time")
    var startTime: Long? = null

    @SerializedName("updated_at")
    var updatedAt: Long? = null

    @SerializedName("end_time")
    var endTime: Long? = null

    @SerializedName("next_billing_date")
    var nextBillingDate: Long? = null

    @SerializedName("days_left")
    var daysLeft: Int? = null

    @SerializedName("driven_this_month")
    var drivenThisMonth: Int? = null

    @SerializedName("usage_due_this_month")
    var usageDueThisMonth: Int? = null

    @SerializedName("base_price")
    var basePrice: String? = null

    @SerializedName("road_tax")
    var roadTax: String? = null

    @SerializedName("insurance_excess")
    var insuranceExcess: Int? = null

    var drivers: List<Driver>? = null

}