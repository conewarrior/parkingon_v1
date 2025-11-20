# Design V2 - Enhanced Visual Design

This folder contains an enhanced version of the ParkingOn UI with improved visual distinction and clarity.

## Color Scheme Changes

### Primary Color
- **Old**: Cyan/Turquoise (#00B0FF)
- **New**: Indigo/Purple (#6366F1)

### Color Palette
```css
--primary-cyan: #6366F1 (Indigo)
--primary-cyan-dark: #4F46E5
--primary-cyan-light: #818CF8
--primary-cyan-bg: #E0E7FF
--primary-cyan-lighter: #EEF2FF
```

### Border Colors (Enhanced)
```css
--border-light: #E2E8F0 (was #F3F4F6)
--border-default: #CBD5E1 (was #E5E7EB)
--border-strong: #94A3B8 (was #D1D5DB)
--border-dark: #64748B (was #9CA3AF)
```

## Table Enhancements

### Border Improvements
- Table headers: 2px solid borders → 3px solid borders
- Table cells: 1px borders → 2px borders
- Row dividers: More prominent with darker colors

## Button Icon Additions

All buttons now include appropriate SVG icons as prefixes:

### Dashboard Buttons
- **조회**: 🔍 Search icon
- **등록**: ➕ Plus icon
- **수정**: ✏️ Edit icon
- **삭제**: 🗑️ Delete/Trash icon
- **저장**: 💾 Save icon
- **취소**: ❌ Cancel icon

### Worker Dashboard Buttons
- **입출차 이력**: 🚗 Car icon
- **VOC 이력**: 💬 Message icon
- **수동제어 이력**: 🎮 Control icon

### Gate Control Buttons
- **열림**: ⬆️ Up arrow
- **닫힘**: ⬇️ Down arrow
- **열림고정**: 🔓 Unlock icon
- **열림고정해제**: 🔒 Lock icon
- **검지 자동열림**: ⚡ Lightning icon
- **검지 자동해제**: 🛑 Stop icon

## File Structure

```
design_v2/
├── templates/
│   ├── dashboard.html
│   ├── dashboard-worker.html
│   ├── login.html
│   └── index.html
├── static/
│   └── css/
│       ├── app.css (main color scheme)
│       ├── dashboard-worker.css
│       └── management.css
└── DESIGN_V2_README.md (this file)
```

## Implementation Notes

1. All colors use CSS variables for easy theming
2. Icons are inline SVG for better performance
3. Border weights increased for better visual separation
4. Hover states updated to match new color scheme
5. Focus states use new indigo color

## Usage

To apply this design:
1. Copy files from `design_v2/` to `src/main/resources/`
2. Restart the application
3. The new design will be applied automatically

## Revert to Original

The original files remain in `src/main/resources/` - simply don't copy over them.
