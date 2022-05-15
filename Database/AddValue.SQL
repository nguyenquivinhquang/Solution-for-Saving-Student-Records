
insert into [user](Username, Password,Role)
values ('admin_quang', '22ae1b3d5a231c373930fd723ce7200847cf81ec6d49a3fa2eeb518427e324d4','admin');

insert into [Administrator](Admin_Id, Admin_name)
values ('admin_quang','Vinh Quang');


INSERT INTO Course (Course_Id,Description,Course_name,Credits) VALUES ('CH012IU','','Chemistry Laboratory',1);
INSERT INTO Course (Course_Id,Description,Course_name,Credits) VALUES ('EN007IU','','Writing AE1',2);
INSERT INTO Course (Course_Id,Description,Course_name,Credits) VALUES ('IT082IU','','Internship',3);
INSERT INTO Course (Course_Id,Description,Course_name,Credits) VALUES ('MA023IU','','Calculus 3',4);
INSERT INTO Course (Course_Id,Description,Course_name,Credits) VALUES ('IT090IU','','Object-Oriented Analysis and Design',4);
INSERT INTO Course (Course_Id,Description,Course_name,Credits) VALUES ('IT013IU','DSA','Algorithms & Data Structures',4);
INSERT INTO Course (Course_Id,Description,Course_name,Credits) VALUES ('IT097IU','','Introduction to Artificial Intelligence',4);
INSERT INTO Course (Course_Id,Description,Course_name,Credits) VALUES ('IT136IU','','Regression Analysis',4);
INSERT INTO Course (Course_Id,Description,Course_name,Credits) VALUES ('IT138IU','','Data Science and Data Visualization',4);
INSERT INTO Course (Course_Id,Description,Course_name,Credits) VALUES ('IT149IU','','Fundamentals of Programming',4);
INSERT INTO Course (Course_Id,Description,Course_name,Credits) VALUES ('IT151IU','','Statistical Methods',3);
INSERT INTO Course (Course_Id,Description,Course_name,Credits) VALUES ('CH012IU','','Chemistry Laboratory',1);
INSERT INTO Course (Course_Id,Description,Course_name,Credits) VALUES ('IT120IU','','Entrepreneurship',3);
INSERT INTO Course (Course_Id,Description,Course_name,Credits) VALUES ('EN007IU','','Writing AE1',2);
INSERT INTO Course (Course_Id,Description,Course_name,Credits) VALUES ('IT154IU','','Linear Algebra',3);
INSERT INTO Course (Course_Id,Description,Course_name,Credits) VALUES ('IT140IU','','Fundamental Concepts of Data Security',4);
INSERT INTO Course (Course_Id,Description,Course_name,Credits) VALUES ('IT135IU','','Introduction to Data Science',3);
INSERT INTO Course (Course_Id,Description,Course_name,Credits) VALUES ('EN008IU','','Listening AE1',2);
INSERT INTO Course (Course_Id,Description,Course_name,Credits) VALUES ('PE016IU','','Political economics of Marxism and Leninism',2);
INSERT INTO Course (Course_Id,Description,Course_name,Credits) VALUES ('PE008IU','','Critical Thinking',3);
INSERT INTO Course (Course_Id,Description,Course_name,Credits) VALUES ('PE015IU','','Philosophy of Marxism and Leninism',3);
INSERT INTO Course (Course_Id,Description,Course_name,Credits) VALUES ('PT001IU','','Physical Training 1',3);
INSERT INTO Course (Course_Id,Description,Course_name,Credits) VALUES ('PH013IU','','Physics 1',2);



INSERT INTO [User](Username, Password,Role) VALUES ('hvus','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name,Last_name,Teacher_Id,Department,Mail) VALUES ('Uyen Synh','Ha Viet','hvus','IT','hvus@hcmiu.edu.vn');

INSERT INTO [User](Username, Password, Role) VALUES ('nvsinh','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name, Last_name, Teacher_Id, Department, Mail) VALUES ('Van Sinh', 'Nguyen', 'nvsinh', 'it', 'nvsinh@hcmiu.edu.vn');

INSERT INTO [User](Username, Password, Role) VALUES ('tttung','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name, Last_name, Teacher_Id, Department, Mail) VALUES ('Thanh Tung', 'Tran', 'tttung', 'it', 'tttung@hcmiu.edu.vn');

INSERT INTO [User](Username, Password, Role) VALUES ('ddavu','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name, Last_name, Teacher_Id, Department, Mail) VALUES ('Anh Vu', 'Dinh Duc', 'ddavu', 'it', 'ddavu@hcmiu.edu.vn');

INSERT INTO [User](Username, Password, Role) VALUES ('nttloan','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name, Last_name, Teacher_Id, Department, Mail) VALUES ('Thuy Loan', 'Nguyen Thi', 'nttloan', 'it', 'nttloan@hcmiu.edu.vn');

INSERT INTO [User](Username, Password, Role) VALUES ('vtlphuong','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name, Last_name, Teacher_Id, Department, Mail) VALUES ('Luu Phuong', 'Vo Thi', 'vtlphuong', 'it', 'vtlphuong@hcmiu.edu.vn');

INSERT INTO [User](Username, Password, Role) VALUES ('hvusynh','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name, Last_name, Teacher_Id, Department, Mail) VALUES ('Uyen Synh', 'Ha Viet', 'hvusynh', 'it', 'hvusynh@hcmiu.edu.vn');

INSERT INTO [User](Username, Password, Role) VALUES ('hktu','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name, Last_name, Teacher_Id, Department, Mail) VALUES ('Kha Tu', 'Huynh', 'hktu', 'it', 'hktu@hcmiu.edu.vn');

INSERT INTO [User](Username, Password, Role) VALUES ('ldtan','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name, Last_name, Teacher_Id, Department, Mail) VALUES ('Duy Tan', 'Le', 'ldtan', 'it', 'ldtan@hcmiu.edu.vn');

INSERT INTO [User](Username, Password, Role) VALUES ('lhduong','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name, Last_name, Teacher_Id, Department, Mail) VALUES ('Hai Duong', 'Le', 'lhduong', 'it', 'lhduong@hcmiu.edu.vn');

INSERT INTO [User](Username, Password, Role) VALUES ('ltnga','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name, Last_name, Teacher_Id, Department, Mail) VALUES ('Tu Nga', 'Ly', 'ltnga', 'it', 'ltnga@hcmiu.edu.vn');

INSERT INTO [User](Username, Password, Role) VALUES ('mhban','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name, Last_name, Teacher_Id, Department, Mail) VALUES ('Bao An', 'Mai Hoang', 'mhban', 'it', 'mhban@hcmiu.edu.vn');

INSERT INTO [User](Username, Password, Role) VALUES ('nttsang','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name, Last_name, Teacher_Id, Department, Mail) VALUES ('Thanh Sang', 'Nguyen Thi', 'nttsang', 'it', 'nttsang@hcmiu.edu.vn');

INSERT INTO [User](Username, Password, Role) VALUES ('ntky','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name, Last_name, Teacher_Id, Department, Mail) VALUES ('Trung Ky', 'Nguyen', 'ntky', 'it', 'ntky@hcmiu.edu.vn');

INSERT INTO [User](Username, Password, Role) VALUES ('dtnhan','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name, Last_name, Teacher_Id, Department, Mail) VALUES ('Tam Nhan', 'Dang', 'dtnhan', 'it', 'dtnhan@hcmiu.edu.vn');

INSERT INTO [User](Username, Password, Role) VALUES ('dthchau','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name, Last_name, Teacher_Id, Department, Mail) VALUES ('Hoang Chau', 'Dao Tran', 'dthchau', 'it', 'dthchau@hcmiu.edu.vn');

INSERT INTO [User](Username, Password, Role) VALUES ('hnmthong','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name, Last_name, Teacher_Id, Department, Mail) VALUES ('Minh Thong', 'Huynh Nguyen', 'hnmthong', 'it', 'hnmthong@hcmiu.edu.vn');


INSERT INTO [User](Username, Password, Role) VALUES ('nqphu','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name, Last_name, Teacher_Id, Department, Mail) VALUES ('Quang Phu', 'Nguyen', 'nqphu', 'it', 'nqphu@hcmiu.edu.vn');

INSERT INTO [User](Username, Password, Role) VALUES ('pqslam','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name, Last_name, Teacher_Id, Department, Mail) VALUES ('Son Lam', 'Pham Quoc', 'pqslam', 'it', 'pqslam@hcmiu.edu.vn');

INSERT INTO [User](Username, Password, Role) VALUES ('vtthinh','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name, Last_name, Teacher_Id, Department, Mail) VALUES ('Tien Thinh', 'Vu', 'vtthinh', 'el', 'vtthinh@hcmiu.edu.vn');

INSERT INTO [User](Username, Password, Role) VALUES ('dtdngoc','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name, Last_name, Teacher_Id, Department, Mail) VALUES ('Dieu Ngoc', 'Do Thi', 'dtdngoc', 'el', 'dtdngoc@hcmiu.edu.vn');

INSERT INTO [User](Username, Password, Role) VALUES ('nhkhanh','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name, Last_name, Teacher_Id, Department, Mail) VALUES ('Hoang Khanh', 'Nguyen', 'nhkhanh', 'el', 'nhkhanh@hcmiu.edu.vn');

INSERT INTO [User](Username, Password, Role) VALUES ('mdthanh','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name, Last_name, Teacher_Id, Department, Mail) VALUES ('Duc Thanh', 'Mai', 'mdthanh', 'math', 'mdthanh@hcmiu.edu.vn');

INSERT INTO [User](Username, Password, Role) VALUES ('quannm','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name, Last_name, Teacher_Id, Department, Mail) VALUES ('Minh Quan', 'Nguyen', 'quannm', 'math', 'quannm@hcmiu.edu.vn');

INSERT INTO [User](Username, Password, Role) VALUES ('phha','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Teacher')
INSERT INTO Teacher (First_name, Last_name, Teacher_Id, Department, Mail) VALUES ('Hai Ha', 'Pham', 'phha', 'math', 'phha@hcmiu.edu.vn');



INSERT INTO [User](Username, Password,Role) VALUES ('ITDSIU19051','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Student')
INSERT INTO Student (First_name,Last_name,Student_Id,Birth_day,Academic_year,Mail) VALUES ('Truc Quyen ','Huynh','ITDSIU19051','2001/07/05',2019,'ITDSIU19051@hcmiu.edu.vn');
INSERT INTO [User](Username, Password,Role) VALUES ('ITDSIU19017','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Student')
INSERT INTO Student (First_name,Last_name,Student_Id,Birth_day,Academic_year,Mail) VALUES ('Minh Thong','Nguyen Duy','ITDSIU19017','2001-01-01',2019,'ITDSIU19017@hcmiu.edu.vn');
INSERT INTO [User](Username, Password,Role) VALUES ('ITDSIU19060','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Student')
INSERT INTO Student (First_name,Last_name,Student_Id,Birth_day,Academic_year,Mail) VALUES ('Ai Vuong','Nguyen','ITDSIU19060','2001-01-01',2019,'ITDSIU19060@hcmiu.edu.vn');
INSERT INTO [User](Username, Password,Role) VALUES ('ITITIU19059','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Student')
INSERT INTO Student (First_name,Last_name,Student_Id,Birth_day,Academic_year,Mail) VALUES ('Cong Trung','Truong','ITITIU19059','2001-01-01',2019,'ITITIU19059@hcmiu.edu.vn');
INSERT INTO [User](Username, Password,Role) VALUES ('ITITIU20177','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Student')
INSERT INTO Student (First_name,Last_name,Student_Id,Birth_day,Academic_year,Mail) VALUES ('Minh Dang','Le Do','ITITIU20177','2001-01-01',2020,'ITITIU20177@hcmiu.edu.vn');
INSERT INTO [User](Username, Password,Role) VALUES ('ITITIU20213','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Student')
INSERT INTO Student (First_name,Last_name,Student_Id,Birth_day,Academic_year,Mail) VALUES ('Anh Huy','Le Dinh','ITITIU20213','2001-01-01',2020,'ITITIU20213@hcmiu.edu.vn');