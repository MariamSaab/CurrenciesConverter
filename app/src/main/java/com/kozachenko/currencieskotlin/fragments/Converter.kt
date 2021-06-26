package com.kozachenko.currencieskotlin.fragments

import android.app.ProgressDialog
import android.content.ClipData
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.federicocotogno.retro2newslist.APIRequest
import com.kozachenko.currencieskotlin.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Converter: Fragment()
{
    private val item: ClipData.Item? = null
    var pd: ProgressDialog? = null
   // private val swipeContainer: SwipeRefreshLayout? = null

    lateinit var countdownTimer: CountDownTimer
    private var seconds = 3L

    private var base = String()
    private var rates = mutableListOf<String>()
    private var date = String()
    private var success= Boolean

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? =
            inflater.inflate(R.layout.fragment_converter, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //makeAPIRequest()
    }

    //requests data from the api and forwards it to the recycler view
//    private fun makeAPIRequest() {
//        // progressBar.visibility = View.VISIBLE
//
//        val api = Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(APIRequest::class.java)
//
//        GlobalScope.launch(Dispatchers.IO) {
//            try {
//                val response = api.getNews()
//
////                for (article in response.rates) {
////                    addToList(article.title, article.description, article.image, article.url)
////                }
//
//                //updates ui when data has been retrieved
//                withContext(Dispatchers.Main) {
//                    //setUpRecyclerView()
//                    //fadeIn()
//                    //progressBar.visibility = View.GONE
//                }
//            } catch (e: Exception) {
//                withContext(Dispatchers.Main) {
//                    attemptRequestAgain(seconds)
//
//                }
//            }
//
//        }
//    }

//    private fun attemptRequestAgain(seconds: Long) {
//        countdownTimer = object: CountDownTimer(seconds*1010,1000){
//            override fun onFinish() {
//                makeAPIRequest()
//                countdownTimer.cancel()
//                this@Converter.seconds+=3
//            }
//            override fun onTick(millisUntilFinished: Long) {
//                //tv_noInternetCountDown.visibility = View.VISIBLE
//                // tv_noInternetCountDown.text = "Cannot retrieve data...\nTrying again in: ${millisUntilFinished/1000}"
//            }
//        }
//        countdownTimer.start()
//    }

}
