package com.example.secureapp

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController


/**
 * A simple [Fragment] subclass.
 */
class ScanFragment : Fragment() {

    @RequiresApi(Build.VERSION_CODES.P)
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
                manager.getInstalledPackages(PackageManager.GET_GIDS) //all the installed packages  in the


            //added
            val appinstalled = ArrayList<Appitem>()


            //Go through each application
            for (application in applist) {
                if (manager.getLaunchIntentForPackage(application.packageName) != null) { //none null intents so we know they can be launched
                    if (application.applicationInfo.flags and ApplicationInfo.FLAG_UPDATED_SYSTEM_APP != 0 || application.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0) {
                        //These are the applications that the user has not installed but came pre installed or are built in
                    } else {
                        //This is a user installed application
                        var version: String = application.versionName
                        var appname =
                            manager.getApplicationLabel(application.applicationInfo).toString()
                        var icon = manager.getApplicationIcon(application.applicationInfo)
                        //output for version
                        Log.d("TAG", "Installed package :$appname")
                        Log.d("TAG", "Version $version")

                        //make a new appitem to display for recycler view
                        var newAppItem = Appitem(appname, version, icon)
                        appinstalled.add(newAppItem)

                    }

                }
            }


            //Navigate to the next page

            view?.findNavController()
                ?.navigate(
                    R.id.action_scanFragment_to_displayFragment, bundleOf(
                        "listofapps" to appinstalled
                    )
                )
        }
        return view
    }


}
