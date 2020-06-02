package com.example.newsapplication.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.newsapplication.R
import com.example.newsapplication.model.Article
import com.example.newsapplication.model.NewResult
import com.example.newsapplication.model.NewsList
import com.example.newsapplication.model.Source
import com.example.newsapplication.retrofit.ServiceBuilder
import com.example.newsapplication.retrofit.ServiceEndpoint
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private lateinit var country:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            arguments?.getString(ARG_DESC)?.let { setIndex(it) }
        }
        country= arguments?.getString(COUNTRY).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        val textView: TextView = root.findViewById(R.id.section_label)
        pageViewModel.text.observe(this, Observer<String> {
            textView.text = it
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ServiceBuilder.buildService(ServiceEndpoint::class.java)

        val call = request.getHeadline(country,"923d85e0dcb748ef8c4681682a95a7d1")

        call.enqueue(object: Callback<NewsList> {
            override fun onFailure(call: Call<NewsList>, t: Throwable) {

            }

            override fun onResponse(call: Call<NewsList>, response: Response<NewsList>) {

                if (response.isSuccessful) {

                    response.body()?.articles?.let { initRecycler(it) }
                }
            }

        })




    }

    private fun initRecycler(
        newsList: List<Article>
    ) {
        val mAdapter = context?.let {
            NewsListAdapter(
                newsList,
                it
            ){
                val intent = Intent(activity, Webview::class.java)
// To pass any data to next activity
                intent.putExtra("url", it.toString())
                startActivity(intent)

// start your next activ
            }
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }


    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"
        private const val ARG_DESC="Description"
        private const val COUNTRY="country"
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int,cat:Source): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_DESC,cat.description)
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                    putString(COUNTRY,cat.country)
                }
            }
        }
    }
}