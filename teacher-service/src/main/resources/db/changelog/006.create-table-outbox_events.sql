--liquibase formatted sql
--changeset Sergey:6

CREATE TABLE outbox_events (
                               id BIGSERIAL PRIMARY KEY,
                               payload TEXT NOT NULL,
                               outbox_status VARCHAR(50) NOT NULL,
                               retry_count INTEGER DEFAULT 0,
                               created_at TIMESTAMP NOT NULL,
                               processed_at TIMESTAMP
);