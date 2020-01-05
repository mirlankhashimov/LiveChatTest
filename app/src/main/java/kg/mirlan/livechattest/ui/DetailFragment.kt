package kg.mirlan.livechattest.ui

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import kg.mirlan.livechattest.BaseFragment
import kg.mirlan.livechattest.R
import kg.mirlan.livechattest.extenions.Constants
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : BaseFragment(R.layout.fragment_detail) {
    private val CAMERA_REQUEST = 100
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        avatar.setActualImageResource(Constants.userImages[arguments?.getInt("imageNumber") ?: 0].id)
        back.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
        camera_start.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    context!!,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST)
            } else {
                camera_start.visibility = View.GONE
                camera.start()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        camera_start.visibility = View.VISIBLE
    }

    override fun onPause() {
        camera.stop()
        super.onPause()
    }

    override fun onDestroyView() {
        camera.destroy()
        super.onDestroyView()
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
                camera_start.visibility = View.GONE
                camera.start()
            }
        }
    }
}