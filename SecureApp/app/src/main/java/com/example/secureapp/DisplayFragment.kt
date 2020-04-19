package com.example.secureapp

import android.content.pm.ApplicationInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass.
 */
class DisplayFragment : Fragment() {

    lateinit var apps: MutableList<ApplicationInfo>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        apps = arguments.getParcelable<MutableList<ApplicationInfo>>("list")
        apps = arguments?.get("listofapps") as MutableList<ApplicationInfo> //get the list of applications
        var view: View? = inflater.inflate(R.layout.fragment_display, container, false)

        val totalapps = ArrayList<Appitem>()
        for ()
        totalapps.add(Appitem("Hello"))
        totalapps.add(Appitem("World"))
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = CustomAdapter(totalapps)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)


        return view
    }
//////////////
class CustomAdapter(val applist: ArrayList<Appitem>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        val application: Appitem = applist[position]
        holder.title.text = application.name //set the text to the name

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view, parent, false)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
        return applist.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.title) as TextView


    }


}


    ////
}
