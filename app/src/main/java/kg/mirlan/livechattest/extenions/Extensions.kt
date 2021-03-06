package kg.mirlan.livechattest.extenions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun <T> diffItemCallback(
    contentsSame: (Pair<T, T>) -> Boolean = { it.first == it.second },
    itemsSame: (Pair<T, T>) -> Boolean
) = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = itemsSame(Pair(oldItem, newItem))
    override fun areContentsTheSame(oldItem: T, newItem: T) = contentsSame(Pair(oldItem, newItem))
}