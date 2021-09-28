package com.dullyoung.androidtest1.model.engine

import com.dullyoung.androidtest1.model.bean.DetailInfo
import com.dullyoung.androidtest1.model.bean.DetailInfoWrapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*


/**
 * @author Dullyoung   2021/9/28
 * because of given data format is not match with my http lib
 * so I just use Gson to format given json string
 */
class DetailInfoEngine {
    val jsonString =
        "{\"data\":{\"id\":12,\"type\":\"subscription\",\"make\":\"Mazda\",\"model\":\"Mazda2\",\"carplate_number\":\"SLT5334G\",\"price\":\"1045.98\",\"start_time\":1587340800,\"end_time\":1589846400,\"next_billing_date\":1608307200,\"mileage\":null,\"total_outstanding_fine_count\":null,\"total_outstanding_fine_amount\":null,\"earliest_payment_due_date\":null,\"total_per_km_rate\":\"0.00\",\"days_left\":null,\"driven_this_month\":0,\"usage_due_this_month\":0,\"base_price\":null,\"road_tax\":682,\"insurance_excess\":2000,\"records\":[{\"key\":\"agreement-documents\",\"label\":\"Agreement Documents\"}],\"has_subscribed_insurance\":true,\"help\":[{\"key\":\"faq\",\"label\":\"FAQ\",\"value\":\"https:\\/\\/carro.sg\\/leap#faq\"},{\"key\":\"terms_and_conditions\",\"label\":\"Terms and conditions\",\"value\":\"https:\\/\\/carro.sg\\/leap\\/terms\"}],\"updated_at\":1597200772,\"drivers\":[{\"name\":\"Driver A\",\"phone\":\"90685298\",\"gender\":\"MALE\",\"id_number\":\"*****631Z\",\"driver_type\":\"MAIN_DRIVER\",\"date_of_birth\":324727300,\"marital_status\":\"MARRIED\",\"driving_experience\":\"10\",\"driving_license_number\":\"*****631Z\",\"driving_license_registration_date\":324727300}]},\"success\":{\"message\":\"Retrieve subscription vehicle successfully!\"}}"

    suspend fun getInfo(): DetailInfo {
        val deferred = CoroutineScope(MainScope().coroutineContext).async(Dispatchers.IO) {
            val detailInfoWrapper =
                Gson().fromJson<DetailInfoWrapper>(
                    jsonString,
                    object : TypeToken<DetailInfoWrapper>() {}.type
                )
            delay(1000)
            detailInfoWrapper.detailInfo!!
        }
        return deferred.await();
    }
}