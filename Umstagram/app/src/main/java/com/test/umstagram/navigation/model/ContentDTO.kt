package com.test.umstagram.navigation.model

data class ContentDTO(
    var explain: String? = null,    //설명을 관리하는
    var imageUri: String? = null,   //이미지 주소를 관리
    var uid: String? = null,        //어느 유저가 올렸는지.
    var userId: String? = null,     //올린 유저의 이미지를 관리해주는.
    var timestamp: Long? = null,    //몇시 몇분에 컨텐츠를 올렸는지 관리.
    var favoriteCount: Int = 0,     //좋아요를 몇개 눌렀는지 관리.
    var favorites: Map<String, Boolean> = HashMap()     //중복 좋아요를 방지. 좋아요를 누른 유저를 관리.
) {
    //댓글을 관리해주는 데이터 클래스
    data class Comment(
        var uid: String? = null,        //
        var userId: String? = null,     //이메일을 관리해주는.
        var comment: String? = null,    //커멘트를 관리해주는.
        var timestamp: Long? = null     //몇시몇분에 커멘트를 달았는지 관리.
    )
}