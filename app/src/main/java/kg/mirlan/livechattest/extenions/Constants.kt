package kg.mirlan.livechattest.extenions

import kg.mirlan.livechattest.R
import kg.mirlan.livechattest.model.User

object Constants {
    val userImages = arrayListOf(
        User(R.drawable.im1, true),
        User(R.drawable.im2, true),
        User(R.drawable.im3, true),
        User(R.drawable.im4, true),
        User(R.drawable.im5),
        User(R.drawable.im6),
        User(R.drawable.im7),
        User(R.drawable.im8),
        User(R.drawable.im9)
    )
    fun getWomen() = userImages.filter { !it.gender }
    fun getMen() = userImages.filter { it.gender }
}