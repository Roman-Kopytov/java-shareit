CREATE TABLE IF NOT EXISTS USERS
(
    id    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name  VARCHAR(255),
    email VARCHAR(512),
    CONSTRAINT USER_PK PRIMARY KEY (id),
    CONSTRAINT UQ_USER_EMAIL UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS ITEMS
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name         VARCHAR(100)                            NOT NULL,
    description  VARCHAR(200)                            NOT NULL,
    is_available BOOLEAN                                 NOT NULL,
    owner_id     BIGINT                                  NOT NULL,
    request_id   BIGINT,
    CONSTRAINT ITEMS_PK PRIMARY KEY (id),
    CONSTRAINT USERS_FK FOREIGN KEY (owner_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS BOOKINGS
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    start_date TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    end_date   TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    item_id    BIGINT                                  NOT NULL,
    booker_id  BIGINT                                  NOT NULL,
    status     VARCHAR(100)                            NOT NULL,
    CONSTRAINT BOOKINGS_PK PRIMARY KEY (id),
    CONSTRAINT ITEMS_FK FOREIGN KEY (item_id) REFERENCES ITEMS (id) ON DELETE CASCADE,
    CONSTRAINT USER_FK FOREIGN KEY (booker_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ITEM_REQUESTS
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    description  VARCHAR(255)                            NOT NULL,
    requestor_id BIGINT                                  NOT NULL,
    created      TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    CONSTRAINT REQUESTS_PK PRIMARY KEY (id),
    CONSTRAINT REQUESTOR_FK FOREIGN KEY (requestor_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS COMMENTS
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    text      VARCHAR(255),
    item_id   BIGINT                                  NOT NULL,
    author_id BIGINT                                  NOT NULL,
    created   TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    CONSTRAINT COMMENTS_PK PRIMARY KEY (id),
    CONSTRAINT AUTHOR_FK FOREIGN KEY (author_id) REFERENCES USERS (id) ON DELETE CASCADE,
    CONSTRAINT ITEMS_ID_FK FOREIGN KEY (item_id) REFERENCES ITEMS (id) ON DELETE CASCADE
);

