package team.nk.kimiljeong.presentation.view.adapter.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun ImageView.loadImageFrom(
    url: String,
) {
    Glide.with(this).load(url).into(this)
}
