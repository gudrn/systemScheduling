# Process Scheduling Simulator

## 프로젝트 개요

CPU 스케줄링 알고리즘의 동작 방식을 시각적으로 학습할 수 있는 GUI 기반 시뮬레이터입니다.
여러 CPU 스케줄링 알고리즘을 직접 구현하고, Gantt Chart를 통해 프로세스 스케줄링 결과를 실시간으로 확인할 수 있습니다.

## 주요 기능

### 지원하는 스케줄링 알고리즘

- **FCFS** (First Come First Served): 선입선출 스케줄링
- **SPN** (Shortest Process Next): 가장 짧은 작업 우선
- **SRTN** (Shortest Remaining Time Next): 가장 짧은 잔여 시간 우선
- **RR** (Round Robin): 시간 할당량 기반 스케줄링
- **HRRN** (Highest Response Ratio Next): 응답률 기반 우선순위 스케줄링
- **OBG** (Operating Based Gradient): 운영 기반 그래디언트 스케줄링

### 주요 특징

- 직관적인 JavaFX 기반 GUI 인터페이스
- 프로세스 도착 시간 및 서비스 시간 입력
- 실시간 Gantt Chart 시각화
- 각 알고리즘별 성능 비교 (대기 시간, 반환 시간 등)
- 다중 코어 처리 지원 (P-Core, E-Core)

## 프로젝트 구조

```
systemScheduling/
├── README.md
└── OSver7_1/
    ├── bin/                          # 컴파일된 바이너리
    │   ├── algorithm/
    │   ├── application/
    │   └── core/
    └── src/                          # 소스 코드
        ├── algorithm/                # 스케줄링 알고리즘 구현
        │   ├── FCFS.java
        │   ├── HRRN.java
        │   ├── OBG.java
        │   ├── RR.java
        │   ├── SPN.java
        │   └── SRTN.java
        ├── application/              # GUI 및 메인 애플리케이션
        │   ├── Main.java             # 애플리케이션 진입점
        │   ├── Controller.java        # UI 컨트롤러
        │   ├── Process.java           # 프로세스 데이터 모델
        │   ├── ProcessColor.java      # 프로세스 색상 관리
        │   ├── LimitedTextField.java  # 텍스트 입력 제한
        │   ├── PrintGanttChart.java   # Gantt Chart 시각화
        │   ├── UI.fxml               # JavaFX UI 정의
        │   └── application.css       # 스타일시트
        └── core/                     # 핵심 처리 로직
            ├── Core.java
            ├── Ecore.java            # E-Core 시뮬레이션
            └── Pcore.java            # P-Core 시뮬레이션
```

## 기술 스택

| 기술   | 버전             |
| ------ | ---------------- |
| Java   | 17               |
| JavaFX | -                |
| IDE    | Eclipse/IntelliJ |

## 사용 방법

### 1. 프로그램 사용

1. 메인 화면에서 프로세스 정보 입력

   - 프로세스명
   - 도착 시간 (Arrival Time)
   - 서비스 시간 (Service Time)

2. 스케줄링 알고리즘 선택

3. 시뮬레이션 실행

4. 결과 확인
   - Gantt Chart 시각화
   - 성능 지표 분석 (평균 대기시간, 평균 반환시간 등)

## 팀원

| 학번       | 이름   | 역할 |
| ---------- | ------ | ---- |
| 2019136053 | 박상준 | 팀장 |
| 2019136108 | 이해민 | 팀원 |
| 2019136050 | 박금도 | 팀원 |
| 2019136047 | 김형구 | 팀원 |

## 주요 클래스 설명

### Process.java

- 프로세스의 도착시간, 서비스시간, 우선순위 등을 저장하는 데이터 모델

### Core.java, Pcore.java, Ecore.java

- CPU 코어의 상태를 관리하고 스케줄링 로직을 처리하는 핵심 클래스

### PrintGanttChart.java

- 스케줄링 결과를 Gantt Chart 형태로 시각화

### Controller.java

- UI 이벤트 처리 및 알고리즘 실행 관리
