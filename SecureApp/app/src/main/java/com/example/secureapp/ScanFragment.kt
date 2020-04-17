package com.example.secureapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

/**
 * A simple [Fragment] subclass.
 */
class ScanFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View? = inflater.inflate(R.layout.fragment_scan, container, false)

        view?.findViewById<Button>(R.id.button_scan)?.setOnClickListener({
            view?.findNavController()
                ?.navigate(R.id.action_scanFragment_to_displayFragment)
        })
        return view
    }

}
