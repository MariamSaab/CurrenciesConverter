package com.kozachenko.currencieskotlin.fragments

import android.app.ProgressDialog
import android.content.ClipData
import android.graphics.Color.alpha
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.federicocotogno.retro2newslist.APIRequest
import com.kozachenko.currencieskotlin.R
import com.kozachenko.currencieskotlin.RecyclerAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.currentsapi.services"

class AllCurrencies : Fragment()
{
    private var rv_recyclerView: RecyclerView? = null
    var Disconnected: TextView? = null
    private val item: ClipData.Item? = null
    var pd: ProgressDialog? = null
    private val swipeContainer: SwipeRefreshLayout? = null

    lateinit var countdownTimer: CountDownTimer
    private var seconds = 3L

    private var titlesList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imagesList = mutableListOf<String>()
    private var linksList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_all_currencies, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        makeAPIRequest()
    }

//    private fun initRecyclerView(){
//        //recyclerView?.layoutManager = LinearLayoutManager((this))
//        //recyclerView =requireView().findViewById<RecyclerView>(R.id.recyclerView)
//        //recyclerViewAdapter = RecyclerViewAdapter()
//        //recyclerView?.adapter = recyclerViewAdapter
//    }

    //simple fade in animation for when the app is done loading
//    private fun fadeIn() {
//        v_blackScreen.animate().apply {
//            alpha(0f)
//            duration = 3000
//        }.start()
//    }

    //requests data from the api and forwards it to the recycler view
    private fun makeAPIRequest() {
       // progressBar.visibility = View.VISIBLE

        val api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIRequest::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getNews()

                for (article in response.news) {
                    addToList(article.title, article.description, article.image, article.url)
                }

                //updates ui when data has been retrieved
                withContext(Dispatchers.Main) {
                    setUpRecyclerView()
                    //fadeIn()
                    //progressBar.visibility = View.GONE
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    attemptRequestAgain(seconds)
                }
            }
        }
    }

    private fun attemptRequestAgain(seconds: Long) {
        countdownTimer = object: CountDownTimer(seconds*1010,1000){
            override fun onFinish() {
                makeAPIRequest()
                countdownTimer.cancel()
                //tv_noInternetCountDown.visibility = View.GONE
                this@AllCurrencies.seconds+=3
            }
            override fun onTick(millisUntilFinished: Long) {
                //tv_noInternetCountDown.visibility = View.VISIBLE
               // tv_noInternetCountDown.text = "Cannot retrieve data...\nTrying again in: ${millisUntilFinished/1000}"
                Log.d("MainActivity", "Could not retrieve data. Trying again in ${millisUntilFinished/1000} seconds")
            }
        }
        countdownTimer.start()
    }

    private fun setUpRecyclerView() {
        rv_recyclerView =requireView().findViewById<RecyclerView>(R.id.rv_recyclerView)
        //rv_recyclerView!!.setLayoutManager(LinearLayoutManager(getApplicationContext()))
        rv_recyclerView!!.adapter = RecyclerAdapter(titlesList, descList, imagesList)
    }

    //adds the items to our recyclerview
    private fun addToList(title: String, description: String, image: String, link: String) {
        linksList.add(link)
        titlesList.add(title)
        descList.add(description)
        imagesList.add(image)
    }

}


//    private fun initViews() {
//        //pd = ProgressDialog(this)
//        pd!!.setMessage("Searching Github Users...")
//        pd!!.setCancelable(false)
//        pd!!.show()
//        recyclerView =requireView().findViewById<RecyclerView>(R.id.recyclerView)
//        recyclerView.setLayoutManager(LinearLayoutManager(getApplicationContext()))
//        //recyclerView.smoothScrollToPosition(0)
//        loadJSON()
//    }
//
//    private fun loadJSON() {
//        Disconnected = findViewById<View>(R.id.disconnected) as TextView
//        try {
//            val Client = Client()
//            val apiService: Service = Client.getClient().create(Service::class.java)
//            val call: Call<ItemResponse> = apiService.getItems()
//            call.enqueue(object : Callback<ItemResponse> {
//                override fun onResponse(
//                    call: Call<ItemResponse>,
//                    response: Response<ItemResponse>
//                ) {
//                    val items: List<Item> = response.body().getItems()
//                    recyclerView!!.adapter = ItemAdapter(getApplicationContext(), items)
//                    recyclerView!!.smoothScrollToPosition(0)
//                    swipeContainer!!.isRefreshing = false
//                    pd!!.hide()
//                }
//
//                override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
//                    Disconnected!!.visibility = View.VISIBLE
//                    pd!!.hide()
//                }
//            })
//        } catch (e: Exception) {
//
//        }
//    }