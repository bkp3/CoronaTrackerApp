package bkp.com.coronatrackerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListAdapter(val list: List<StateWiseItem>): BaseAdapter() {
    override fun getCount():Int=list.size

    override fun getItem(p0: Int) = list[p0]

    override fun getItemId(p0: Int) = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = p1?:LayoutInflater.from(p2?.context).inflate(R.layout.item_list,p2,false)
        val item = list[p0]

        var confirmedTv = view.findViewById<TextView>(R.id.confirmedTv)
        var activeTv = view.findViewById<TextView>(R.id.activeTv)
        var recoveredTv = view.findViewById<TextView>(R.id.recoveredTv)
        var deathTv = view.findViewById<TextView>(R.id.deceasedTv)
        var stateTv = view.findViewById<TextView>(R.id.stateTv)

        confirmedTv.text = item.confirmed
        activeTv.text = item.active
        recoveredTv.text = item.recovered
        deathTv.text = item.deaths
        stateTv.text = item.state

        return view

    }
}