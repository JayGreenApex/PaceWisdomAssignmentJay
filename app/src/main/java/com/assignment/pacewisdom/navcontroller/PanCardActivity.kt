package com.assignment.pacewisdom.navcontroller

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.assignment.pacewisdom.R
import com.assignment.pacewisdom.utils.setLocalImage
import com.github.dhaval2404.imagepicker.ImagePicker
import java.io.File


class PanCardActivity : AppCompatActivity() {

    private lateinit var imgBack: AppCompatImageView
    private lateinit var txvNoCardDetails: AppCompatTextView
    private lateinit var btnPanOne: AppCompatButton
    private lateinit var btnPanTwo: AppCompatButton
    private lateinit var imgPanOne: AppCompatImageView
    private lateinit var imgPanTwo: AppCompatImageView

    companion object {
        private const val GALLERY_IMAGE_REQ_CODE = 102
        private const val CAMERA_IMAGE_REQ_CODE = 103
    }

    private var mCameraFile: File? = null
    private var mGalleryFile: File? = null

    var clickedOne = false


    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pan_card)

        supportActionBar?.hide()

        val window: Window = this.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        }

        initComponents()
    }

    private fun initComponents() {
        imgBack = findViewById(R.id.imgBack)
        imgBack.setOnClickListener {
            onBackPressed()
        }

        imgPanOne = findViewById(R.id.imgPanOne)
        imgPanTwo = findViewById(R.id.imgPanTwo)

        btnPanOne = findViewById(R.id.btnPanOne)

        btnPanOne.setOnClickListener {
            clickedOne = true
            showImagePickDialog()
        }

        btnPanTwo = findViewById(R.id.btnPanTwo)

        btnPanTwo.setOnClickListener {
            clickedOne = false
            showImagePickDialog()
        }

        imgPanOne.setOnClickListener {
            clickedOne = true
            showImagePickDialog()
        }

        imgPanTwo.setOnClickListener {
            clickedOne = false
            showImagePickDialog()
        }


        val wordSpan: Spannable =
            SpannableString(getString(R.string.no_pan_info))
        wordSpan.setSpan(
            ForegroundColorSpan(Color.BLUE),
            51,
            60,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        txvNoCardDetails = findViewById(R.id.txvNoCardDetails)
        txvNoCardDetails.text = wordSpan
    }


    private fun pickGalleryImage() {
        ImagePicker.with(this)
            // Crop Image(User can choose Aspect Ratio)
            .crop()
            // User can only select image from Gallery
            .galleryOnly()
            .galleryMimeTypes( // no gif images at all
                mimeTypes = arrayOf(
                    "image/png",
                    "image/jpg",
                    "image/jpeg"
                )
            )
            // Image resolution will be less than 1080 x 1920
            .maxResultSize(1080, 1920)
            .start(GALLERY_IMAGE_REQ_CODE)
    }

    private fun pickCameraImage() {
        ImagePicker.with(this)
            .crop()
            // User can only capture image from Camera
            .cameraOnly()
            // Image size will be less than 1024 KB
            .compress(1024)
            .saveDir(File(this.externalCacheDir!!.absolutePath, "ImagePicker"))
            // .saveDir(File(cacheDir, "ImagePicker"))
            // .saveDir(getExternalFilesDir("ImagePicker")!!)
            .start(CAMERA_IMAGE_REQ_CODE)
    }

    private fun showImagePickDialog() {
        val dialogPickImage = AppCompatDialog(this)
        dialogPickImage.setContentView(R.layout.dialog_choose_image)
        //Grab the window of the dialog, and change the width
        val lp: WindowManager.LayoutParams = WindowManager.LayoutParams()
        val window = dialogPickImage.window
        dialogPickImage.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        lp.copyFrom(window!!.attributes)
        //This makes the dialog take up the full width
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.BOTTOM
        window.attributes = lp
        val txvGallery: AppCompatTextView = dialogPickImage.findViewById(R.id.txvGallery)!!
        val txvCamera: AppCompatTextView = dialogPickImage.findViewById(R.id.txvCamera)!!
        val txvCancel: AppCompatTextView = dialogPickImage.findViewById(R.id.txvCancel)!!
        txvGallery.setOnClickListener {
            pickGalleryImage()
            dialogPickImage.dismiss()
        }
        txvCamera.setOnClickListener {
            pickCameraImage()
            dialogPickImage.dismiss()
        }
        txvCancel.setOnClickListener { dialogPickImage.dismiss() }
        dialogPickImage.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            Log.e("TAG", "Path:${ImagePicker.getFilePath(data)}")
            // File object will not be null for RESULT_OK
            val file = ImagePicker.getFile(data)!!
            when (requestCode) {
                GALLERY_IMAGE_REQ_CODE -> {
                    mGalleryFile = file
                    if (clickedOne) {
                        imgPanOne.visibility = View.VISIBLE
                        btnPanOne.visibility = View.GONE
                        imgPanOne.setLocalImage(file)
                    } else {
                        imgPanTwo.visibility = View.VISIBLE
                        btnPanTwo.visibility = View.GONE
                        imgPanTwo.setLocalImage(file)
                    }

                    clickedOne = false
                }
                CAMERA_IMAGE_REQ_CODE -> {
                    mCameraFile = file
                    if (clickedOne) {
                        imgPanOne.visibility = View.VISIBLE
                        btnPanOne.visibility = View.GONE
                        imgPanOne.setLocalImage(file, false)
                    } else {
                        imgPanTwo.visibility = View.VISIBLE
                        btnPanTwo.visibility = View.GONE
                        imgPanTwo.setLocalImage(file, false)
                    }

                    clickedOne = false
                }
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


}