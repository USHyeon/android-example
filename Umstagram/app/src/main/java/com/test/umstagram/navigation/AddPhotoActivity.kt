package com.test.umstagram.navigation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.test.umstagram.R
import kotlinx.android.synthetic.main.activity_add_photo.*
import java.text.SimpleDateFormat
import java.util.*

class AddPhotoActivity : AppCompatActivity() {
    private var PICK_IMAGE_FROM_ALBUM = 0
    var storage: FirebaseStorage? = null
    var photoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_photo)

        //Initiate storage
        storage = FirebaseStorage.getInstance()

        //Open the album
        var photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, PICK_IMAGE_FROM_ALBUM)

        //add image upload event
        add_photo_btn_upload.setOnClickListener {
            contentUpload()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PICK_IMAGE_FROM_ALBUM -> {
                if(resultCode == Activity.RESULT_OK) {
                    //사진을 선택했을 때 경로가 넘어옴
                    photoUri = data?.data
                    add_photo_image.setImageURI(photoUri)
                } else {
                    //취소 버튼을 눌렀을 때 작동하는 부분.
                    finish()
                }
            }

        }
    }

    private fun contentUpload() {
        var timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        var imageFileName = "IMAGE_${timestamp}_.png"
        var storageRef = storage?.reference?.child("images")?.child(imageFileName)

        //File Upload
        if (storageRef != null) {
            photoUri?.let {
                storageRef.putFile(it).addOnSuccessListener {
                    Toast.makeText(this, getString(R.string.upload_success), Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
