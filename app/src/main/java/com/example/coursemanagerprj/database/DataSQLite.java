package com.example.coursemanagerprj.database;

public class DataSQLite {
    public static final String INSERT_QUAN_LY = "Insert into QuanLy(MaQL,HoTen,MatKhau) values "+
            "('admin','Admin','admin')," +
            "('minhtd','Trần Đức Minh','minhtd')," +
            "('giangnx','Nguyễn Xuân Giang','giangnx')";
    public static final String INSERT_THANH_VIEN = "Insert into ThanhVien(HoTen,NamSinh) values "+
            "('Trần Đức Minh','2002')," +
            "('Nguyễn Xuân Giang','2002'), " +
            "('Nguyễn Thái Hưng','2002')," +
            "('Trịnh Viết Hiếu','2002')," +
            "('Nguyễn Phú Quân','2002')," +
            "('Hoàng Anh Xuân','2002')," +
            "('Chu Văn Long','2002')";
    public static final String INSERT_LOAI_COURSE = "Insert into LoaiCourse(tenloai) values " +
            "('Ngoại ngữ')," +
            "('Tin học văn phòng')," +
            "('Công nghệ thông tin')," +
            "('Marketing')," +
            "('Thiết kế và Xây dựng')," +
            "('Tài chính và Kế toán')," +
            "('Kỹ năng')";
    public static final String INSERT_COURSE = "Insert into Course(tenCourse,giaThue,maLoai) values " +
            "('Học tiếng Nhật thật dễ','550000','1')," +
            "('Học tiếng Hàn thật dễ','650000','1')," +
            "('Tự học tiếng Trung cơ bản','300000','1')," +
            "('Toeic thần tốc cho người mất gốc','250000','1')," +
            "('Thiết kế Powerpoint chuyên nghiệp','299000','2')," +
            "('Chinh phục Excel công sở','199000','2')," +
            "('Thành thạo Words cơ bản','150000','2')," +
            "('Lập trình Android','500000','3')," +
            "('Lập trình Web cơ bản','900000','3')," +
            "('Lập trình Python nâng cao','250000','3')," +
            "('Lập trình hướng đối tượng','100000','3')," +
            "('Kỹ thuật quảng cáo Pr','50000','4')," +
            "('Youtube Marketing','300000','4')," +
            "('Bán hàng Online đỉnh cao','350000','4')," +
            "('Cẩm nang dựng hình 3D cơ bản','220000','5')," +
            "('Xây dựng mô hình 3D trong thiết kế kiến trúc','280000','5')," +
            "('Kỹ năng Photoshop đỉnh cao','400000','5')," +
            "('Tự do tài chính cùng chứng khoán','399000','6')," +
            "('Đầu từ Bitcoin cho người mới bắt đầu','599000','6')," +
            "('Nhập môn chứng khoán đầu tư','250000','6')," +
            "('Bí quyết viết CV dự phỏng vấn','150000','7')," +
            "('Nghệ thuật quyến rũ khán giả trong thuyết trình','450000','7')," +
            "('Giao tiếp xã hội tự tin','299000','7')," +
            "('Giao tiếp qua điện thoại','100000','7')," +
            "('Kỹ năng giao tiếp và ứng xử hiệu quả','299000','7')";
    public static final String INSERT_PHIEU_MUON = "Insert into PhieuMuon(MaQL,MaTV,MaCourse,TienThue,Ngay,TraCourse) values" +
            "('admin','1','1','550000','2024/04/12','0')," +
            "('admin','2','2','650000','2024/04/18','0')," +
            "('minhtd','2','4','250000','2024/03/12','0')," +
            "('minhtd','5','4','250000','2024/03/02','0')," +
            "('admin','3','3','300000','2024/04/02','0')," +
            "('giangnx','2','7','150000','2024/02/12','0')," +
            "('admin','1','8','500000','2024/01/10','0')," +
            "('admin','4','10','250000','2024/02/02','0')," +
            "('giangnx','5','10','250000','2024/03/02','1')," +
            "('admin','1','12','50000','2024/03/01','0')," +
            "('minhtd','3','14','350000','2024/04/11','0')," +
            "('admin','3','15','220000','2024/04/14','0')," +
            "('admin','7','16','280000','2024/02/12','0')," +
            "('admin','6','18','399000','2024/03/12','0')," +
            "('minhtd','6','20','250000','2024/04/10','1')," +
            "('minhtd','2','20','250000','2024/01/10','1')," +
            "('minhtd','6','21','150000','2024/03/11','0')";

}
