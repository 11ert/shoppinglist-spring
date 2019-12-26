LOAD DATA LOCAL INFILE "J:\\dev\\work\\shoppinglist\\data\\Schweizer-Naehrwertdatenbank-V6.1.csv" INTO TABLE test.suggestion
 CHARACTER SET utf8mb4
 FIELDS TERMINATED BY ';'
 LINES TERMINATED BY '\n'
 IGNORE 1 LINES
 (id, name, synonym, category);