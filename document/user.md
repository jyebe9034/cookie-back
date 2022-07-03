### 사용자 API

#### 네이버 로그인
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
PATH: http://tumuto.kr/oauth2/authorization/naver
PARAM: 파라미터 없음
```
* 결과 예시(성공)
```
{
    "resultMsg" : "SUCCESS",
    "jwt-token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJfZnltdll1TGxHcnU5MjJ5RDNBR0Vfd2Z0VmM2c2pzVWRMT3B0ajRuQ1FVIiwiaWF0IjoxNjU2MTQxMzMyLCJleHAiOjE2NTYxNDE0MTl9.AneWYGsR_dC6u2vJ23kL6zovFd7_fCBKMyzUs_89L3E",
    "message":"SUCCESS",
    "user":{
        "role":"USER",
        "nickname":null,
        "id":"_fymvYuLlGru922yD3AGE_wftVc6sjsUdLOptj4nCQU",
        "seq":30
    }
}
```
* 결과 예시(실패)
```
{
    "resultMsg" : "로그인 중 오류가 발생했습니다. 다시 로그인해주세요."
}
``` 
    
#### 카카오 로그인
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
PATH: http://tumuto.kr/oauth2/authorization/kakao
PARAM: 파라미터 없음
```
* 결과 예시(성공)
```
{
    "resultMsg" : "SUCCESS",
    "jwt-token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyMjAzOTAzNjY3IiwiaWF0IjoxNjU2MTQxNDc3LCJleHAiOjE2NTYxNDE1NjN9.rvYDSgsRWWUzHw9eb_gLx0TpyJ1D7GIHtzAaEMImLr4",
    "message":"SUCCESS",
    "user":{
        "role":"USER",
        "nickname":null,
        "id":"2203903667",
        "seq":32
    }
}
```
* 결과 예시(실패)
```
{
    "resultMsg" : "로그인 중 오류가 발생했습니다. 다시 로그인해주세요."
}
``` 

##### 회원가입 추가 정보
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: POST
PATH: /api/user/join
PARAM: 
Long seq
String nickname
String[] taste
```
* 요청 예시
```
{
    "seq": 30,
    "nickname": "다다",
    "taste": [
        "로맨스", "판타지"
    ]
}
```
* 결과 예시(성공)
```
{
    "resultMsg" : "SUCCESS",
    "jwt-token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJfZnltdll1TGxHcnU5MjJ5RDNBR0Vfd2Z0VmM2c2pzVWRMT3B0ajRuQ1FVIiwiaWF0IjoxNjU2MTQ3MzgxLCJleHAiOjE2NTYxNDc0Njh9.8u5hWq_QHFIlIO4gkPo2EgqNa6ZP_a_TGXPLUhijti8",
    "message": "SUCCESS",
    "user": {
        "role": "USER",
        "nickname": "다다",
        "id": "_fymvYuLlGru922yD3AGE_wftVc6sjsUdLOptj4nCQU",
        "seq": 30
    }
}
```
* 결과 예시(실패)
```
{
    "resultMsg" : "로그인 중 오류가 발생했습니다. 다시 로그인해주세요."
}
``` 

##### 로그아웃

* 기본 정보 및 파라미터 설명
```
HTTP METHOD: POST
PATH: /api/user/logout
PARAM: Long seq 
```
* 결과 예시(성공)
```
{
    "resultCode" : "SUCCESS",
    "resultMsg" : "SUCCESS"
}
```
* 결과 예시(실패)
```
{
    "resultMsg" : "로그아웃 중 오류가 발생했습니다. 다시 시도해주세요."
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