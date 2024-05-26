# First Stage: Builder
FROM docker.io/library/eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /src/toytopiaproduct

# Copy all source files to the build stage
COPY . .

# Set the environment variables
ARG PRODUCTION
ARG JDBC_DATABASE_PASSWORD
ARG JDBC_DATABASE_URL
ARG JDBC_DATABASE_USERNAME

ENV PRODUCTION=${PRODUCTION}
ENV JDBC_DATABASE_PASSWORD=${JDBC_DATABASE_PASSWORD}
ENV JDBC_DATABASE_URL=${JDBC_DATABASE_URL}
ENV JDBC_DATABASE_USERNAME=${JDBC_DATABASE_USERNAME}

# Ensure gradlew is executable
RUN chmod +x ./gradlew

# Build the application
RUN ./gradlew clean bootjar

# Second Stage: Runner
FROM docker.io/library/eclipse-temurin:21-jre-alpine AS runner

ARG USER_NAME=toytopiaproduct
ARG USER_UID=1000
ARG USER_GID=${USER_UID}

# Set the environment variables
ARG PRODUCTION
ARG JDBC_DATABASE_PASSWORD
ARG JDBC_DATABASE_URL
ARG JDBC_DATABASE_USERNAME

ENV PRODUCTION=${PRODUCTION}
ENV JDBC_DATABASE_PASSWORD=${JDBC_DATABASE_PASSWORD}
ENV JDBC_DATABASE_URL=${JDBC_DATABASE_URL}
ENV JDBC_DATABASE_USERNAME=${JDBC_DATABASE_USERNAME}

# Create a user and group for running the application
RUN addgroup -g ${USER_GID} ${USER_NAME} \
    && adduser -h /opt/toytopiaproduct -D -u ${USER_UID} -G ${USER_NAME} ${USER_NAME}

# Use the created user for running the application
USER ${USER_NAME}

WORKDIR /opt/toytopiaproduct

# Copy the built JAR file from the builder stage
COPY --from=builder --chown=${USER_UID}:${USER_GID} /src/toytopiaproduct/build/libs/*.jar app.jar

# Set the entry point to run the application
ENTRYPOINT ["java"]
CMD ["-jar", "app.jar"]