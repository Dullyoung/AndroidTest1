package com.dullyoung.androidtest1.model.bean

import com.google.gson.annotations.SerializedName


/**
 * @author Dullyoung   2021/9/28
 */
class DetailInfoWrapper {
    @SerializedName("data")
    var detailInfo: DetailInfo? = null;

    var success: SuccessInfo? = null;
}