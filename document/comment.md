### 댓글 API

##### 댓글 등록 
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: POST
METHOD NAME : insertComment
PATH: /api/comment 
PARAM: Map<String, Object>

boardSeq: 게시판 시퀀스
parentSeq: 부모 댓글 시퀀스(부모 댓글이 있는 경우에만)
contents: 댓글 내용
file: 이미지 파일 
```
* 결과 예시(성공)
```
{
    "code" : "SUCCESS",
    "message" : "SUCCESS"
}
```

##### 댓글 수정
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: PUT
METHOD NAME : updateComment
PATH: /api/comment
PARAM: Map<String, Object>

commentSeq: 댓글 시퀀스 
contents: 댓글 내용
file: 이미지 파일
```

##### 댓글 삭제
* 기본 정보 및 파라미터 설명
```
HTTP METHOD: DELETE
METHOD NAME : deleteComment
PATH: /api/comment/{commentSeq}
PARAM: 없음
```