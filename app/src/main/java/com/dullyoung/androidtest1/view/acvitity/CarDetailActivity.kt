package com.dullyoung.androidtest1.view.acvitity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dullyoung.androidtest1.BR
import com.dullyoung.androidtest1.R
import com.dullyoung.androidtest1.databinding.ActivityCarDetailBinding
import com.dullyoung.androidtest1.model.bean.DetailInfo
import com.dullyoung.androidtest1.viewmodel.DetailViewModel
import com.google.gson.Gson

class CarDetailActivity : AppCompatActivity() {

    enum class CountryType(var type: String) {
        Singapore("Singapore"), Thailand("Thailand");
    }

    companion object {
        fun start(context: Context, type: CountryType) {
            val intent = Intent(context, CarDetailActivity::class.java);
            intent.putExtra("type", type.type);
            context.startActivity(intent);
        }
    }

    private lateinit var binding: ActivityCarDetailBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_car_detail)
        binding.ivBack.setOnClickListener { onBackPressed() }

        loadData()

    }

    private fun loadData() {
        val progressDialog = ProgressDialog(this)
        progressDialog.actionBar?.title = "Loading..."
        progressDialog.show()
        val type = intent.getStringExtra("type");
        val detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java);
        binding.detailVM = detailViewModel
        detailViewModel.detailInfo.observe(this, {
            it.country = type
            binding.clRoot.visibility= View.VISIBLE
            progressDialog.dismiss()
        })
        binding.lifecycleOwner = this
        detailViewModel.loadData()
        binding.setVariable(BR.country, type)
    }
}