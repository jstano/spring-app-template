FROM eclipse-temurin:21-jre-alpine

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ARG SERVICE_NAME
ARG PRODUCT_GROUP

COPY *.jar app.jar
COPY otel/opentelemetry-javaagent*.jar /opentelemetry-javaagent.jar

EXPOSE 8080

ENV JDK_JAVA_OPTIONS="-XX:InitialRAMPercentage=50 -XX:MaxRAMPercentage=75"

# --- OpenTelemetry config ---
ENV OTEL_SERVICE_NAME=${SERVICE_NAME} \
    OTEL_RESOURCE_ATTRIBUTES="service.namespace=${PRODUCT_GROUP}" \
    OTEL_EXPORTER_OTLP_PROTOCOL=http/protobuf \
    OTEL_TRACES_EXPORTER=otlp \
    OTEL_METRICS_EXPORTER=otlp \
    OTEL_LOGS_EXPORTER=otlp \
    OTEL_INSTRUMENTATION_LOGBACK_APPENDER_ENABLED=true \
    OTEL_INSTRUMENTATION_LOGBACK_APPENDER_EXPERIMENTAL_CAPTURE_MDC_ATTRIBUTES=* \
    OTEL_JAVAAGENT_LOGGING=application

ENTRYPOINT [ \
  "java", \
  "-javaagent:/opentelemetry-javaagent.jar", \
  "-jar", \
  "/app.jar" \
]
