## 1차 요구사항 구현
- [x] 유저가 루트 url로 접속시에 게시글 리스트 페이지(http://주소:포트/article/list)가 나온다.
- [x] 리스트 페이지에서는 등록 버튼이 있고 버튼을 누르면 http://주소:포트/article/create 경로로 이동하고 등록 폼이 나온다.
- [x] 게시글 등록을 하면 http://주소:포트/article/create로 POST 요청을 보내어 DB에 해당 내용을 저장한다.
- [x] 게시글 등록이 되면 해당 게시글 리스트 페이지로 리다이렉트 된다. 페이지 URL 은 http://주소:포트/article/list 이다.
- [x] 리스트 페이지에서 해당 게시글을 클릭하면 상세페이지로 이동한다. 해당 경로는 http://주소:포트/article/detail/{id} 가 된다.
- [x] 게시글 상세 페이지에는 id에 맞는 게시글 데이터와 목록 버튼이 있다. 목록 버튼을 누르면 게시글 리스트 페이지로 이동하게 된다.

- 없는 id로 상세페이지 접근 시 `ResponseStatusException`으로 404 응답 처리
- `ArticleService`에서 게시글 생성 로직을 담당하여 Controller는 요청/응답만 처리하도록 역할 분리

## 미비사항 or 막힌 부분
- 상세페이지 이동하는 구현에서 id값을 어떻게 넘겨주는지 고민함

## UI/UX (화면 캡처본을 복사 붙여 넣기, url 주소 나오도록)
- 게시글 리스트 페이지
![img_1.png](img_1.png)
- 게시글 등록 폼 페이지
![img.png](img.png)
- 게시글 상세 페이지
![img_2.png](img_2.png)

## MVC 패턴
- **Model**: `Article` (엔티티), `ArticleRepository` (DB 접근), `ArticleService` (비즈니스 로직)
- **View**: Thymeleaf 템플릿 (`article_list.html`, `article_create.html`, `article_detail.html`)
- **Controller**: `ArticleController` — HTTP 요청을 받아 Service를 호출하고 View에 데이터를 전달
- 각 계층이 역할을 분리하여 유지보수가 용이한 구조

## 스프링에서 의존성 주입(DI) 방법 3가지 방법
- 필드 주입 (@Autowired를 필드에 직접 선언)
- 세터 주입 (@Autowired를 setter 메서드에 선언)
- 생성자 주입 (생성자를 통해 의존성 주입, Spring 권장 방식)

## JPA의 장점과 단점
**장점**
- SQL을 직접 작성하지 않아도 되어 생산성이 높음
- 객체 중심으로 개발할 수 있어 비즈니스 로직에 집중 가능
- DB 종류가 바뀌어도 코드 변경 최소화 (DB 독립성)

**단점**
- 복잡한 쿼리는 JPQL이나 네이티브 쿼리를 별도로 작성해야 함
- 잘못 사용하면 N+1 문제 등 성능 이슈가 발생할 수 있음
- 학습 곡선이 있어 초기 진입 비용이 있음

## HTTP GET 요청과 POST 요청의 차이
| | GET | POST |
|---|---|---|
| 용도 | 데이터 조회 | 데이터 생성/변경 |
| 데이터 위치 | URL 쿼리스트링 | 요청 Body |
| 보안 | URL에 노출됨 | Body에 담겨 노출 안됨 |
| 멱등성 | 여러 번 호출해도 결과 동일 | 호출할 때마다 결과가 달라질 수 있음 |

- 이 프로젝트에서 게시글 목록/상세 조회는 GET, 게시글 등록은 POST 요청을 사용
