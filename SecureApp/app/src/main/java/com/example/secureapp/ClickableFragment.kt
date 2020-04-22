package com.example.secureapp

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_display.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class ClickableFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private var arraysstring = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_clickable, container, false)
        var app = arguments?.getSerializable("application") as ArrayList<Appitem>
        var name = app[0].name
        var version = app[0].version
        var icon = app[0].icon
        var body = arguments?.getString("body")
        arraysstring = splitbody(body!!) //get the string arrays
        view.findViewById<TextView>(R.id.click_text_name).text = name
        view.findViewById<TextView>(R.id.click_text_version).text = version
        view.findViewById<ImageView>(R.id.click_image).setImageDrawable(icon)

        //Set up Recycler

        recyclerView = view.findViewById(R.id.recyclerclickable)

        // This tells the recyclerview that we want to show our data items in a vertical list. We could do a horizontal list,
        // a grid or even something custom in order to display the data items.
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        viewAdapter = RecyclerViewAdapter(arraysstring, activity as MainActivity)

        recyclerView.adapter = viewAdapter

        return view

    }


    //Split the body response from the web request
    fun splitbody(body: String): ArrayList<String> {
        var splitstring =
            body.split("\"") //{"McDonalds_v6.5.0": "Untrusted PRNG, Found Broken Hash Function"}
        var trimmedstring = splitstring[3] //"Untrusted PRNG, Found Broken Hash Function"
        if(trimmedstring.contains(",")){
            return trimmedstring.split(",") as ArrayList<String>
        }
        else{ //no comma to split on
            var oneitem = ArrayList<String>()
            oneitem.add(trimmedstring)
            return oneitem
        }

    }

}

//Recyclerview
class RecyclerViewAdapter(
    private val flaws: ArrayList<String>,
    private val activity: MainActivity
) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerviewitem, parent, false)


        return ViewHolder(v, activity)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItems(flaws[position])

    }

    class ViewHolder(private val view: View, private val activity: MainActivity) :
        RecyclerView.ViewHolder(view) {
        fun bindItems(value: String) {
            val textblock: TextView = itemView.findViewById(R.id.flawvalue)

            textblock.text = value

        }
    }

    override fun getItemCount(): Int {
        return flaws.size
    }
}
//        // Return the size of your dataset (invoked by the layout manager)
//        override fun getItemCount() = flaws
