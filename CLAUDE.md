# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**Parking Management System (Design V2 Prototype)**

This is a Spring Boot-based parking management system for apartment complexes. This specific codebase is a **design prototype** running on **port 8081** (the original production version runs on port 8080). The prototype focuses on testing new UI/UX improvements before merging into the main system.

**Technology Stack:**
- Spring Boot 3.2.0
- Java 17
- Thymeleaf (server-side templating)
- Spring Security (authentication/authorization)
- Spring Data JPA + Hibernate
- H2 Database (in-memory for development)
- Gradle 8.5
- Spring Boot DevTools (hot reload)

## Essential Commands

### Build and Run

```bash
# Clean build and run server (recommended for first start or after major changes)
./gradlew clean bootRun

# Run server (quick start)
./gradlew bootRun

# Build without running
./gradlew build

# Clean build artifacts
./gradlew clean
```

### Server Management

**IMPORTANT**: This project can sometimes leave multiple Gradle processes running. Always check for and clean up orphaned processes:

```bash
# Kill all Gradle processes
pkill -9 -f "gradlew"

# Stop Gradle daemon
./gradlew --stop

# Clear port 8081 if blocked
lsof -ti:8081 | xargs kill -9

# Complete cleanup and restart sequence
pkill -9 -f "gradlew" && ./gradlew --stop && lsof -ti:8081 | xargs kill -9 && ./gradlew clean bootRun
```

### Access Points

- **Application**: http://localhost:8081
- **H2 Console**: http://localhost:8081/h2-console
  - JDBC URL: `jdbc:h2:mem:parkingon`
  - Username: `sa`
  - Password: (empty)

### Default Credentials

- **Username**: `admin`
- **Password**: `admin`

## Architecture Overview

### Module Structure

The system is organized into three main functional modules:

1. **VOC (Voice of Customer)** - `/voc/*`
   - VOC history management (`/voc/list`, `/voc/voc-list`)
   - In/out vehicle history (`/voc/inout-car`)
   - Control history (`/voc/control-history`)

2. **System Management** - `/system/*`
   - User management (`/system/user`)
   - Code management (`/system/code`)
   - Notification management (`/system/notify`)

3. **APT (Apartment)** - `/apt/*`
   - Apartment management (`/apt/apt`)
   - Car management (`/apt/car`)
   - LPR device management (`/apt/lpr`)

### Key Directories

```
src/main/
├── java/com/parkingon/
│   ├── config/           # Security, data initialization
│   ├── controller/       # MVC controllers (VOC, System, APT, Dashboard)
│   ├── entity/          # JPA entities (User, Apartment, Vehicle, etc.)
│   ├── repository/      # Spring Data JPA repositories
│   └── service/         # Business logic services
└── resources/
    ├── static/
    │   ├── css/         # Stylesheets (app.css, management.css, popup.css)
    │   └── js/          # Client-side JavaScript
    └── templates/       # Thymeleaf HTML templates
        ├── fragments/   # Reusable fragments (header, footer, notify-popup)
        ├── voc/        # VOC module pages
        ├── system/     # System management pages
        └── apt/        # Apartment management pages
```

## Critical Development Patterns

### 1. Thymeleaf Templating

**Fragment Inclusion:**
```html
<!-- Header with breadcrumb -->
<div th:replace="~{fragments/header :: header('모듈명 → 페이지명')}"></div>

<!-- Footer -->
<div th:replace="~{fragments/footer :: footer}"></div>
```

**Data Binding:**
```html
<!-- Loop through collections -->
<tr th:each="user : ${users}">
    <td th:text="${user.username}">default</td>
</tr>

<!-- Conditional rendering -->
<tr th:if="${users == null or users.isEmpty()}">
    <td colspan="7">데이터가 없습니다.</td>
</tr>
```

### 2. Management Pages Layout Pattern

All management pages follow a **2-column layout** defined in `management.css`:

**HTML Structure:**
```html
<main class="management-container">
    <!-- Left sidebar: Item list with search and pagination -->
    <aside class="management-sidebar">
        <div class="sidebar-search">...</div>
        <div class="item-table-wrapper">...</div>
        <div class="pagination">...</div>
    </aside>

    <!-- Right content: Data grid and form -->
    <div class="management-content">
        <div class="search-bar">...</div>
        <div class="data-grid">...</div>
        <div class="form-section">...</div>
    </div>
</main>
```

**CSS Grid:**
```css
.management-container {
    display: grid;
    grid-template-columns: 420px 1fr;
    gap: var(--spacing-4);
}
```

### 3. Controller Pattern

Controllers follow a consistent pattern:

```java
@Controller
@RequestMapping("/module")
@RequiredArgsConstructor
public class ModuleController {

    private final Repository repository;

    @GetMapping("/page")
    public String page(HttpSession session, Model model) {
        setSessionInfo(session);
        model.addAttribute("data", repository.findAll());
        return "module/page";
    }

    private void setSessionInfo(HttpSession session) {
        if (session.getAttribute("username") == null) {
            session.setAttribute("username", "관리자");
            session.setAttribute("loginTime",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
    }
}
```

### 4. Form Design Patterns

**Search Bar (Compact, Single Line):**
```html
<div class="search-bar">
    <div class="input-group">
        <label>라벨</label>
        <input type="text" class="form-input">
    </div>
    <button class="btn-primary">조회</button>
</div>
```

**Form Grid (Label-Value Pairs):**
```html
<div class="form-grid">
    <label class="form-field-label">필드명</label>
    <input type="text" class="form-input">
</div>
```

**Fixed Value Header Style:**
```html
<div class="form-field-fixed">
    <label class="form-field-label">아파트명</label>
    <div class="form-fixed-value">삼성1단지</div>
</div>
```

## CSS Design System

The project uses CSS custom properties for consistent theming:

**Colors:**
- `--primary-blue: #00B0FF`
- `--bg-white: #FFFFFF`
- `--bg-gray-light: #F8F9FA`
- `--text-primary: #1E293B`

**Spacing:**
- `--spacing-2: 8px`
- `--spacing-3: 12px`
- `--spacing-4: 16px`
- `--spacing-5: 20px`

**Typography:**
- `--text-sm: 13px`
- `--text-base: 14px`
- `--text-lg: 16px`

## Database and Data Initialization

**H2 Configuration:**
- Database: `jdbc:h2:mem:parkingon` (in-memory, recreated on restart)
- DDL: `create-drop` (schema recreated each start)
- Data initialized via `DataInitializer.java` component

**Entity Relationships:**
```
Apartment (1) ---> (N) User
Apartment (1) ---> (N) Vehicle
Apartment (1) ---> (N) InOutHistory
Apartment (1) ---> (N) ControlHistory
Apartment (1) ---> (N) LprDevice
```

## Hot Reload with Spring Boot DevTools

**Important**: DevTools watches `build/classes` not `src/`. Changes require synchronization:

1. Edit file in `src/`
2. Gradle automatically compiles to `build/classes/`
3. DevTools detects change and restarts context

**If changes don't appear:**
- Ensure Gradle build succeeded
- Check `build/classes/java/main/` has updated `.class` files
- Restart server with `./gradlew clean bootRun`

## Common Issues and Solutions

### Issue 1: Changes Not Reflected
**Symptoms**: Modified code/templates don't appear in browser
**Causes**: Multiple server instances, DevTools not reloading, browser cache
**Solution**:
```bash
# Kill all processes and clean restart
pkill -9 -f "gradlew"
./gradlew --stop
./gradlew clean bootRun
```
Then hard refresh browser (Ctrl+Shift+R or Cmd+Shift+R)

### Issue 2: Port 8081 Already in Use
**Symptoms**: Server fails to start with "Port 8081 already in use"
**Solution**:
```bash
lsof -ti:8081 | xargs kill -9
./gradlew bootRun
```

### Issue 3: 404 Errors on Valid Pages
**Symptoms**: Whitelabel Error Page for URLs that should exist
**Common Causes**:
- Controller not mapping to exact URL path
- Template file name mismatch with return value
- Missing `@GetMapping` annotation

**Solution**: Check controller mappings and template paths match exactly

### Issue 4: White Screen or Template Errors
**Symptoms**: Page loads but shows white screen
**Common Causes**:
- Thymeleaf syntax error in template
- Missing model attribute referenced in template
- JavaScript error blocking page render

**Solution**:
- Check browser console (F12) for JavaScript errors
- Check server logs for Thymeleaf template exceptions
- Verify all `${...}` references have corresponding model attributes

## Important Notes

1. **This is Design V2 Prototype**: Running on port 8081 for UI/UX testing before production merge

2. **Data is Ephemeral**: H2 in-memory database is recreated on every restart. All data is lost.

3. **Search Bar Sizing**: Keep elements compact (font-size: 13px, max-width: 140px) to prevent button wrapping

4. **Fixed Value Design**: Use `.form-field-fixed` wrapper for header-style read-only fields with blue background

5. **Multiple URL Mappings**: Some controllers accept multiple paths (e.g., `/voc/list` and `/voc/voc-list`) for backward compatibility

6. **Session Management**: Controllers set session info if not present, allowing pages to work without explicit login

7. **Security Config**: H2 console and static resources are permitted for development ease

## Testing Credentials

Default test accounts created by `DataInitializer`:

- **Admin**: admin / admin
- **Apartment 1 Manager**: man01 / pass01
- **Apartment 2 Manager**: man02 / pass02

## Quick Reference for Common Tasks

**Add new management page:**
1. Create controller in `controller/` package
2. Create HTML template in `templates/{module}/` directory
3. Use `management.css` layout pattern (sidebar + content)
4. Add navigation link in header fragment

**Add sample data:**
1. Edit `config/DataInitializer.java`
2. Add entity creation in `@PostConstruct` method
3. Restart server to initialize data

**Modify styling:**
1. Edit `static/css/app.css` (global) or `management.css` (management pages)
2. Use existing CSS custom properties for consistency
3. DevTools will auto-reload CSS changes

**Debug template issues:**
1. Enable template cache logging: `logging.level.org.thymeleaf=DEBUG`
2. Check `application.properties` for Thymeleaf settings
3. Verify model attribute names match template references
