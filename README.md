## 1차 요구사항 구현
- [x] 유저가 루트 url로 접속시에 게시글 리스트 페이지(http://주소:포트/article/list)가 나온다.
- [x] 리스트 페이지에서는 등록 버튼이 있고 버튼을 누르면 http://주소:포트/article/create 경로로 이동하고 등록 폼이 나온다.
- [x] 게시글 등록을 하면 http://주소:포트/article/create로 POST 요청을 보내어 DB에 해당 내용을 저장한다.
- [x] 게시글 등록이 되면 해당 게시글 리스트 페이지로 리다이렉트 된다. 페이지 URL 은 http://주소:포트/article/list 이다.
- [x] 리스트 페이지에서 해당 게시글을 클릭하면 상세페이지로 이동한다. 해당 경로는 http://주소:포트/article/detail/{id} 가 된다.
- [x] 게시글 상세 페이지에는 id에 맞는 게시글 데이터와 목록 버튼이 있다. 목록 버튼을 누르면 게시글 리스트 페이지로 이동하게 된다.
- [x] 삭제 메서드 구현 및 삭제 후 게시글 List 페이지로 Redirect
- [x] 수정 메서드 구현 및 수정 후 게시글 상세 페이지로 Redirect
- (추가 기능이나 구현기능설명이 필요한 경우 서술)

## 미비사항 or 막힌 부분
- 기능 구현에 집중하고 ui 적인 부분을 준비하지 못했습니다.

## UI/UX (화면 캡처본을 복사 붙여 넣기, url 주소 나오도록)
- 게시글 리스트 페이지: 
![스크린샷 2026-05-04 오후 5.31.06.png](../../../../../var/folders/xy/88m1v9jj46ld51t6yykcgv6r0000gn/T/TemporaryItems/NSIRD_screencaptureui_sP5mGp/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202026-05-04%20%EC%98%A4%ED%9B%84%205.31.06.png)
- 게시글 등록 폼 페이지: ![스크린샷 2026-05-04 오후 5.31.42.png](../../../../../var/folders/xy/88m1v9jj46ld51t6yykcgv6r0000gn/T/TemporaryItems/NSIRD_screencaptureui_XX46nW/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202026-05-04%20%EC%98%A4%ED%9B%84%205.31.42.png)
- 게시글 상세 페이지: ![스크린샷 2026-05-04 오후 5.32.06.png](../../../../../var/folders/xy/88m1v9jj46ld51t6yykcgv6r0000gn/T/TemporaryItems/NSIRD_screencaptureui_BWLJ6c/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202026-05-04%20%EC%98%A4%ED%9B%84%205.32.06.png)

## MVC 패턴
- Model, View, Controller
- 화면에 보여지는 

## 스프링에서 의존성 주입(DI) 방법 3가지 방법
1. Bean으로 등록후 Autowired 를 통해서 등록한다
2. 클래스에 Component Annotation 을 추가하고 주입 받는 클래스에 선언후 Lombok을 사용하여 RequiredArgsConstructor Annotation을 추가하거나
생성자를 직접 만들어서 사용한다.
3. 선언한 메서드에 Setter 메서드를 만들어 Autowired 어노테이션을 붙여 사용한다

## JPA의 장점과 단점
- Jpa의 장점: 사용하는 DMBS 의 제한없이 JPA만으로 데이터베이스를 활용할 수 있다,
또한 데이터베이스 쿼리중심이 아닌 객체 중심의 개발이 가능하여 코드의 직관성을 향상 시킬 수 있다.
- Jpa의 단점: 학습의 러닝커브가 있다. Jpa 만의 메서드 규칙들을 학습하고 익히는 시간 + 데이터베이스를 활용할 수 있는 DBMS 를 
배우는 시간이 모두 있어야 깊이 이해하고 사용할 수 있을것같다

## HTTP GET 요청과 POST 요청의 차이
- GET요청: 서버에 있는 자원(데이터)을 조회할 시에 요청(게시글 목록, 상세, 검색 등)
- POST요청: 서버에 자원의 변화(생성, 수정, 삭제 등)를 처리해야할때 요청 