import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.io.*;

public class initDB {
    static String[] firstPhoneNumber = {"086", "096", "097", "098", "032", "033", "034", "035", "036", "037","038", "039",
                            "086", "096", "097", "098", "032", "033", "034", "035", "036", "037","038", "039",
                            "086", "096", "097", "098", "032", "033", "034", "035", "036", "037","038", "039",
                            "086", "096", "097", "098", "032", "033", "034", "035", "036", "037","038", "039",
                            "086", "096", "097", "098", "032", "033", "034", "035", "036", "037","038", "039",
                            "088", "091", "094", "083", "084", "085", "081", "082", "089", "090", "093", "070",
                            "079", "077", "076", "078", "092", "056", "058"};
    static String[] ho = {"Nguyen", "Nguyen", "Nguyen", "Nguyen", "Nguyen", "Nguyen", "Tran", "Tran", "Tran", 
                "Tran", "Le", "Le", "Le", "Pham", "Pham", "Pham", "Hoang", "Huynh", "Phan", "Vu", "Vo", 
                "Dang", "Bui", "Do", "Ho", "Ngo", "Duong", "Ly", "Trinh", "Dinh"};
    static String[] tenNam = {"Dung", "Minh", "Kien", "Thanh", "Khoa", "Thang", "Tai", "Trung", "Huy", "Dang", 
                    "Hoang", "Dat", "Cuong", "Phi", "Viet", "Van", "Phuoc", "Vi", "Hung", "Quang", "Quan",
                    "Son", "Duy", "Cong", "Tung", "Manh", "Khanh", "Hung", "Trung", "Khai", "Khoi", "An",
                    "Tuan", "Nam", "Bao", "Giang", "Nhan", "Phuc", "Vinh", "Hieu", "Toan", "Truong", "Duc",
                    "Thien", "Thai", "Quoc", "Trong", "Khiem", "Hau"};
    static String[] demNam = {"Viet", "Tuan", "Truong", "Trung", "Anh", "Chi", "Dang", "Huu", "Hung", "Hoang", 
                    "Manh", "Huu", "Duc", "Minh", "Toan", "Thanh", "Thai", "Gia", "Quoc", "The", "Hong", 
                    "Trong", "Thien", "Duc", "Tai", "Phi", "Van", "Quang", "Duy", "Cong"};
    static String[] tenNu = {"Vy", "Tuong", "Han", "An", "Ngoc", "Ngan", "Chi", "Khue", "Ha", "Thuy", "Ngoc", 
                    "Chau", "Ngan", "Nhung", "Nhi", "Khanh", "Diep", "Anh", "Nhien", "Linh", "Thuong", 
                    "Binh", "Thao", "Diem", "Trang", "Tam", "Thuy", "Trinh", "Phuong", "Mai", "Chi", "Dung",
                    "Huong", "Le", "Tien", "Thu", "Tu", "Quyen", "Lien", "Chau", "My", "Kieu", "Thanh",
                    "Oanh", "Quynh", "Duyen", "Bich", "Hoa", "Diep", "Mai", "Tram", "Minh", "Anh", "Chi",
                    "Huong", "Nhi", "Linh", "Nguyet", "Van", "Trang", "Nhu", "Nga", "Uyen", "Lan", "Tuyet"};
    static String[] demNu = {"Thi", "Thi Ngoc", "Thi Phuong", "Thi Huong", "Thi Quynh", "Thanh", "Thu", "Diem",
                    "Ngoc", "Thuy", "Diem", "Quynh", "Diep", "Kieu", "Khanh", "Huong", "Bich", "Lien", "Kim", 
                    "Tuyet"};
    static String[] diaChi = new String[11000];
    static int cntDiaChi = 0;



    static final int NUMBER_OF_CUSTOMERS = 1100302;
    static String name[] = new String[NUMBER_OF_CUSTOMERS];
    static String email[] = new String[NUMBER_OF_CUSTOMERS];
    static String phone[] = new String[NUMBER_OF_CUSTOMERS];
    static String IdKhachHang[] = new String[NUMBER_OF_CUSTOMERS];
    static String diaChiKH[] = new String[NUMBER_OF_CUSTOMERS];



    static long seed = 1003, modules = ((long) 1<<31) - 1, mul = 48271, inc = 0;
    static int rand(int base) {
        seed = (seed * mul + inc) % modules;
        return (int) (seed % base);
    }

    static Connection conn = null;
    static String url = "jdbc:mysql://localhost:3306/DatabaseLab";
    static String user = "root";
    static String password = "";





    static void importExcel() {

    }





    static void createKhachHang() {
        System.out.println("Inserting values into KHACH_HANG");
        try {
            File file = new File("TinhHuyenXa2021.txt");
            BufferedReader br
                = new BufferedReader(new FileReader(file));
            String text;
            String[] str = new String[7];
            while ((text = br.readLine()) != null) {
                str = text.split("\\t", -2);
                diaChi[cntDiaChi++] = str[3].replaceAll("\'", "") + ", " + str[4].replaceAll("\'", "") + ", " + str[5].replaceAll("\'", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i=0; i<NUMBER_OF_CUSTOMERS; i++) {
            int bool = rand(5), bool2 = rand(2);
            String firstName = "", middleName = "", lastName = "", second = "";
            if (bool < 3) {
                do {
                    firstName = ho[rand(ho.length)];
                    middleName = demNam[rand(demNam.length)];
                    lastName = tenNam[rand(tenNam.length)];
                } while (firstName == middleName || middleName == lastName);
                name[i] = firstName + ' ' + middleName + ' ' + lastName;
            } 
            else {
                do {
                    firstName = ho[rand(ho.length)];
                    middleName = demNu[rand(demNu.length)];
                    if ( !middleName.contains(" ") && rand(4) == 0 ) {
                        second = " " + ho[rand(ho.length)] + " ";
                    } else second = " ";
                    lastName = tenNu[rand(tenNu.length)];
                } while (firstName == middleName || firstName == second || middleName == lastName);
                name[i] = firstName + second + middleName + ' ' + lastName;
            }
            

            switch (bool2) {
                case 0: 
                    email[i] = lastName.toLowerCase() + (rand(2) == 0 ? "." : "" ) + 
                    (rand(2) == 0 ? firstName.toLowerCase() : firstName.toLowerCase().charAt(0) ) +
                    (rand(2) == 0 ? middleName.replaceAll("\\s+","").toLowerCase() : middleName.toLowerCase().charAt(0) ) + 
                    (rand(4) == 0 ? "." : "" ) + 
                    rand(1000000) +
                    (rand(10) < 9 ? "@gmail.com" : "@outlook.com");
                    break;
                case 1:
                    email[i] = "" + (rand(2) == 0 ? firstName.toLowerCase() : firstName.toLowerCase().charAt(0) ) +
                    (rand(2) == 0 ? middleName.toLowerCase().replaceAll("\\s+","") : middleName.toLowerCase().charAt(0) ) + 
                    lastName.toLowerCase() +
                    (rand(4) == 0 ? "." : "" ) + 
                    rand(1000000) +
                    (rand(10) < 9 ? "@gmail.com" : "@outlook.com");
                    break;
            }

            phone[i] = firstPhoneNumber[rand(firstPhoneNumber.length)] + (rand(9000000) + 1000000);
            IdKhachHang[i] = "KH" + String.format("%07d", i);
            diaChiKH[i] = diaChi[i % cntDiaChi];
        }

        // Tạo mới bảng khách hàng
        String sql = "DROP TABLE KHACH_HANG";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = "CREATE TABLE KHACH_HANG(" +
                "ID_KH CHAR(10), " +
                "TEN_KH VARCHAR(50), " +
                "DIA_CHI_KH VARCHAR(100), " +
                "EMAIL_KH VARCHAR(50), " +
                "SDT CHAR(15), " +
                "CONSTRAINT PK_KH PRIMARY KEY (ID_KH));";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // insert dữ liệu vào bảng
        sql = "INSERT INTO KHACH_HANG VALUES";
        for (int i=0; i<NUMBER_OF_CUSTOMERS; i++) {
            sql+= "('" + IdKhachHang[i] + "','"+ name[i] + "','" + diaChiKH[i] + "','" + email[i] + "','" + phone[i] + "')";
            if (i % 10000 == 9999 || i == NUMBER_OF_CUSTOMERS-1) 
            {
                sql+= ';';
                try {
                    Statement st = conn.createStatement();
                    st.executeUpdate(sql);
                    System.out.printf("___________________%.2f %%\r", (double) i/NUMBER_OF_CUSTOMERS * 100, 2);
                } catch (Exception e) {
                    System.out.println("-----" + i);
                    e.printStackTrace();
                }
                sql = "INSERT INTO KHACH_HANG VALUES";
            } else {
                sql+= ',';
            }
        }
        System.out.println();
    }

    static void createNhaCungCap() {
    }






    public static void main(String[] args) 
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        };

        createKhachHang();
        // createNhaCungCap();
    }
}
