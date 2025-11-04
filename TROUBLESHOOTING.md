# 문제 해결 가이드

## 현재 해결된 문제

### 1. ERR_INCOMPLETE_CHUNKED_ENCODING 에러
**원인**: 정적 리소스 설정 누락
**해결**: application.properties에 정적 리소스 설정 추가

### 2. favicon.ico 404 에러
**원인**: 브라우저가 자동으로 favicon 요청
**해결**: 모든 HTML에 `<link rel="icon" href="data:,">` 추가

### 3. logo.png 404 에러
**원인**: 어딘가에서 logo.png 참조 (브라우저 캐시 가능성)
**해결**: ResourceController로 동적 이미지 제공

### 4. /main 엔드포인트 404 에러
**원인**: /main 경로 핸들러 없음
**해결**: HomeController에 /main → / 리다이렉트 추가

## 애플리케이션 실행 방법

```bash
# 1. Gradle Wrapper 생성 (처음 한 번만)
gradle wrapper --gradle-version 8.5

# 2. 애플리케이션 실행
./gradlew bootRun

# 3. 브라우저에서 접속
http://localhost:8080

# 4. 로그인
- ID: admin
- PW: admin
```

## 브라우저 캐시 문제 해결

에러가 계속 발생하면 브라우저 캐시를 완전히 삭제하세요:

### Chrome
1. F12 → Network 탭
2. "Disable cache" 체크
3. 페이지 새로고침 (Ctrl+Shift+R 또는 Cmd+Shift+R)

또는

1. 설정 → 개인정보 및 보안 → 인터넷 사용 기록 삭제
2. "캐시된 이미지 및 파일" 선택
3. 삭제

### 하드 리프레시
- Windows: `Ctrl + Shift + R` 또는 `Ctrl + F5`
- Mac: `Cmd + Shift + R`

## 현재 제공되는 리소스

### 엔드포인트
- `/` - 대시보드
- `/main` - 대시보드로 리다이렉트
- `/login` - 로그인 페이지
- `/apt/*` - 아파트 관리
- `/voc/*` - VOC 관리
- `/system/*` - 시스템 관리

### 이미지 (동적 생성)
- `/images/logo.png` - 투명 1x1 PNG
- `/images/family-illustration.png` - 투명 1x1 PNG
- `/favicon.ico` - 빈 favicon

### 정적 리소스
- `/css/*` - CSS 파일들
- `/js/*` - JavaScript 파일들

## 차트가 안 보일 때

1. 브라우저 콘솔(F12) 확인
2. Chart.js 로드 확인: `console.log(typeof Chart)`
3. Canvas 요소 확인: `document.getElementById('regionChart')`
4. 네트워크 탭에서 chart.js 로딩 확인

## 더미 데이터 확인

애플리케이션 시작 시 자동으로 다음 데이터가 생성됩니다:
- 아파트: 5개
- 사용자: 4명
- 차량: 5대
- VOC: 4건
- LPR 장비: 3대
- 입출차 이력: 4건
- 제어 이력: 3건
- 공통코드: 10개
- 시스템 설정: 8개
- 공지사항: 3개
