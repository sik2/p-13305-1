## 1차 요구사항 구현
- [o] 유저가 루트 url로 접속시에 게시글 리스트 페이지(http://주소:포트/article/list)가 나온다.
- [o] 리스트 페이지에서는 등록 버튼이 있고 버튼을 누르면 http://주소:포트/article/create 경로로 이동하고 등록 폼이 나온다.
- [o] 게시글 등록을 하면 http://주소:포트/article/create로 POST 요청을 보내어 DB에 해당 내용을 저장한다.
- [o] 게시글 등록이 되면 해당 게시글 리스트 페이지로 리다이렉트 된다. 페이지 URL 은 http://주소:포트/article/list 이다.
- [o] 리스트 페이지에서 해당 게시글을 클릭하면 상세페이지로 이동한다. 해당 경로는 http://주소:포트/article/detail/{id} 가 된다.
- [o] 게시글 상세 페이지에는 id에 맞는 게시글 데이터와 목록 버튼이 있다. 목록 버튼을 누르면 게시글 리스트 페이지로 이동하게 된다.

- (추가 기능이나 구현기능설명이 필요한 경우 서술)

## 미비사항 or 막힌 부분
- 초기 MySQL Docker 연결 과정에서 오류 발생
- MySQL 8 버전 인증 문제 (allowPublicKeyRetrieval 설정 필요)
- 포트 충돌(8080) 문제 발생 및 해결

## UI/UX (화면 캡처본을 복사 붙여 넣기, url 주소 나오도록)
### 게시글 리스트 페이지
- URL: http://localhost:8080/article/list
- <img width="640" height="370" alt="1번" src="https://github.com/user-attachments/assets/0a61b462-785e-4af6-bae6-f3433098c332" />

### 게시글 등록 페이지
- URL: http://localhost:8080/article/create
- <img width="587" height="334" alt="2번" src="https://github.com/user-attachments/assets/e9e2ef20-d051-4a14-a8f9-8911992f8d8c" />

### 게시글 상세 페이지
- URL: http://localhost:8080/article/detail/{id}
- <img width="618" height="348" alt="4번" src="https://github.com/user-attachments/assets/05468424-91d6-464a-a54c-1794a7185500" />

## MVC 패턴
- **Model**
  - Article 엔티티 및 Repository, Service
  - 데이터 처리 및 비즈니스 로직 담당

- **View**
  - Thymeleaf 템플릿 (list.html, create.html, detail.html)
  - 사용자에게 보여지는 화면 담당

- **Controller**
  - ArticleController
  - 요청을 받아 Service와 View를 연결하는 역할

## 스프링에서 의존성 주입(DI) 방법 3가지 방법

1. **생성자 주입 (Constructor Injection)**
   - 가장 권장되는 방식
   - @RequiredArgsConstructor 사용

2. **필드 주입 (Field Injection)**
   - @Autowired 사용
   - 테스트 및 유지보수에 불리

3. **Setter 주입 (Setter Injection)**
   - setter 메서드를 통해 주입
   - 선택적 의존성에 사용

## JPA의 장점과 단점
### 장점
- SQL을 직접 작성하지 않아도 됨 (생산성 향상)
- 객체 지향적인 코드 작성 가능
- 데이터베이스 변경에 유연하게 대응 가능

### 단점
- 복잡한 쿼리는 작성이 어려움
- 성능 최적화가 필요할 수 있음
- 학습 비용이 존재

---


## HTTP GET 요청과 POST 요청의 차이

| 구분 | GET | POST |
|------|-----|------|
| 목적 | 데이터 조회 | 데이터 생성 |
| 데이터 위치 | URL 파라미터 | Request Body |
| 보안 | 낮음 | 상대적으로 높음 |
| 캐싱 | 가능 | 불가능 |
| 사용 예시 | 게시글 조회 | 게시글 작성 |
