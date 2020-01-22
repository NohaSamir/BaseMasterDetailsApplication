package com.example.basemasterdetailsapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

        /*apiServices.getProperties().enqueue(object : Callback<List<DummyData>> {
            override fun onFailure(call: Call<List<DummyData>>, t: Throwable) {
                //textView.text = t.message
            }

            override fun onResponse(call: Call<List<DummyData>>, response: Response<List<DummyData>>) {
                //textView.text = response.body().toString()
            }

        })*/
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }
}
