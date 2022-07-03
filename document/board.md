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
    "data": [
        {
            "boardSeq": 26,
            "thumbnailPath": "https://shared-comic.pstatic.net/thumb/webtoon/727798/thumbnail/thumbnail_IMAG06_62cc7200-df0d-45eb-ab58-8258baefd8a2.jpg",
            "title": "액션 좋아하세요? 겟백 어떠세요?",
            "nickname": "소소",
            "createDate": "2022-05-22T00:00",
            "readCount": 0,
            "likeCount": 0,
            "commentCount": 0,
            "genre": "에피소드"
        },
        {
            "boardSeq": 18,
            "thumbnailPath": "https://shared-comic.pstatic.net/thumb/webtoon/721433/thumbnail/thumbnail_IMAG06_0bf37896-e9db-4457-9568-1d9912675b9d.jpg",
            "title": "집이 없어..? 어..?",
            "nickname": "투무토",
            "createDate": "2022-05-15T00:00",
            "readCount": 0,
            "likeCount": 0,
            "commentCount": 0,
            "genre": "드라마"
        },
        {
            "boardSeq": 25,
            "thumbnailPath": "https://shared-comic.pstatic.net/thumb/webtoon/785250/thumbnail/thumbnail_IMAG06_31c92ada-cebe-415d-b5fc-d400238cbab7.jpg",
            "title": "title",
            "nickname": "소소",
            "createDate": "2022-05-22T00:00",
            "readCount": 0,
            "likeCount": 0,
            "commentCount": 0,
            "genre": "로맨스"
        },
        {
            "boardSeq": 30,
            "thumbnailPath": "https://shared-comic.pstatic.net/thumb/webtoon/774870/thumbnail/thumbnail_IMAG06_38b55265-2706-4e98-9cc0-1aad0839c0fa.jpg",
            "title": "제목 테스트",
            "nickname": "소소",
            "createDate": "2022-05-22T00:00",
            "readCount": 0,
            "likeCount": 0,
            "commentCount": 0,
            "genre": "로맨스"
        },
        {
            "boardSeq": 27,
            "thumbnailPath": "https://shared-comic.pstatic.net/thumb/webtoon/774870/thumbnail/thumbnail_IMAG06_38b55265-2706-4e98-9cc0-1aad0839c0fa.jpg",
            "title": "제목 테스트",
            "nickname": "소소",
            "createDate": "2022-05-22T00:00",
            "readCount": 0,
            "likeCount": 0,
            "commentCount": 0,
            "genre": "로맨스"
        },
        {
            "boardSeq": 29,
            "thumbnailPath": "https://shared-comic.pstatic.net/thumb/webtoon/727798/thumbnail/thumbnail_IMAG06_62cc7200-df0d-45eb-ab58-8258baefd8a2.jpg",
            "title": "액션 좋아하세요? 겟백 어떠세요?",
            "nickname": "소소",
            "createDate": "2022-05-22T00:00",
            "readCount": 0,
            "likeCount": 0,
            "commentCount": 0,
            "genre": "에피소드"
        },
        {
            "boardSeq": 17,
            "thumbnailPath": "https://shared-comic.pstatic.net/thumb/webtoon/726214/thumbnail/thumbnail_IMAG06_e7362193-69c0-4f55-8b7c-c651811f10d7.jpg",
            "title": "정년이를 아세요?",
            "nickname": "소소",
            "createDate": "2022-05-15T00:00",
            "readCount": 0,
            "likeCount": 4,
            "commentCount": 4,
            "genre": "드라마"
        },
        {
            "boardSeq": 21,
            "thumbnailPath": "https://shared-comic.pstatic.net/thumb/webtoon/727798/thumbnail/thumbnail_IMAG06_62cc7200-df0d-45eb-ab58-8258baefd8a2.jpg",
            "title": "액션 좋아하세요? 겟백 어떠세요?",
            "nickname": "소소",
            "createDate": "2022-05-15T00:00",
            "readCount": 0,
            "likeCount": 0,
            "commentCount": 0,
            "genre": "에피소드"
        },
        {
            "boardSeq": 28,
            "thumbnailPath": "https://shared-comic.pstatic.net/thumb/webtoon/785250/thumbnail/thumbnail_IMAG06_31c92ada-cebe-415d-b5fc-d400238cbab7.jpg",
            "title": "title",
            "nickname": "소소",
            "createDate": "2022-05-22T00:00",
            "readCount": 0,
            "likeCount": 0,
            "commentCount": 0,
            "genre": "로맨스"
        }
    ],
    "resultMsg": "SUCCESS"
}
```
* 결과 예시(실패)
```
{
    "resultMsg" : "아직 글이 존재하지 않습니다."
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
    "data": [
        {
            "boardSeq": 29,
            "thumbnailPath": "https://shared-comic.pstatic.net/thumb/webtoon/727798/thumbnail/thumbnail_IMAG06_62cc7200-df0d-45eb-ab58-8258baefd8a2.jpg",
            "title": "액션 좋아하세요? 겟백 어떠세요?",
            "nickname": "소소",
            "createDate": "2022-05-22T00:00",
            "readCount": 0,
            "likeCount": 0,
            "commentCount": 0,
            "genre": "에피소드"
        },
        {
            "boardSeq": 26,
            "thumbnailPath": "https://shared-comic.pstatic.net/thumb/webtoon/727798/thumbnail/thumbnail_IMAG06_62cc7200-df0d-45eb-ab58-8258baefd8a2.jpg",
            "title": "액션 좋아하세요? 겟백 어떠세요?",
            "nickname": "소소",
            "createDate": "2022-05-22T00:00",
            "readCount": 0,
            "likeCount": 0,
            "commentCount": 0,
            "genre": "에피소드"
        },
        {
            "boardSeq": 21,
            "thumbnailPath": "https://shared-comic.pstatic.net/thumb/webtoon/727798/thumbnail/thumbnail_IMAG06_62cc7200-df0d-45eb-ab58-8258baefd8a2.jpg",
            "title": "액션 좋아하세요? 겟백 어떠세요?",
            "nickname": "소소",
            "createDate": "2022-05-15T00:00",
            "readCount": 0,
            "likeCount": 0,
            "commentCount": 0,
            "genre": "에피소드"
        }
    ],
    "resultMsg": "SUCCESS"
}
```
* 결과 예시(실패)
```
{
    "resultMsg" : "검색어를 입력해주세요."
}
```
* 결과 예시(실패)
```
{
    "resultMsg" : "(검색어)에 대한 검색 결과가 없습니다."
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
    "data": {
        "boardSeq": 18,
        "thumbnailPath": "https://shared-comic.pstatic.net/thumb/webtoon/721433/thumbnail/thumbnail_IMAG06_0bf37896-e9db-4457-9568-1d9912675b9d.jpg",
        "title": "집이 없어..? 어..?",
        "contents": "나도 집이 있음 좋겠다. 암튼 뭐 이건 그런 웹툰은 아니구요",
        "writer": 21,
        "nickname": "투무토",
        "createDate": "2022-05-15T00:00",
        "readCount": 0,
        "likeCount": 0,
        "commentCount": 0,
        "genre": "드라마"
    },
    "resultMsg": "SUCCESS"
}
```
* 결과 예시(실패)
```
{
    "resultMsg" : "해당 게시글이 없습니다."
}
```

##### 게시글 등록
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: POST
PATH: /api/board/{boardSeq}
TOKEN: O
PARAM: 
Long webtoonSeq 웹툰 번호
String title    제목
String contents 내용
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
    "resultMsg" : "게시글 등록 중 오류가 발생했습니다. 다시 시도해주세요."
}
```

##### 게시글 수정
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: PUT
PATH: /api/board/{boardSeq}
TOKEN: O
PARAM: 
Long boardSeq   게시글 번호
Long webtoonSeq 웹툰 번호
String title    제목
String contents 내용
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
    "resultMsg" : "해당 게시글이 없습니다."
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
    "resultCode" : "SUCCESS",
    "resultMsg" : "SUCCESS"
}
```
* 결과 예시(실패)
```
{
    "resultMsg" : "해당 게시글이 없습니다."
}
```

##### 게시글 좋아요 클릭
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: POST
PATH: /api/board/like
TOKEN: O
PARAM: 
Long boardSeq 게시글 번호
```
* 결과 예시(성공)
```
{
    "resultCode" : "SUCCESS",
    "resultMsg" : "SUCCESS"
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

