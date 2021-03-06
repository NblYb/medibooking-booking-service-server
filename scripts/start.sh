#!/bin/sh

java -cp app:app/lib/* com.medibooking.bookingserver.BookingServerApplication \
            -Dspring.datasource.url=${SPRING_DATASOURCE_URL} \
            -Dspring.datasource.username=${SPRING_DATASOURCE_USERNAME} \
            -Dspring.datasource.password=${SPRING_DATASOURCE_PASSWORD} \
            -Dspring.flyway.url=${SPRING_FLYWAY_URL} \
            -Dspring.flyway.user=${SPRING_FLYWAY_USER} \
            -Dspring.flyway.password=${SPRING_FLYWAY_PASSWORD}