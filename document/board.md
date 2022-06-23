### 게시판 API

##### 게시글 목록 조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
PATH: /api/board
PARAM: 
```
* 결과 예시(성공)
```
{
    "code" : "SUCCESS",
    "message" : "SUCCESS"
}
```

##### 게시글 검색
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
PATH: /api/board/search
PARAM: String title
```
* 결과 예시(성공)
```
{
    "code" : "SUCCESS",
    "message" : "SUCCESS"
}
```

##### 게시글 조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
PATH: /api/board/{boardSeq}
PARAM: Long boardSeq 게시글 번호
```
* 결과 예시(성공)
```
{
    "code" : "SUCCESS",
    "message" : "SUCCESS"
}
```

##### 게시글 등록
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: POST
PATH: /api/board/{boardSeq}
PARAM: 
```
* 결과 예시(성공)
```
{
    "code" : "SUCCESS",
    "message" : "SUCCESS"
}
```

##### 게시글 수정
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: PUT
PATH: /api/board/{boardSeq}
PARAM: Long boardSeq 게시글 번호
```
* 결과 예시(성공)
```
{
    "code" : "SUCCESS",
    "message" : "SUCCESS"
}
```

##### 게시글 삭제
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: DELETE
PATH: /api/board/{boardSeq}
PARAM: Long boardSeq 게시글 번호
```
* 결과 예시(성공)
```
{
    "code" : "SUCCESS",
    "message" : "SUCCESS"
}
```

##### 게시글 좋아요 클릭
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: POST
PATH: /api/board/like
PARAM: 
Long boardSeq 게시글 번호
Long userSeq 고객 번호
```
* 결과 예시(성공)
```
{
    "code" : "SUCCESS",
    "message" : "SUCCESS"
}
```

##### 메인화면에서 노출되는 인기달글, 신규달글
```
HTTP METHOD: GET
METHOD NAME : selectMainBoardList
PATH: /api/board/main/best 
PARAM: 없음 
```

##### 게시글 내용 이미지 업로드
```
HTTP METHOD: POST
METHOD NAME : uploadContentImage
PATH: /api/board/editor/image 
PARAM: 

formData.append('contentImage', file) 
이런식으로 파일 한개를 formDate로 보내주시면 되요!
```

