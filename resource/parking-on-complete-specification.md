# 파킹온(ParkingOn) 시스템 - 완전한 화면 구현 명세서

## 📋 문서 개요

본 문서는 Spring Boot + Thymeleaf 기반의 파킹온 시스템 웹 퍼블리싱을 위한 **완전한 구현 명세서**입니다.
각 화면의 레이아웃, HTML 구조, CSS 클래스, JavaScript 기능을 상세하게 정의합니다.

---

## 🎨 공통 디자인 시스템

### 색상 팔레트
```css
:root {
  /* Primary Colors */
  --primary-red: #e63946;
  --primary-blue: #0891b2;
  --primary-black: #1a1a1a;
  
  /* Background Colors */
  --bg-white: #ffffff;
  --bg-gray-light: #f5f7fa;
  --bg-gray: #e5e7eb;
  --bg-dark: #2d3748;
  
  /* Text Colors */
  --text-primary: #1a1a1a;
  --text-secondary: #6b7280;
  --text-disabled: #d1d5db;
  
  /* Status Colors */
  --status-success: #10b981;
  --status-warning: #f59e0b;
  --status-error: #ef4444;
  --status-info: #3b82f6;
  
  /* Border Colors */
  --border-light: #e5e7eb;
  --border-default: #d1d5db;
  --border-dark: #9ca3af;
}
```

### 타이포그래피
```css
/* Font Family */
--font-primary: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, "Noto Sans", sans-serif;
--font-mono: "SFMono-Regular", Consolas, "Liberation Mono", Menlo, Courier, monospace;

/* Font Sizes */
--text-xs: 0.75rem;    /* 12px */
--text-sm: 0.875rem;   /* 14px */
--text-base: 1rem;     /* 16px */
--text-lg: 1.125rem;   /* 18px */
--text-xl: 1.25rem;    /* 20px */
--text-2xl: 1.5rem;    /* 24px */
--text-3xl: 1.875rem;  /* 30px */
--text-4xl: 2.25rem;   /* 36px */
```

### 간격 시스템
```css
--spacing-1: 0.25rem;  /* 4px */
--spacing-2: 0.5rem;   /* 8px */
--spacing-3: 0.75rem;  /* 12px */
--spacing-4: 1rem;     /* 16px */
--spacing-5: 1.25rem;  /* 20px */
--spacing-6: 1.5rem;   /* 24px */
--spacing-8: 2rem;     /* 32px */
--spacing-10: 2.5rem;  /* 40px */
--spacing-12: 3rem;    /* 48px */
```

---

## 📱 공통 레이아웃 구조

### 전체 페이지 구조 (메인 화면들)
```
┌─────────────────────────────────────────────┐
│ Header (고정)                                │
│ - BI 로고 (빨간색)                           │
│ - 메뉴명 (빨간색)                            │
│ - 네비게이션: Main | APT관리 | VOC관리 |     │
│               시스템관리 | PBX               │
├─────────────────────────────────────────────┤
│                                             │
│ Main Content Area                           │
│                                             │
│ ┌───────────┬───────────────────────────┐  │
│ │           │                           │  │
│ │ Sidebar   │   Content Area            │  │
│ │ (좌측)     │   (우측 메인)             │  │
│ │           │                           │  │
│ │ - 아파트   │                           │  │
│ │   목록     │                           │  │
│ │           │                           │  │
│ └───────────┴───────────────────────────┘  │
│                                             │
├─────────────────────────────────────────────┤
│ Footer (고정)                                │
│ [로그인명] [로그인시간] [현재시간]            │
└─────────────────────────────────────────────┘
```

### HTML 기본 구조 (fragments 사용)
```html
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title th:text="${pageTitle}">파킹온 시스템</title>
    <link rel="stylesheet" th:href="@{/css/app.css}">
</head>
<body>
    <!-- Header Fragment -->
    <div th:replace="~{fragments/header :: header}"></div>
    
    <!-- Main Content -->
    <main class="main-content">
        <!-- 각 페이지 콘텐츠 -->
    </main>
    
    <!-- Footer Fragment -->
    <div th:replace="~{fragments/footer :: footer}"></div>
    
    <script th:src="@{/js/main.js}"></script>
</body>
</html>
```

---

## 🔐 화면 1: 로그인 (Login.html)

### 레이아웃 구조
```
┌─────────────────────────────────────────────┐
│                                             │
│              관리자 사이트                   │
│                                             │
│            ╔═══════════╗                    │
│            ║ 아파트너   ║                    │
│            ╚═══════════╝                    │
│                                             │
│      더욱 편리한 입주민 관리가 시작됩니다.    │
│                                             │
│         [가족 일러스트 이미지]               │
│                                             │
├─────────────────────────────────────────────┤
│                                             │
│         ┌─────────────────────┐            │
│         │     로그인          │            │
│         ├─────────────────────┤            │
│         │                     │            │
│         │ [아이디 입력]        │            │
│         │ ⚠ 아이디를 입력해주세요 │            │
│         │                     │            │
│         │ [비밀번호]      👁️  │            │
│         │ ⚠ 비밀번호를 입력해주세요 │          │
│         │                     │            │
│         │   [로그인 버튼]      │            │
│         │                     │            │
│         └─────────────────────┘            │
│                                             │
├─────────────────────────────────────────────┤
│ 이용약관 | 개인정보취급방침 | 게시판...        │
│ © 두개비세상 Corp. All rights reserved.      │
│ 고객센터 1600-3123 (오전 9시~오후 6시)        │
└─────────────────────────────────────────────┘
```

### HTML 구조
```html
<!-- src/main/resources/templates/login.html -->
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인 - 파킹온 아파트너</title>
    <link rel="stylesheet" th:href="@{/css/app.css}">
</head>
<body class="login-page">
    <!-- 로그인 컨테이너 -->
    <div class="login-container">
        
        <!-- 로고 및 타이틀 섹션 -->
        <div class="logo-section">
            <p class="site-type">관리자 사이트</p>
            <h1 class="brand-name">아파트너</h1>
            <p class="brand-description">더욱 편리한 입주민 관리가 시작됩니다.</p>
            
            <!-- 일러스트 이미지 -->
            <div class="illustration">
                <img th:src="@{/images/family-illustration.png}" alt="가족 일러스트">
            </div>
        </div>
        
        <!-- 로그인 카드 -->
        <div class="login-card">
            <h2 class="login-title">로그인</h2>
            
            <form th:action="@{/login}" method="post" class="login-form">
                <!-- 아이디 입력 -->
                <div class="form-group">
                    <input 
                        type="text" 
                        name="username" 
                        id="username"
                        class="form-input" 
                        placeholder="아이디 입력"
                        required>
                    <span class="error-message" id="username-error">
                        아이디를 입력해주세요.
                    </span>
                </div>
                
                <!-- 비밀번호 입력 -->
                <div class="form-group">
                    <div class="password-wrapper">
                        <input 
                            type="password" 
                            name="password" 
                            id="password"
                            class="form-input" 
                            placeholder="비밀번호"
                            required>
                        <button 
                            type="button" 
                            class="toggle-password" 
                            aria-label="비밀번호 표시">
                            👁️
                        </button>
                    </div>
                    <span class="error-message" id="password-error">
                        비밀번호를 입력해주세요.
                    </span>
                </div>
                
                <!-- 로그인 버튼 -->
                <button type="submit" class="btn-login">로그인</button>
            </form>
        </div>
        
        <!-- 푸터 -->
        <footer class="login-footer">
            <div class="footer-links">
                <a href="#">이용약관</a>
                <span class="separator">|</span>
                <a href="#">개인정보취급방침</a>
                <span class="separator">|</span>
                <a href="#">게시판서비스운영정책</a>
                <span class="separator">|</span>
                <a href="mailto:help@aptner.com">help@aptner.com</a>
                <span class="separator">|</span>
                <a href="#">공식 홈페이지</a>
            </div>
            <p class="copyright">© 두개비세상 Corp. All rights reserved.</p>
            <p class="contact">고객센터 1600-3123 (오전 9시~오후 6시 / 주말, 공휴일 휴무)</p>
        </footer>
    </div>
    
    <script th:src="@{/js/login.js}"></script>
</body>
</html>
```

### CSS 클래스 정의
```css
/* 로그인 페이지 전체 */
.login-page {
    background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
    min-height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: var(--spacing-4);
}

/* 로그인 컨테이너 */
.login-container {
    max-width: 1200px;
    width: 100%;
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: var(--spacing-8);
    align-items: center;
}

/* 로고 섹션 */
.logo-section {
    text-align: center;
}

.site-type {
    font-size: var(--text-sm);
    color: var(--text-secondary);
    margin-bottom: var(--spacing-2);
}

.brand-name {
    font-size: var(--text-4xl);
    font-weight: 900;
    color: var(--primary-black);
    margin-bottom: var(--spacing-3);
}

.brand-description {
    font-size: var(--text-base);
    color: var(--text-secondary);
    margin-bottom: var(--spacing-8);
}

.illustration {
    max-width: 400px;
    margin: 0 auto;
}

.illustration img {
    width: 100%;
    height: auto;
}

/* 로그인 카드 */
.login-card {
    background: var(--bg-white);
    border-radius: 12px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    padding: var(--spacing-8);
}

.login-title {
    font-size: var(--text-2xl);
    font-weight: 700;
    color: var(--primary-black);
    margin-bottom: var(--spacing-6);
}

/* 폼 그룹 */
.form-group {
    margin-bottom: var(--spacing-5);
}

.form-input {
    width: 100%;
    padding: var(--spacing-3) var(--spacing-4);
    border: 1px solid var(--border-default);
    border-radius: 6px;
    font-size: var(--text-base);
    transition: border-color 0.3s;
}

.form-input:focus {
    outline: none;
    border-color: var(--primary-blue);
}

.form-input.error {
    border-color: var(--status-error);
}

/* 비밀번호 래퍼 */
.password-wrapper {
    position: relative;
}

.toggle-password {
    position: absolute;
    right: var(--spacing-3);
    top: 50%;
    transform: translateY(-50%);
    background: none;
    border: none;
    cursor: pointer;
    font-size: var(--text-lg);
    padding: var(--spacing-2);
}

/* 에러 메시지 */
.error-message {
    display: none;
    color: var(--status-error);
    font-size: var(--text-sm);
    margin-top: var(--spacing-2);
}

.error-message.show {
    display: block;
}

/* 로그인 버튼 */
.btn-login {
    width: 100%;
    padding: var(--spacing-4);
    background: var(--primary-blue);
    color: var(--bg-white);
    border: none;
    border-radius: 6px;
    font-size: var(--text-base);
    font-weight: 600;
    cursor: pointer;
    transition: background-color 0.3s;
}

.btn-login:hover {
    background: #0e7490;
}

/* 푸터 */
.login-footer {
    grid-column: 1 / -1;
    text-align: center;
    margin-top: var(--spacing-8);
}

.footer-links {
    margin-bottom: var(--spacing-3);
}

.footer-links a {
    color: var(--text-secondary);
    text-decoration: none;
    font-size: var(--text-sm);
}

.footer-links .separator {
    margin: 0 var(--spacing-2);
    color: var(--border-default);
}

.copyright,
.contact {
    font-size: var(--text-xs);
    color: var(--text-secondary);
    margin-top: var(--spacing-2);
}

/* 모바일 반응형 */
@media (max-width: 768px) {
    .login-container {
        grid-template-columns: 1fr;
        gap: var(--spacing-6);
    }
    
    .logo-section {
        display: none; /* 모바일에서는 로고 섹션 숨김 */
    }
    
    .login-card {
        padding: var(--spacing-6);
    }
}
```

### JavaScript 기능
```javascript
// src/main/resources/static/js/login.js

document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.querySelector('.login-form');
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');
    const togglePasswordBtn = document.querySelector('.toggle-password');
    
    // 비밀번호 표시/숨김 토글
    togglePasswordBtn.addEventListener('click', function() {
        const type = passwordInput.type === 'password' ? 'text' : 'password';
        passwordInput.type = type;
        
        // 아이콘 변경 (선택사항)
        this.textContent = type === 'password' ? '👁️' : '🙈';
    });
    
    // 폼 검증
    loginForm.addEventListener('submit', function(e) {
        let isValid = true;
        
        // 아이디 검증
        if (usernameInput.value.trim() === '') {
            showError('username');
            isValid = false;
        } else {
            hideError('username');
        }
        
        // 비밀번호 검증
        if (passwordInput.value.trim() === '') {
            showError('password');
            isValid = false;
        } else {
            hideError('password');
        }
        
        if (!isValid) {
            e.preventDefault();
        }
    });
    
    // 입력 필드 포커스 시 에러 제거
    usernameInput.addEventListener('input', function() {
        hideError('username');
    });
    
    passwordInput.addEventListener('input', function() {
        hideError('password');
    });
    
    // 에러 표시 함수
    function showError(fieldName) {
        const input = document.getElementById(fieldName);
        const error = document.getElementById(fieldName + '-error');
        input.classList.add('error');
        error.classList.add('show');
    }
    
    // 에러 숨김 함수
    function hideError(fieldName) {
        const input = document.getElementById(fieldName);
        const error = document.getElementById(fieldName + '-error');
        input.classList.remove('error');
        error.classList.remove('show');
    }
});
```

---

## 🔔 화면 2: 공지사항 팝업 (NotifyPopup.html)

### 레이아웃 구조
```
┌─────────────────────────────────────────────┐
│ [일련번호: 2]                    [닫기 ×]   │
├─────────────────────────────────────────────┤
│                                             │
│ 발신자: 콜센터 관리자                        │
│ 발송일시: 2025-10-17 17:21:10               │
│                                             │
│ 제목: 콜센터 운영지침 변경                   │
│                                             │
├─────────────────────────────────────────────┤
│                                             │
│ 공지내용:                                    │
│                                             │
│ 콜센터 운영지침 변경 10월 17일부로           │
│ 변경됨을 알립니다.                           │
│                                             │
│ 콜센터의 업무는 ….                          │
│ 업무 시간은 ….                              │
│ 차량 입차 처리에 대하여는…..                 │
│                                             │
│         [공지 이미지 000×000px]              │
│                                             │
├─────────────────────────────────────────────┤
│            [◁ 이전] [다음 ▷]                │
├─────────────────────────────────────────────┤
│ ☐ 오늘 하루 보지 않기                       │
└─────────────────────────────────────────────┘
```

### HTML 구조
```html
<!-- src/main/resources/templates/fragments/notify-popup.html -->
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항</title>
    <link rel="stylesheet" th:href="@{/css/app.css}">
</head>
<body>
    <!-- 팝업 오버레이 -->
    <div class="popup-overlay" id="notifyPopup" th:if="${showNotify}">
        <div class="popup-container">
            <!-- 팝업 헤더 -->
            <div class="popup-header">
                <span class="popup-number">일련번호: <span th:text="${notify.id}">2</span></span>
                <button class="btn-close" onclick="closePopup()">×</button>
            </div>
            
            <!-- 팝업 정보 -->
            <div class="popup-info">
                <div class="info-row">
                    <span class="info-label">발신자:</span>
                    <span class="info-value" th:text="${notify.sender}">콜센터 관리자</span>
                </div>
                <div class="info-row">
                    <span class="info-label">발송일시:</span>
                    <span class="info-value" th:text="${#temporals.format(notify.sendDate, 'yyyy-MM-dd HH:mm:ss')}">
                        2025-10-17 17:21:10
                    </span>
                </div>
            </div>
            
            <!-- 팝업 제목 -->
            <h3 class="popup-title" th:text="${notify.title}">콜센터 운영지침 변경</h3>
            
            <!-- 팝업 내용 -->
            <div class="popup-content">
                <h4 class="content-label">공지내용:</h4>
                <div class="content-text" th:utext="${notify.content}">
                    콜센터 운영지침 변경 10월 17일부로 변경됨을 알립니다.<br>
                    콜센터의 업무는 ….<br>
                    업무 시간은 ….<br>
                    차량 입차 처리에 대하여는…..
                </div>
                
                <!-- 공지 이미지 (있는 경우) -->
                <div class="content-image" th:if="${notify.imageUrl}">
                    <img th:src="${notify.imageUrl}" alt="공지 이미지">
                </div>
            </div>
            
            <!-- 네비게이션 -->
            <div class="popup-navigation">
                <button class="btn-nav btn-prev" onclick="showPrevNotify()">
                    ◁ 이전
                </button>
                <button class="btn-nav btn-next" onclick="showNextNotify()">
                    다음 ▷
                </button>
            </div>
            
            <!-- 하루 보지 않기 -->
            <div class="popup-footer">
                <label class="checkbox-label">
                    <input type="checkbox" id="hideToday" onchange="setHideToday()">
                    <span>오늘 하루 보지 않기</span>
                </label>
            </div>
        </div>
    </div>
    
    <script th:src="@{/js/notify-popup.js}"></script>
</body>
</html>
```

### CSS 클래스 정의
```css
/* 팝업 오버레이 */
.popup-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    align-items: center;
    justify-content: center;
    z-index: 9999;
}

/* 팝업 컨테이너 */
.popup-container {
    background: var(--bg-white);
    border-radius: 12px;
    width: 90%;
    max-width: 600px;
    max-height: 90vh;
    overflow-y: auto;
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.3);
}

/* 팝업 헤더 */
.popup-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: var(--spacing-4);
    border-bottom: 1px solid var(--border-light);
}

.popup-number {
    font-size: var(--text-sm);
    color: var(--text-secondary);
}

.btn-close {
    background: none;
    border: none;
    font-size: var(--text-3xl);
    color: var(--text-secondary);
    cursor: pointer;
    line-height: 1;
    padding: 0;
    width: 32px;
    height: 32px;
}

.btn-close:hover {
    color: var(--primary-red);
}

/* 팝업 정보 */
.popup-info {
    padding: var(--spacing-4);
    background: var(--bg-gray-light);
}

.info-row {
    display: flex;
    gap: var(--spacing-2);
    margin-bottom: var(--spacing-2);
    font-size: var(--text-sm);
}

.info-row:last-child {
    margin-bottom: 0;
}

.info-label {
    font-weight: 600;
    color: var(--text-primary);
}

.info-value {
    color: var(--text-secondary);
}

/* 팝업 제목 */
.popup-title {
    padding: var(--spacing-5) var(--spacing-4);
    font-size: var(--text-xl);
    font-weight: 700;
    color: var(--primary-black);
    border-bottom: 1px solid var(--border-light);
}

/* 팝업 내용 */
.popup-content {
    padding: var(--spacing-4);
}

.content-label {
    font-size: var(--text-base);
    font-weight: 600;
    margin-bottom: var(--spacing-3);
}

.content-text {
    font-size: var(--text-base);
    line-height: 1.6;
    color: var(--text-primary);
    margin-bottom: var(--spacing-4);
}

.content-image {
    text-align: center;
    margin-top: var(--spacing-6);
}

.content-image img {
    max-width: 100%;
    height: auto;
    border-radius: 8px;
}

/* 네비게이션 */
.popup-navigation {
    display: flex;
    justify-content: center;
    gap: var(--spacing-4);
    padding: var(--spacing-4);
    border-top: 1px solid var(--border-light);
}

.btn-nav {
    padding: var(--spacing-2) var(--spacing-5);
    border: 1px solid var(--border-default);
    background: var(--bg-white);
    border-radius: 6px;
    font-size: var(--text-sm);
    cursor: pointer;
    transition: all 0.3s;
}

.btn-nav:hover {
    background: var(--bg-gray-light);
}

/* 푸터 */
.popup-footer {
    padding: var(--spacing-4);
    border-top: 1px solid var(--border-light);
}

.checkbox-label {
    display: flex;
    align-items: center;
    gap: var(--spacing-2);
    cursor: pointer;
    font-size: var(--text-sm);
}

.checkbox-label input[type="checkbox"] {
    width: 18px;
    height: 18px;
    cursor: pointer;
}

/* 모바일 반응형 */
@media (max-width: 768px) {
    .popup-container {
        width: 95%;
        max-height: 95vh;
    }
    
    .popup-navigation {
        flex-direction: column;
    }
    
    .btn-nav {
        width: 100%;
    }
}
```

### JavaScript 기능
```javascript
// src/main/resources/static/js/notify-popup.js

// 팝업 닫기
function closePopup() {
    document.getElementById('notifyPopup').style.display = 'none';
}

// 이전 공지 보기
function showPrevNotify() {
    // AJAX로 이전 공지 로드
    const currentId = parseInt(document.querySelector('.popup-number span').textContent);
    window.location.href = `/notify-popup?id=${currentId - 1}`;
}

// 다음 공지 보기
function showNextNotify() {
    // AJAX로 다음 공지 로드
    const currentId = parseInt(document.querySelector('.popup-number span').textContent);
    window.location.href = `/notify-popup?id=${currentId + 1}`;
}

// 오늘 하루 보지 않기 설정
function setHideToday() {
    const checkbox = document.getElementById('hideToday');
    if (checkbox.checked) {
        const today = new Date().toDateString();
        localStorage.setItem('hideNotifyUntil', today);
        closePopup();
    }
}

// 페이지 로드 시 오늘 하루 보지 않기 체크
document.addEventListener('DOMContentLoaded', function() {
    const hideUntil = localStorage.getItem('hideNotifyUntil');
    const today = new Date().toDateString();
    
    if (hideUntil === today) {
        closePopup();
    }
});
```

---

## 📊 화면 3: 대시보드 - 멀티스크린 (Main.html - 통계용)

### 레이아웃 구조
```
┌─────────────────────────────────────────────────────────────┐
│ BI -> 대시보드(멀티스크린)  Main APT관리 VOC관리 시스템관리 PBX │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│ ┌───────────────────────┬───────────────────────────────┐  │
│ │                       │                               │  │
│ │   [파이 차트 1]        │   [파이 차트 2]               │  │
│ │   상담 VOC 지역 분포   │   상담 VOC 업무 분포          │  │
│ │   2025-10-28 ▼        │                               │  │
│ │                       │                               │  │
│ └───────────────────────┴───────────────────────────────┘  │
│                                                             │
│ ┌─────────────────────────────────────────────────────────┐ │
│ │         총 단지    전상 운영 단지    장애 단지           │ │
│ │          123         120            3                  │ │
│ └─────────────────────────────────────────────────────────┘ │
│                                                             │
│ ┌─────────────────────────────────────────────────────────┐ │
│ │                   VOC 고객구분                          │ │
│ │ 전체  일반  입주민  주기/방문  방문  출역  회이트        │ │
│ │ 500   100    100     100     100    0      0           │ │
│ └─────────────────────────────────────────────────────────┘ │
│                                                             │
│ ┌─────────────────────────────────────────────────────────┐ │
│ │                   VOC 발생 목적                         │ │
│ │ 전체  방문  미인식  관동  기타                          │ │
│ │ 500   100   100    100   200                           │ │
│ └─────────────────────────────────────────────────────────┘ │
│                                                             │
│ ┌─────────────────────────────────────────────────────────┐ │
│ │      월 ▼        2025-10 ▼  입차/출차 현황(월)         │ │
│ │ ┌─────────────────────────────────────────────────────┐ │ │
│ │ │       2025-10 월별 차량입차/출차                    │ │ │
│ │ │ 구분  1 2 3 4 5 6 7 8 9 10 11 12 13 14...31        │ │ │
│ │ │ 입주민 □ □ □ □ □ □ □ □ □ □ □ □ □ □...□           │ │ │
│ │ │ 방문   □ □ □ □ □ □ □ □ □ □ □ □ □ □...□           │ │ │
│ │ │ 출역   □ □ □ □ □ □ □ □ □ □ □ □ □ □...□           │ │ │
│ │ │ 회이트 □ □ □ □ □ □ □ □ □ □ □ □ □ □...□           │ │ │
│ │ └─────────────────────────────────────────────────────┘ │ │
│ └─────────────────────────────────────────────────────────┘ │
│                                                             │
│ ┌─────────────────────────────────────────────────────────┐ │
│ │        2025 ▼  신규/폐쇄 현황(연)                       │ │
│ │             [막대 차트: 월별 신규/폐쇄]                 │ │
│ │   8                                                     │ │
│ │   7                                  ■ 신규             │ │
│ │   6                                  ■ 폐쇄             │ │
│ │   ...                                                   │ │
│ │   0 ─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─                     │ │
│ │     1월 2월 3월 4월 5월 6월 7월 8월 9월 10월 11월 12월   │ │
│ └─────────────────────────────────────────────────────────┘ │
│                                                             │
├─────────────────────────────────────────────────────────────┤
│ Footer[로그인명/로그인시간/현재시간]                         │
└─────────────────────────────────────────────────────────────┘
```

### HTML 구조
```html
<!-- src/main/resources/templates/main.html -->
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>대시보드 - 파킹온</title>
    <link rel="stylesheet" th:href="@{/css/app.css}">
    <!-- Chart.js 라이브러리 -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.0/dist/chart.umd.min.js"></script>
</head>
<body>
    <!-- Header -->
    <div th:replace="~{fragments/header :: header(menuTitle='대시보드(멀티스크린)')}"></div>
    
    <main class="main-content">
        <!-- 차트 섹션 -->
        <section class="dashboard-charts">
            <div class="chart-container">
                <div class="chart-card">
                    <div class="chart-header">
                        <h3>상담 VOC 지역 분포</h3>
                        <select class="date-selector">
                            <option>2025-10-28</option>
                            <option>2025-10-27</option>
                        </select>
                    </div>
                    <canvas id="regionChart"></canvas>
                    <div class="chart-legend">
                        <span>■ 수도권</span>
                        <span>■ 강원권</span>
                        <span>■ 충청권</span>
                        <span>■ 남부권</span>
                        <span>■ 제주권</span>
                    </div>
                </div>
                
                <div class="chart-card">
                    <div class="chart-header">
                        <h3>콜 센터 현황(일)</h3>
                    </div>
                    <canvas id="callStatusChart"></canvas>
                    <div class="chart-legend">
                        <span>■ 출근중</span>
                        <span>■ 휴근중</span>
                        <span>■ 담당배제</span>
                        <span>■ 내다주</span>
                        <span>■ 담당중</span>
                        <span>■ 이행중</span>
                    </div>
                </div>
            </div>
        </section>
        
        <!-- 단지 현황 -->
        <section class="status-summary">
            <div class="summary-card">
                <h4>총 단지</h4>
                <p class="summary-number" th:text="${totalSites}">123</p>
            </div>
            <div class="summary-card">
                <h4>정상 운영 단지</h4>
                <p class="summary-number success" th:text="${normalSites}">120</p>
            </div>
            <div class="summary-card">
                <h4>장애 단지</h4>
                <p class="summary-number error" th:text="${errorSites}">3</p>
            </div>
        </section>
        
        <!-- VOC 고객구분 -->
        <section class="voc-section">
            <h3 class="section-title">VOC 고객구분</h3>
            <div class="voc-stats">
                <div class="stat-item">
                    <span class="stat-label">전체</span>
                    <span class="stat-value" th:text="${vocTotal}">500</span>
                </div>
                <div class="stat-item">
                    <span class="stat-label">일반</span>
                    <span class="stat-value" th:text="${vocGeneral}">100</span>
                </div>
                <div class="stat-item">
                    <span class="stat-label">입주민</span>
                    <span class="stat-value" th:text="${vocResident}">100</span>
                </div>
                <div class="stat-item">
                    <span class="stat-label">주기/방문</span>
                    <span class="stat-value" th:text="${vocVisit}">100</span>
                </div>
                <div class="stat-item">
                    <span class="stat-label">방문</span>
                    <span class="stat-value" th:text="${vocVisitor}">100</span>
                </div>
                <div class="stat-item">
                    <span class="stat-label">출역</span>
                    <span class="stat-value" th:text="${vocDelivery}">0</span>
                </div>
                <div class="stat-item">
                    <span class="stat-label">화이트</span>
                    <span class="stat-value" th:text="${vocWhite}">0</span>
                </div>
            </div>
        </section>
        
        <!-- VOC 발생 목적 -->
        <section class="voc-section">
            <h3 class="section-title">VOC 발생 목적</h3>
            <div class="voc-stats">
                <div class="stat-item">
                    <span class="stat-label">전체</span>
                    <span class="stat-value" th:text="${purposeTotal}">500</span>
                </div>
                <div class="stat-item">
                    <span class="stat-label">방문</span>
                    <span class="stat-value" th:text="${purposeVisit}">100</span>
                </div>
                <div class="stat-item">
                    <span class="stat-label">미인식</span>
                    <span class="stat-value" th:text="${purposeUnknown}">100</span>
                </div>
                <div class="stat-item">
                    <span class="stat-label">관동</span>
                    <span class="stat-value" th:text="${purposeManagement}">100</span>
                </div>
                <div class="stat-item">
                    <span class="stat-label">기타</span>
                    <span class="stat-value" th:text="${purposeEtc}">200</span>
                </div>
            </div>
        </section>
        
        <!-- 입출차 현황 테이블 -->
        <section class="inout-section">
            <div class="section-header">
                <h3 class="section-title">입차/출차 현황(월)</h3>
                <div class="filter-group">
                    <select class="filter-select">
                        <option>월</option>
                        <option>년</option>
                    </select>
                    <select class="filter-select">
                        <option>2025-10</option>
                        <option>2025-09</option>
                    </select>
                </div>
            </div>
            
            <div class="inout-table-wrapper">
                <table class="inout-table">
                    <thead>
                        <tr>
                            <th>구분</th>
                            <th th:each="day : ${#numbers.sequence(1, 31)}" th:text="${day}">1</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>입주민</td>
                            <td th:each="day : ${#numbers.sequence(1, 31)}">
                                <input type="checkbox" th:checked="${residentDays.contains(day)}">
                            </td>
                        </tr>
                        <tr>
                            <td>방문</td>
                            <td th:each="day : ${#numbers.sequence(1, 31)}">
                                <input type="checkbox" th:checked="${visitDays.contains(day)}">
                            </td>
                        </tr>
                        <tr>
                            <td>출역</td>
                            <td th:each="day : ${#numbers.sequence(1, 31)}">
                                <input type="checkbox" th:checked="${deliveryDays.contains(day)}">
                            </td>
                        </tr>
                        <tr>
                            <td>화이트</td>
                            <td th:each="day : ${#numbers.sequence(1, 31)}">
                                <input type="checkbox" th:checked="${whiteDays.contains(day)}">
                            </td>
                        </tr>
                        <tr>
                            <td>주기/방문</td>
                            <td th:each="day : ${#numbers.sequence(1, 31)}">
                                <input type="checkbox" th:checked="${periodicDays.contains(day)}">
                            </td>
                        </tr>
                        <tr class="total-row">
                            <td>출차</td>
                            <td th:each="count : ${outCounts}" th:text="${count}">0</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </section>
        
        <!-- 신규/폐쇄 현황 차트 -->
        <section class="status-chart-section">
            <div class="section-header">
                <h3 class="section-title">신규/폐쇄 현황(연)</h3>
                <select class="filter-select">
                    <option>2025</option>
                    <option>2024</option>
                </select>
            </div>
            <div class="chart-wrapper">
                <canvas id="statusChart"></canvas>
                <p class="chart-subtitle">월별 신규/폐쇄 단지 추이</p>
            </div>
        </section>
    </main>
    
    <!-- Footer -->
    <div th:replace="~{fragments/footer :: footer}"></div>
    
    <script th:src="@{/js/dashboard.js}"></script>
</body>
</html>
```

### CSS 클래스 정의
```css
/* 대시보드 메인 콘텐츠 */
.main-content {
    padding: var(--spacing-6);
    background: var(--bg-gray-light);
}

/* 차트 섹션 */
.dashboard-charts {
    margin-bottom: var(--spacing-6);
}

.chart-container {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(400px, 1fr));
    gap: var(--spacing-6);
}

.chart-card {
    background: var(--bg-white);
    border-radius: 8px;
    padding: var(--spacing-5);
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.chart-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: var(--spacing-4);
}

.chart-header h3 {
    font-size: var(--text-lg);
    font-weight: 600;
}

.date-selector,
.filter-select {
    padding: var(--spacing-2) var(--spacing-3);
    border: 1px solid var(--border-default);
    border-radius: 4px;
    font-size: var(--text-sm);
}

.chart-legend {
    display: flex;
    flex-wrap: wrap;
    gap: var(--spacing-4);
    margin-top: var(--spacing-4);
    font-size: var(--text-sm);
}

/* 단지 현황 요약 */
.status-summary {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
    gap: var(--spacing-4);
    margin-bottom: var(--spacing-6);
}

.summary-card {
    background: var(--bg-white);
    border-radius: 8px;
    padding: var(--spacing-5);
    text-align: center;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.summary-card h4 {
    font-size: var(--text-base);
    color: var(--text-secondary);
    margin-bottom: var(--spacing-3);
}

.summary-number {
    font-size: var(--text-4xl);
    font-weight: 700;
    color: var(--primary-black);
}

.summary-number.success {
    color: var(--status-success);
}

.summary-number.error {
    color: var(--status-error);
}

/* VOC 섹션 */
.voc-section {
    background: var(--bg-white);
    border-radius: 8px;
    padding: var(--spacing-5);
    margin-bottom: var(--spacing-6);
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.section-title {
    font-size: var(--text-lg);
    font-weight: 600;
    margin-bottom: var(--spacing-4);
}

.voc-stats {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(100px, 1fr));
    gap: var(--spacing-4);
}

.stat-item {
    text-align: center;
    padding: var(--spacing-3);
    border: 1px solid var(--border-light);
    border-radius: 4px;
}

.stat-label {
    display: block;
    font-size: var(--text-sm);
    color: var(--text-secondary);
    margin-bottom: var(--spacing-2);
}

.stat-value {
    display: block;
    font-size: var(--text-xl);
    font-weight: 700;
    color: var(--primary-black);
}

/* 입출차 현황 섹션 */
.inout-section {
    background: var(--bg-white);
    border-radius: 8px;
    padding: var(--spacing-5);
    margin-bottom: var(--spacing-6);
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.section-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: var(--spacing-4);
}

.filter-group {
    display: flex;
    gap: var(--spacing-2);
}

.inout-table-wrapper {
    overflow-x: auto;
}

.inout-table {
    width: 100%;
    border-collapse: collapse;
    font-size: var(--text-sm);
}

.inout-table th,
.inout-table td {
    padding: var(--spacing-2);
    text-align: center;
    border: 1px solid var(--border-light);
}

.inout-table th {
    background: var(--bg-gray-light);
    font-weight: 600;
}

.inout-table td:first-child {
    font-weight: 600;
    background: var(--bg-gray-light);
}

.inout-table .total-row {
    background: var(--bg-gray-light);
    font-weight: 600;
}

.inout-table input[type="checkbox"] {
    width: 16px;
    height: 16px;
}

/* 차트 래퍼 */
.status-chart-section {
    background: var(--bg-white);
    border-radius: 8px;
    padding: var(--spacing-5);
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.chart-wrapper {
    margin-top: var(--spacing-4);
}

.chart-subtitle {
    text-align: center;
    font-size: var(--text-sm);
    color: var(--text-secondary);
    margin-top: var(--spacing-3);
}

/* 모바일 반응형 */
@media (max-width: 768px) {
    .chart-container {
        grid-template-columns: 1fr;
    }
    
    .status-summary {
        grid-template-columns: 1fr;
    }
    
    .voc-stats {
        grid-template-columns: repeat(2, 1fr);
    }
    
    .section-header {
        flex-direction: column;
        gap: var(--spacing-3);
    }
}
```

### JavaScript 기능 (차트 초기화)
```javascript
// src/main/resources/static/js/dashboard.js

document.addEventListener('DOMContentLoaded', function() {
    // 지역 분포 파이 차트
    const regionCtx = document.getElementById('regionChart');
    if (regionCtx) {
        new Chart(regionCtx, {
            type: 'doughnut',
            data: {
                labels: ['수도권', '강원권', '충청권', '남부권', '제주권'],
                datasets: [{
                    data: [45, 15, 20, 15, 5],
                    backgroundColor: [
                        '#3b82f6',
                        '#ef4444',
                        '#10b981',
                        '#f59e0b',
                        '#8b5cf6'
                    ]
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: true,
                plugins: {
                    legend: {
                        display: false
                    }
                }
            }
        });
    }
    
    // 콜 센터 현황 파이 차트
    const callStatusCtx = document.getElementById('callStatusChart');
    if (callStatusCtx) {
        new Chart(callStatusCtx, {
            type: 'doughnut',
            data: {
                labels: ['출근중', '휴근중', '담당배제', '내다주', '담당중', '이행중'],
                datasets: [{
                    data: [30, 20, 15, 10, 15, 10],
                    backgroundColor: [
                        '#3b82f6',
                        '#ef4444',
                        '#10b981',
                        '#f59e0b',
                        '#8b5cf6',
                        '#ec4899'
                    ]
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: true,
                plugins: {
                    legend: {
                        display: false
                    }
                }
            }
        });
    }
    
    // 신규/폐쇄 현황 막대 차트
    const statusCtx = document.getElementById('statusChart');
    if (statusCtx) {
        new Chart(statusCtx, {
            type: 'bar',
            data: {
                labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
                datasets: [
                    {
                        label: '신규',
                        data: [1, 2, 2, 4, 3, 2, 5, 4, 3, 6, 7, 5],
                        backgroundColor: '#3b82f6'
                    },
                    {
                        label: '폐쇄',
                        data: [1, 0, 0, 2, 0, 3, 1, 2, 1, 4, 1, 0],
                        backgroundColor: '#ef4444'
                    }
                ]
            },
            options: {
                responsive: true,
                maintainAspectRatio: true,
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: {
                            stepSize: 1
                        }
                    }
                },
                plugins: {
                    legend: {
                        position: 'top'
                    }
                }
            }
        });
    }
});
```

---

## 📱 화면 4: 대시보드 - 근무자용 (Main.html - 실무용)

### 레이아웃 구조
```
┌─────────────────────────────────────────────────────────────────────────────┐
│ BI -> 대시보드(근무자용)  Main APT관리 VOC관리 시스템관리 PBX                 │
├─────────────┬─────────────────────────────────────────────┬─────────────────┤
│             │                                             │                 │
│  아파트목록  │           상단 상태 표시줄                   │  12가1234       │
│  ┌────────┐ │  ┌─────┬─────┬─────┬─────┬─────┬─────┐    │  이전 상담내역  │
│  │조회    │ │  │차단기│시스템│ CPU │메모리│통신 │저장소│    │                 │
│  └────────┘ │  │정상 │점검 │ 30% │ 40% │ 20M │50/100│    │  10-14 21:20:21│
│             │  └─────┴─────┴─────┴─────┴─────┴─────┘    │  102동 1004호...│
│ ○ A1001234  │                                             │                 │
│   삼성1단지  │  ┌────────────────────────────────────┐    │  10-14 21:20:21│
│             │  │ CCTV                    320×180px  │    │  102동 1004호...│
│ ■ A1001235  │  │                                    │    │                 │
│   삼성2단지  │  │      [CCTV 화면 영역]              │    │ ─────────────── │
│             │  │                                    │    │                 │
│ ○ A1001236  │  │                                    │    │  아파트1 단지A  │
│   정문인구   │  └────────────────────────────────────┘    │                 │
│   방문      │                                             │  [2025-10-20    │
│             │  ┌────────────────────────────────────┐    │   20:30:50]     │
│ ○ ...       │  │ 입차이미지              320×180px  │    │                 │
│             │  │                                    │    │  차량번호:      │
│             │  │   [입차 이미지 영역]               │    │  12가1234       │
│             │  │                                    │    │                 │
│ ◁ 1 2 3 4 5 ▷│  │                                    │    │  고객유형: ▼    │
│             │  └────────────────────────────────────┘    │  방문고객        │
│             │                                             │                 │
│             │                                             │  방문목적: ▼    │
│             │                                             │  방문            │
│             │                                             │                 │
│             │                                             │  동/호:         │
│             │                                             │  101 ▼ 1002 ▼  │
│             │                                             │                 │
│             │                                             │  상담내역:      │
│             │                                             │  ┌───────────┐  │
│             │                                             │  │           │  │
│             │                                             │  └───────────┘  │
│             │                                             │                 │
│             │                                             │ ╔═══════════╗  │
│             │                                             │ ║차단기 제어 ║  │
│             │                                             │ ║ ○ 열림    ║  │
│             │                                             │ ║ ● 닫힘    ║  │
│             │                                             │ ║           ║  │
│             │                                             │ ║ [차량]    ║  │
│             │                                             │ ╚═══════════╝  │
├─────────────┴─────────────────────────────────────────────┴─────────────────┤
│ [방문차단이력: 정상] [VOC이력: 정상] [수동제어: 정상] [검지차: 정상] ...      │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                      [PBX콜: 동아아파트]      │
├─────────────────────────────────────────────────────────────────────────────┤
│ Footer[로그인명/로그인시간/현재시간]                                          │
└─────────────────────────────────────────────────────────────────────────────┘
```

### HTML 구조
```html
<!-- src/main/resources/templates/dashboard-worker.html -->
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>대시보드 - 파킹온</title>
    <link rel="stylesheet" th:href="@{/css/app.css}">
</head>
<body class="dashboard-page">
    <!-- Header -->
    <div th:replace="~{fragments/header :: header(menuTitle='대시보드(근무자용)')}"></div>
    
    <main class="dashboard-container">
        <!-- 좌측: 아파트 목록 -->
        <aside class="apartment-sidebar">
            <div class="sidebar-header">
                <h2 class="sidebar-title">아파트명</h2>
                <button class="btn-search" onclick="searchApartments()">조회</button>
            </div>
            
            <ul class="apartment-list" id="apartmentList">
                <li class="apartment-item" data-id="A1001234">
                    <span class="status-indicator"></span>
                    <div class="apt-info">
                        <span class="apt-code">A1001234</span>
                        <span class="apt-name">삼성1단지</span>
                    </div>
                </li>
                <li class="apartment-item selected" data-id="A1001235">
                    <span class="status-indicator active"></span>
                    <div class="apt-info">
                        <span class="apt-code">A1001235</span>
                        <span class="apt-name">삼성2단지</span>
                    </div>
                </li>
                <li class="apartment-item" data-id="A1001236">
                    <span class="status-indicator"></span>
                    <div class="apt-info">
                        <span class="apt-code">A1001236</span>
                        <span class="apt-name">정문인구 방문</span>
                    </div>
                </li>
                <!-- 더 많은 아파트 항목들... -->
            </ul>
            
            <!-- 페이지네이션 -->
            <div class="pagination">
                <button class="btn-page">◁</button>
                <button class="btn-page active">1</button>
                <button class="btn-page">2</button>
                <button class="btn-page">3</button>
                <button class="btn-page">4</button>
                <button class="btn-page">5</button>
                <button class="btn-page">▷</button>
            </div>
        </aside>
        
        <!-- 중앙: CCTV 및 이미지 -->
        <section class="center-content">
            <!-- 상단 상태 표시줄 -->
            <div class="status-bar">
                <div class="status-item">
                    <span class="status-label">차단기</span>
                    <span class="status-value status-normal">정상</span>
                </div>
                <div class="status-item">
                    <span class="status-label">시스템</span>
                    <span class="status-value status-warning">점검</span>
                </div>
                <div class="status-item">
                    <span class="status-label">CPU</span>
                    <span class="status-value">30%</span>
                </div>
                <div class="status-item">
                    <span class="status-label">메모리</span>
                    <span class="status-value">40%</span>
                </div>
                <div class="status-item">
                    <span class="status-label">통신</span>
                    <span class="status-value">20M</span>
                </div>
                <div class="status-item">
                    <span class="status-label">저장소</span>
                    <span class="status-value">50/100</span>
                </div>
            </div>
            
            <!-- CCTV 뷰어 -->
            <div class="viewer-container">
                <div class="viewer-header">
                    <h3>CCTV</h3>
                    <span class="viewer-size">320 × 180px</span>
                </div>
                <div class="cctv-viewer">
                    <!-- CCTV 스트림이 여기에 표시됩니다 -->
                    <div class="viewer-placeholder">
                        <span>CCTV 화면</span>
                    </div>
                </div>
            </div>
            
            <!-- 입차 이미지 뷰어 -->
            <div class="viewer-container">
                <div class="viewer-header">
                    <h3>입차이미지</h3>
                    <span class="viewer-size">320 × 180px</span>
                </div>
                <div class="incar-viewer">
                    <div class="viewer-placeholder">
                        <span>입차 이미지</span>
                    </div>
                </div>
            </div>
        </section>
        
        <!-- 우측: 상담 이력 및 제어 -->
        <aside class="consultation-sidebar">
            <!-- 이전 상담 이력 -->
            <div class="consultation-history">
                <h3 class="history-title">12가1234 이전 상담내역</h3>
                <div class="history-list">
                    <div class="history-item">
                        <span class="history-time">10-14 21:20:21</span>
                        <span class="history-content">102동 1004호 차량/전치 방문</span>
                    </div>
                    <div class="history-item">
                        <span class="history-time">10-14 21:20:21</span>
                        <span class="history-content">102동 1004호 차량/전치 방문</span>
                    </div>
                    <div class="history-item">
                        <span class="history-time">10-14 21:20:21</span>
                        <span class="history-content">102동 1004호 차량/전치 방문</span>
                    </div>
                    <div class="history-item">
                        <span class="history-time">10-14 21:20:21</span>
                        <span class="history-content">102동 1004호 차량/전치 방문</span>
                    </div>
                </div>
            </div>
            
            <!-- 현재 상담 정보 -->
            <div class="consultation-form">
                <h3 class="form-title">아파트명 아파트1 정문인구 방문차량</h3>
                
                <div class="consultation-info">
                    <div class="info-header">
                        <strong>단지A</strong>
                        <span>[2025-10-20 20:30:50]</span>
                    </div>
                    
                    <div class="form-row">
                        <label>차량번호:</label>
                        <span class="car-number">12가1234</span>
                    </div>
                    
                    <div class="form-group">
                        <label class="form-label">고객유형</label>
                        <select class="form-select" name="customerType">
                            <option value="visit">방문고객</option>
                            <option value="resident">입주민</option>
                            <option value="delivery">배송</option>
                            <option value="etc">기타</option>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label class="form-label">방문목적</label>
                        <select class="form-select" name="visitPurpose">
                            <option value="visit">방문</option>
                            <option value="delivery">배송</option>
                            <option value="maintenance">유지보수</option>
                            <option value="etc">기타</option>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label class="form-label">동/호</label>
                        <div class="input-group">
                            <select class="form-select" name="dong">
                                <option>101</option>
                                <option>102</option>
                                <option>103</option>
                            </select>
                            <select class="form-select" name="ho">
                                <option>1002</option>
                                <option>1003</option>
                                <option>1004</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="form-label">상담내역</label>
                        <textarea 
                            class="form-textarea" 
                            name="consultationNote"
                            rows="4"
                            placeholder="상담 내용을 입력하세요"></textarea>
                    </div>
                </div>
            </div>
            
            <!-- 차단기 제어 -->
            <div class="gate-control">
                <h3 class="control-title">차단기 제어</h3>
                <div class="control-status">
                    <span class="status-indicator-circle open"></span>
                    <span>게이트 열림</span>
                    <span class="status-indicator-circle closed"></span>
                    <span>열림</span>
                </div>
                
                <div class="control-options">
                    <label class="radio-label">
                        <input type="radio" name="gateControl" value="open" checked>
                        <span>열림</span>
                    </label>
                    <label class="radio-label">
                        <input type="radio" name="gateControl" value="close">
                        <span>닫힘</span>
                    </label>
                </div>
                
                <button class="btn-control" onclick="controlGate()">차량</button>
            </div>
        </aside>
    </main>
    
    <!-- 하단 상태 바들 -->
    <div class="bottom-status-bars">
        <div class="status-bar-item status-normal">
            <span>방문차단이력</span>
            <span class="status-badge">정상</span>
        </div>
        <div class="status-bar-item status-normal">
            <span>VOC 이력</span>
            <span class="status-badge">정상</span>
        </div>
        <div class="status-bar-item status-normal">
            <span>수동제어이력</span>
            <span class="status-badge">정상</span>
        </div>
        <div class="status-bar-item status-normal">
            <span>검지차 자동열림</span>
            <span class="status-badge">정상</span>
        </div>
        <div class="status-bar-item status-error">
            <span>컨피그</span>
            <span class="status-badge">폐쇄</span>
        </div>
        <div class="status-bar-item status-normal">
            <span>검지차 자동열림2</span>
            <span class="status-badge">정상</span>
        </div>
    </div>
    
    <!-- PBX 콜 알림 -->
    <div class="pbx-notification" id="pbxNotification">
        <div class="pbx-header">
            <strong>PBX 콜: 동아 아파트</strong>
            <button class="btn-close-pbx" onclick="closePbxNotification()">×</button>
        </div>
        <div class="pbx-body">
            수신 중...
        </div>
    </div>
    
    <!-- Footer -->
    <div th:replace="~{fragments/footer :: footer}"></div>
    
    <script th:src="@{/js/dashboard-worker.js}"></script>
</body>
</html>
```

### CSS 클래스 정의
```css
/* 대시보드 페이지 레이아웃 */
.dashboard-page {
    display: flex;
    flex-direction: column;
    height: 100vh;
    background: var(--bg-gray-light);
}

.dashboard-container {
    display: grid;
    grid-template-columns: 250px 1fr 400px;
    gap: var(--spacing-4);
    padding: var(--spacing-4);
    flex: 1;
    overflow: hidden;
}

/* 좌측 아파트 사이드바 */
.apartment-sidebar {
    background: var(--bg-white);
    border-radius: 8px;
    padding: var(--spacing-4);
    display: flex;
    flex-direction: column;
    overflow: hidden;
}

.sidebar-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: var(--spacing-4);
    padding-bottom: var(--spacing-3);
    border-bottom: 2px solid var(--border-light);
}

.sidebar-title {
    font-size: var(--text-base);
    font-weight: 600;
}

.btn-search {
    background: var(--primary-blue);
    color: var(--bg-white);
    border: none;
    padding: var(--spacing-2) var(--spacing-4);
    border-radius: 4px;
    font-size: var(--text-sm);
    cursor: pointer;
    transition: background 0.3s;
}

.btn-search:hover {
    background: #0e7490;
}

/* 아파트 목록 */
.apartment-list {
    list-style: none;
    flex: 1;
    overflow-y: auto;
    margin-bottom: var(--spacing-3);
}

.apartment-item {
    display: flex;
    align-items: center;
    gap: var(--spacing-3);
    padding: var(--spacing-3);
    border-bottom: 1px solid var(--border-light);
    cursor: pointer;
    transition: all 0.2s;
}

.apartment-item:hover {
    background: var(--bg-gray-light);
}

.apartment-item.selected {
    background: #e0f2fe;
    border-left: 4px solid var(--primary-blue);
}

.status-indicator {
    width: 12px;
    height: 12px;
    border-radius: 50%;
    background: var(--text-secondary);
    flex-shrink: 0;
}

.status-indicator.active {
    background: var(--primary-blue);
}

.apt-info {
    display: flex;
    flex-direction: column;
    gap: var(--spacing-1);
}

.apt-code {
    font-size: var(--text-xs);
    color: var(--text-secondary);
}

.apt-name {
    font-size: var(--text-sm);
    font-weight: 500;
}

/* 페이지네이션 */
.pagination {
    display: flex;
    justify-content: center;
    gap: var(--spacing-2);
    padding-top: var(--spacing-3);
    border-top: 1px solid var(--border-light);
}

.btn-page {
    padding: var(--spacing-1) var(--spacing-2);
    border: 1px solid var(--border-default);
    background: var(--bg-white);
    border-radius: 4px;
    cursor: pointer;
    font-size: var(--text-sm);
    transition: all 0.2s;
}

.btn-page:hover {
    background: var(--bg-gray-light);
}

.btn-page.active {
    background: var(--primary-blue);
    color: var(--bg-white);
    border-color: var(--primary-blue);
}

/* 중앙 콘텐츠 */
.center-content {
    display: flex;
    flex-direction: column;
    gap: var(--spacing-4);
    overflow-y: auto;
}

/* 상태 표시줄 */
.status-bar {
    display: grid;
    grid-template-columns: repeat(6, 1fr);
    gap: var(--spacing-3);
    background: var(--bg-white);
    border-radius: 8px;
    padding: var(--spacing-4);
}

.status-item {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: var(--spacing-2);
    padding: var(--spacing-3);
    background: var(--bg-gray-light);
    border-radius: 6px;
}

.status-label {
    font-size: var(--text-xs);
    color: var(--text-secondary);
}

.status-value {
    font-size: var(--text-lg);
    font-weight: 700;
    color: var(--text-primary);
}

.status-value.status-normal {
    color: var(--status-success);
}

.status-value.status-warning {
    color: var(--status-warning);
}

/* 뷰어 컨테이너 */
.viewer-container {
    background: var(--bg-white);
    border-radius: 8px;
    padding: var(--spacing-4);
}

.viewer-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: var(--spacing-3);
}

.viewer-header h3 {
    font-size: var(--text-base);
    font-weight: 600;
}

.viewer-size {
    font-size: var(--text-xs);
    color: var(--text-secondary);
}

.cctv-viewer,
.incar-viewer {
    background: #000;
    border-radius: 6px;
    height: 300px;
    display: flex;
    align-items: center;
    justify-content: center;
}

.viewer-placeholder {
    color: var(--bg-white);
    font-size: var(--text-sm);
    text-align: center;
}

/* 우측 상담 사이드바 */
.consultation-sidebar {
    background: var(--bg-white);
    border-radius: 8px;
    padding: var(--spacing-4);
    display: flex;
    flex-direction: column;
    gap: var(--spacing-4);
    overflow-y: auto;
}

/* 상담 이력 */
.consultation-history {
    border: 1px solid var(--border-light);
    border-radius: 6px;
    padding: var(--spacing-3);
}

.history-title {
    font-size: var(--text-base);
    font-weight: 600;
    margin-bottom: var(--spacing-3);
}

.history-list {
    max-height: 200px;
    overflow-y: auto;
}

.history-item {
    padding: var(--spacing-2);
    border-bottom: 1px solid var(--border-light);
    margin-bottom: var(--spacing-2);
}

.history-item:last-child {
    border-bottom: none;
    margin-bottom: 0;
}

.history-time {
    display: block;
    font-size: var(--text-xs);
    color: var(--text-secondary);
    margin-bottom: var(--spacing-1);
}

.history-content {
    font-size: var(--text-sm);
}

/* 상담 폼 */
.consultation-form {
    border: 1px solid var(--border-light);
    border-radius: 6px;
    padding: var(--spacing-3);
}

.form-title {
    font-size: var(--text-base);
    font-weight: 600;
    margin-bottom: var(--spacing-3);
}

.consultation-info {
    display: flex;
    flex-direction: column;
    gap: var(--spacing-3);
}

.info-header {
    display: flex;
    justify-content: space-between;
    padding: var(--spacing-2);
    background: var(--bg-gray-light);
    border-radius: 4px;
    font-size: var(--text-sm);
}

.form-row {
    display: flex;
    gap: var(--spacing-2);
    align-items: center;
    font-size: var(--text-sm);
}

.car-number {
    font-weight: 600;
    color: var(--primary-blue);
}

.form-group {
    display: flex;
    flex-direction: column;
    gap: var(--spacing-2);
}

.form-label {
    font-size: var(--text-sm);
    font-weight: 500;
    color: var(--text-primary);
}

.form-select {
    padding: var(--spacing-2) var(--spacing-3);
    border: 1px solid var(--border-default);
    border-radius: 4px;
    font-size: var(--text-sm);
    background: var(--bg-white);
}

.input-group {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: var(--spacing-2);
}

.form-textarea {
    padding: var(--spacing-2) var(--spacing-3);
    border: 1px solid var(--border-default);
    border-radius: 4px;
    font-size: var(--text-sm);
    font-family: inherit;
    resize: vertical;
}

/* 차단기 제어 */
.gate-control {
    border: 2px solid var(--primary-red);
    border-radius: 8px;
    padding: var(--spacing-4);
    margin-top: auto;
}

.control-title {
    font-size: var(--text-base);
    font-weight: 600;
    color: var(--primary-red);
    margin-bottom: var(--spacing-3);
}

.control-status {
    display: flex;
    align-items: center;
    gap: var(--spacing-2);
    margin-bottom: var(--spacing-3);
    font-size: var(--text-sm);
}

.status-indicator-circle {
    width: 16px;
    height: 16px;
    border-radius: 50%;
    border: 2px solid var(--text-secondary);
}

.status-indicator-circle.open {
    background: transparent;
}

.status-indicator-circle.closed {
    background: var(--text-primary);
}

.control-options {
    display: flex;
    gap: var(--spacing-4);
    margin-bottom: var(--spacing-3);
}

.radio-label {
    display: flex;
    align-items: center;
    gap: var(--spacing-2);
    cursor: pointer;
    font-size: var(--text-sm);
}

.radio-label input[type="radio"] {
    width: 18px;
    height: 18px;
    cursor: pointer;
}

.btn-control {
    width: 100%;
    padding: var(--spacing-3);
    background: var(--status-error);
    color: var(--bg-white);
    border: none;
    border-radius: 6px;
    font-size: var(--text-base);
    font-weight: 600;
    cursor: pointer;
    transition: background 0.3s;
}

.btn-control:hover {
    background: #dc2626;
}

/* 하단 상태 바들 */
.bottom-status-bars {
    display: grid;
    grid-template-columns: repeat(6, 1fr);
    gap: var(--spacing-3);
    padding: var(--spacing-3) var(--spacing-4);
    background: var(--bg-white);
    border-top: 1px solid var(--border-light);
}

.status-bar-item {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: var(--spacing-2);
    padding: var(--spacing-2);
    border-radius: 6px;
    font-size: var(--text-sm);
}

.status-bar-item.status-normal {
    background: #d1fae5;
}

.status-bar-item.status-error {
    background: #fee2e2;
}

.status-badge {
    font-weight: 600;
    font-size: var(--text-xs);
}

.status-normal .status-badge {
    color: var(--status-success);
}

.status-error .status-badge {
    color: var(--status-error);
}

/* PBX 알림 */
.pbx-notification {
    position: fixed;
    bottom: 100px;
    right: var(--spacing-6);
    width: 280px;
    background: var(--bg-white);
    border: 2px solid var(--primary-blue);
    border-radius: 8px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
    z-index: 1000;
}

.pbx-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: var(--spacing-3);
    background: var(--primary-blue);
    color: var(--bg-white);
    border-radius: 6px 6px 0 0;
}

.btn-close-pbx {
    background: none;
    border: none;
    color: var(--bg-white);
    font-size: var(--text-xl);
    cursor: pointer;
    line-height: 1;
    padding: 0;
}

.pbx-body {
    padding: var(--spacing-4);
    font-size: var(--text-sm);
}

/* 모바일 반응형 */
@media (max-width: 1400px) {
    .dashboard-container {
        grid-template-columns: 200px 1fr 350px;
    }
}

@media (max-width: 1024px) {
    .dashboard-container {
        grid-template-columns: 1fr;
        grid-template-rows: auto;
    }
    
    .apartment-sidebar,
    .consultation-sidebar {
        max-height: 400px;
    }
    
    .bottom-status-bars {
        grid-template-columns: repeat(3, 1fr);
    }
}

@media (max-width: 768px) {
    .status-bar {
        grid-template-columns: repeat(3, 1fr);
    }
    
    .bottom-status-bars {
        grid-template-columns: repeat(2, 1fr);
    }
}
```

### JavaScript 기능
```javascript
// src/main/resources/static/js/dashboard-worker.js

// 아파트 검색
function searchApartments() {
    const searchTerm = prompt('아파트명 또는 코드를 입력하세요:');
    if (searchTerm) {
        // AJAX로 검색 수행
        console.log('Searching for:', searchTerm);
        // 실제 구현에서는 서버로 요청
    }
}

// 아파트 선택
document.addEventListener('DOMContentLoaded', function() {
    const apartmentItems = document.querySelectorAll('.apartment-item');
    
    apartmentItems.forEach(item => {
        item.addEventListener('click', function() {
            // 이전 선택 제거
            apartmentItems.forEach(i => i.classList.remove('selected'));
            
            // 현재 항목 선택
            this.classList.add('selected');
            
            // 선택된 아파트 정보 로드
            const aptId = this.dataset.id;
            loadApartmentDetails(aptId);
        });
    });
});

// 아파트 상세 정보 로드
function loadApartmentDetails(aptId) {
    console.log('Loading details for apartment:', aptId);
    // AJAX로 상세 정보 가져오기
    // fetch(`/api/apartments/${aptId}`)
    //     .then(response => response.json())
    //     .then(data => {
    //         // CCTV 스트림 업데이트
    //         // 상담 이력 업데이트
    //         // 등등...
    //     });
}

// 차단기 제어
function controlGate() {
    const selectedOption = document.querySelector('input[name="gateControl"]:checked').value;
    
    if (confirm(`차단기를 ${selectedOption === 'open' ? '열기' : '닫기'} 하시겠습니까?`)) {
        console.log('Gate control:', selectedOption);
        
        // AJAX로 차단기 제어 명령 전송
        // fetch('/api/gate/control', {
        //     method: 'POST',
        //     headers: { 'Content-Type': 'application/json' },
        //     body: JSON.stringify({
        //         action: selectedOption,
        //         apartmentId: getSelectedApartmentId()
        //     })
        // })
        // .then(response => response.json())
        // .then(data => {
        //     alert('차단기 제어 완료');
        // });
    }
}

// PBX 알림 닫기
function closePbxNotification() {
    document.getElementById('pbxNotification').style.display = 'none';
}

// 실시간 데이터 업데이트 (WebSocket 또는 SSE)
function initializeRealTimeUpdates() {
    // WebSocket 연결 예시
    // const ws = new WebSocket('ws://localhost:8080/ws/dashboard');
    // 
    // ws.onmessage = function(event) {
    //     const data = JSON.parse(event.data);
    //     updateDashboard(data);
    // };
}

// 대시보드 업데이트
function updateDashboard(data) {
    // 상태 표시줄 업데이트
    if (data.status) {
        updateStatusBar(data.status);
    }
    
    // CCTV 스트림 업데이트
    if (data.cctvStream) {
        updateCCTV(data.cctvStream);
    }
    
    // 입차 이미지 업데이트
    if (data.incarImage) {
        updateIncarImage(data.incarImage);
    }
}

// 페이지 로드 시 초기화
document.addEventListener('DOMContentLoaded', function() {
    initializeRealTimeUpdates();
});
```

---

## 🏢 화면 5: APT 관리 - 아파트 단지 관리 (AptManage.html)

### 레이아웃 구조
```
┌─────────────────────────────────────────────────────────────────────────────┐
│ BI -> APT관리 -> 아파트 단지 관리  Main APT관리 VOC관리 시스템관리 PBX        │
├─────────────┬───────────────────────────────────────────────────────────────┤
│  아파트명   │  아파트  [아파트2      ▼] [추정]                               │
│  [조회]     │  아파트 코드  [A12345678] [중복확인]                           │
│             │  권역        [수도권 ▼]                                         │
│ □ 아파트코드 │  관리소명    [○ ○ 관리사무소]                                │
│             │  담당자명    [홍길동]                                           │
│ ☑ A1001234  │  연락처      [00-0000-0000]                                    │
│   삼성1단지  │  업업담당자  [김영업]                                           │
│             │  업업담당자 연락처  [00-0000-0000]                             │
│ □ A1001235  │  우편번호    [12345]      [찾기]                               │
│   삼성2단지  │  주소        [                    ]                             │
│             │  메모        [                    ]                             │
│ □ ...       │                                                                 │
│             │  업무 시작 시간    [18:00]                    [기본값]         │
│ ◁ 1 2 3 4 5 ▷│  업무 종료 시간    [09:00]                                     │
│             │  차단기              [정상 운영 ▼]                              │
│             │  블랙 리스트      [입차 제한 ▼]                                │
│             │  화이트 리스트    [자동 입차 ▼]                                │
│             │  주기적 방문차량  [10 ▼] 일 이내 [1 ▼]회 이상 입차            │
│             │                   [자동 입차 ▼]                                │
│             │                                        [저장]                   │
└─────────────┴───────────────────────────────────────────────────────────────┘
```

_이 화면은 아파트 단지의 기본 정보, 운영 시간, 차량 출입 정책을 관리합니다._

**핵심 기능:**
- 아파트 코드 중복 확인
- 우편번호 검색 (다음/카카오 주소 API 연동)
- 블랙리스트/화이트리스트 정책 설정
- 주기적 방문차량 자동 인식 설정

---

## 🚗 화면 6: APT 관리 - 입/출구 관제기 (LprDevice.html)

### 레이아웃 구조
```
┌─────────────────────────────────────────────────────────────────────────────┐
│ BI -> APT관리 -> 입/출구 관제기  Main APT관리 VOC관리 시스템관리 PBX          │
├─────────────┬───────────────────────────────────────────────────────────────┤
│  아파트명   │ ┌──────────────────────────────────────────────────┐         │
│  [조회]     │ │관제종│장비코드│장비종│장비명│장비URL│Voip│RTSP주소│사용여부│ │
│             │ ├──────────────────────────────────────────────────┤         │
│ □ 아파트코드 │ │삼성2단지│PK030│입구LPR│정문입구│test...│010...│rtsp...│Y│ │
│             │ │삼성2단지│PK030│출구LPR│정문출구│test...│010...│       │Y│ │
│ ☑ A1001234  │ └──────────────────────────────────────────────────┘         │
│   삼성1단지  │            ◁ 1 2 3 4 5 ▷                                     │
│             │                                                                 │
│ □ A1001235  │  아파트명    [삼성2단지]                                        │
│   삼성2단지  │  업태도록    [입구LPR ▼]                                       │
│             │  장비명      [정문입구]                                         │
│ □ ...       │  장비URL     [test.iptime.org:1234]                            │
│             │  Voip        [010-1234-5678]                                   │
│ ◁ 1 2 3 4 5 ▷│  RTSP주소    [rtsp://test.iptime.org]                         │
│             │              [9654/profile2]                                    │
│             │  사용여부    ● 사용      ○미사용                               │
│             │  [저장]  [초기화]                                               │
└─────────────┴───────────────────────────────────────────────────────────────┘
```

_LPR(License Plate Recognition) 장비와 CCTV 정보를 관리하는 화면입니다._

**핵심 기능:**
- LPR 장비 등록/수정/삭제
- RTSP 스트림 주소 설정
- VoIP 연동 설정

---

## 🚙 화면 7: APT 관리 - 차량관리 (CarManage.html)

### 레이아웃 구조
```
┌─────────────────────────────────────────────────────────────────────────────┐
│ BI -> APT관리 -> 차량관리  Main APT관리 VOC관리 시스템관리 PBX                │
├─────────────┬───────────────────────────────────────────────────────────────┤
│  아파트명   │ ┌──────────────────────────────────────────────┐             │
│  [조회]     │ │아파트명│구분│고객명│차량번호│연락처│시작│종료│사용여부│     │
│             │ ├──────────────────────────────────────────────┤             │
│ ☑ A1001234  │ │삼성2단지│블랙│홍길동│12가1111│010..│YY-MM│YY-MM│Y│       │
│   삼성1단지  │ │삼성2단지│화이트│홍이름│12가2222│010..│YY-MM│YY-MM│Y│     │
│             │ │삼성2단지│정기방문│김정기│12가3333│010..│YY-MM│YY-MM│Y│   │
│ □ A1001235  │ └──────────────────────────────────────────────┘             │
│   삼성2단지  │            ◁ 1 2 3 4 5 ▷                                     │
│             │                                                                 │
│ □ ...       │  아파트명    [삼성2단지]                                        │
│             │  구분        [블랙 ▼]                                           │
│ ◁ 1 2 3 4 5 ▷│  고객명      [홍길동]                                          │
│             │  차량번호    [12가1111]                                         │
│             │  연락처      [010-123-4567]                                     │
│             │  유효기간    [YYYY-MM-DD 돌]                                   │
│             │              [YYYY-MM-DD 돌]                                   │
│             │  사용여부    ● 사용      ○미사용                               │
│             │  [저장]  [초기화]                                               │
└─────────────┴───────────────────────────────────────────────────────────────┘
```

_블랙리스트, 화이트리스트, 정기 방문 차량을 관리하는 화면입니다._

**핵심 기능:**
- 차량 구분별 관리 (블랙/화이트/정기방문)
- 유효기간 설정
- 일괄 등록/삭제 기능

---

## 📋 화면 8: VOC 관리 - VOC 이력 (Voc.html)

### 레이아웃 구조
```
┌─────────────────────────────────────────────────────────────────────────────┐
│ BI -> VOC관리 -> VOC 이력  Main APT관리 VOC관리 시스템관리 PBX               │
├─────────────┬───────────────────────────────────────────────────────────────┤
│  아파트명   │ [조회기간] [2025-10-21 ▼] [2025-10-21 ▼]                     │
│  [조회]     │ [고객구분] [전체 ▼] [차량번호] [조회]                          │
│             │                                                                 │
│ □ 아파트코드 │ ┌──────────────────────────────────────────────────┐         │
│   전체      │ │조회기간│고객구분│상담구분│등록일시│차량번호│상담내역│호│콜│  │
│             │ ├──────────────────────────────────────────────────┤         │
│ ☑ A1001234  │ │정기전│정기관리인식│입각│YY-MM-DD hh:mm:ss│22ㄷ1234│정기관리...│ │
│   삼성1단지  │ │정기전│정기관리인식│입각│YY-MM-DD hh:mm:ss│22ㄷ1234│정기관리...│ │
│             │ │방문고객│방문│방문│YY-MM-DD hh:mm:ss│22ㄷ1333│미등록방문│   │
│ ○ A1001235  │ └──────────────────────────────────────────────────┘         │
│   삼성2단지  │            ◁ 1 2 3 4 5 ▷                                     │
│             │                                                                 │
│ ○ ...       │                                                                 │
│             │                                                                 │
│ ◁ 1 2 3 4 5 ▷│                                                                │
└─────────────┴───────────────────────────────────────────────────────────────┘
```

_고객 상담 이력을 조회하고 관리하는 화면입니다._

**핵심 기능:**
- 기간별, 아파트별, 차량번호별 검색
- 고객구분별 필터링 (입주민/방문/정기방문 등)
- 상담 내역 상세 조회

---

## 🚗 화면 9: VOC 관리 - 입출차 이력 (InOutCar.html)

### 레이아웃 구조
```
┌─────────────────────────────────────────────────────────────────────────────┐
│ BI -> VOC관리 -> 입출차 이력  Main APT관리 VOC관리 시스템관리 PBX            │
├─────────────┬───────────────────────────────────────────────────────────────┤
│  아파트명   │ [조회기간] [2025-10-21 ▼] [2025-10-21 ▼]                     │
│  [조회]     │ [고객구분] [전체 ▼] [차량번호] [조회]                          │
│             │                                                                 │
│ ☑ A1001234  │ ┌──────────────────────────────────────────────────────┐     │
│   삼성1단지  │ │조회기간│차량번호│입차일시│출차일시│차량번호│호│고객구분│상태│ │
│             │ ├──────────────────────────────────────────────────────┤     │
│ ○ A1001235  │ │12가1234│YY-MM-DD hh:mm:ss│YY-MM-DD hh:mm:ss│120│701│1203│...│ │
│   삼성2단지  │ │12가1235│YY-MM-DD hh:mm:ss│YY-MM-DD hh:mm:ss│120│701│1203│...│ │
│             │ └──────────────────────────────────────────────────────┘     │
│ ○ ...       │            ◁ 1 2 3 4 5 ▷                                     │
│             │                                                                 │
│ ◁ 1 2 3 4 5 ▷│                                                                │
└─────────────┴───────────────────────────────────────────────────────────────┘
```

_차량의 입출차 기록을 조회하는 화면입니다._

**핵심 기능:**
- 기간별 입출차 이력 조회
- 차량번호 검색
- Excel 다운로드 기능

---

## 🚧 화면 10: VOC 관리 - 차단기 수동제어 이력 (Control.html)

### 레이아웃 구조
```
┌─────────────────────────────────────────────────────────────────────────────┐
│ BI -> VOC관리 -> 차단기 수동제어 이력  Main APT관리 VOC관리 시스템관리 PBX   │
├─────────────┬───────────────────────────────────────────────────────────────┤
│  아파트명   │ [조회기간] [2025-10-21 ▼] [2025-10-21 ▼]                     │
│  [조회]     │ [차량번호] [조회]                                               │
│             │                                                                 │
│ ☑ A1001234  │ ┌──────────────────────────────────────────────┐             │
│   삼성1단지  │ │처리일시│사유│차량번호│상태│장비명│처리자│              │
│             │ ├──────────────────────────────────────────────┤             │
│ ○ A1001235  │ │YY-MM-DD hh:mm:ss│정기간 미인식│12가1234│열림│정문입구│홍길동│ │
│   삼성2단지  │ │YY-MM-DD hh:mm:ss│미등록 방문│12가1235│열림│후문입구│홍길동│ │
│             │ └──────────────────────────────────────────────┘             │
│ ○ ...       │            ◁ 1 2 3 4 5 ▷                                     │
│             │                                                                 │
│ ◁ 1 2 3 4 5 ▷│                                                                │
└─────────────┴───────────────────────────────────────────────────────────────┘
```

_수동으로 차단기를 제어한 이력을 조회하는 화면입니다._

**핵심 기능:**
- 수동 제어 이력 조회
- 제어 사유 확인
- 처리자별 필터링

---

## 👥 화면 11: 시스템 관리 - 사용자 관리 (User.html)

### 레이아웃 구조
```
┌─────────────────────────────────────────────────────────────────────────────┐
│ BI -> 시스템관리 -> 사용자 관리  Main APT관리 VOC관리 시스템관리 PBX         │
├─────────────┬───────────────────────────────────────────────────────────────┤
│  아파트명   │ ┌────────────────────────────────────────────┐               │
│  [조회]     │ │아파트명│관리자ID│관리자명│권한유형│연락처│사용여부│등록자ID│ │
│             │ ├────────────────────────────────────────────┤               │
│ ☑ A1001234  │ │삼성1단지│man01│홍길동│관리자│010-123-4567│Y│admin│       │
│   삼성1단지  │ │삼성1단지│ppp01│홍길동│경비실│010-123-4567│Y│admin│       │
│             │ │삼성1단지│call01│홍길순│상담사│010-123-4567│Y│admin│       │
│ ○ A1001235  │ └────────────────────────────────────────────┘               │
│   삼성2단지  │            ◁ 1 2 3 4 5 ▷                                     │
│             │                                                                 │
│ ○ ...       │  아파트명    [삼성2단지]                                        │
│             │  관리자명    [홍길동]                                           │
│ ◁ 1 2 3 4 5 ▷│  관리자ID    [man01] [중복]                                   │
│             │  비밀번호    [*****]                                            │
│             │  비밀번호확인 [*****]                                          │
│             │  권한유형    [관리자 ▼]                                         │
│             │  연락처      [010-123-4567]                                     │
│             │  사용여부    ● 사용      ○미사용                               │
│             │  [저장]  [초기화]                                               │
└─────────────┴───────────────────────────────────────────────────────────────┘
```

_시스템 사용자 계정을 관리하는 화면입니다._

**핵심 기능:**
- 사용자 계정 생성/수정/삭제
- 권한 설정 (관리자/경비실/상담사)
- 아이디 중복 체크
- 비밀번호 암호화 저장

---

## 🔧 화면 12: 시스템 관리 - 공통코드 관리 (Code.html)

### 레이아웃 구조
```
┌─────────────────────────────────────────────────────────────────────────────┐
│ BI -> 시스템관리 -> 공통코드 관리  Main APT관리 VOC관리 시스템관리 PBX       │
├─────────────┬─────────────────┬───────────────────────────────────────────┤
│  아파트명   │  코드명  [조회] │  구분       [고객구분 ▼]                   │
│  [조회]     │                 │  한글코드값 [일반]                          │
│             │  순번│구분│한글 │  코드       [300]                           │
│ ☑ 아파트코드 │   1 │등급│정상 │  비고       [일반차량]                      │
│   전체      │   2 │등급│필수한│                                             │
│             │   3 │등급│전문유│  사용여부   ● 사용      ○미사용            │
│ □ A1000000  │   4 │등급│코드│                                              │
│   전체      │   5 │등급│데이터│  [저장]  [초기화]                           │
│             │  ... │    │     │                                             │
│             │   9 │Call│정상 │                                             │
│             │  10 │Call│작감중│                                             │
│ ◁ 1 2 3 4 5 ▷│  11 │Call│오류│                                             │
│             │  12 │고객│일반 │                                             │
│             │  13 │고객│입주민│                                             │
└─────────────┴─────────────────┴───────────────────────────────────────────┘
```

_시스템에서 사용하는 공통 코드를 관리하는 화면입니다._

**핵심 기능:**
- 코드 그룹별 관리
- 코드 추가/수정/삭제
- 순서 변경
- 사용 여부 관리

---

## ⚙️ 화면 13: 시스템 관리 - 환경설정 (Config.html)

### 레이아웃 구조
```
┌─────────────────────────────────────────────────────────────────────────────┐
│ BI -> 시스템관리 -> 환경설정  Main APT관리 VOC관리 시스템관리 PBX            │
├─────────────┬───────────────────────────────────────────────────────────────┤
│  아파트명   │                                                                 │
│  [조회]     │  텍스체크 주기시간    [3 ▼] 분                                 │
│             │  장애시 주기시간      [1 ▼] 분                                 │
│ ☑ 아파트코드 │  ☑ 텍스체크 장애 장출 차단기 자동 열림                        │
│   전체      │                                                                 │
│             │  콜센터 운영 상태    [정상 ▼]                                  │
│ □ A1000000  │                                                                 │
│   전체      │                                                                 │
│             │  상단환용     http://parkingon.tooz.co.kr:9030                 │
│             │  Standby     http://standby.tooz.co.kr:9031                    │
│ ◁ 1 2 3 4 5 ▷│  PBX 인터페이스 http://pbx.tooz.co.kr:9033                    │
│             │  아파트너    http://aptner.tooz.co.kr:9034                     │
│             │  DB 서버     dbms.tooz.co.kr:9040                              │
│             │  기존시스템  http://legacy.tooz.co.kr:9050                     │
│             │  Rtsp 서버   (비어있음)                                         │
│             │  ftp 서버    ftp://parkingon.tooz.co.kr:9800                   │
│             │                                                                 │
│             │                                                  [저장]         │
└─────────────┴───────────────────────────────────────────────────────────────┘
```

_시스템 전반의 환경 설정을 관리하는 화면입니다._

**핵심 기능:**
- 헬스체크 주기 설정
- 자동 열림 정책 설정
- 외부 시스템 연동 URL 설정
- 서버 주소 관리

---

## 📢 화면 14: 시스템 관리 - 공지사항 (Notify.html)

### 레이아웃 구조
```
┌─────────────────────────────────────────────────────────────────────────────┐
│ BI -> 시스템관리 -> 공지사항  Main APT관리 VOC관리 시스템관리 PBX            │
├─────────────┬───────────────────────────────────────────────────────────────┤
│  아파트명   │ [검색] [조회]                                                  │
│  [조회]     │                                                                 │
│             │ ┌──────────────────────────────────────────────┐             │
│ ☑ 아파트코드 │ │순번│발송시간│수신자│발송│수신인│공지입│제목│          │
│   전체      │ ├──────────────────────────────────────────────┤             │
│             │ │1│YYY-MM-DD│236│236│81│V│통합서버 관리점검│             │
│ □ A1000000  │ │2│YYY-MM-DD│236│236│81│V│정전 작업│                    │
│   전체      │ └──────────────────────────────────────────────┘             │
│             │            ◁ 1 2 3 4 5 ▷                                     │
│             │                                                                 │
│ ◁ 1 2 3 4 5 ▷│  일련번호    [3]                                              │
│             │  아파트      [전체공지 ▼]                                      │
│             │  수신자      [전체공지 ▼]                                      │
│             │  제목        [통합테스트]                                       │
│             │  공지내용    ┌─────────────────────────┐                     │
│             │              │                         │                     │
│             │              │                         │                     │
│             │              └─────────────────────────┘                     │
│             │  알림방식    ● 팝업공지    ○도착알림만                        │
│             │  이미지      c:\test.jpg  [파일 등록]                         │
│             │              [저장(팝업)]  [조기화]                            │
└─────────────┴───────────────────────────────────────────────────────────────┘
```

_관리자가 사용자에게 공지사항을 발송하는 화면입니다._

**핵심 기능:**
- 공지사항 작성/수정/삭제
- 수신 대상 선택 (전체/특정 아파트/특정 사용자)
- 팝업 형식 또는 알림 형식 선택
- 이미지 첨부 기능

---

## 🧩 공통 Fragments

### Header Fragment
```html
<!-- src/main/resources/templates/fragments/header.html -->
<div th:fragment="header(menuTitle)" xmlns:th="http://www.thymeleaf.org">
    <header class="app-header">
        <div class="header-left">
            <span class="brand">BI</span>
            <span class="arrow">→</span>
            <span class="menu-title" th:text="${menuTitle}">대시보드</span>
        </div>
        
        <nav class="header-nav">
            <a th:href="@{/}" class="nav-link">Main</a>
            <a th:href="@{/apt}" class="nav-link">APT관리</a>
            <a th:href="@{/voc}" class="nav-link">VOC관리</a>
            <a th:href="@{/system}" class="nav-link">시스템관리</a>
            <a th:href="@{/pbx}" class="nav-link">PBX</a>
        </nav>
        
        <div class="header-right">
            <span class="user-info" th:text="${#authentication.name}">관리자</span>
            <button class="btn-logout" th:onclick="|location.href='@{/logout}'|">로그아웃</button>
        </div>
    </header>
</div>
```

### Footer Fragment
```html
<!-- src/main/resources/templates/fragments/footer.html -->
<div th:fragment="footer" xmlns:th="http://www.thymeleaf.org">
    <footer class="app-footer">
        <div class="footer-info">
            <span>로그인명: <span th:text="${#authentication.name}">관리자</span></span>
            <span>로그인시간: <span th:text="${loginTime}">2025-10-20 09:00:00</span></span>
            <span>현재시간: <span id="currentTime">--:--:--</span></span>
        </div>
    </footer>
    
    <script>
        // 실시간 시계 업데이트
        function updateClock() {
            const now = new Date();
            const timeString = now.toLocaleTimeString('ko-KR');
            document.getElementById('currentTime').textContent = timeString;
        }
        
        updateClock();
        setInterval(updateClock, 1000);
    </script>
</div>
```

---

## 📦 공통 컴포넌트 CSS

```css
/* 공통 버튼 스타일 */
.btn-primary {
    padding: var(--spacing-3) var(--spacing-6);
    background: var(--primary-blue);
    color: var(--bg-white);
    border: none;
    border-radius: 6px;
    font-weight: 600;
    cursor: pointer;
    transition: background 0.3s;
}

.btn-primary:hover {
    background: #0e7490;
}

.btn-secondary {
    padding: var(--spacing-2) var(--spacing-4);
    background: var(--bg-gray);
    color: var(--text-primary);
    border: 1px solid var(--border-default);
    border-radius: 4px;
    cursor: pointer;
    transition: all 0.2s;
}

.btn-danger {
    background: var(--status-error);
    color: var(--bg-white);
}

/* 공통 폼 스타일 */
.form-horizontal {
    display: grid;
    grid-template-columns: 150px 1fr;
    gap: var(--spacing-3);
    align-items: center;
}

.form-vertical {
    display: flex;
    flex-direction: column;
    gap: var(--spacing-3);
}

/* 공통 카드 스타일 */
.card {
    background: var(--bg-white);
    border-radius: 8px;
    padding: var(--spacing-4);
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.card-header {
    padding-bottom: var(--spacing-3);
    border-bottom: 1px solid var(--border-light);
    margin-bottom: var(--spacing-4);
}

/* 공통 배지 스타일 */
.badge {
    display: inline-block;
    padding: var(--spacing-1) var(--spacing-2);
    border-radius: 4px;
    font-size: var(--text-xs);
    font-weight: 600;
}

.badge-success {
    background: #d1fae5;
    color: var(--status-success);
}

.badge-error {
    background: #fee2e2;
    color: var(--status-error);
}

.badge-warning {
    background: #fef3c7;
    color: var(--status-warning);
}

/* 공통 알림 스타일 */
.alert {
    padding: var(--spacing-4);
    border-radius: 6px;
    margin-bottom: var(--spacing-4);
}

.alert-success {
    background: #d1fae5;
    color: var(--status-success);
    border: 1px solid var(--status-success);
}

.alert-error {
    background: #fee2e2;
    color: var(--status-error);
    border: 1px solid var(--status-error);
}

/* 공통 모달 스타일 */
.modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    align-items: center;
    justify-content: center;
    z-index: 9999;
}

.modal-content {
    background: var(--bg-white);
    border-radius: 12px;
    padding: var(--spacing-6);
    max-width: 600px;
    width: 90%;
    max-height: 90vh;
    overflow-y: auto;
}
```

---

## 📝 마무리

### 구현 완료된 화면 목록

1. ✅ 로그인 (Login.html)
2. ✅ 공지사항 팝업 (NotifyPopup.html)
3. ✅ 대시보드 - 멀티스크린 (Main.html)
4. ✅ 대시보드 - 근무자용 (Dashboard-Worker.html)
5. ✅ APT 관리 - 아파트 단지 관리 (AptManage.html)
6. ✅ APT 관리 - 입/출구 관제기 (LprDevice.html)
7. ✅ APT 관리 - 차량관리 (CarManage.html)
8. ✅ VOC 관리 - VOC 이력 (Voc.html)
9. ✅ VOC 관리 - 입출차 이력 (InOutCar.html)
10. ✅ VOC 관리 - 차단기 수동제어 이력 (Control.html)
11. ✅ 시스템 관리 - 사용자 관리 (User.html)
12. ✅ 시스템 관리 - 공통코드 관리 (Code.html)
13. ✅ 시스템 관리 - 환경설정 (Config.html)
14. ✅ 시스템 관리 - 공지사항 (Notify.html)
15. ✅ Fragments (Header, Footer)
16. ✅ 공통 컴포넌트 CSS

### 다음 단계

이 명세서를 바탕으로:
1. **Spring Boot Controller** 작성
2. **Thymeleaf 템플릿** 실제 구현
3. **JavaScript 인터랙션** 완성
4. **백엔드 API** 연동
5. **테스트 및 디버깅**

### 주요 참고사항

- 모든 HTML은 Thymeleaf 문법 포함
- CSS는 Mobile-First 반응형
- JavaScript는 바닐라 JS (jQuery 미사용)
- 파일 경로는 Spring Boot 표준 구조 준수

**이 명세서가 완전한 웹 퍼블리싱의 청사진이 되기를 바랍니다!** 🎉
