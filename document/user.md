### 사용자 API

##### 네이버 로그인
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
PATH: http://tumuto.kr/oauth2/authorization/naver
PARAM: 
```
* 결과 예시(성공)
```
{
    "code" : "SUCCESS",
    "message" : "SUCCESS"
}
```

##### 카카오 로그인
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
PATH: http://tumuto.kr/oauth2/authorization/kakao
PARAM: 
```
* 결과 예시(성공)
```
{
    "code" : "SUCCESS",
    "message" : "SUCCESS"
}
```

##### 회원가입 추가 정보
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: POST
PATH: /api/user/join-info
PARAM: 
String nickname
String[] taste
```
* 결과 예시(성공)
```
{
    "code" : "SUCCESS",
    "message" : "SUCCESS"
}
```

##### 로그아웃

* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
PATH: /api/user/logout
PARAM: 
```
* 결과 예시(성공)
```
{
    "code" : "SUCCESS",
    "message" : "SUCCESS"
}
```

##### 마이페이지 내 정보 조회
```
HTTP METHOD: GET
METHOD NAME : selectMyInfo
PATH: /api/user/info/{userSeq}
PARAM: 없음
```

##### 마이페이지 내 정보 수정
```
HTTP METHOD: PUT
METHOD NAME : updateMyInfo
PATH: /api/user/info/{userSeq}
PARAM: Map<String, Object>

nickName : 닉네임
taste : 취향(배열)
```

##### 마이페이지 내가 좋아요 누른 목록
```
HTTP METHOD: GET
METHOD NAME : selectMyLikedList
PATH: /api/user/likedList/{userSeq}
PARAM: 없음
```

##### 마이페이지 내가 작성한 글 목록
```
HTTP METHOD: GET
METHOD NAME : selectMyBoardList
PATH: /api/user/boardList/{userSeq}
PARAM: 없음
```

##### 마이페이지 내가 작성한 댓글 목록 조회
```
HTTP METHOD: GET
METHOD NAME : selectMyCommentList
PATH: /api/user/commentList/{userSeq}
PARAM: 없음
```

##### 마이페이지 탈퇴
```
HTTP METHOD: DELETE
METHOD NAME : deleteMyInfo
PATH: /api/user/{userSeq}
PARAM: 없음
```

##### 추천 웹툰 목록 조회
```
HTTP METHOD: DELETE
METHOD NAME : selectMyRecommendList
PATH: /api/recommendList/{userSeq}
PARAM: 없음
```