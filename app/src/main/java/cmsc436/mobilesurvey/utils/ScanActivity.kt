package cmsc436.mobilesurvey.utils

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import cmsc436.mobilesurvey.main.DashboardActivity
import cmsc436.mobilesurvey.main.MainActivity
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import java.util.jar.Manifest

class ScanActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private var mScannerView: ZXingScannerView? = null

    public override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        mScannerView = ZXingScannerView(this)   // Programmatically initialize the scanner view
        setContentView(mScannerView)                // Set the scanner view as the content view
    }

    public override fun onResume() {
        super.onResume()
        Log.i("TAG", "RESUME")
        mScannerView!!.setResultHandler(this) // Register ourselves as a handler for scan results.

        if (ContextCompat.checkSelfPermission(
                this@ScanActivity,
                android.Manifest.permission.READ_CONTACTS
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            val permissions = arrayOf(android.Manifest.permission.CAMERA)
            ActivityCompat.requestPermissions(this, permissions, 0)
        }
        mScannerView!!.startCamera()          // Start camera on resume
    }

    public override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()           // Stop camera on pause
    }

    override fun handleResult(rawResult: Result) {
        // Do something with the result here
        Log.v("tag", "DEBUG  :" + rawResult.getText()); // Prints scan results
        Log.v(
            "tag",
            "DEBUG: " + rawResult.getBarcodeFormat().toString()
        ); // Prints the scan format (qrcode, pdf417 etc.)

        DashboardActivity.tvresult!!.setText(rawResult.text)
        onBackPressed()

        // If you would like to resume scanning, call this method below:
//        mScannerView!!.resumeCameraPreview(this);
    }
}