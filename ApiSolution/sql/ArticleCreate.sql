CREATE TABLE articles (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    news_site VARCHAR(255),
    published_date TIMESTAMP,
    article TEXT
);