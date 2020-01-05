package kg.mirlan.livechattest.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import kg.mirlan.livechattest.BaseFragment
import kg.mirlan.livechattest.R
import kg.mirlan.livechattest.extenions.AppPreferences
import kg.mirlan.livechattest.extenions.Constants
import kg.mirlan.livechattest.model.User
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber
import kotlin.random.Random

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val usersAdapter by lazy {
        UsersAdapter(
            this::selectImage
        )
    }
    private val appPreferences by lazy { context?.let { AppPreferences(it) } }
    private var currentPosition = 1
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        count_online_users.text = "Online: ${Random.nextInt(500, 1500)}"
        scrollView.adapter = usersAdapter
        val divider = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL).apply {
            setDrawable(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.divider_0dp
                )!!
            )
        }
        scrollView.addItemDecoration(divider)
        scrollView.scrollToPosition(currentPosition)
        scrollView.setItemTransformer(
            ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build()
        )
        setClicks()

    }

    private fun setClicks() {
        settings.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_home_screen_to_settings_screen)
        }
        slide.setOnClickListener {
            if (currentPosition == Constants.userImages.size - 1) {
                currentPosition = 0
            }
            scrollView.scrollToPosition(++currentPosition)
        }
        write.setOnClickListener {
            val bundle = bundleOf("imageNumber" to currentPosition)
            Navigation.findNavController(it).navigate(R.id.action_home_screen_to_detail_screen, bundle)
        }
        start_search.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_home_screen_to_search_screen)
        }
    }

    private fun selectImage(user: User) {}
    override fun onResume() {
        super.onResume()
        updateUsers()
    }

    private fun updateUsers() {
        when {
            appPreferences?.isMen == false -> usersAdapter.submitList(Constants.getWomen())
            appPreferences?.isWomen == false -> usersAdapter.submitList(Constants.getMen())
            else -> usersAdapter.submitList(Constants.userImages)
        }

    }
}