package kg.mirlan.livechattest.extenions

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import kg.mirlan.livechattest.BuildConfig

class AppPreferences(context: Context) {
    private var preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(SETTINGS_STORAGE__NAME, Context.MODE_PRIVATE)
    }

    var isMen: Boolean
        get() {
            return preferences.getBoolean(
                MEN_KEY,
                IS_CHECK_MEN
            )
        }
        set(value) {
            preferences.edit {
                putBoolean(MEN_KEY, value)
            }
        }
    var isWomen: Boolean
        get() {
            return preferences.getBoolean(
                WOMEN_KEY,
                IS_CHECK_WOMEN
            )
        }
        set(value) {
            preferences.edit {
                putBoolean(WOMEN_KEY, value)
            }
        }

    companion object {
        private const val SETTINGS_STORAGE__NAME =
            BuildConfig.APPLICATION_ID
        private const val MEN_KEY = "men"
        private const val WOMEN_KEY = "women"
        private const val IS_CHECK_MEN = true
        private const val IS_CHECK_WOMEN = true
    }
}