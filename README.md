# Create random database with Java

## Cài đặt hệ thống

#### 1. Cài đặt JDK
Chạy các lệnh sau
```shell
sudo add-apt-repository ppa:linuxuprising/java
sudo apt-get update
sudo apt install oracle-java17-installer --install-recommends
```
Sau đó kiểm tra đã cài đặt thành công chưa bằng 2 lệnh sau:
```shell
javac -version
java -version
```
Nếu thành công:
```shell
root@VietDung:~# javac -version
javac 17.0.1
root@VietDung:~# java -version
java version "17.0.1" 2021-10-19 LTS
Java(TM) SE Runtime Environment (build 17.0.1+12-LTS-39)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.1+12-LTS-39, mixed mode, sharing)
```

#### 2. Clone repo này về máy
```shell
git clone https://github.com/VietDung7301/Create-MySQL-Database.git
```


## Bắt đầu quá trình tạo Database
- Di chuyển tới thư mục Create-MySQL-Database vừa tạo ở trên
```shell
cd Create-MySQL-Database
```
- Khởi động mysql và tạo 1 database mới:
```shell
service mysql start
mysql -u root -p
create database DatabaseLab;
exit;
```
- Khởi tạo biến môi trường Connector/J:
```shell
export CLASSPATH=mysql-connector-java-8.0.27.jar:$CLASSPATH
```
- Dịch và chạy file `initDB.java`
```shell
javac initDB.java
java initDB
```
