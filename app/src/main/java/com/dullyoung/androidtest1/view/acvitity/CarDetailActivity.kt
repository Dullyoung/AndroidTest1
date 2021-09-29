package com.dullyoung.androidtest1.view.acvitity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
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

    private lateinit var binding: ActivityCarDetailBinding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_car_detail)
        binding.ivBack.setOnClickListener { onBackPressed() }
        binding.tvError.setOnClickListener { loadData() }

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("loading...")
        val type = intent.getStringExtra("type");
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java);
        binding.detailVM = detailViewModel
        detailViewModel.detailInfo.observe(this, {
            progressDialog.dismiss()
            if (it == null) {
                binding.clError.visibility = View.VISIBLE
                binding.clRoot.visibility = View.GONE
                Toast.makeText(this, "Request Error", Toast.LENGTH_SHORT).show()
            } else {
                it.country = type
                binding.clRoot.visibility = View.VISIBLE
                binding.clError.visibility = View.GONE
            }
        })
        binding.lifecycleOwner = this
        binding.setVariable(BR.country, type)
        loadData()
    }

    private fun loadData() {
        progressDialog.show()
        detailViewModel.loadData()
    }
}