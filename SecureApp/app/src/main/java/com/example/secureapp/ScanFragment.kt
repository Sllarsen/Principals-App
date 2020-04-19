package com.example.secureapp

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
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


        view?.findViewById<Button>(R.id.button_scan)?.setOnClickListener {


            val manager: PackageManager =
                activity!!.packageManager; //Package Manager for the activity
            val applist =
                manager.getInstalledApplications(PackageManager.GET_GIDS)  // Get all applications on the phone installed by the group ids


            //Empty list of our apps
            var listofapps: ArrayList<ApplicationInfo> = ArrayList() //The array of all the apps we want to keep


                    for (application in applist) {
                        if (manager.getLaunchIntentForPackage(application.packageName) != null) { //none null intents so we know they can be launched
                            if (application.flags and ApplicationInfo.FLAG_UPDATED_SYSTEM_APP != 0 || application.flags and ApplicationInfo.FLAG_SYSTEM != 0) {
                                //This is a system app or one that has been updated.
                            } else {
                                //This is a user installed application
                                Log.d("TAG", "Installed package :" + application.packageName)
                                listofapps.add(application) //add user app to our list

                            }

                        }
                    }


            //Navigate to the next page

            view?.findNavController()
                ?.navigate(R.id.action_scanFragment_to_displayFragment, bundleOf(
                    "listofapps" to listofapps)
                )
        }
        return view
    }


}
