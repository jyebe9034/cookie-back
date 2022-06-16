# cookie-back

### main 디렉토리 구조
```
root/src/main/java/com/example/cookie
├── CookieApplication.java
├── board
│   ├── controller
│   │   └── BoardController.java
│   ├── domain
│   │   ├── Board.java
│   │   ├── BoardDto.java
│   │   ├── BoardImage.java
│   │   ├── BoardRequestDto.java
│   │   ├── LikeRequestDto.java
│   │   └── Liked.java
│   ├── repository
│   │   ├── BoardRepository.java
│   │   ├── BoardRepositorySupport.java
│   │   ├── BoardRepositorySupportImpl.java
│   │   └── LikedRepository.java
│   └── service
│       └── BoardService.java
├── comment
│   ├── controller
│   │   └── CommentController.java
│   ├── domain
│   │   ├── Comment.java
│   │   ├── CommentFormData.java
│   │   └── CommentImage.java
│   ├── repository
│   │   ├── CommentImageRepository.java
│   │   └── CommentRepository.java
│   └── service
│       └── CommentService.java
├── common
│   ├── BaseController.java
│   ├── BaseDomain.java
│   ├── ProfileController.java
│   ├── Role.java
│   └── UploadController.java
├── config
│   ├── AmazonS3Config.java
│   ├── QueryDslConfig.java
│   ├── SecurityConfig.java
│   └── WebConfig.java
├── exception
│   ├── EmptyFileException.java
│   └── FileUploadFailedException.java
├── notice
│   ├── controller
│   │   └── NoticeController.java
│   ├── domain
│   │   └── Notice.java
│   ├── repository
│   │   └── NoticeRepository.java
│   └── service
│       └── NoticeService.java
├── security
│   ├── JwtAuthenticationFilter.java
│   ├── JwtTokenProvider.java
│   ├── JwtUserService.java
│   └── oauth
│       ├── KakaoOAuthService.java
│       ├── NaverOAuthService.java
│       └── domain
│           ├── KakaoProfile.java
│           ├── NaverProfile.java
│           └── OAuthToken.java
├── test
│   └── TestController.java
├── user
│   ├── controller
│   │   └── UserController.java
│   ├── domain
│   │   ├── User.java
│   │   └── UserDto.java
│   ├── repository
│   │   ├── UserRepository.java
│   │   ├── UserRepositorySupport.java
│   │   └── UserRepositorySupportImpl.java
│   └── service
│       └── UserService.java
├── util
│   ├── FileUtil.java
│   ├── S3UploadUtil.java
│   ├── message
│   │   ├── Message.java
│   │   └── MessageUtil.java
│   └── validator
│       ├── MapValidateMessageWrapper.java
│       ├── MapValidateResult.java
│       ├── MapValidateRule.java
│       └── MapValidator.java
└── webtoon
    ├── controller
    │   └── WebtoonController.java
    ├── domain
    │   ├── Webtoon.java
    │   └── WebtoonDTO.java
    ├── repository
    │   └── WebtoonRepository.java
    └── service
        └── WebtoonService.java
```
