package bkp.com.coronatrackerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ListView
import android.widget.TextView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var listAdapter: ListAdapter
    private lateinit var list:ListView

    private lateinit var confirmed_txt: TextView
    private lateinit var active_txt: TextView
    private lateinit var recovered_txt: TextView
    private lateinit var deceased_txt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list = findViewById(R.id.list_view)

        confirmed_txt = findViewById(R.id.confirmedTv_am)
        active_txt = findViewById(R.id.activeTv_am)
        recovered_txt = findViewById(R.id.recoveredTv_am)
        deceased_txt = findViewById(R.id.deathTv_am)

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO){Client.api.execute()}
            if(response.isSuccessful){
                val data = Gson().fromJson(response.body?.string(),Response::class.java)
                launch(Dispatchers.Main) {
                    bindCombinedData(data.statewise[0])
                    bindStateWiseData(data.statewise.subList(0,data.statewise.size))
                }
            }
        }
    }

    private fun bindStateWiseData(subList: List<StateWiseItem>){
        listAdapter= ListAdapter(subList)
        list.addHeaderView(LayoutInflater.from(this).inflate(
            R.layout.list_header,list,false
        ))
        list.adapter=listAdapter
    }

    private fun bindCombinedData(data: StateWiseItem){

        confirmed_txt.text = data.confirmed
        active_txt.text = data.active
        recovered_txt.text = data.recovered
        deceased_txt.text = data.deaths
    }
}