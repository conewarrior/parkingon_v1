# Button & List Design Guide

이 문서는 UI 이미지 분석을 통해 추출한 버튼 및 리스트 컴포넌트의 디자인 가이드입니다.

## 📋 목차
1. [Button Components](#button-components)
2. [List Components](#list-components)
3. [Color Palette](#color-palette)

---

## 🔘 Button Components

### Primary Button

**용도**: 주요 액션, 제출, 확인 등

**스타일**:
```css
.btn-primary {
  background-color: #00B0FF;
  color: #FFFFFF;
  border: none;
  border-radius: 6px;
  padding: 10px 24px;
  font-size: 14px;
  font-weight: 600;
  min-height: 40px;
  cursor: pointer;
  transition: background-color 0.2s ease;
}

.btn-primary:hover {
  background-color: #0099E6;
}

.btn-primary:active {
  background-color: #0088CC;
}

.btn-primary:disabled {
  background-color: #B3E5FC;
  cursor: not-allowed;
  opacity: 0.6;
}
```

**Figma 속성**:
- Fill: #00B0FF
- Text: #FFFFFF, 14px, Semi-Bold (600)
- Corner Radius: 6px
- Padding: 10px 24px
- Min Height: 40px
- Shadow: none

---

### Secondary Button

**용도**: 보조 액션, 취소, 이전 등

**스타일**:
```css
.btn-secondary {
  background-color: #FFFFFF;
  color: #666666;
  border: 1px solid #D1D5DB;
  border-radius: 6px;
  padding: 10px 24px;
  font-size: 14px;
  font-weight: 500;
  min-height: 40px;
  cursor: pointer;
  transition: all 0.2s ease;
}

.btn-secondary:hover {
  background-color: #F9FAFB;
  border-color: #9CA3AF;
}

.btn-secondary:active {
  background-color: #F3F4F6;
  border-color: #6B7280;
}

.btn-secondary:disabled {
  background-color: #F9FAFB;
  color: #D1D5DB;
  cursor: not-allowed;
}
```

**Figma 속성**:
- Fill: #FFFFFF
- Text: #666666, 14px, Medium (500)
- Stroke: 1px, #D1D5DB
- Corner Radius: 6px
- Padding: 10px 24px
- Min Height: 40px
- Shadow: none

---

### Outline Button

**용도**: 덜 중요한 액션, 선택적 기능

**스타일**:
```css
.btn-outline {
  background-color: transparent;
  color: #00B0FF;
  border: 1px solid #00B0FF;
  border-radius: 6px;
  padding: 10px 24px;
  font-size: 14px;
  font-weight: 500;
  min-height: 40px;
  cursor: pointer;
  transition: all 0.2s ease;
}

.btn-outline:hover {
  background-color: #E3F5FF;
  border-color: #0099E6;
  color: #0099E6;
}

.btn-outline:active {
  background-color: #B3E5FC;
  border-color: #0088CC;
  color: #0088CC;
}

.btn-outline:disabled {
  background-color: transparent;
  border-color: #D1D5DB;
  color: #D1D5DB;
  cursor: not-allowed;
}
```

**Figma 속성**:
- Fill: Transparent (또는 #FFFFFF)
- Text: #00B0FF, 14px, Medium (500)
- Stroke: 1px, #00B0FF
- Corner Radius: 6px
- Padding: 10px 24px
- Min Height: 40px
- Shadow: none

---

### Button Size Variations

#### Large Button
```css
.btn-large {
  padding: 12px 32px;
  font-size: 16px;
  min-height: 48px;
  border-radius: 8px;
}
```

#### Small Button
```css
.btn-small {
  padding: 6px 16px;
  font-size: 13px;
  min-height: 32px;
  border-radius: 4px;
}
```

---

## 📝 List Components

### List Item (Default State)

**스타일**:
```css
.list-item {
  background-color: #FFFFFF;
  border: 1px solid #E5E7EB;
  border-radius: 8px;
  padding: 20px;
  margin-bottom: 12px;
  cursor: pointer;
  transition: all 0.2s ease;
}

.list-item:hover {
  background-color: #F9FAFB;
  border-color: #D1D5DB;
}
```

**Figma 속성**:
- Fill: #FFFFFF
- Stroke: 1px, #E5E7EB
- Corner Radius: 8px
- Padding: 20px
- Margin Bottom: 12px

---

### List Item (Selected/Active State)

**스타일**:
```css
.list-item.selected,
.list-item.active {
  background-color: #E0F7FF;
  border: 2px solid #00B0FF;
  box-shadow: 0 2px 8px rgba(0, 176, 255, 0.15);
}

.list-item.selected:hover,
.list-item.active:hover {
  background-color: #CCF2FF;
  border-color: #0099E6;
}
```

**Figma 속성**:
- Fill: #E0F7FF (밝은 파란색 배경)
- Stroke: 2px, #00B0FF
- Corner Radius: 8px
- Shadow: 0px 2px 8px rgba(0, 176, 255, 0.15)
- Padding: 20px

---

### Sidebar Menu Item (Default)

**스타일**:
```css
.sidebar-menu-item {
  background-color: transparent;
  color: #4B5563;
  padding: 12px 16px;
  border-radius: 8px;
  font-size: 14px;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.2s ease;
}

.sidebar-menu-item:hover {
  background-color: #F3F4F6;
  color: #1F2937;
}
```

**Figma 속性**:
- Fill: Transparent
- Text: #4B5563, 14px, Medium (500)
- Corner Radius: 8px
- Padding: 12px 16px

---

### Sidebar Menu Item (Selected/Active)

**스타일**:
```css
.sidebar-menu-item.selected,
.sidebar-menu-item.active {
  background-color: #00B0FF;
  color: #FFFFFF;
  font-weight: 600;
}

.sidebar-menu-item.selected:hover,
.sidebar-menu-item.active:hover {
  background-color: #0099E6;
}
```

**Figma 속성**:
- Fill: #00B0FF
- Text: #FFFFFF, 14px, Semi-Bold (600)
- Corner Radius: 8px
- Padding: 12px 16px

---

## 🎨 Color Palette

### Primary Colors
```css
--color-primary: #00B0FF;          /* 주요 액션, 선택된 상태 */
--color-primary-hover: #0099E6;    /* Primary Hover */
--color-primary-active: #0088CC;   /* Primary Active */
--color-primary-light: #E0F7FF;    /* 선택된 배경 */
--color-primary-lighter: #F0FBFF;  /* 매우 밝은 배경 */
```

### Neutral Colors
```css
--color-white: #FFFFFF;            /* 카드, 버튼 배경 */
--color-gray-50: #F9FAFB;          /* Hover 배경 */
--color-gray-100: #F3F4F6;         /* 비활성 배경 */
--color-gray-200: #E5E7EB;         /* 기본 테두리 */
--color-gray-300: #D1D5DB;         /* 강조 테두리 */
--color-gray-400: #9CA3AF;         /* 보조 요소 */
--color-gray-500: #6B7280;         /* 보조 텍스트 */
--color-gray-600: #4B5563;         /* 본문 텍스트 */
--color-gray-700: #374151;         /* 제목 텍스트 */
--color-gray-800: #1F2937;         /* 진한 텍스트 */
--color-gray-900: #111827;         /* 최고 강조 텍스트 */
```

### Semantic Colors
```css
--color-success: #10B981;          /* 성공, 완료 */
--color-warning: #F59E0B;          /* 경고 */
--color-error: #EF4444;            /* 에러, 삭제 */
--color-info: #3B82F6;             /* 정보 */
```

---

## 📐 Spacing & Sizing

### Border Radius
```css
--radius-sm: 4px;    /* 작은 요소 */
--radius-md: 6px;    /* 버튼, 입력 필드 */
--radius-lg: 8px;    /* 카드, 리스트 아이템 */
--radius-xl: 12px;   /* 큰 카드, 모달 */
```

### Button Heights
```css
--btn-height-sm: 32px;    /* Small */
--btn-height-md: 40px;    /* Medium (기본) */
--btn-height-lg: 48px;    /* Large */
```

### Padding
```css
--padding-btn-sm: 6px 16px;     /* Small Button */
--padding-btn-md: 10px 24px;    /* Medium Button */
--padding-btn-lg: 12px 32px;    /* Large Button */
--padding-list-item: 20px;      /* List Item */
--padding-menu-item: 12px 16px; /* Menu Item */
```

### Font Sizes
```css
--font-size-xs: 12px;
--font-size-sm: 13px;
--font-size-base: 14px;
--font-size-lg: 16px;
--font-size-xl: 18px;
```

### Font Weights
```css
--font-weight-normal: 400;
--font-weight-medium: 500;
--font-weight-semibold: 600;
--font-weight-bold: 700;
```

---

## 🎯 Implementation Guide

### HTML 예시

```html
<!-- Primary Button -->
<button class="btn-primary">텔플릿 적용</button>

<!-- Secondary Button -->
<button class="btn-secondary">메시지 작성 취소</button>

<!-- Outline Button -->
<button class="btn-outline">입주자 대표회의</button>

<!-- List Item -->
<div class="list-item">
  <h3>아파트 알림</h3>
  <p>내용...</p>
</div>

<!-- Selected List Item -->
<div class="list-item selected">
  <h3>선택된 항목</h3>
  <p>내용...</p>
</div>

<!-- Sidebar Menu -->
<nav class="sidebar">
  <div class="sidebar-menu-item">공지사항</div>
  <div class="sidebar-menu-item active">메시지 작성</div>
  <div class="sidebar-menu-item">발송 현황</div>
</nav>
```

---

## ✅ Design Checklist

구현 시 확인사항:

- [ ] Primary 버튼이 #00B0FF 색상을 사용하는가?
- [ ] 모든 버튼의 높이가 40px(또는 정의된 크기)인가?
- [ ] Border radius가 6px(버튼) / 8px(리스트)인가?
- [ ] 선택된 리스트 아이템이 밝은 파란색 배경(#E0F7FF)을 사용하는가?
- [ ] Hover 상태가 정의되어 있는가?
- [ ] 텍스트 색상 대비가 적절한가? (접근성)
- [ ] Disabled 상태가 명확하게 구분되는가?
- [ ] 터치 타겟 크기가 충분한가? (최소 40px)

---

**문서 버전**: 1.0  
**작성일**: 2025-11-05  
**기반**: UI 이미지 분석
