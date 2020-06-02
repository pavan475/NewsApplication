package com.example.newsapplication

import android.app.ProgressDialog
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.newsapplication.model.NewResult
import com.example.newsapplication.retrofit.ServiceBuilder
import com.example.newsapplication.retrofit.ServiceEndpoint
import com.example.newsapplication.ui.main.SectionsPagerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
     private lateinit var alertDialog: android.app.AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val progressDialog = ProgressDialog(this@MainActivity)

        progressDialog.setTitle("New source")
        progressDialog.setMessage("Loading..., please wait")
        progressDialog.show()

        val request = ServiceBuilder.buildService(ServiceEndpoint::class.java)

        val call = request.getNewsSource("923d85e0dcb748ef8c4681682a95a7d1")

        call.enqueue(object: Callback<NewResult> {
            override fun onFailure(call: Call<NewResult>, t: Throwable) {
                progressDialog.dismiss()

            }

            override fun onResponse(call: Call<NewResult>, response: Response<NewResult>) {
                progressDialog.dismiss()

                if (response.isSuccessful) {
                    val sectionsPagerAdapter =
                        response.body()?.sources?.let {
                            SectionsPagerAdapter(this@MainActivity, supportFragmentManager,
                                it
                            )
                        }
                    val viewPager: ViewPager = findViewById(R.id.view_pager)
                    viewPager.adapter = sectionsPagerAdapter
                    val tabs: TabLayout = findViewById(R.id.tabs)
                    tabs.setupWithViewPager(viewPager)
                }
                progressDialog.dismiss()
            }

        })



      //  val fab: FloatingActionButton = findViewById(R.id.fab)
/*
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
    }
}