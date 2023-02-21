package team.nk.kimiljeong.presentation.adapter.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import team.nk.kimiljeong.R

@BindingAdapter("image")
fun ImageView.loadImageFrom(
    url: String?,
) {
    url?.let {
        Glide.with(this).load(it).into(this)
    } ?: run {
        setImageResource(
            R.drawable.img_global_no_profile,
        )
    }
}
