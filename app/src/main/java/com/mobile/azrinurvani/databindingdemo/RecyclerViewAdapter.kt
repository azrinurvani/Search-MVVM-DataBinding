package com.mobile.azrinurvani.databindingdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.azrinurvani.databindingdemo.databinding.RecyclerviewListBinding

//TODO 8 - Create RecyclerView Adapter
class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var items = ArrayList<RecyclerData>()

    fun setDataLists(data : ArrayList<RecyclerData>){
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
        //Binding RecyclerView dengan DataBinding
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding  = RecyclerviewListBinding.inflate(layoutInflater)

        return MyViewHolder((binding))
    }

    override fun getItemCount()= items.size

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    class MyViewHolder (val binding : RecyclerviewListBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindData(data : RecyclerData){
            //binding.recyclerData --> (recyclerData) ini adalah variabel yang di set pada layout
            binding.recyclerData = data
            binding.executePendingBindings()
        }

        companion object{
            @JvmStatic
            @BindingAdapter("loadImage")
            fun loadImage(thumbImage : ImageView, url : String){
                Glide.with(thumbImage)
                    .load(url)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
                    .fallback(R.drawable.ic_launcher_background)
                    .into(thumbImage)
            }
        }
    }
}