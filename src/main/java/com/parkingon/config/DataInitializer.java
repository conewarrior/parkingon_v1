package com.parkingon.config;

import com.parkingon.entity.*;
import com.parkingon.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ApartmentRepository apartmentRepository;
    private final NotifyRepository notifyRepository;
    private final CarRepository carRepository;
    private final VocRepository vocRepository;
    private final LprDeviceRepository lprDeviceRepository;
    private final InOutHistoryRepository inOutHistoryRepository;
    private final ControlHistoryRepository controlHistoryRepository;
    private final CommonCodeRepository commonCodeRepository;
    private final SystemConfigRepository systemConfigRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        log.info("=== Initializing sample data... ===");

        List<Apartment> apartments = initApartments();
        initUsers(apartments);
        initNotifies();
        initCars(apartments);
        initVocs(apartments);
        initLprDevices(apartments);
        initInOutHistory(apartments);
        initControlHistory(apartments);
        initCommonCodes();
        initSystemConfigs();

        log.info("=== Data initialization completed! ===");
    }

    private void initUsers(List<Apartment> apartments) {
        if (userRepository.count() > 0) return;

        List<User> users = new ArrayList<>();
        Apartment apt1 = apartments.isEmpty() ? null : apartments.get(0);

        // 관리자
        users.add(User.builder()
            .username("admin")
            .password(passwordEncoder.encode("admin"))
            .name("시스템관리자")
            .email("admin@parkingon.com")
            .phone("010-1234-5678")
            .role("ADMIN")
            .apartment(apt1)
            .createdBy("system")
            .isActive(true)
            .build());

        // 일반 관리자들
        users.add(User.builder()
            .username("manager1")
            .password(passwordEncoder.encode("1234"))
            .name("홍길동")
            .email("hong@parkingon.com")
            .phone("010-2345-6789")
            .role("MANAGER")
            .apartment(apt1)
            .createdBy("admin")
            .isActive(true)
            .build());

        users.add(User.builder()
            .username("guard1")
            .password(passwordEncoder.encode("1234"))
            .name("김경비")
            .email("guard1@parkingon.com")
            .phone("010-3456-7890")
            .role("GUARD")
            .apartment(apt1)
            .createdBy("admin")
            .isActive(true)
            .build());

        users.add(User.builder()
            .username("operator1")
            .password(passwordEncoder.encode("1234"))
            .name("이상담")
            .email("operator1@parkingon.com")
            .phone("010-4567-8901")
            .role("OPERATOR")
            .apartment(apt1)
            .createdBy("admin")
            .isActive(true)
            .build());

        userRepository.saveAll(users);
        log.info("Created {} users", users.size());
    }

    private List<Apartment> initApartments() {
        if (apartmentRepository.count() > 0) {
            return apartmentRepository.findAll();
        }

        List<Apartment> apartments = new ArrayList<>();

        apartments.add(Apartment.builder()
            .code("A1001234")
            .name("삼성1단지")
            .address("서울시 강남구 삼성동 123")
            .phone("02-1234-5678")
            .totalDong(15)
            .totalHo(500)
            .status("ACTIVE")
            .contractStartDate(LocalDateTime.now().minusMonths(6))
            .contractEndDate(LocalDateTime.now().plusMonths(18))
            .build());

        apartments.add(Apartment.builder()
            .code("A1001235")
            .name("삼성2단지")
            .address("서울시 강남구 삼성동 456")
            .phone("02-2345-6789")
            .totalDong(20)
            .totalHo(800)
            .status("ACTIVE")
            .contractStartDate(LocalDateTime.now().minusMonths(12))
            .contractEndDate(LocalDateTime.now().plusMonths(12))
            .build());

        apartments.add(Apartment.builder()
            .code("A1001236")
            .name("정문인구 방문")
            .address("서울시 송파구 잠실동 789")
            .phone("02-3456-7890")
            .totalDong(10)
            .totalHo(300)
            .status("ACTIVE")
            .contractStartDate(LocalDateTime.now().minusMonths(3))
            .contractEndDate(LocalDateTime.now().plusMonths(21))
            .build());

        apartments.add(Apartment.builder()
            .code("A1001237")
            .name("래미안아파트")
            .address("서울시 서초구 반포동 321")
            .phone("02-4567-8901")
            .totalDong(25)
            .totalHo(1000)
            .status("ACTIVE")
            .contractStartDate(LocalDateTime.now().minusMonths(8))
            .contractEndDate(LocalDateTime.now().plusMonths(16))
            .build());

        apartments.add(Apartment.builder()
            .code("A1001238")
            .name("힐스테이트")
            .address("서울시 마포구 상암동 654")
            .phone("02-5678-9012")
            .totalDong(18)
            .totalHo(600)
            .status("ACTIVE")
            .contractStartDate(LocalDateTime.now().minusMonths(4))
            .contractEndDate(LocalDateTime.now().plusMonths(20))
            .build());

        apartmentRepository.saveAll(apartments);
        log.info("Created {} apartments", apartments.size());
        return apartments;
    }

    private void initNotifies() {
        if (notifyRepository.count() > 0) return;

        List<Notify> notifies = new ArrayList<>();

        notifies.add(Notify.builder()
            .title("콜센터 운영지침 변경")
            .content("콜센터 운영지침 변경 10월 17일부로 변경됨을 알립니다.<br>" +
                "콜센터의 업무는 입주민 및 방문차량의 원활한 입출차를 지원하는 것입니다.<br>" +
                "업무 시간은 평일 오전 9시부터 오후 6시까지입니다.<br>" +
                "차량 입차 처리에 대하여는 블랙리스트를 우선 확인하여 주시기 바랍니다.")
            .sender("콜센터 관리자")
            .sendDate(LocalDateTime.now().minusDays(3))
            .notifyType("POPUP")
            .targetType("ALL")
            .isActive(true)
            .build());

        notifies.add(Notify.builder()
            .title("시스템 정기 점검 안내")
            .content("매월 마지막 주 일요일 새벽 2시~4시에 정기 점검이 진행됩니다.<br>" +
                "점검 시간 동안 시스템 이용이 제한될 수 있습니다.<br>" +
                "긴급한 경우 관리자에게 연락 바랍니다.")
            .sender("시스템 관리자")
            .sendDate(LocalDateTime.now().minusDays(1))
            .notifyType("POPUP")
            .targetType("ALL")
            .isActive(true)
            .build());

        notifies.add(Notify.builder()
            .title("신규 아파트 계약 체결")
            .content("래미안아파트와 힐스테이트 2개 단지가 신규로 계약 체결되었습니다.<br>" +
                "각 단지별 담당자를 배정하여 주시기 바랍니다.")
            .sender("영업팀")
            .sendDate(LocalDateTime.now().minusHours(5))
            .notifyType("ALERT")
            .targetType("ALL")
            .isActive(true)
            .build());

        notifyRepository.saveAll(notifies);
        log.info("Created {} notifications", notifies.size());
    }

    private void initCars(List<Apartment> apartments) {
        if (carRepository.count() > 0) return;

        List<Car> cars = new ArrayList<>();
        Apartment apt = apartments.get(0);

        // 블랙리스트 차량
        cars.add(Car.builder()
            .apartment(apt)
            .carNumber("12가1111")
            .dong("101")
            .ho("1002")
            .ownerName("홍길동")
            .ownerPhone("010-1111-2222")
            .carType("BLACK")
            .isRegistered(true)
            .registeredAt(LocalDateTime.now().minusDays(30))
            .build());

        // 화이트리스트 차량
        cars.add(Car.builder()
            .apartment(apt)
            .carNumber("12가2222")
            .dong("102")
            .ho("1503")
            .ownerName("홍이름")
            .ownerPhone("010-2222-3333")
            .carType("WHITE")
            .isRegistered(true)
            .registeredAt(LocalDateTime.now().minusDays(60))
            .build());

        // 정기방문 차량
        cars.add(Car.builder()
            .apartment(apt)
            .carNumber("12가3333")
            .dong("103")
            .ho("2001")
            .ownerName("김정기")
            .ownerPhone("010-3333-4444")
            .carType("PERIODIC")
            .isRegistered(true)
            .registeredAt(LocalDateTime.now().minusDays(15))
            .build());

        cars.add(Car.builder()
            .apartment(apt)
            .carNumber("22가1234")
            .dong("101")
            .ho("1002")
            .ownerName("박입주")
            .ownerPhone("010-4444-5555")
            .carType("RESIDENT")
            .isRegistered(true)
            .registeredAt(LocalDateTime.now().minusMonths(3))
            .build());

        cars.add(Car.builder()
            .apartment(apt)
            .carNumber("33가5678")
            .dong("105")
            .ho("801")
            .ownerName("최주민")
            .ownerPhone("010-5555-6666")
            .carType("RESIDENT")
            .isRegistered(true)
            .registeredAt(LocalDateTime.now().minusMonths(6))
            .build());

        carRepository.saveAll(cars);
        log.info("Created {} cars", cars.size());
    }

    private void initVocs(List<Apartment> apartments) {
        if (vocRepository.count() > 0) return;

        List<Voc> vocs = new ArrayList<>();
        Apartment apt = apartments.get(0);

        vocs.add(Voc.builder()
            .apartment(apt)
            .carNumber("22가1234")
            .customerType("정기방문")
            .visitPurpose("입차")
            .dong("101")
            .ho("1002")
            .content("정기방문 차량 자동 입차 처리")
            .status("RESOLVED")
            .createdBy("operator1")
            .resolvedAt(LocalDateTime.now().minusHours(3))
            .build());

        vocs.add(Voc.builder()
            .apartment(apt)
            .carNumber("22가1234")
            .customerType("정기방문인식")
            .visitPurpose("입차")
            .dong("102")
            .ho("1503")
            .content("정기방문 차량 확인 후 입차")
            .status("RESOLVED")
            .createdBy("operator1")
            .resolvedAt(LocalDateTime.now().minusHours(2))
            .build());

        vocs.add(Voc.builder()
            .apartment(apt)
            .carNumber("22가1333")
            .customerType("방문고객")
            .visitPurpose("방문")
            .dong("103")
            .ho("2001")
            .content("미등록 방문 차량 - 전화 확인 후 입차 허용")
            .status("RESOLVED")
            .createdBy("guard1")
            .resolvedAt(LocalDateTime.now().minusHours(1))
            .build());

        vocs.add(Voc.builder()
            .apartment(apt)
            .carNumber("44가9999")
            .customerType("방문")
            .visitPurpose("배달")
            .dong("104")
            .ho("302")
            .content("택배 배달 차량 입차")
            .status("OPEN")
            .createdBy("operator1")
            .build());

        vocRepository.saveAll(vocs);
        log.info("Created {} VOCs", vocs.size());
    }

    private void initLprDevices(List<Apartment> apartments) {
        if (lprDeviceRepository.count() > 0) return;

        List<LprDevice> devices = new ArrayList<>();
        Apartment apt = apartments.get(0);

        devices.add(LprDevice.builder()
            .apartment(apt)
            .deviceCode("PK030")
            .deviceType("입구LPR")
            .deviceName("정문입구")
            .deviceUrl("test.iptime.org:1234")
            .voip("010-1234-5678")
            .rtspUrl("rtsp://test.iptime.org/9654/profile2")
            .isActive(true)
            .build());

        devices.add(LprDevice.builder()
            .apartment(apt)
            .deviceCode("PK031")
            .deviceType("출구LPR")
            .deviceName("정문출구")
            .deviceUrl("test.iptime.org:1235")
            .voip("010-2345-6789")
            .rtspUrl("rtsp://test.iptime.org/9655/profile2")
            .isActive(true)
            .build());

        devices.add(LprDevice.builder()
            .apartment(apt)
            .deviceCode("PK032")
            .deviceType("입구LPR")
            .deviceName("후문입구")
            .deviceUrl("test.iptime.org:1236")
            .voip("010-3456-7890")
            .rtspUrl("rtsp://test.iptime.org/9656/profile2")
            .isActive(true)
            .build());

        lprDeviceRepository.saveAll(devices);
        log.info("Created {} LPR devices", devices.size());
    }

    private void initInOutHistory(List<Apartment> apartments) {
        if (inOutHistoryRepository.count() > 0) return;

        List<InOutHistory> histories = new ArrayList<>();
        Apartment apt = apartments.get(0);

        histories.add(InOutHistory.builder()
            .apartment(apt)
            .carNumber("12가1234")
            .inoutType("IN")
            .dong("101")
            .ho("1002")
            .gateName("정문")
            .customerType("입주민")
            .inoutTime(LocalDateTime.now().minusHours(10))
            .build());

        histories.add(InOutHistory.builder()
            .apartment(apt)
            .carNumber("12가1234")
            .inoutType("OUT")
            .dong("101")
            .ho("1002")
            .gateName("정문")
            .customerType("입주민")
            .inoutTime(LocalDateTime.now().minusHours(2))
            .build());

        histories.add(InOutHistory.builder()
            .apartment(apt)
            .carNumber("22가5678")
            .inoutType("IN")
            .dong("102")
            .ho("1503")
            .gateName("후문")
            .customerType("방문")
            .inoutTime(LocalDateTime.now().minusHours(4))
            .build());

        histories.add(InOutHistory.builder()
            .apartment(apt)
            .carNumber("33가9012")
            .inoutType("IN")
            .dong("103")
            .ho("2001")
            .gateName("정문")
            .customerType("출역")
            .inoutTime(LocalDateTime.now().minusHours(1))
            .build());

        inOutHistoryRepository.saveAll(histories);
        log.info("Created {} in/out histories", histories.size());
    }

    private void initControlHistory(List<Apartment> apartments) {
        if (controlHistoryRepository.count() > 0) return;

        List<ControlHistory> histories = new ArrayList<>();
        Apartment apt = apartments.get(0);

        histories.add(ControlHistory.builder()
            .apartment(apt)
            .carNumber("12가1111")
            .controlType("ALLOW")
            .gateName("정문")
            .controller("관리자")
            .reason("사전 승인된 방문차량")
            .controlTime(LocalDateTime.now().minusHours(6))
            .build());

        histories.add(ControlHistory.builder()
            .apartment(apt)
            .carNumber("22가9999")
            .controlType("DENY")
            .gateName("후문")
            .controller("관리자")
            .reason("블랙리스트 차량")
            .controlTime(LocalDateTime.now().minusHours(3))
            .build());

        histories.add(ControlHistory.builder()
            .apartment(apt)
            .carNumber("33가5555")
            .controlType("ALLOW")
            .gateName("정문")
            .controller("보안요원")
            .reason("긴급 배달 차량")
            .controlTime(LocalDateTime.now().minusHours(1))
            .build());

        controlHistoryRepository.saveAll(histories);
        log.info("Created {} control histories", histories.size());
    }

    private void initCommonCodes() {
        if (commonCodeRepository.count() > 0) return;

        List<CommonCode> codes = new ArrayList<>();

        // 고객구분
        codes.add(CommonCode.builder()
            .groupCode("CUST_TYPE").groupName("고객구분")
            .codeValue("RESIDENT").codeName("입주민")
            .sortOrder(1).isActive(true).build());
        codes.add(CommonCode.builder()
            .groupCode("CUST_TYPE").groupName("고객구분")
            .codeValue("VISITOR").codeName("방문")
            .sortOrder(2).isActive(true).build());
        codes.add(CommonCode.builder()
            .groupCode("CUST_TYPE").groupName("고객구분")
            .codeValue("PERIODIC").codeName("정기방문")
            .sortOrder(3).isActive(true).build());
        codes.add(CommonCode.builder()
            .groupCode("CUST_TYPE").groupName("고객구분")
            .codeValue("DELIVERY").codeName("출역")
            .sortOrder(4).isActive(true).build());

        // 차량구분
        codes.add(CommonCode.builder()
            .groupCode("CAR_TYPE").groupName("차량구분")
            .codeValue("BLACK").codeName("블랙")
            .sortOrder(1).isActive(true).build());
        codes.add(CommonCode.builder()
            .groupCode("CAR_TYPE").groupName("차량구분")
            .codeValue("WHITE").codeName("화이트")
            .sortOrder(2).isActive(true).build());
        codes.add(CommonCode.builder()
            .groupCode("CAR_TYPE").groupName("차량구분")
            .codeValue("PERIODIC").codeName("정기방문")
            .sortOrder(3).isActive(true).build());

        // 권역
        codes.add(CommonCode.builder()
            .groupCode("REGION").groupName("권역")
            .codeValue("METRO").codeName("수도권")
            .sortOrder(1).isActive(true).build());
        codes.add(CommonCode.builder()
            .groupCode("REGION").groupName("권역")
            .codeValue("GANGWON").codeName("강원권")
            .sortOrder(2).isActive(true).build());
        codes.add(CommonCode.builder()
            .groupCode("REGION").groupName("권역")
            .codeValue("CHUNGCHEONG").codeName("충청권")
            .sortOrder(3).isActive(true).build());

        commonCodeRepository.saveAll(codes);
        log.info("Created {} common codes", codes.size());
    }

    private void initSystemConfigs() {
        if (systemConfigRepository.count() > 0) return;

        List<SystemConfig> configs = new ArrayList<>();

        configs.add(SystemConfig.builder()
            .configKey("AUTO_LOGOUT_TIME")
            .configValue("1800")
            .description("자동 로그아웃 시간(초)")
            .build());

        configs.add(SystemConfig.builder()
            .configKey("SESSION_TIMEOUT")
            .configValue("1800")
            .description("세션 타임아웃(초)")
            .build());

        configs.add(SystemConfig.builder()
            .configKey("LOG_RETENTION_DAYS")
            .configValue("90")
            .description("로그 보관 기간(일)")
            .build());

        configs.add(SystemConfig.builder()
            .configKey("IMAGE_RETENTION_DAYS")
            .configValue("30")
            .description("이미지 저장 기간(일)")
            .build());

        configs.add(SystemConfig.builder()
            .configKey("MAX_UPLOAD_SIZE")
            .configValue("10")
            .description("최대 업로드 크기(MB)")
            .build());

        configs.add(SystemConfig.builder()
            .configKey("PASSWORD_MIN_LENGTH")
            .configValue("8")
            .description("비밀번호 최소 길이")
            .build());

        configs.add(SystemConfig.builder()
            .configKey("PASSWORD_EXPIRE_DAYS")
            .configValue("90")
            .description("비밀번호 만료 기간(일)")
            .build());

        configs.add(SystemConfig.builder()
            .configKey("PAGE_SIZE")
            .configValue("20")
            .description("페이지당 표시 행수")
            .build());

        systemConfigRepository.saveAll(configs);
        log.info("Created {} system configs", configs.size());
    }
}
