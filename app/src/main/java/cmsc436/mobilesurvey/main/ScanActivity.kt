package cmsc436.mobilesurvey.main

import android.os.Bundle
import android.widget.Button

import androidx.appcompat.app.AppCompatActivity
import cmsc436.mobilesurvey.R
import com.google.zxing.integration.android.IntentIntegrator
import android.widget.Toast
import android.content.Intent



class ScanActivity : AppCompatActivity() {


    private var scanBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        scanBtn = findViewById(R.id.scan)
        scanBtn!!.setOnClickListener{
            val scanner = IntentIntegrator(this)
            scanner.initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}
