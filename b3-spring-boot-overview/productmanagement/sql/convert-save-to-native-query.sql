-- VD: bang student (id, name, age)
INSERT INTO student (id, name, age) VALUES(1, "Do Duc Tam", 20) ON DUPLICATE KEY UPDATE name="Do Duc Tam", age=20