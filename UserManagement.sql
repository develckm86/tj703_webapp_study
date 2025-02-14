DROP DATABASE IF EXISTS UserManagement;
CREATE DATABASE UserManagement;
DROP USER IF EXISTS 'usermanager'@'%';
DROP USER IF EXISTS 'usermanager_dev'@'%';
USE UserManagement;

CREATE USER 'usermanager'@'%' IDENTIFIED BY 'mysql';
CREATE USER 'usermanager_dev'@'%' IDENTIFIED BY 'mysql';

-- 2. UserManagement 데이터베이스에 조회(SELECT) 및 수정(INSERT, UPDATE) 권한 부여
GRANT SELECT, INSERT, UPDATE, DELETE ON UserManagement.* TO 'usermanager'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP ON UserManagement.* TO 'usermanager_dev'@'%';

use UserManagement;
-- 1. 회원 정보 테이블
CREATE TABLE users
(
    user_id    INT AUTO_INCREMENT PRIMARY KEY,
    email      VARCHAR(100) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. 로그인 로그 테이블
CREATE TABLE login_logs
(
    log_id     INT AUTO_INCREMENT PRIMARY KEY,
    user_id    INT NOT NULL,
    login_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ip_address VARCHAR(45),
    user_agent VARCHAR(45),
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

-- 3. 비밀번호 변경 이력 테이블
CREATE TABLE password_change_history
(
    change_id    INT AUTO_INCREMENT PRIMARY KEY,
    user_id      INT          NOT NULL,
    old_password VARCHAR(255) NOT NULL,
    changed_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);
-- 더미 유저 생성 (비밀번호는 단순 예시, 실제로는 해시해야 함)
INSERT INTO users (email, password)
VALUES ('user1@example.com', '1234'),
       ('user2@example.com', '1234');

-- 로그인 기록 추가
INSERT INTO login_logs (user_id, ip_address, user_agent)
VALUES (1, '192.168.1.1', 'Mozilla/5.0'),
       (2, '192.168.1.2', 'Chrome/120');

-- 비밀번호 변경 이력 추가
INSERT INTO password_change_history (user_id, old_password)
VALUES (1, '1234'),
       (2, '1234');