package com.example.secureapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext


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

    var success = true

    //adapter for recyclerview
    class CustomAdapter(val applist: ArrayList<Appitem>) :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

        override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
            val application: Appitem = applist[position]
            holder.name.text = application.name //set the text to the name of the app
            holder.version.text = "Version: " + application.version //set the text to the version
            holder.icon.setImageDrawable(application.icon) //set it to the drawable


            holder.itemView.setOnClickListener { view ->

                //Set an array list of one item to pass as bundle
                var onearraylist = ArrayList<Appitem>()
                onearraylist.add(application)
                //call post with retrofit


                //Build Json
                val json = JSONObject()
//            json.accumulate("name", application.name)
//            json.accumulate("version", application.version)
//            json.accumulate("date" , MainActivity.DATE)

                //Trim the name for spaces and special chars
                var trimmedname = application.name.filter { it.isLetterOrDigit() }
                //main append with the application name and the version
                json.accumulate(trimmedname, application.version)
               // getAppCall(json)

                //explore land


                val dataCall: Call<ResponseBody> = RetrofitService.create(MainActivity.URL).getClickedApp(json)
                dataCall.enqueue(object : Callback<ResponseBody?> {
                    override fun onResponse(
                        call: Call<ResponseBody?>,
                        response: Response<ResponseBody?>
                    ) {

                        //navigate to next page
                        if(response.isSuccessful){
                            var body =  response.body()?.string()
                            view!!.findNavController().navigate(
                                R.id.action_displayFragment_to_clickableFragment, bundleOf(
                                    "application" to onearraylist, "body" to body
                                )
                            )

                        }


                    }

                    override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
//                        Toast.makeText(this, "Enter a non-zero number ", Toast.LENGTH_SHORT).show()
//                        Toast.makeText()
                        Log.e("ERR", "ERROR on request")

                    }
                })





                //explore land
//
//                //navigate to next page
//                view!!.findNavController().navigate(
//                    R.id.action_displayFragment_to_clickableFragment, bundleOf(
//                        "application" to onearraylist
//                    )
//                )


            }


        }


        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): CustomAdapter.ViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view, parent, false)

            return ViewHolder(v)
        }


        override fun getItemCount(): Int {
            return applist.size
        }

//        //Server Posting
//        private var coroutineJob = Job()
//        private val coroutineContext: CoroutineContext
//            get() = coroutineJob + Dispatchers.IO
//        private val scope = CoroutineScope(coroutineContext)
//        var thisresponse = null
//
//        //post the application to server
//        fun getAppCall(json: JSONObject) {//} = scope.launch(Dispatchers.IO) {
//
//            coroutineJob?.cancel()
//            coroutineJob = scope.launch(Dispatchers.IO) {
//                val response = async {
//                    var call = RetrofitService.create(MainActivity.URL).getClickedApp(json)
//                    call.execute()
//                }
//
//                var responseFinal = response.await()
//                if(responseFinal.isSuccessful){
//                    thisresponse = responseFinal.body()
//                }
//
//
//            } as CompletableJob
//
//
//        }


        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val name = itemView.findViewById<TextView>(R.id.name) as TextView
            val version = itemView.findViewById<TextView>(R.id.version) as TextView
            val icon = itemView.findViewById<ImageView>(R.id.icon) as ImageView

        }


    }


    ////
}
