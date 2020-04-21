package com.example.secureapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 */
class ClickableFragment : Fragment() {

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
        view.findViewById<TextView>(R.id.click_text_name).text = name
        view.findViewById<TextView>(R.id.click_text_version).text = version
        view.findViewById<ImageView>(R.id.click_image).setImageDrawable(icon)

        return view

    }

}
