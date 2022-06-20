### 게시판 API

##### 게시글 목록 조회

##### 게시글 검색

##### 게시글 조회

##### 게시글 수정

##### 게시글 삭제

##### 게시글 좋아요 클릭

##### 게시글 좋아요 클릭 해제

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

