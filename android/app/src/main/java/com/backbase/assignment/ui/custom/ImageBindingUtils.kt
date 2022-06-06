package com.backbase.assignment.ui.custom

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.backbase.assignment.R
import com.backbase.assignment.ui.utils.Singleton
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter(value = ["customImagePath", "placeholder"], requireAll = false)
fun imageUrls(view: ImageView, customImagePath: String?, placeholder: Drawable) {
    Glide.with(view.context)
        .load(Singleton.imageBaseUrl + customImagePath)
        .placeholder(placeholder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply(RequestOptions().error(placeholder))
        .into(view)

}

@BindingAdapter(value = ["customRateView"], requireAll = false)
fun ratingView(view: ShapeableImageView, count: Double?) {
    count?.let {
            val value=getCount(it)
        when {
            value>50 -> {
                view.setBackgroundColor(ContextCompat.getColor(view.context,R.color.colorGreen))
            }
            value<50 -> {
                view.setBackgroundColor(ContextCompat.getColor(view.context,R.color.colorRed))
            }
        }
    }

}
private fun getCount(count:Double): Long {
    return (((count / 10) * 100).toLong())
}
@BindingAdapter("customImageFromUrl")
fun setImageFromUrl(imageView: ImageView, filePath: String?) {
    val option: RequestOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .placeholder(R.drawable.ic_no_image)
        .fitCenter()

    Glide.with(imageView.context)
        .load(filePath)
        .apply(option)
        .into(imageView)
}
