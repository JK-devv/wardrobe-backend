ALTER DATABASE postgres SET TIME ZONE 'UTC';

CREATE TABLE "size_chart" (
        name VARCHAR not null primary key
);

CREATE TABLE "type" (
        name VARCHAR not null primary key
);

CREATE TABLE "products" (
        id UUID primary key not null,
        name VARCHAR not null,
        brand VARCHAR not null,
        type VARCHAR,
        affiliate_link VARCHAR(2048) ,
        price NUMERIC(10, 2),
        size_chart VARCHAR[],
        available_size_range VARCHAR,
        description VARCHAR,
        image_url VARCHAR(2048),
        created_at TIMESTAMP DEFAULT now()
);