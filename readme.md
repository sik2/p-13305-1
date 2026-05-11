## 1차 요구사항 구현
- [x] 유저가 루트 url로 접속시에 게시글 리스트 페이지(http://주소:포트/article/list)가 나온다.
- [x] 리스트 페이지에서는 등록 버튼이 있고 버튼을 누르면 http://주소:포트/article/create 경로로 이동하고 등록 폼이 나온다.
- [x] 게시글 등록을 하면 http://주소:포트/article/create로 POST 요청을 보내어 DB에 해당 내용을 저장한다.
- [x] 게시글 등록이 되면 해당 게시글 리스트 페이지로 리다이렉트 된다. 페이지 URL 은 http://주소:포트/article/list 이다.
- [x] 리스트 페이지에서 해당 게시글을 클릭하면 상세페이지로 이동한다. 해당 경로는 http://주소:포트/article/detail/{id} 가 된다.
- [x] 게시글 상세 페이지에는 id에 맞는 게시글 데이터와 목록 버튼이 있다. 목록 버튼을 누르면 게시글 리스트 페이지로 이동하게 된다.

- (추가 기능이나 구현기능설명이 필요한 경우 서술)

## 미비사항 or 막힌 부분
- 기능 구현에 집중하여, UI/UX 측면이 미흡합니다.

## UI/UX (화면 캡처본을 복사 붙여 넣기, url 주소 나오도록)
- 게시글 리스트 페이지
- 게시글 등록 폼 페이지
- 게시글 상세 페이지

## MVC 패턴
- MVC 패턴은 애플리케이션을 Model, View, Controller 세 가지 역할로 분리하는 설계 패턴이다.

- Model: 데이터와 비즈니스 로직을 담당한다. (Article 엔티티, ArticleService, ArticleRepository)
- View: 화면 출력을 담당한다. (Thymeleaf 템플릿 - list.html, create.html, detail.html)
- Controller: 요청을 받아 Model과 View를 연결하는 역할을 한다. (ArticleController)

요청 흐름:
브라우저 → Controller → Service → Repository → DB

## 스프링에서 의존성 주입(DI) 방법 3가지 방법
- 1. 필드 주입 (Field Injection)
     @Autowired
     private ArticleRepository articleRepository;
    - 코드가 간결하지만 테스트하기 어렵고, 순환참조 감지가 어렵다. 실무에서 권장하지 않는다.

2. 수정자 주입 (Setter Injection)
   @Autowired
   public void setArticleRepository(ArticleRepository articleRepository) {
   this.articleRepository = articleRepository;
   }
    - 선택적 의존성에 사용할 수 있으나 거의 사용하지 않는다.

3. 생성자 주입 (Constructor Injection)
   @RequiredArgsConstructor
   private final ArticleRepository articleRepository;
    - final 키워드로 불변성을 보장하고, 테스트가 용이하며
      순환참조를 컴파일 시점에 감지할 수 있어 가장 권장된다.

## JPA의 장점과 단점
- 장점:
1. SQL을 직접 작성하지 않아도 된다. JPA가 자동으로 SQL을 생성해준다.
2. 객체 중심으로 개발할 수 있어 생산성이 높아진다.
3. ddl-auto 설정으로 엔티티 클래스 변경 시 테이블 구조를 자동으로 반영한다.
4. 특정 DB에 종속되지 않아 DB 변경이 용이하다.

단점:
1. 학습 곡선이 높다. 잘못 사용하면 오히려 성능이 저하된다.
2. 복잡한 쿼리(통계, 대량 데이터 처리)는 JPA만으로 처리하기 어렵다.
3. 자동 생성된 SQL이 최적화되지 않을 수 있어 N+1 문제 등이 발생할 수 있다.

## HTTP GET 요청과 POST 요청의 차이
- GET 요청:
- 데이터를 조회할 때 사용한다.
- 데이터가 URL 쿼리 파라미터에 노출된다. (예: /search?keyword=spring)
- 브라우저 주소창 입력, 링크 클릭이 모두 GET 요청이다.
- 같은 요청을 여러 번 해도 결과가 같다. (멱등성)
- 예시: 게시글 목록 조회(/article/list), 상세 조회(/article/detail/{id})

POST 요청:
- 데이터를 저장/변경할 때 사용한다.
- 데이터가 HTTP Body에 담겨 전송되어 URL에 노출되지 않는다.
- HTML form의 method="post"로 전송한다.
- 같은 요청을 여러 번 하면 데이터가 중복 저장될 수 있다.
- 예시: 게시글 등록(/article/create POST)

이 테스트에서는 POST 요청 후 redirect(PRG 패턴)를 적용해
새로고침 시 게시글이 중복 저장되는 문제를 방지하였습니다.
