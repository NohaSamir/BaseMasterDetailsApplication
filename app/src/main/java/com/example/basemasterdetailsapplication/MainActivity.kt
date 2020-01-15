package com.example.basemasterdetailsapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.basemasterdetailsapplication.models.DummyData
import com.example.basemasterdetailsapplication.network.apiServices
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiServices.getProperties().enqueue(object : Callback<List<DummyData>> {
            override fun onFailure(call: Call<List<DummyData>>, t: Throwable) {
                textView.text = t.message
            }

            override fun onResponse(call: Call<List<DummyData>>, response: Response<List<DummyData>>) {
                textView.text = response.body().toString()
            }

        })
    }
}
