package com.example.secureapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * A simple [Fragment] subclass.
 */
class DisplayFragment : Fragment() {

    lateinit var applist: ArrayList<Appitem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        applist = arguments?.getSerializable("listofapps") as ArrayList<Appitem>

        var view: View? = inflater.inflate(R.layout.fragment_display, container, false)




        //set up recycler

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = CustomAdapter(applist)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)


        return view
    }

class CustomAdapter(val applist: ArrayList<Appitem>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        val application: Appitem = applist[position]
        holder.name.text = application.name //set the text to the name of the app
        holder.version.text = "Version: " + application.version //set the text to the version
        holder.icon.setImageDrawable(application.icon) //set it to the drawable


        holder.itemView.setOnClickListener { view ->
            var onearraylist = ArrayList<Appitem>()
            onearraylist.add(application)
            view!!.findNavController().navigate(
            R.id.action_displayFragment_to_clickableFragment, bundleOf(
                    "application" to onearraylist))





        }



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
        val name = itemView.findViewById<TextView>(R.id.name) as TextView
        val version = itemView.findViewById<TextView>(R.id.version) as TextView
        val icon = itemView.findViewById<ImageView>(R.id.icon) as ImageView




    }


}


    ////
}
