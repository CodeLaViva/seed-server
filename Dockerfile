# 构建阶段
FROM gradle:8.6-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon -x test

# 运行阶段
FROM openjdk:21-slim
WORKDIR /app

# 添加应用用户
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# 复制构建产物
COPY --from=build --chown=spring:spring /app/build/libs/*.jar app.jar

# 设置环境变量
ENV DB_USERNAME=${DB_USERNAME} \
    DB_PASSWORD=${DB_PASSWORD}

EXPOSE 6666

# 使用 exec 格式的 ENTRYPOINT，便于接收 SIGTERM 信号
ENTRYPOINT ["java", "-jar", "app.jar"]