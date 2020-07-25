package com.andysoft.test.views.dashboard.adapters

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andysoft.test.databinding.ListItemBinding
import com.andysoft.test.models.local.BookData
import com.andysoft.test.utils.extensions.READABLE_FORMAT
import com.andysoft.test.utils.extensions.toDateFormat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget


class BookListAdapter(val context: Context?) : RecyclerView.Adapter<BookListAdapter.MyViewHolder>(){
    private var list = mutableListOf<BookData>()
    private val IMAGE_DIRECTORY = "/BookHub/images/"


    fun populate(l: MutableList<BookData>) {
        list = l
        notifyDataSetChanged()
    }

    fun clear() {
        val size: Int = list.size
        list.clear()
        notifyItemRangeRemoved(0, size)
    }

    inner class MyViewHolder(val itemBinding: ListItemBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val bookData = list[position]

        holder.itemBinding.authorName.text = bookData.authorName
        holder.itemBinding.bookName.text = bookData.bookName
        holder.itemBinding.doi.text = bookData.doi.toDateFormat(READABLE_FORMAT)
        holder.itemBinding.price.text = bookData.price.toString()

        context?.let {
            var strPath = "file://" +context.getExternalFilesDir(null).toString() + IMAGE_DIRECTORY + (bookData.coverPhoto?.get(0) ?: "")
            Glide.with(it).asBitmap()
                .load(Uri.parse(strPath))
                .into(holder.itemBinding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )

    override fun getItemCount() = list.size
    override fun getItemViewType(position: Int): Int = position



}