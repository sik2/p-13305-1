## 1차 요구사항 구현
- [x] 유저가 루트 url로 접속시에 게시글 리스트 페이지(http://주소:포트/article/list)가 나온다.
- [x] 리스트 페이지에서는 등록 버튼이 있고 버튼을 누르면 http://주소:포트/article/create 경로로 이동하고 등록 폼이 나온다.
- [x] 게시글 등록을 하면 http://주소:포트/article/create로 POST 요청을 보내어 DB에 해당 내용을 저장한다.
- [x] 게시글 등록이 되면 해당 게시글 리스트 페이지로 리다이렉트 된다. 페이지 URL 은 http://주소:포트/article/list 이다.
- [x] 리스트 페이지에서 해당 게시글을 클릭하면 상세페이지로 이동한다. 해당 경로는 http://주소:포트/article/detail/{id} 가 된다.
- [x] 게시글 상세 페이지에는 id에 맞는 게시글 데이터와 목록 버튼이 있다. 목록 버튼을 누르면 게시글 리스트 페이지로 이동하게 된다.

- (추가 기능이나 구현기능설명이 필요한 경우 서술)

## 미비사항 or 막힌 부분
- GET 요청과 POST 요청의 역할 차이 및 @GetMapping, @PostMapping 사용 시점이 처음에는 헷갈렸습니다.
  특히 버튼 클릭만으로는 GET 요청이 발생한다는 점과, 데이터 저장은 form submit을 통한 POST 요청으로 처리해야 한다는 부분을 학습했습니다.
- Thymeleaf에서 th:href, th:each, th:text 등의 문법 사용 시 경로 매핑과 Model 데이터 전달 구조를 이해하는 과정에서 시행착오가 있었습니다.
## UI/UX (화면 캡처본을 복사 붙여 넣기, url 주소 나오도록)
- 게시글 리스트 페이지
- <img src="src/main/resources/images/게시글 리스트 페이지.png" width="369" alt="게시글 리스트 페이지">
- 게시글 등록 폼 페이지
- <img src="src/main/resources/images/게시글 등록 폼 페이지.png" width="343" alt="게시글 등록 폼 페이지">
- 게시글 상세 페이지
- <img src="src/main/resources/images/게시글 상세 페이지.png" width="357" alt="게시글 상세 페이지">

## MVC 패턴
프로젝트는 Spring Boot의 MVC(Model-View-Controller) 패턴을 기반으로 구현하였습니다.  
역할을 분리하여 유지보수성과 코드 가독성을 높이고자 하였습니다.

| 구성 요소 | 역할 | 프로젝트 예시                                                     |
|---|---|-------------------------------------------------------------|
| Model | 데이터 처리 및 비즈니스 로직 담당 | Article, ArticleService, ArticleRepository                  |
| View | 사용자에게 보여지는 화면 담당 | article_list.html, article_detail.html, article_create.html |
| Controller | 사용자 요청 처리 및 흐름 제어 | ArticleController, MainController                           |

### 구조 설명

- Controller는 사용자의 요청(URL)을 받아 적절한 로직을 실행하고 화면(View)을 반환하도록 구성하였습니다.
- Service 계층에서는 게시글 저장, 조회 등의 비즈니스 로직을 처리하였습니다.
- Repository는 JPA를 활용하여 데이터베이스와의 연결 및 CRUD 작업을 담당하도록 구현하였습니다.
- View는 Thymeleaf를 사용하여 서버에서 전달한 데이터를 화면에 출력하도록 구성하였습니다.

### 동작 흐름

사용자 요청 → Controller → Service / Repository → DB → View 반환

## 스프링에서 의존성 주입(DI) 방법 3가지 방법

Spring에서는 객체를 직접 생성하지 않고 Spring 컨테이너가 관리하도록 하며,  
객체 간 필요한 의존 관계를 자동으로 연결해주는 DI(Dependency Injection, 의존성 주입) 방식을 사용합니다.

프로젝트에서는 Controller → Service → Repository 구조로 객체들이 서로 의존하도록 구성하였으며,  
Spring의 DI를 활용하여 객체 생성 및 관리를 효율적으로 처리하였습니다.

### 의존성 주입 방식

| 방식 | 설명 | 특징 |
|---|---|---|
| 생성자 주입 | 생성자를 통해 객체를 주입받는 방식 | 불변성 보장 및 테스트가 용이하여 가장 권장되는 방식 |
| 필드 주입 | `@Autowired`를 필드에 직접 사용하는 방식 | 코드가 간단하지만 테스트와 유지보수 측면에서 비권장 |
| Setter 주입 | setter 메서드를 통해 객체를 주입받는 방식 | 선택적 의존성 처리에 사용 가능 |

### 프로젝트 적용 방식

프로젝트에서는 `@RequiredArgsConstructor`를 활용한 생성자 주입 방식을 사용하였습니다.

## JPA의 장점과 단점

### JPA의 장점

| 장점 | 설명 |
|---|---|
| 생산성 향상 | 기본적인 CRUD SQL을 직접 작성하지 않아도 되어 개발 속도를 높일 수 있습니다. |
| 객체 중심 개발 | 테이블이 아닌 객체(Entity)를 중심으로 개발할 수 있어 코드의 가독성과 유지보수성이 좋아집니다. |
| SQL 자동 생성 | JPA가 상황에 맞는 SQL을 자동 생성해주므로 반복적인 SQL 작성이 줄어듭니다. |
| 데이터베이스 독립성 | 특정 DBMS에 종속되지 않고 다양한 데이터베이스 환경에서 비교적 쉽게 변경 가능합니다. |
| 유지보수 용이 | 테이블 구조 변경 시 SQL을 일일이 수정하는 작업을 줄일 수 있습니다. |

### JPA의 단점

| 단점 | 설명 |
|---|---|
| 학습 난이도 | 영속성 컨텍스트, 지연 로딩 등 개념이 많아 처음 학습 시 어려움이 있습니다. |
| 복잡한 쿼리 한계 | 매우 복잡한 SQL은 JPA만으로 처리하기 어려워 JPQL이나 Native Query가 필요할 수 있습니다. |
| 성능 문제 가능성 | 잘못 사용할 경우 N+1 문제 등 성능 이슈가 발생할 수 있습니다. |
| SQL 파악 어려움 | 자동 생성되는 SQL을 직접 제어하지 않기 때문에 실행 쿼리 파악이 어려울 수 있습니다. |

## HTTP GET 요청과 POST 요청의 차이
웹에서는 클라이언트와 서버가 HTTP 요청(Request)을 통해 데이터를 주고받습니다.  
그 중 가장 많이 사용되는 방식이 GET 요청과 POST 요청입니다.

프로젝트에서는 게시글 조회 기능에는 GET 요청을 사용하였고,  
게시글 작성 및 저장 기능에는 POST 요청을 사용하여 구현하였습니다.

| 항목 | GET | POST |
|---|---|---|
| 목적 | 데이터 조회 | 데이터 저장 및 처리 |
| 데이터 전달 방식 | URL(Query String) | Request Body |
| URL 노출 여부 | O | X |
| 보안성 | 상대적으로 낮음 | 상대적으로 높음 |
| 데이터 길이 제한 | URL 길이 제한 존재 | 비교적 제한이 적음 |
| 북마크 가능 여부 | O | X |
| 사용 예시 | 게시글 조회 | 게시글 작성 |
| 프로젝트 적용 예시 | `@GetMapping("/article/list")` | `@PostMapping("/article/create")` |
