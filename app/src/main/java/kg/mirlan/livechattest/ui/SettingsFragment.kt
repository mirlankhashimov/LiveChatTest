package kg.mirlan.livechattest.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import kg.mirlan.livechattest.BaseFragment
import kg.mirlan.livechattest.R
import kg.mirlan.livechattest.extenions.AppPreferences
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {
    private val appPreferences by lazy { context?.let { AppPreferences(it) } }
    private val CAMERA_REQUEST = 100
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
        if (ContextCompat.checkSelfPermission(
                context!!,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) camera_permission.isChecked = true
        camera_permission.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                if (ContextCompat.checkSelfPermission(
                        context!!,
                        Manifest.permission.CAMERA
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermissions(
                        arrayOf(
                            Manifest.permission.CAMERA
                        ),
                         CAMERA_REQUEST
                    )
                } else {
                    camera_permission.isChecked = true
                }
            }
        }
        check_women.isChecked = appPreferences?.isWomen!!
        check_men.isChecked = appPreferences?.isMen!!
        check_men.setOnCheckedChangeListener { _, isChecked ->
            appPreferences?.isMen = isChecked
        }
        check_women.setOnCheckedChangeListener { _, isChecked ->
            appPreferences?.isWomen = isChecked
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CAMERA_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                camera_permission.isChecked = true
            }
        } else
            camera_permission.isChecked = false
    }
}