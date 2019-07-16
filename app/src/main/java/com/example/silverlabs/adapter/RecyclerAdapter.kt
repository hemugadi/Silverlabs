package com.example.silverlabs.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater;
import android.view.View
import android.view.ViewGroup;
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.silverlabs.R
import com.example.silverlabs.datamodel.Celebrity


class RecyclerAdapter(val context: Context) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    var celebrityList : List<Pair<String,Celebrity>> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return celebrityList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.name.text = celebrityList.get(position).first
        holder.age.text = celebrityList.get(position).second.age.toString()
        holder.popularity.text = celebrityList.get(position).second.popularity.toString()
        holder.height.text = celebrityList.get(position).second.height
        Glide.with(context).load(celebrityList.get(position).second.photo)
            .apply(RequestOptions().centerCrop())
            .into(holder.image)
    }

    fun setCelebrityListItems(celebrityList: List<Pair<String,Celebrity>>){
        this.celebrityList = celebrityList;
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val name: TextView = itemView!!.findViewById(R.id.name)
        val image: ImageView = itemView!!.findViewById(R.id.image)
        val age: TextView = itemView!!.findViewById(R.id.age)
        val popularity: TextView = itemView!!.findViewById(R.id.popularity)
        val height: TextView = itemView!!.findViewById(R.id.height)

    }
}