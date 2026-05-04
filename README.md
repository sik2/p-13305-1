## 1차 요구사항 구현
- [ ] 유저가 루트 url로 접속시에 게시글 리스트 페이지(http://주소:포트/article/list)가 나온다.
- [ ] 리스트 페이지에서는 등록 버튼이 있고 버튼을 누르면 http://주소:포트/article/create 경로로 이동하고 등록 폼이 나온다.
- [ ] 게시글 등록을 하면 http://주소:포트/article/create로 POST 요청을 보내어 DB에 해당 내용을 저장한다.
- [ ] 게시글 등록이 되면 해당 게시글 리스트 페이지로 리다이렉트 된다. 페이지 URL 은 http://주소:포트/article/list 이다.
- [ ] 리스트 페이지에서 해당 게시글을 클릭하면 상세페이지로 이동한다. 해당 경로는 http://주소:포트/article/detail/{id} 가 된다.
- [ ] 게시글 상세 페이지에는 id에 맞는 게시글 데이터와 목록 버튼이 있다. 목록 버튼을 누르면 게시글 리스트 페이지로 이동하게 된다.

- (추가 기능이나 구현기능설명이 필요한 경우 서술)

## 미비사항 or 막힌 부분
- 게시글 등록 폼에서 입력한 데이터를 DB에 저장하는 부분이 조금 서투르다.

## UI/UX (화면 캡처본을 복사 붙여 넣기, url 주소 나오도록)
- 게시글 리스트 페이지
<img width="269" height="185" alt="스크린샷 2026-05-04 오후 5 43 26" src="https://github.com/user-attachments/assets/3c21f67e-08dd-46f6-a25e-b0104e5c350c" />
- 게시글 등록 폼 페이지
<img width="576" height="475" alt="스크린샷 2026-05-04 오후 5 42 58" src="https://github.com/user-attachments/assets/1f06576b-cd70-4085-a58a-473cf406f128" />
- 게시글 상세 페이지
<img width="327" height="285" alt="스크린샷 2026-05-04 오후 5 43 52" src="https://github.com/user-attachments/assets/4ab8387d-f42e-4011-8f43-9bb6076830a5" />

## MVC 패턴
- Model: `Article`, `ArticleRepository`, `ArticleService`가 데이터와 비즈니스 로직을 담당한다.
- View: `templates/article` 아래의 Thymeleaf 파일들이 화면을 담당한다.
- Controller: `ArticleController`가 요청을 받아 서비스를 호출하고 뷰 또는 리다이렉트를 반환한다.

## 스프링에서 의존성 주입(DI) 방법 3가지 방법
- 생성자 주입: 생성자로 의존성을 전달받는다. 필수 의존성을 명확히 표현할 수 있어 권장된다.
- 필드 주입: 필드에 `@Autowired`를 붙여 의존성을 주입받는다.
- Setter 주입: setter 메서드로 의존성을 주입받는다.

## JPA의 장점과 단점
- 장점: 반복적인 SQL 작성이 줄어들고 객체 중심으로 DB 작업을 처리할 수 있다.
- 장점: 엔티티 변경 감지, 연관관계 매핑, 페이징 등을 편하게 사용할 수 있다.
- 단점: JPA가 생성하는 SQL을 이해하지 못하면 성능 문제가 생길 수 있다.

## HTTP GET 요청과 POST 요청의 차이
- GET: 주로 데이터를 조회할 때 사용한다. 요청 정보가 URL에 노출될 수 있다.
- POST: 주로 데이터를 생성하거나 변경할 때 사용한다. 요청 데이터는 주로 body에 담긴다.
