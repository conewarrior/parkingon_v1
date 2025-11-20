# ParkingOn Design V2 - Design Prototype

이 프로젝트는 ParkingOn의 새로운 디자인 시안을 테스트하기 위한 독립 프로젝트입니다.

## 프로젝트 정보

- **원본 프로젝트**: `/Users/hskim/dev/parkingon_1104`
- **디자인 프로토타입**: `/Users/hskim/dev/parkingon_design_v2`
- **포트**: 8081 (원본은 8080)
- **목적**: 새로운 UI/UX 디자인 시안 테스트

## 주요 변경사항

### 1. 색상 테마
**원본 (청록색 테마)**
- Primary: #00B0FF (Cyan)
- 밝고 친근한 느낌

**Design V2 (인디고/보라색 테마)**
- Primary: #6366F1 (Indigo)
- 전문적이고 모던한 느낌
- 더 차분하고 세련된 분위기

### 2. 테이블 디자인
**강화된 보더**
- 헤더 보더: 2px → **3px solid**
- 셀 보더: 1px → **2px solid**
- 열 구분선 추가
- 더 진한 색상으로 영역 구분 명확화

**색상 팔레트**
```css
--border-light: #E2E8F0
--border-default: #CBD5E1
--border-strong: #94A3B8
--border-dark: #64748B
```

### 3. 버튼 아이콘 ✅
각 버튼에 적합한 SVG 아이콘 추가 완료:
- **조회**: 🔍 검색 아이콘 (Search icon)
- **로그인**: ➡️ 로그인 아이콘 (Login icon)
- **추가**: ➕ 추가 아이콘 (Plus icon)
- **저장**: 💾 저장 아이콘 (Save icon)
- **초기화**: 🔄 초기화 아이콘 (Reset icon)
- **입출차 이력**: 🚗 차량 아이콘 (Car icon)
- **VOC 이력**: 💬 메시지 아이콘 (Message icon)
- **수동제어 이력**: 🎮 컨트롤 아이콘 (Control icon)
- **열림**: ⬆️ 위 화살표 (Up arrow)
- **닫힘**: ⬇️ 아래 화살표 (Down arrow)
- **열림고정**: 🔓 잠금해제 아이콘 (Unlock icon)
- **열림고정 해제**: 🔒 잠금 아이콘 (Lock icon)
- **검지 자동열림**: ⚡ 번개 아이콘 (Lightning icon)
- **검지 자동해제**: 🛑 중지 아이콘 (Stop icon)

## 실행 방법

### 원본 프로젝트 (포트 8080)
```bash
cd /Users/hskim/dev/parkingon_1104
./gradlew bootRun
# http://localhost:8080
```

### 디자인 V2 프로젝트 (포트 8081)
```bash
cd /Users/hskim/dev/parkingon_design_v2
./gradlew bootRun
# http://localhost:8081
```

### 동시 실행
두 프로젝트를 동시에 실행하여 비교 가능:
- 원본: http://localhost:8080
- Design V2: http://localhost:8081

## 파일 구조

```
parkingon_design_v2/
├── src/main/resources/
│   ├── static/css/
│   │   ├── app.css           (새 색상 테마 적용)
│   │   ├── dashboard.css
│   │   ├── dashboard-worker.css
│   │   └── management.css
│   └── templates/
│       ├── dashboard.html
│       ├── dashboard-worker.html
│       └── ...
└── application.properties     (포트 8081로 설정)
```

## 완료된 작업 ✅

1. ✅ **색상 테마 변경**
   - 청록색(Cyan) → 인디고/보라색(Indigo) 테마로 변경
   - 모든 CSS 변수 및 hover 상태 업데이트 완료

2. ✅ **테이블 보더 강화**
   - 헤더 보더: 2px → 3px solid
   - 셀 보더: 1px → 2px solid
   - 열 구분선 추가 및 색상 강화

3. ✅ **버튼 아이콘 추가**
   - 모든 주요 버튼에 SVG 아이콘 추가 완료
   - 아이콘 크기 및 정렬 최적화
   - 일관성 있는 스타일 적용

## 다음 작업

1. **사용자 피드백**
   - 디자인 시안 검토 및 테스트
   - 최종 디자인 결정

2. **추가 개선 사항 (선택)**
   - hover, focus, active 상태 세밀 조정
   - 반응형 디자인 확인 및 개선

## 원본으로 복귀

디자인이 마음에 들지 않으면 원본 프로젝트 사용:
```bash
cd /Users/hskim/dev/parkingon_1104
./gradlew bootRun
```

## 디자인 최종 적용

디자인이 확정되면:
```bash
# Design V2의 변경사항을 원본에 복사
cp -r parkingon_design_v2/src/main/resources/static/* parkingon_1104/src/main/resources/static/
cp -r parkingon_design_v2/src/main/resources/templates/* parkingon_1104/src/main/resources/templates/
```
