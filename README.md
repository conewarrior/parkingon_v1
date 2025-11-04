# 파킹온(ParkingOn) 시스템

Spring Boot + Thymeleaf 기반의 아파트 주차 관리 시스템입니다.

## 기술 스택

- **Backend**: Spring Boot 3.2.0, Spring Security, Spring Data JPA
- **Frontend**: Thymeleaf, HTML5, CSS3, JavaScript
- **Database**: H2 (개발용), MySQL (운영용)
- **Build Tool**: Gradle
- **Java Version**: 17

## 프로젝트 구조

```
parkingon_1104/
├── src/
│   ├── main/
│   │   ├── java/com/parkingon/
│   │   │   ├── ParkingOnApplication.java
│   │   │   ├── config/
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   └── DataInitializer.java
│   │   │   ├── controller/
│   │   │   │   └── HomeController.java
│   │   │   ├── entity/
│   │   │   │   ├── User.java
│   │   │   │   ├── Apartment.java
│   │   │   │   ├── Car.java
│   │   │   │   ├── Voc.java
│   │   │   │   └── Notify.java
│   │   │   └── repository/
│   │   │       ├── UserRepository.java
│   │   │       ├── ApartmentRepository.java
│   │   │       ├── CarRepository.java
│   │   │       ├── VocRepository.java
│   │   │       └── NotifyRepository.java
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── css/
│   │       │   │   ├── app.css
│   │       │   │   ├── login.css
│   │       │   │   ├── dashboard.css
│   │       │   │   ├── dashboard-worker.css
│   │       │   │   └── popup.css
│   │       │   ├── js/
│   │       │   │   ├── login.js
│   │       │   │   ├── dashboard.js
│   │       │   │   ├── dashboard-worker.js
│   │       │   │   └── notify-popup.js
│   │       │   └── images/
│   │       ├── templates/
│   │       │   ├── login.html
│   │       │   ├── dashboard.html
│   │       │   ├── dashboard-worker.html
│   │       │   └── fragments/
│   │       │       ├── header.html
│   │       │       ├── footer.html
│   │       │       └── notify-popup.html
│   │       └── application.properties
│   └── test/
└── build.gradle
```

## 주요 기능

### 구현된 화면 (1-14)

#### 기본 화면
1. **로그인 화면** (`/login`)
   - 사용자 인증, 비밀번호 표시/숨김, 폼 검증

2. **공지사항 팝업**
   - 팝업 형태의 공지사항, 이전/다음 네비게이션, 오늘 하루 보지 않기

3. **대시보드 - 멀티스크린** (`/`)
   - Chart.js 통계 차트, 단지 현황, VOC 통계, 입출차 현황

4. **대시보드 - 근무자용** (`/dashboard-worker`)
   - 3단 레이아웃, 실시간 모니터링, 차단기 제어, 상담 관리

#### APT 관리
5. **아파트 단지 관리** (`/apt/manage`)
   - 아파트 정보 관리, 운영 시간 설정, 블랙/화이트리스트 정책

6. **입/출구 관제기** (`/apt/lpr-device`)
   - LPR 장비 등록, RTSP 스트림 설정, VoIP 연동

7. **차량관리** (`/apt/car-manage`)
   - 블랙/화이트/정기방문 차량 관리, 유효기간 설정

#### VOC 관리
8. **VOC 이력** (`/voc/list`)
   - 고객 상담 이력 조회, 기간별/차량번호별 검색

9. **입출차 이력** (`/voc/inout-car`)
   - 차량 입출차 기록 조회, 차량 이미지 보기

10. **차단기 수동제어 이력** (`/voc/control-history`)
    - 수동 제어 이력 조회, 제어 사유 확인

#### 시스템 관리
11. **사용자 관리** (`/system/user-manage`)
    - 계정 생성/수정/삭제, 권한 설정, 비밀번호 암호화

12. **공통코드 관리** (`/system/code-manage`)
    - 시스템 공통코드 관리, 코드 그룹별 관리

13. **환경설정** (`/system/config`)
    - 시스템 전역 설정, 로그아웃 시간, 세션 타임아웃 등

14. **공지사항 관리** (`/system/notify-manage`)
    - 공지사항 작성/수정/삭제, 팝업/알림 형식 선택

## 실행 방법

### 1. Gradle Wrapper 생성 (처음 한 번만)

```bash
# Gradle이 설치되어 있는 경우
gradle wrapper --gradle-version 8.5
```

### 2. 빌드

```bash
./gradlew clean build
```

### 3. 실행

```bash
./gradlew bootRun
```

또는

```bash
java -jar build/libs/parkingon-1.0.0.jar
```

### 4. 접속

브라우저에서 http://localhost:8080 접속

## 기본 계정

- **아이디**: admin
- **비밀번호**: admin

## 데이터베이스

### H2 Console (개발용)

- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:parkingon`
- Username: `sa`
- Password: (빈 값)

### 샘플 데이터

애플리케이션 시작 시 다음 데이터가 자동으로 생성됩니다:
- 관리자 계정 (admin/admin)
- 아파트 3개 (삼성1단지, 삼성2단지, 정문인구 방문)
- 공지사항 2개

## 환경 설정

`src/main/resources/application.properties` 파일에서 설정을 변경할 수 있습니다.

### 주요 설정 항목

- `server.port`: 서버 포트 (기본값: 8080)
- `spring.datasource.url`: 데이터베이스 URL
- `spring.jpa.hibernate.ddl-auto`: DDL 자동 생성 설정

## 개발 상태

### 완료된 작업

#### 프론트엔드
- ✅ 전체 화면 14개 HTML 구현
- ✅ 공통 디자인 시스템 (CSS Variables)
- ✅ Header/Footer Fragments
- ✅ 반응형 레이아웃
- ✅ JavaScript 인터랙션
- ✅ Chart.js 통계 차트

#### 백엔드
- ✅ Spring Boot 3.2.0 설정
- ✅ Spring Security (로그인/로그아웃)
- ✅ Entity 10개 (User, Apartment, Car, Voc, Notify, LprDevice, InOutHistory, ControlHistory, CommonCode, SystemConfig)
- ✅ Controller 4개 (Home, Apt, Voc, System)
- ✅ Repository Layer
- ✅ 데이터 초기화

### 예정된 작업

- ⏳ Service Layer 구현
- ⏳ REST API 구현 (AJAX 연동)
- ⏳ 실시간 CCTV 연동 (WebSocket)
- ⏳ 차단기 제어 API
- ⏳ 파일 업로드 (공지사항 이미지)
- ⏳ Excel 다운로드 기능
- ⏳ 통합 테스트
- ⏳ 배포 설정

## 라이선스

© 두개비세상 Corp. All rights reserved.
