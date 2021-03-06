USE [master]
GO
/****** Object:  Database [StudentRecord]    Script Date: 5/15/2022 8:29:56 PM ******/
CREATE DATABASE [StudentRecord]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'StudentRecord', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\StudentRecord.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'StudentRecord_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\StudentRecord_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [StudentRecord] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [StudentRecord].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [StudentRecord] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [StudentRecord] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [StudentRecord] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [StudentRecord] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [StudentRecord] SET ARITHABORT OFF 
GO
ALTER DATABASE [StudentRecord] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [StudentRecord] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [StudentRecord] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [StudentRecord] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [StudentRecord] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [StudentRecord] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [StudentRecord] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [StudentRecord] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [StudentRecord] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [StudentRecord] SET  DISABLE_BROKER 
GO
ALTER DATABASE [StudentRecord] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [StudentRecord] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [StudentRecord] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [StudentRecord] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [StudentRecord] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [StudentRecord] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [StudentRecord] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [StudentRecord] SET RECOVERY FULL 
GO
ALTER DATABASE [StudentRecord] SET  MULTI_USER 
GO
ALTER DATABASE [StudentRecord] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [StudentRecord] SET DB_CHAINING OFF 
GO
ALTER DATABASE [StudentRecord] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [StudentRecord] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [StudentRecord] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [StudentRecord] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'StudentRecord', N'ON'
GO
ALTER DATABASE [StudentRecord] SET QUERY_STORE = OFF
GO
USE [StudentRecord]
GO
/****** Object:  Table [dbo].[Administrator]    Script Date: 5/15/2022 8:29:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Administrator](
	[Admin_name] [char](50) NOT NULL,
	[Admin_Id] [char](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Admin_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Class]    Script Date: 5/15/2022 8:29:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Class](
	[Section] [char](30) NOT NULL,
	[Size] [int] NOT NULL,
	[Course_Id] [char](30) NOT NULL,
	[Teacher_Id] [char](30) NOT NULL,
	[Midterm_percentage] [int] NULL,
	[Inclass_percentage] [int] NULL,
	[Final_percentage] [int] NULL,
	[Remaining] [int] NULL,
	[Year-Semester] [nchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[Section] ASC,
	[Course_Id] ASC,
	[Teacher_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Course]    Script Date: 5/15/2022 8:29:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Course](
	[Course_Id] [char](30) NOT NULL,
	[Course_name] [char](100) NOT NULL,
	[Credits] [int] NOT NULL,
	[Description] [char](500) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Course_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Enrolled_Class]    Script Date: 5/15/2022 8:29:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Enrolled_Class](
	[In_class] [numeric](5, 2) NOT NULL,
	[Midterm] [numeric](5, 2) NOT NULL,
	[Final] [numeric](5, 2) NOT NULL,
	[Section] [char](30) NOT NULL,
	[Course_Id] [char](30) NOT NULL,
	[Teacher_Id] [char](30) NOT NULL,
	[Student_Id] [char](30) NOT NULL,
	[Year-Semester] [char](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[Section] ASC,
	[Course_Id] ASC,
	[Teacher_Id] ASC,
	[Student_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Scholarship]    Script Date: 5/15/2022 8:29:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Scholarship](
	[Semester] [int] NOT NULL,
	[Tuition] [int] NOT NULL,
	[Scholarship_type] [char](1) NOT NULL,
	[Year] [int] NOT NULL,
	[Student_Id] [char](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Student_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Semester_billing]    Script Date: 5/15/2022 8:29:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Semester_billing](
	[Money_received] [float] NOT NULL,
	[Semeter] [int] NOT NULL,
	[Year] [int] NOT NULL,
	[Student_Id] [char](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Student_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Student]    Script Date: 5/15/2022 8:29:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Student](
	[First_name] [char](30) NOT NULL,
	[Last_name] [char](30) NOT NULL,
	[Birth_day] [char](12) NOT NULL,
	[Academic_year] [int] NOT NULL,
	[Mail] [char](50) NOT NULL,
	[Student_Id] [char](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Student_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Teacher]    Script Date: 5/15/2022 8:29:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Teacher](
	[Department] [char](100) NOT NULL,
	[First_name] [char](30) NOT NULL,
	[Last_name] [char](30) NOT NULL,
	[Mail] [char](50) NOT NULL,
	[Teacher_Id] [char](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Teacher_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tuition_fee]    Script Date: 5/15/2022 8:29:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tuition_fee](
	[Price_per_credit] [float] NOT NULL,
	[Semeter] [int] NOT NULL,
	[Year] [int] NOT NULL,
	[Insurance_price] [float] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Semeter] ASC,
	[Year] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 5/15/2022 8:29:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[Username] [char](30) NOT NULL,
	[Password] [char](100) NOT NULL,
	[Role] [char](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Administrator]  WITH CHECK ADD FOREIGN KEY([Admin_Id])
REFERENCES [dbo].[User] ([Username])
GO
ALTER TABLE [dbo].[Class]  WITH CHECK ADD FOREIGN KEY([Course_Id])
REFERENCES [dbo].[Course] ([Course_Id])
GO
ALTER TABLE [dbo].[Class]  WITH CHECK ADD FOREIGN KEY([Teacher_Id])
REFERENCES [dbo].[Teacher] ([Teacher_Id])
GO
ALTER TABLE [dbo].[Enrolled_Class]  WITH CHECK ADD FOREIGN KEY([Student_Id])
REFERENCES [dbo].[Student] ([Student_Id])
GO
ALTER TABLE [dbo].[Enrolled_Class]  WITH CHECK ADD FOREIGN KEY([Section], [Course_Id], [Teacher_Id])
REFERENCES [dbo].[Class] ([Section], [Course_Id], [Teacher_Id])
GO
ALTER TABLE [dbo].[Scholarship]  WITH CHECK ADD FOREIGN KEY([Student_Id])
REFERENCES [dbo].[Student] ([Student_Id])
GO
ALTER TABLE [dbo].[Semester_billing]  WITH CHECK ADD FOREIGN KEY([Student_Id])
REFERENCES [dbo].[Student] ([Student_Id])
GO
ALTER TABLE [dbo].[Semester_billing]  WITH CHECK ADD FOREIGN KEY([Semeter], [Year])
REFERENCES [dbo].[Tuition_fee] ([Semeter], [Year])
GO
ALTER TABLE [dbo].[Student]  WITH CHECK ADD FOREIGN KEY([Student_Id])
REFERENCES [dbo].[User] ([Username])
GO
ALTER TABLE [dbo].[Teacher]  WITH CHECK ADD FOREIGN KEY([Teacher_Id])
REFERENCES [dbo].[User] ([Username])
GO
USE [master]
GO
ALTER DATABASE [StudentRecord] SET  READ_WRITE 
GO
