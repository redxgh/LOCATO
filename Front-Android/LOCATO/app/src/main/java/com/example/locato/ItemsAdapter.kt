package com.example.locato

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.io.Serializable
import java.text.DecimalFormat

class ItemsAdapter(
    private val items: ArrayList<ItemsDomaine>,
    private val recyclerViewType: Int,
    private val listener: OnItemClickListener? = null
) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    private val formatter = DecimalFormat("##,##,##,##,##")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemslistview, parent, false)
        return ViewHolder(inflate)
    }

    @SuppressLint("DiscouragedApi")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]

        // Set data to your views here
        holder.addressTextView.text = currentItem.accomodation?.location ?: ""
        holder.priceTextView.text = formatter.format(currentItem.price)
        holder.titleTextView.text = currentItem.title
        val imageName = currentItem.accomodation?.images?.get(0)

        if (imageName != null) {
            // Get the resource ID based on the image name
            val drawableResourceId = holder.itemView.context.resources.getIdentifier("image1", "drawable", holder.itemView.context.packageName)
            // Log statements for debugging
            Log.d("ImageLoading", "Image Name: $imageName")
            Log.d("ImageLoading", "Drawable Resource ID: $drawableResourceId")
            // Load the image using Glide
            Glide.with(holder.itemView.context)
                .load(drawableResourceId)
                .into(holder.pic)
        } else {
            Log.e("ImageLoading", "Image name is null or empty.")
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("object", currentItem as Serializable)
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteButton.setOnClickListener {
            listener?.onItemClick(currentItem, Action.DELETE)
        }
        holder.img.setOnClickListener {
            val intent = Intent(holder.itemView.context,EditAdActivity::class.java)
            intent.putExtra("id",currentItem.id)
            intent.putExtra("price",currentItem.price)
            intent.putExtra("title",currentItem.title)
            intent.putExtra("desc",currentItem.description)
            intent.putExtra("gender",currentItem.gender)
            intent.putExtra("best", currentItem.accomodation?.best)
            intent.putExtra("location",currentItem.accomodation?.location)
            intent.putExtra("bathrooms",currentItem.accomodation?.bathrooms)
            intent.putExtra("rooms",currentItem.accomodation?.rooms)
            intent.putExtra("surface",currentItem.accomodation?.surface)
            holder.itemView.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val addressTextView: TextView = itemView.findViewById(R.id.adress)
        val priceTextView: TextView = itemView.findViewById(R.id.price)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTxt)
        val pic: ImageView = itemView.findViewById(R.id.pic1)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteCard)
        var img:ImageView = itemView.findViewById(R.id.picEd);

        init {
            deleteButton.setOnClickListener {
                listener?.onItemClick(items[adapterPosition], Action.DELETE)
            }
        }

    }
    interface OnItemClickListener {
        fun onItemClick(item: ItemsDomaine?, action: ItemsAdapter.Action)
    }

    enum class Action {
        DELETE,

    }
}
