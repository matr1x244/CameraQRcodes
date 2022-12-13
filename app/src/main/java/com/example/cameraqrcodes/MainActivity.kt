package com.example.cameraqrcodes

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder

class MainActivity : AppCompatActivity() {

    var imageQr: AppCompatImageView? = null
    var buttonGenerate: AppCompatButton? = null
    var textViewText: AppCompatEditText? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageQr = findViewById(R.id.imageView_QR)
        textViewText = findViewById(R.id.edit_text_QR)
        buttonGenerate = findViewById(R.id.button_generate)

        startGenerate()
        visibilityQR()
    }

    private fun startGenerate() {
        val barcodeEncode: BarcodeEncoder = BarcodeEncoder()
        val defaultQR: Bitmap =
            barcodeEncode.encodeBitmap("DEFAULT", BarcodeFormat.QR_CODE, 1000, 1000)
        imageQr?.setImageBitmap(defaultQR)

        buttonGenerate?.setOnClickListener {
            try {
                val bitmap: Bitmap = barcodeEncode.encodeBitmap(
                    textViewText?.text.toString(), BarcodeFormat.QR_CODE, 500, 500
                )
                imageQr?.setImageBitmap(bitmap)
                imageQr?.visibility = View.VISIBLE
            } catch (e: WriterException) {
            }
        }
    }

    private fun visibilityQR() {
        imageQr?.setOnClickListener {
            if (textViewText?.text.toString() == "0000") {
                imageQr?.visibility = View.INVISIBLE
            } else {
                imageQr?.visibility = View.VISIBLE
            }
        }
    }

}