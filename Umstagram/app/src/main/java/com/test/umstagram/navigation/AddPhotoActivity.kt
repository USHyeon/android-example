package com.test.umstagram.navigation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.test.umstagram.R
import com.test.umstagram.navigation.model.ContentDTO
import kotlinx.android.synthetic.main.activity_add_photo.*
import java.text.SimpleDateFormat
import java.util.*

class AddPhotoActivity : AppCompatActivity() {
    private var PICK_IMAGE_FROM_ALBUM = 0
    var storage: FirebaseStorage? = null
    var photoUri: Uri? = null
    var auth: FirebaseAuth? = null  //2-1유저의 정보를 가져올 수 있도록 추가.
    var firestore: FirebaseFirestore? = null //2-2데이터 베이스를 받아올 수 있도록 추가.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_photo)

        //Initiate
        storage = FirebaseStorage.getInstance()
        auth = FirebaseAuth.getInstance()   //2-3 유저정보 초기화
        firestore = FirebaseFirestore.getInstance() //2-4 데이터베이스 초기화

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

        //택1)File Upload - Promise 방식 (권장)
        if (storageRef != null) {
            photoUri?.let {
                storageRef.putFile(it).continueWithTask { task: Task<UploadTask.TaskSnapshot> ->
                    return@continueWithTask storageRef.downloadUrl
                }.addOnSuccessListener { uri ->
                    var contentDTO = ContentDTO()

                    //Insert downloadUri of image
                    contentDTO.imageUri = uri.toString()

                    //insert uid of user
                    contentDTO.uid = auth?.currentUser?.uid

                    //insert userId
                    contentDTO.userId = auth?.currentUser?.email

                    //insert explain of content
                    contentDTO.explain = add_photo_edit_explain.text.toString()

                    //insert timestamp
                    contentDTO.timestamp = System.currentTimeMillis()

                    //images 컬렉션 안에 데이터를 넣어줄
                    firestore?.collection("images")?.document()?.set(contentDTO)

                    //업로드가 완려되면 창을 닫음
                    finish()

                    //데이터베이스를 테스트로 생성하고, 규칙에서
                    //  allow read, write: if request.auth.uid != null;
                    //코드를 넣어준다. 그리고 앱을 실행후 사진을 올리면,
                    //파이어베이스(umstagram)->데이터베이스->images 에서 사진 정보 확인 가능.

                }
            }
        }

        //택2)File Upload - callback 방식
        /*if (storageRef != null) {
            photoUri?.let {
                storageRef.putFile(it).addOnSuccessListener {
//                    Toast.makeText(this, getString(R.string.upload_success), Toast.LENGTH_LONG).show()
                    //2-5 이미지가 업로드 되면 이미지 주소를 받아오는 코드를 작성.
                    storageRef.downloadUrl.addOnSuccessListener { uri ->
                        var contentDTO = ContentDTO()

                        //Insert downloadUri of image
                        contentDTO.imageUri = uri.toString()

                        //insert uid of user
                        contentDTO.uid = auth?.currentUser?.uid

                        //insert userId
                        contentDTO.userId = auth?.currentUser?.email

                        //insert explain of content
                        contentDTO.explain = add_photo_edit_explain.text.toString()

                        //insert timestamp
                        contentDTO.timestamp = System.currentTimeMillis()

                        //images 컬렉션 안에 데이터를 넣어줄
                        firestore?.collection("images")?.document()?.set(contentDTO)

                        //업로드가 완려되면 창을 닫음
                        finish()

                    }
                }
            }
        }*/
    }
}
