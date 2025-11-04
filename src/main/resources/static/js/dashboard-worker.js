// Dashboard Worker JavaScript

document.addEventListener('DOMContentLoaded', function() {
    // 아파트 목록 클릭 이벤트
    const apartmentItems = document.querySelectorAll('.apartment-item');
    apartmentItems.forEach(item => {
        item.addEventListener('click', function() {
            // 모든 항목에서 selected 클래스 제거
            apartmentItems.forEach(i => i.classList.remove('selected'));
            // 클릭된 항목에 selected 클래스 추가
            this.classList.add('selected');

            // TODO: AJAX로 선택된 아파트 정보 로드
            const aptCode = this.querySelector('.apt-code').textContent;
            console.log('선택된 아파트:', aptCode);
        });
    });

    // 차단기 제어 버튼
    const gateControlBtn = document.querySelector('.btn-gate-action');
    if (gateControlBtn) {
        gateControlBtn.addEventListener('click', function() {
            const selectedGate = document.querySelector('input[name="gate"]:checked');
            if (selectedGate) {
                const action = selectedGate.value === 'open' ? '열기' : '닫기';
                if (confirm(`차단기를 ${action}하시겠습니까?`)) {
                    console.log('차단기 제어:', action);
                    // TODO: AJAX로 차단기 제어 요청
                }
            }
        });
    }

    // 조회 버튼
    const searchBtn = document.querySelector('.btn-search');
    if (searchBtn) {
        searchBtn.addEventListener('click', function() {
            console.log('아파트 조회 실행');
            // TODO: AJAX로 아파트 목록 조회
        });
    }
});
