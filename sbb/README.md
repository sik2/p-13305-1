## 1차 요구사항 구현
- [x] 유저가 루트 url로 접속시에 게시글 리스트 페이지(http://주소:포트/article/list)가 나온다.
- [x] 리스트 페이지에서는 등록 버튼이 있고 버튼을 누르면 http://주소:포트/article/create 경로로 이동하고 등록 폼이 나온다.
- [x] 게시글 등록을 하면 http://주소:포트/article/create로 POST 요청을 보내어 DB에 해당 내용을 저장한다.
- [x] 게시글 등록이 되면 해당 게시글 리스트 페이지로 리다이렉트 된다. 페이지 URL 은 http://주소:포트/article/list 이다.
- [x] 리스트 페이지에서 해당 게시글을 클릭하면 상세페이지로 이동한다. 해당 경로는 http://주소:포트/article/detail/{id} 가 된다.
- [x] 게시글 상세 페이지에는 id에 맞는 게시글 데이터와 목록 버튼이 있다. 목록 버튼을 누르면 게시글 리스트 페이지로 이동하게 된다.

- ### 추가 기능이나 구현기능설명이 필요한 경우 서술

html의 경우 AI에 도움을 받아 모던적인 디자인으로 꾸몄다.
## 미비사항 or 막힌 부분
- IntelliJ에서 Gradle 프로젝트가 바로 인식되지 않아 코끼리(Gradle) 아이콘이 보이지 않는 문제가 있었다.
    - 원인은 `build.gradle.kts`가 있는 실제 Spring Boot 프로젝트 폴더가 `p-13305-1/sbb`였기 때문이다.
    - `sbb` 폴더를 기준으로 프로젝트를 열거나 `build.gradle.kts`를 Gradle 프로젝트로 연결해서 해결했다.

- Git 변경 파일이 IntelliJ에서 README만 보이는 문제가 있었다.
    - 원인은 Git 저장소 루트가 `p-13305-1`이고, 실제 작업 파일들은 그 하위 폴더인 `sbb` 안에 있었기 때문이다.
    - `sbb` 폴더의 파일들이 untracked 상태였기 때문에 IntelliJ에서 일반 변경 파일처럼 바로 보이지 않았다.

- `http://localhost:8080/article/list` 접속 시 `ERR_CONNECTION_REFUSED`가 발생했다.
    - 원인은 Spring Boot 서버가 정상적으로 실행되지 않았거나 8080 포트가 이미 다른 Java 프로세스에 의해 사용 중이었기 때문이다.
    - 실행 중인 서버와 포트 상태를 확인한 뒤 다시 실행해서 해결했다.

- Spring Boot 실행 중 JPA EntityManagerFactory 초기화 오류가 발생했다.
    - 원인은 `application.yaml`의 MySQL 접속 정보와 Docker MySQL 설정이 맞지 않았기 때문이다.
    - Docker MySQL의 root 비밀번호와 실제 존재하는 DB 이름을 확인하고, `application.yaml`의 `url`, `username`, `password`를 맞춰 해결했다.

- MySQL을 Docker로 실행했지만 프로젝트 폴더에 `board.db` 파일을 만들어야 하는지 혼동이 있었다.
    - 현재 프로젝트는 파일 DB가 아니라 MySQL을 사용하므로, 프로젝트 폴더에 DB 파일을 만드는 방식이 아니었다.
    - Docker MySQL 서버 안에 데이터베이스를 생성하고 Spring Boot에서 해당 DB로 접속해야 한다는 점을 확인했다.

## UI/UX (화면 캡처본을 복사 붙여 넣기, url 주소 나오도록)
- 게시글 리스트 페이지
- ![img.png](src/main/resources/static/images/게시글_리스트.png)
- 게시글 등록 폼 페이지
- ![img.png](src/main/resources/static/images/게시글_등록.png)
- 게시글 상세 페이지
![img.png](src/main/resources/static/images/게시글_상세.png)
## MVC 패턴
MVC 패턴은 사용자의 요청을 처리하는 구조를 역할별로 분리한 설계 패턴이다.
애플리케이션을 Model, View, Controller로 나누어 각각의 책임을 분리한다.

**Model**은 데이터와 비즈니스 로직을 담당하는 영역이다.
주로 Entity, DTO, Service 계층의 처리 결과와 같은 데이터가 포함된다.

**view**는 사용자에게 보여지는 화면을 담당하는 영역이다.
HTML, CSS, JavaScript와 같은 화면 요소를 사용하며, 
스프링에서는 Thymeleaf 같은 템플릿 엔진을 함께 사용하기도 한다.

**Controller**는 사용자의 요청을 받아 Model과 View를 연결하는 역할을 담당한다.

예를 들어 /article/list 요청이 들어오면, Controller는 게시글 목록을 조회한 뒤 데이터를 Model에 담고,
article/list.html View로 전달하여 화면에 출력하도록 처리한다.

**Controller**
- ArticleController
- /article/list, /article/create, /article/detail/{id} 요청 처리

**Model**
- Article
- ArticleRepository
- 게시글 데이터 구조와 DB 접근 담당

**View**
- list.html
- create.html
- detail.html
- 사용자에게 보여지는 화면 담당
## 스프링에서 의존성 주입(DI) 방법 3가지 방법
의존성 주입(DI)은 객체가 필요한 의존 객체를 직접 생성하지 않고, 스프링 컨테이너가 대신 주입해주는 방식이다.

1. 생성자 주입
- 생성자를 통해 의존 객체를 주입받는 방식이다.
- 필수 의존성을 명확하게 표현할 수 있고 `final`을 사용할 수 있어 가장 권장된다.
- 이번 프로젝트에서는 `ArticleController`가 `ArticleRepository`를 생성자 주입으로 사용했다.

2. 필드 주입
- 필드에 `@Autowired`를 붙여 의존 객체를 바로 주입받는 방식이다.
- 코드가 짧지만 테스트가 어렵고 의존 관계가 잘 드러나지 않아 권장되지 않는다.

3. 세터 주입
- setter 메서드를 통해 의존 객체를 주입받는 방식이다.
- 선택적으로 필요한 의존성을 주입할 때 사용할 수 있다.
## JPA의 장점과 단점

JPA는 자바 객체와 데이터베이스 테이블을 매핑해주는 ORM 기술이다. SQL을 직접 많이 작성하지 않아도 객체 중심으로 데이터를 저장하고 조회할 수 있게 도와준다.

### 장점

- 반복적인 SQL 작성이 줄어든다.
- 자바 객체 중심으로 DB 데이터를 다룰 수 있다.
- `save`, `findAll`, `findById` 같은 메서드를 통해 기본 CRUD를 쉽게 구현할 수 있다.
- DB 테이블과 객체의 관계를 엔티티로 관리할 수 있다.
- 특정 DB에 종속되는 코드를 줄일 수 있다.

### 단점

- 내부에서 어떤 SQL이 실행되는지 모르면 성능 문제가 생길 수 있다.
- 복잡한 쿼리는 JPA만으로 작성하기 어려울 수 있다.
- 엔티티 관계 설정을 잘못하면 예상보다 많은 쿼리가 실행될 수 있다.
- 처음 학습할 때 개념이 어렵다.
## HTTP GET 요청과 POST 요청의 차이

GET과 POST는 클라이언트가 서버에 요청을 보낼 때 사용하는 HTTP 메서드이다.

### GET 요청

GET은 서버에서 데이터를 조회할 때 주로 사용한다.

- 데이터를 조회할 때 사용한다.
- 요청 데이터가 URL에 노출된다.
- 브라우저 주소창에 직접 입력해서 요청할 수 있다.
- 같은 요청을 여러 번 보내도 서버 데이터가 변경되지 않는 것이 일반적이다.

예시:

```text
GET /article/list
GET /article/detail/1
