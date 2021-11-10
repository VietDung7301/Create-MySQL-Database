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

#### 2. Tải JDBC (Connector/J 8.0.27)
- Link download: [Connector/J](https://dev.mysql.com/downloads/connector/j/)
- Giải nén file vừa tải

#### 3. Tạo 1 thư mục làm việc
```shell
mkdir DatabaseLab
echo > DatabaseLab/initDB.java
```
- Copy/paste nội dung file `initDB.java` (trong Github) vào file vừa tạo (Chú ý chỉnh lại tên database, username và password)
- Copy file `mysql-connector-java-8.0.27.jar` từ thư mục đã giải nén ở trên vào folder DatabaseLab, ví dụ:
```shell
cp /Downloads/mysql-connector-java_8.0.27-1ubuntu20.04_all/usr/share/java/mysql-connector-java-8.0.27.jar ./DatabaseLab
```


## Bắt đầu quá trình tạo Database
- Di chuyển tới thư mục DatabaseLab vừa tạo ở trên, kiểm tra xem trong folder này đã có 2 file `initDB.java` và `mysql-connector-java-8.0.27.jar` chưa
```shell
cd DatabaseLab
ls -a
```
- Khởi động mysql và tạo 1 database mới:
```shell
service mysql start
mysql -u root -p
create database DatabaseLab;
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
