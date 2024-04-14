# SpringBoot JWT login, 게시판 기능 basic 코드 

## 개발 환경
- JAVA 11
- Framework : SpringBoot 2.7.9
- Build : Gradle 7.6.1
- Database : MySQL 8.0

## 라이브러리

- Spring Boot Web
- Lombok
- Spring Data Jpa, MySQL
- Spring Security,jjwt
- Spring Boot DevTools
- Thymeleaf, Validation, thymeleaf-extras-springsecurity5

## 기능 및 End Point

### 로그인 
- spring security + JWT를 사용한 로그인 

### 유저 기능

- 회원가입 페이지
    - GET /users/join
    - 회원가입 성공 시 성공 메세지 출력 후 로그인 화면으로 redirect
    - 로그인 한 유저는 회원가입 페이지에 접근할 수 없음
- 회원가입 기능
    - POST /users/join
    - 아이디, 닉네임이 중복되거나 비밀번호, 비밀번호 확인이 일치하지 않으면 회원가입 불가
    - 비밀번호는 암호화해서 저장
    - 회원가입 시 신규 유저의 등급은 BRONZE로 설정
- 로그인 페이지
    - GET /users/login
    - 아이디(loginId), 비밀번호로 로그인
    - 로그인 성공시 성공 메세지 출력 후 이전 페이지로 redirect
        - 만약 이전 페이지가 없거나 회원가입 페이지라면 홈으로 redirect
    - 로그인 한 유저는 로그인 페이지에 접근할 수 없음
- 로그인 기능
    - POST /users/login
    - Spring Security에서 로그인 진행
- 마이 페이지
    - GET /users/myPage/{category}
    - 로그인하지 않은 유저는 접근할 수 없음
    - 로그인 한 유저의 정보 확인 가능
    - 회원 정보(비밀번호, 닉네임) 수정 가능
    - 회원 탈퇴 가능
        - 탈퇴 시 작성한 글, 좋아요, 댓글 모두 삭제
    - category에 따라 내가 작성한 글(board), 좋아요 누른 글(like), 댓글 추가한 글(comment) 리스트 확인 가능

### 게시판 기능

- 게시판 종류
    - 가입인사(GREETING), 자유게시판(FREE)
- 글 리스트 페이지
    - GET /boards/{category}
    - 해당 카테고리의 글 리스트 출력
    - 글 작성 버튼 클릭 시 해당 카테고리에 글을 작성할 수 있는 글 작성 페이지로 이동
    - 글 클릭 시 해당 글 조회 페이지로 이동
    - 한 페이지에 10개의 글 출력 (공지 제외)
    - 제목, 작성자로 검색 가능
    - 최신순, 좋아요순, 댓글순으로 정렬 가능 (내림차순)
- 글 작성 페이지
    - GET /boards/{category}/write
    - 로그인 한 유저만 접근 가능
    - 제목, 내용 작성 가능 + 이미지 업로드 가능 (최대 10MB)
    - 글 작성 성공 시 작성된 글 조회 페이지로 redirect
    - ADMIN 등급의 유저만 공지사항 작성 가능
        - ADMIN 등급이 작성한 글은 해당 게시판의 공지 사항으로 등록됨
- 글 작성 기능
    - POST /boards/{category}
    - 입력된 제목(title), 내용(body)로 글 저장
    - 이미지가 입력되었으면 이미지도 저장
    - 등록 시 작성일, 작성 유저도 같이 저장
    - 로그인 한 유저만 작성 가능
- 글 조회 페이지
    - GET /boards/{category}/{boardId}
    - boardId에 해당하는 글 내용을 화면에 출력
    - boardId에 해당하는 글의 카테고리가 category와 일치하지 않으면 에러 발생
    - 해당 글의 작성자라면 수정, 삭제 버튼 출력
    - ADMIN 등급의 유저라면 삭제 버튼만 출력
    - 좌측 상단에 해당 글이 받은 좋아요 개수 출력
    - 로그인 한 유저는 하트 버튼 클릭시 좋아요 추가, 두 번 누르면 좋아요 취소
    - 로그인 한 유저는 댓글 작성 가능, 본인이 작성한 댓글 수정, 삭제 가능
    - 이미지가 있으면 이미지 출력 + 다운로드 가능
- 글 수정 기능
    - POST /boards/{category}/{boardId}/modify
    - 글 조회 페이지에서 수정 버튼 클릭 시 수정 가능
    - 제목, 내용 수정 가능 + 이미지 수정 가능
    - 글의 작성자만 수정 가능
    - 글 수정 성공 시 메세지 출력 후 해당 글로 redirect
- 글 삭제 기능
    - GET /boards/{category}/{boardId}/delete
    - 글 조회 페이지에서 삭제 버튼 클릭 시 재확인 후 삭제
    - 글 작성자와 ADMIN만 삭제 가능
    - 글 삭제 성공 시 메세지 출력 후 리스트로 redirect
    - 가입인사는 삭제할 수 없음
    - 글 삭제 시, 해당 글에 달린 좋아요, 댓글 모두 삭제
    - 좋아요가 삭제되어도 GOLD 등급의 유저의 등급은 하락하지 않음
- 댓글 기능
    - 댓글 작성: POST /comments/{boardId}
    - 댓글 수정: POST /comments/{commentId}/edit
    - 댓글 삭제: GET /comments/{commmentId}/delete
    - 로그인 한 유저만 댓글 작성 가능
    - 본인이 작성한 댓글 수정, 삭제 가능
    - 글 조회 페이지 하단에서 해당 글에 댓글 추가 가능
- 검색 기능
    - 글 제목, 작성자 닉네임으로 검색 가능
    - 최신순(default), 좋아요, 조회수 순으로 (내림차순) 정렬 가능
- 페이징 기능
    - 한 페이지에 글 10개씩 출력
    - 버튼을 통해 페이지 이동 가능

### 파일 기능

- 파일 업로드 기능
    - 게시판 글 작성 시 이미지 파일을 업로드 할 수 있음
    - 하나의 글에 이미지 한 개 (최대 10MB) 업로드 가능
    - 업로드 한 이미지는 로컬이 아닌 AWS S3 Bucket에 업로드 됨
- 파일 다운로드 기능
    - GET /boards/images/download/{boardId}
    - 게시판 글 조회 페이지에서 글에 추가된 이미지를 다운로드 할 수 있음
    - 데이터는 ResponseEntity<UrlResource> 타입으로 return
- 이미지 파일 미리보기 기능
    - GET /boards/images/{filename}
    - 게시판 글 조회 페이지에서 글에 추가된 이미지를 미리보기 할 수 있음
    - filename에 해당하는 이미지를 S3 Bucket에서 찾고, img 태그를 사용해 미리보기 구현


### 관리자 기능

- 관리자가 게시판에 글을 작성하면 그 글은 공지글로 등록됨
    - 공지글은 게시판 상단에 출력
- 관리자는 모든 글, 댓글 삭제 가능
- 관리자 페이지
    - GET /users/admin
    - 관리자 페이지에서는 모든 유저에 대한 정보를 확인할 수 있음
    - 닉네임을 통해 유저를 검색할 수 있음
    - 유저 클릭 시 해당 유저의 등급이 변경됨
      - user <-> admin


## 개선점
- refresh jwt 기능 추가 
- Oauth 구글, 카카오 로그인 추가
- 로그인 보안 처리 


### 참고 사이트 
https://chb2005.tistory.com/173

https://chb2005.tistory.com/194
- 수정내역
  - 로그인 방식 session 방식에서 jwt 로그인 방식으로 변경 
  - 게시글 수정 방식을 기존에 Detail 화면에서 게시글 기본 화면, 수정 화면 동시에 쓰는 부분을 화면, API 수정
  - html 코드 수정 
  