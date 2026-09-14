# 상품 기능 개발 기록

## 작업 원칙

- 상품 API 8개와 추가 피드백인 내 상품 목록 조회까지 총 9개를 구현한다.
- 리뷰, 로그인, 회원 기능은 수정하지 않는다. 기존 인증 기능과 연동한다.
- 사용자 요청에 따라 product/productimage는 src/test/java/com/example/test 아래에 유지한다.
- 구현 후 두 폴더를 src/main/java/com/example/test로 이동하고 실행·테스트를 확인한 뒤 develop 병합을 준비한다.
- 작은 단계별로 구현하고 설명한다. 사용자가 커밋·푸시를 요청하면 해당 변경만 성민 브랜치에 올린다.
- 커밋 제목에는 구현 내용을, 본문에는 상세 변경·미연결 항목·검증 한계를 기록한다.
- 마지막에 이 기록과 Git 이력을 바탕으로 전체 구조 및 단계별 변경을 설명한다.

## 완료한 단계와 푸시한 커밋

| 단계 | 커밋 | 내용 |
|---|---|---|
| 상품 기본 구조 | fa26e0a | Product ERD 매핑, 시간 자동 기록, Repository 및 findByUserId, Controller·Service·DTO 기본 틀 |
| 상품 이미지 기본 구조 | ab29ea4 | ProductImage ERD 매핑, 상품 연관관계, 상품별 이미지 조회, API 기본 틀 |
| 1. 등록 요청 DTO | 80ae973 | name·description·price·stock, 필수값 및 길이·음수 검증, validation 의존성 |
| 2. 상품 생성 메서드 | 76e498b | Product.create로 상품 정보와 등록자 ID를 담는 객체 생성 |
| 3. 등록 Service | bf8d556 | 요청 검증, 객체 생성, 트랜잭션 내 Repository 저장, 상품 ID 반환 |
| 4. 등록 응답 DTO | 59c9708 | ProductCreateResponse에 생성된 productId 전달 |

## 확정 조건과 제안 구분

- 상품명·가격·재고는 필수, 설명은 선택이다. 상품명 최대 길이는 ERD 기준 100이다.
- 가격과 재고는 현재 0 이상으로 구현했다.
- 등록 사진은 선택이며, 미등록 시 기본 이미지를 제공해야 한다.
- 사진을 별도로 전달하고 이미지가 없으면 조회 응답에서 기본 URL을 제공하는 방식은 제안 상태다.
- 내 상품 목록 조회는 인증 사용자 ID와 Product.user_id가 같은 상품을 조회한다.
- 내 상품 경로 GET /api/products/me 및 등록 응답 {"productId": 1}은 제안한 계약이며 팀 명세 확인이 필요하다.

## 미완료 및 다음 단계

### 상품 목록 조회 DTO 및 Service (85b32cb, 성민 브랜치 푸시 완료)

- 등록 Controller는 인증 연동 방식 확인을 기다리며, 인증 없는 상품 목록 조회 작업을 먼저 진행한다.
- ProductListResponse: 개별 상품의 productId, name, price, stock, createdAt을 전달한다. 상세 응답 계약은 제안 상태다.
- ProductService.getProducts: 전체 상품을 createdAt 및 productId 내림차순으로 조회하여 DTO 목록으로 변환한다. 상품이 없으면 빈 목록을 반환한다.
- Controller, 대표·기본 이미지 URL 및 페이지네이션은 미연결이다. 현재 전체 목록 조회이므로 데이터가 많아지기 전 페이지네이션 정책을 정해야 한다.
- 이번 변경도 컴파일·DB 실행은 미검증이며, 커밋 이력은 커밋 후 Git 로그에서 확인한다.

### 남은 작업

### 상품 상세 조회 DTO 및 Service (422429c, 성민 브랜치 푸시 완료)

- ProductDetailResponse: productId, name, description, price, stock, createdAt, updatedAt을 전달한다. 응답 필드는 제안 상태이며 팀 상세 명세 확인이 필요하다.
- ProductService.getProduct: 상품 ID의 필수값·양수 검증, 읽기 전용 트랜잭션에서 findById 조회 후 DTO로 변환한다.
- 상품이 없으면 ProductNotFoundException을 발생시킨다. HTTP 404 매핑은 아직 없으며, Controller와 상품 API 오류 처리 연결 시 구현해야 한다.
- 상품 이미지 및 기본 이미지 URL, 기존 JWT 인증 연결은 미완료 상태를 유지한다.
- 현재 src/test/java 위치 유지. 공백 검사 외 컴파일·DB 실행 검증은 미수행이다.

### 이후 작업 순서

### 상품 검색 DTO·Repository·Service (1171210, 성민 브랜치 푸시 완료)

- ProductSearchRequest.keyword는 필수이며 빈 문자열·공백 검색어를 거부한다. Service에서 앞뒤 공백을 제거한다.
- ProductRepository.findByNameContainingIgnoreCase로 상품명 부분 검색을 수행한다. 상품 설명은 현재 검색 대상이 아니다. 범위와 파라미터 이름은 팀 명세 확인이 필요하다.
- ProductService.searchProducts는 읽기 전용 트랜잭션으로 조회하고 createdAt, productId 내림차순으로 정렬한 ProductListResponse 목록을 반환한다. 결과가 없으면 빈 목록이다.
- 검색 Controller 및 검증 오류 HTTP 응답, 페이지네이션, 이미지 URL은 미연결이다. 기존 등록 JWT 연동도 대기 상태다.
- src/test/java 위치 유지. 공백 검사 수행, Java 환경 문제로 컴파일·DB 검색 검증 미수행.

### 남은 구현 순서

### 현재 작업: 내 상품 목록 조회 Service

- 피드백 항목인 내 상품 목록 조회의 Service 로직을 추가했다. 엔드포인트 제안은 GET /api/products/me이며 아직 연결되지 않았다.
- ProductRepository.findByUserId에 Sort 인자를 추가했다. Product.userId가 전달된 사용자 ID와 일치하는 상품만 조회한다.
- ProductService.getMyProducts는 사용자 ID 필수값·양수 검증 후 읽기 전용 트랜잭션에서 createdAt, productId 내림차순 조회 및 ProductListResponse 변환을 수행한다. 해당 사용자의 상품이 없으면 빈 목록을 반환한다.
- 현재 사용자 ID 필터링만 구현했다. Controller는 반드시 기존 JWT 인증에서 사용자 ID를 얻어 전달해야 하며 요청 파라미터의 임의 사용자 ID를 사용하면 안 된다. JWT 검증 및 사용자 존재 확인은 미구현이다.
- 페이지네이션, 대표·기본 이미지 URL, HTTP 검증 오류 응답은 미연결이다. 파일 위치는 src/test/java에 유지한다.
- 공백 검사 수행. 컴파일·DB 실행 및 사용자 간 조회 격리 검증은 Java 환경 문제로 미수행이다.

### 다음 구현

1. 등록 Controller: 요청 방식(JSON/multipart), 팀 공통 응답 형식을 확정하고 Service·응답 DTO 연결.
2. 인증 연동: 현재 체크아웃에는 JWT/인증 구현이 없다. 팀 인증 코드에서 사용자 ID를 얻는 방식을 확인해야 한다. 요청 본문의 사용자 ID를 신뢰하거나 임시 고정 사용자 ID를 사용하지 않는다.
3. 사진 업로드·저장 및 기본 이미지 응답: 저장 위치, 파일 규칙, 기본 이미지 URL과 이미지 API 전체 경로 확인 필요.
4. 상품 목록·상세 조회, 검색·내 상품 조회, 수정·삭제, 이미지 추가·삭제를 단계별로 구현.
5. 수정·삭제의 등록자 권한 확인 및 오류 응답 연결.
6. 소스 위치 이동, Java 21 환경에서 컴파일·테스트 및 DB 연동 검증, develop 대상 PR 준비.

## 검증 한계

- 지금까지 공백 검사를 수행했다. Java 실행 환경을 찾지 못해 컴파일 및 DB 저장 검증은 미수행이다.
- API 엔드포인트는 아직 연결되지 않았다. Service의 userId 검증은 필수값·양수 검사이며 JWT 검증이나 사용자 존재 확인이 아니다.
- src/test/java에 있는 현재 코드는 운영 실행·배포 코드에 포함되지 않는다.
