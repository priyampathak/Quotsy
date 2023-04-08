package com.example.quotsy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var a:Int = 0

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://type.fit/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getQuoteData()
        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(
                call: Call<MyData?>,
                response: Response<MyData?>
            ) {
                var collectDataInSB = StringBuilder()
                var collectDataInSBA = StringBuilder()
                var quoteList = response.body()!!

                collectDataInSB.append(quoteList.get(a).text)
                collectDataInSBA.append(quoteList.get(a).author)

                textviews.text = collectDataInSB
                textviews2.text  = collectDataInSBA

                button1.setOnClickListener {
                    a--;
                    collectDataInSB.clear()
                    collectDataInSBA.clear()
                    collectDataInSB.append(quoteList.get(a).text)
                    collectDataInSBA.append(quoteList.get(a).author)
                    textviews.text = collectDataInSB
                    textviews2.text  = collectDataInSBA
                }

                button2.setOnClickListener {
                    a++;
                    collectDataInSB.clear()
                    collectDataInSBA.clear()
                    collectDataInSB.append(quoteList.get(a).text)
                    collectDataInSBA.append(quoteList.get(a).author)
                    textviews.text = collectDataInSB
                    textviews2.text  = collectDataInSBA
                }
            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                Log.d("Main Activity", "onFailure :" + t.message)
            }
        })
    }
}