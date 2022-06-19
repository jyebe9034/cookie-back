### 댓글 API

##### 댓글 조회
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: GET
METHOD NAME : selectComment
PATH: /api/comment/{boardSeq} 
PARAM: 없음 
```
* 결과 구조 (List)
```
[
    {
        "createDate": 생성일자,
        "writer": 작성자,
        "commentSeq": 댓글 시퀀스,
        "boardSeq": 게시판 시퀀스,
        "parentSeq": 부모댓글 시퀀스,
        "contents": 댓글 내용
    }
]
```
* 결과 예시
```
[
    {
        "createDate": [
            2022,
            5,
            8,
            0,
            0
        ],
        "writer": null,
        "commentSeq": 1,
        "boardSeq": 5,
        "parentSeq": null,
        "contents": "댓글 수정 테스트!!!!"
    },
    {
        "createDate": [
            2022,
            5,
            8,
            0,
            0
        ],
        "writer": null,
        "commentSeq": 5,
        "boardSeq": 5,
        "parentSeq": null,
        "contents": "댓글 수정 테스트!!!!"
    }
]
```

##### 댓글 등록
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: POST
METHOD NAME : insertComment
PATH: /api/comment 
PARAM: 
```
* 결과 구조
* 결과 예시

##### 댓글 수정
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: PUT
METHOD NAME : updateComment
PATH: /api/comment
PARAM: 
```
* 결과 구조
* 결과 예시

##### 댓글 삭제
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: DELETE
METHOD NAME : deleteComment
PATH: /api/comment/{commentSeq}
PARAM: 없음
```
* 결과 구조
* 결과 예시