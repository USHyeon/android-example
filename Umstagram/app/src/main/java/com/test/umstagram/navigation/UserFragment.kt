package com.test.umstagram.navigation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.test.umstagram.LoginActivity
import com.test.umstagram.MainActivity
import kotlinx.android.synthetic.main.fragment_user.view.*

import com.test.umstagram.R
import com.test.umstagram.navigation.model.ContentDTO
import kotlinx.android.synthetic.main.activity_main.*

class UserFragment : Fragment() {
    var fragmentView: View? = null
    var firestore: FirebaseFirestore? = null
    var uid: String? = null
    var auth: FirebaseAuth? = null
    var currentUserUid: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.fragment_user, container, false)
        uid = arguments?.getString("destinationUid")    //이전 화면에서 넘어온 값을 받아옴
        firestore = FirebaseFirestore.getInstance()     //firebaseStore initialization
        auth = FirebaseAuth.getInstance()
        currentUserUid = auth?.currentUser?.uid

        if(uid == currentUserUid) {
            //MyPage
            fragmentView?.account_btn_follow_signout?.text = getString(R.string.signout)
            fragmentView?.account_btn_follow_signout?.setOnClickListener {
                activity?.finish()
                startActivity(Intent(activity, LoginActivity::class.java))
                auth?.signOut()
            }
        } else {
            //OtherUserPage
            fragmentView?.account_btn_follow_signout?.text = getString(R.string.follow)
            var mainActivity = (activity as MainActivity).apply {
                this.toolbar_user_name?.text = arguments?.getString("userId")
                this.toolbar_btn_back?.setOnClickListener {
                    this.bottom_navigation.selectedItemId = R.id.action_home
                }
                this.toolbar_title_image?.visibility = View.GONE
                this.toolbar_user_name?.visibility = View.VISIBLE
                this.toolbar_btn_back?.visibility = View.VISIBLE
            }

        }
        fragmentView?.account_recyclerview?.adapter = UserFragmentRecycleViewAdapter()
        fragmentView?.account_recyclerview?.layoutManager = GridLayoutManager(activity,3)
        return fragmentView
    }

    inner class UserFragmentRecycleViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        var contentDTOs: ArrayList<ContentDTO> = arrayListOf()
        init {
            firestore?.collection("images")?.whereEqualTo("uid",uid)?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                //Sometimes, This code return null of querySnapshot then it signout
                if(querySnapshot == null) {
                    return@addSnapshotListener
                }
                //Get Data
                for (snapshot in querySnapshot.documents) {
                    contentDTOs.add(snapshot.toObject(ContentDTO::class.java)!!)
                }
                fragmentView?.account_tv_post_count?.text = contentDTOs.size.toString()
                notifyDataSetChanged()
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            //화면의 폭을 먼저 가져옴.
            var width = resources.displayMetrics.widthPixels / 3    //화면의 폭의 3분 1
            var imageView = ImageView(parent.context)
            imageView.layoutParams = LinearLayoutCompat.LayoutParams(width,width) //정사각형 이미지뷰를 만듬
            return CustomViewHolder(imageView)
        }

        // 넘어온 imageView 를 RecyclerView.ViewHolder 로 넘겨준다.
        inner class CustomViewHolder(var imageView: ImageView) : RecyclerView.ViewHolder(imageView) {

        }

        override fun getItemCount(): Int {
            return contentDTOs.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var imageView = (holder as CustomViewHolder).imageView
            Glide.with(holder.itemView.context).load(contentDTOs[position].imageUri).apply(RequestOptions().centerCrop()).into(imageView)
        }

    }
}
