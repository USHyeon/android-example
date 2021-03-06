package com.test.umstagram.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.test.umstagram.R
import com.test.umstagram.navigation.model.ContentDTO
import kotlinx.android.synthetic.main.fragment_detail_view.view.*
import kotlinx.android.synthetic.main.item_detail.view.*

/**
 * A simple [Fragment] subclass.
 */
class DetailViewFragment : Fragment() {

    var firestore: FirebaseFirestore? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_view, container, false)
        firestore = FirebaseFirestore.getInstance()

        view.detailviewfragment_recyclerview.adapter = DetailViewRecyclerViewAdapter()
        view.detailviewfragment_recyclerview.layoutManager = LinearLayoutManager(activity)
        return view
    }

    inner class DetailViewRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        var contentDTOs: ArrayList<ContentDTO> = arrayListOf()
        var contentUidList: ArrayList<String> = arrayListOf()

        init {
            //DB 에 접근해서 데이터를 받아올 수 있는 쿼리를 작성
            firestore?.collection("images")?.orderBy("timestamp")
                ?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                    //스탭샷을 찍도록 함
                    //그리고 찍자마자 contentDTOs 와 contentUidList 에 있는 값을 초기화.
                    contentDTOs.clear()
                    contentUidList.clear()
                    //스냅샷에 들어가 있는 데이터를 하나하나씩 부른다.
                    querySnapshot?.documents?.let {
                        for (snapshot in it) {
                            val item = snapshot.toObject(ContentDTO::class.java)
                            if (item != null) {
                                contentDTOs.add(item)
                                contentUidList.add(snapshot.id)
                            }
                            notifyDataSetChanged()
                        }
                    }
                }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_detail, parent, false)
            return CustomViewHolder(view)
        }

        inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view)

        override fun getItemCount(): Int {
            return contentDTOs.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            //서버에서 넘어온 데이터를 매핑 시켜주는 부분
            val viewHolder = (holder as CustomViewHolder).itemView

            //UserId
            viewHolder.detailviewItem_profile_textview.text = contentDTOs[position].userId
            //Image
            Glide.with(holder.itemView.context).load(contentDTOs[position].imageUri).into(viewHolder.detailviewitem_imageview_content)
            //Explain
            viewHolder.detailviewitem_explain_textview.text = contentDTOs[position].explain
            //likes
            viewHolder.detailviewitem_favoritecounter_textview.text = "likes ${contentDTOs[position].favoriteCount}"
            //ProfileImage
            Glide.with(holder.itemView.context).load(contentDTOs[position].imageUri).into(viewHolder.detailviewitem_profile_image)

        }

    }

}
