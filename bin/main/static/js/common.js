// Common JavaScript functions

// Placeholder functions to prevent errors
function selectAptFromData() {
    console.log('selectAptFromData called - not implemented yet');
}

function checkDuplicate() {
    alert('중복확인 기능은 서버 연동 후 구현됩니다.');
}

function checkUserId() {
    alert('아이디 중복확인 기능은 서버 연동 후 구현됩니다.');
}

function searchAddress() {
    alert('주소 검색 기능은 서버 연동 후 구현됩니다.');
}

// closePopup, showPrevNotify, showNextNotify는 notify-popup.js에 정의되어 있음

// 현재 페이지에 맞는 네비게이션 메뉴 활성화
document.addEventListener('DOMContentLoaded', function() {
    const currentPath = window.location.pathname;
    const navLinks = document.querySelectorAll('.nav-link');

    navLinks.forEach(link => {
        const href = link.getAttribute('href');

        // 정확한 경로 매칭
        if (currentPath === href) {
            link.classList.add('active');
        }
        // 하위 경로 매칭 (예: /apt, /voc, /system)
        else if (href !== '/' && currentPath.startsWith(href)) {
            link.classList.add('active');
        }
        // 대시보드 특별 처리
        else if ((currentPath === '/' || currentPath === '/dashboard') && href === '/') {
            link.classList.add('active');
        }
        // 근무자용 대시보드
        else if (currentPath === '/dashboard-worker' && href === '/dashboard-worker') {
            link.classList.add('active');
        }
    });
});

console.log('Common functions loaded');
