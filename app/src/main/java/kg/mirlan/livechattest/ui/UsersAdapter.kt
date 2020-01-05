package kg.mirlan.livechattest.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kg.mirlan.livechattest.R
import kg.mirlan.livechattest.extenions.diffItemCallback
import kg.mirlan.livechattest.extenions.inflate
import kg.mirlan.livechattest.model.User
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_scrollview.view.*

class UsersAdapter(private val onExperienceClick: (User) -> Unit) :
    ListAdapter<User, UsersAdapter.ViewHolder>(
        diffItemCallback { it.first.id == it.second.id }
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_scrollview))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        init {
            containerView.setOnClickListener {
                onExperienceClick(getItem(adapterPosition))
            }
        }

        fun bind(user: User) {
            containerView.image.setActualImageResource(user.id) //setImageURI(experience.id)
        }
    }
}