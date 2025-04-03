# Seed Server

Spring Boot

## 技术栈
- Spring Boot 3.2.2
- Spring Security
- Spring Data JPA
- Kafka
- Gradle
- Docker

## 快速开始

### 环境要求
- JDK 21
- Gradle 8.6
- Docker

### 本地开发
```bash
./gradlew bootRun
```

### 构建部署
```bash
./gradlew build
docker build -t seed-server .
```
