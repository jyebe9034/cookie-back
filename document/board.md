### 게시판 API

##### 게시글 목록 조회 O
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
PATH: /api/board
PARAM: String title (*검색어가 없어도 title은 넣어주서야 합니다!)
```
* 결과 예시(성공)
```
{
    "data": {
        "commentList": [
            {
                "createDate": [
                    2022,
                    5,
                    8,
                    0,
                    0
                ],
                "writer": 32,
                "commentSeq": 3,
                "boardSeq": 17,
                "parentSeq": null,
                "contents": "댓글 등 테스트!!!!"
            },
            {
                "createDate": [
                    2022,
                    5,
                    8,
                    0,
                    0
                ],
                "writer": 32,
                "commentSeq": 4,
                "boardSeq": 17,
                "parentSeq": null,
                "contents": "댓글 등 테스트!!!!"
            }
        ],
        "board": {
            "boardSeq": 17,
            "thumbnailPath": "https://shared-comic.pstatic.net/thumb/webtoon/726214/thumbnail/thumbnail_IMAG06_e7362193-69c0-4f55-8b7c-c651811f10d7.jpg",
            "title": "정년이를 아세요?",
            "contents": "제 최애 웹툰 정년이를 소개합니다~~!",
            "writer": 20,
            "nickname": "소소",
            "createDate": "2022-05-15",
            "readCount": 0,
            "likeCount": 4,
            "commentCount": 4,
            "genre": "드라마",
            "hasLiked": true
        }
    },
    "resultMsg": "SUCCESS"
}
```
* 결과 예시(실패)
```
{
    "resultMsg" : "아직 글이 존재하지 않습니다."
}
```

##### 게시글 조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
PATH: /api/board/{boardSeq}
PARAM: Long boardSeq 게시글 번호 (경로)
```
* 결과 예시(성공)
```
{
    "data": {
        "commentList": [
            {
                "createDate": [
                    2022,
                    5,
                    8,
                    0,
                    0
                ],
                "writer": 32,
                "commentSeq": 3,
                "boardSeq": 17,
                "parentSeq": null,
                "contents": "댓글 등 테스트!!!!"
            },
            {
                "createDate": [
                    2022,
                    5,
                    8,
                    0,
                    0
                ],
                "writer": 32,
                "commentSeq": 4,
                "boardSeq": 17,
                "parentSeq": null,
                "contents": "댓글 등 테스트!!!!"
            }
        ],
        "board": {
            "boardSeq": 17,
            "thumbnailPath": "https://shared-comic.pstatic.net/thumb/webtoon/726214/thumbnail/thumbnail_IMAG06_e7362193-69c0-4f55-8b7c-c651811f10d7.jpg",
            "title": "정년이를 아세요?",
            "contents": "제 최애 웹툰 정년이를 소개합니다~~!",
            "writer": 20,
            "nickname": "소소",
            "createDate": "2022-05-15",
            "readCount": 0,
            "likeCount": 4,
            "commentCount": 4,
            "genre": "드라마",
            "hasLiked": true
        }
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
PATH: /api/board
TOKEN: O
PARAM: 
Long webtoonSeq 웹툰 번호
String title    제목
String contents 내용 
```
* 결과 예시(성공)
```
{
    "boardSeq": 42,
    "resultMsg": "SUCCESS"
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
PARAM: Long boardSeq 게시글 번호 (경로)
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
PARAM: Long boardSeq 게시글 번호 (경로)
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

