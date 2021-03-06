USE [master]
GO
/****** Object:  Database [TechnicalEvalDB]    Script Date: 2/15/2022 12:36:33 PM ******/
CREATE DATABASE [TechnicalEvalDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'TechnicalEvalDB', FILENAME = N'D:\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\TechnicalEvalDB.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'TechnicalEvalDB_log', FILENAME = N'D:\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\TechnicalEvalDB_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [TechnicalEvalDB] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [TechnicalEvalDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [TechnicalEvalDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [TechnicalEvalDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [TechnicalEvalDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [TechnicalEvalDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [TechnicalEvalDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [TechnicalEvalDB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [TechnicalEvalDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [TechnicalEvalDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [TechnicalEvalDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [TechnicalEvalDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [TechnicalEvalDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [TechnicalEvalDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [TechnicalEvalDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [TechnicalEvalDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [TechnicalEvalDB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [TechnicalEvalDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [TechnicalEvalDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [TechnicalEvalDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [TechnicalEvalDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [TechnicalEvalDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [TechnicalEvalDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [TechnicalEvalDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [TechnicalEvalDB] SET RECOVERY FULL 
GO
ALTER DATABASE [TechnicalEvalDB] SET  MULTI_USER 
GO
ALTER DATABASE [TechnicalEvalDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [TechnicalEvalDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [TechnicalEvalDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [TechnicalEvalDB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [TechnicalEvalDB] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [TechnicalEvalDB] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'TechnicalEvalDB', N'ON'
GO
ALTER DATABASE [TechnicalEvalDB] SET QUERY_STORE = OFF
GO
USE [TechnicalEvalDB]
GO
/****** Object:  Table [dbo].[BranchLocation]    Script Date: 2/15/2022 12:36:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BranchLocation](
	[LocationID] [int] IDENTITY(1,1) NOT NULL,
	[LocationDescription] [varchar](50) NOT NULL,
 CONSTRAINT [PK_BranchLocation] PRIMARY KEY CLUSTERED 
(
	[LocationID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[EmployeeTable]    Script Date: 2/15/2022 12:36:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EmployeeTable](
	[employeeID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](50) NOT NULL,
	[Address1] [varchar](50) NOT NULL,
	[City] [varchar](50) NOT NULL,
	[Country] [varchar](50) NOT NULL,
	[telephoneNo] [varchar](15) NULL,
	[role] [int] NOT NULL,
	[email] [varchar](50) NOT NULL,
	[addressLocation] [int] NOT NULL,
 CONSTRAINT [PK_EmployeeTable] PRIMARY KEY CLUSTERED 
(
	[employeeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NotificationReportTable]    Script Date: 2/15/2022 12:36:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NotificationReportTable](
	[notificationID] [int] IDENTITY(1,1) NOT NULL,
	[notificationDate] [varchar](50) NOT NULL,
	[dateNotifiedFor] [varchar](50) NOT NULL,
	[notificationReason] [varchar](10) NOT NULL,
	[notificationLocation] [int] NOT NULL,
 CONSTRAINT [PK_NotificationReportTable] PRIMARY KEY CLUSTERED 
(
	[notificationID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RoleTable]    Script Date: 2/15/2022 12:36:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RoleTable](
	[roleID] [int] IDENTITY(1,1) NOT NULL,
	[roleDescription] [varchar](50) NOT NULL,
 CONSTRAINT [PK_RoleTable] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[EmployeeTable]  WITH CHECK ADD  CONSTRAINT [FK_EmployeeTable_BranchLocation] FOREIGN KEY([addressLocation])
REFERENCES [dbo].[BranchLocation] ([LocationID])
GO
ALTER TABLE [dbo].[EmployeeTable] CHECK CONSTRAINT [FK_EmployeeTable_BranchLocation]
GO
ALTER TABLE [dbo].[EmployeeTable]  WITH CHECK ADD  CONSTRAINT [FK_EmployeeTable_RoleTable] FOREIGN KEY([role])
REFERENCES [dbo].[RoleTable] ([roleID])
GO
ALTER TABLE [dbo].[EmployeeTable] CHECK CONSTRAINT [FK_EmployeeTable_RoleTable]
GO
ALTER TABLE [dbo].[NotificationReportTable]  WITH CHECK ADD  CONSTRAINT [FK_NotificationReportTable_BranchLocation] FOREIGN KEY([notificationLocation])
REFERENCES [dbo].[BranchLocation] ([LocationID])
GO
ALTER TABLE [dbo].[NotificationReportTable] CHECK CONSTRAINT [FK_NotificationReportTable_BranchLocation]
GO
/****** Object:  StoredProcedure [dbo].[checkReports]    Script Date: 2/15/2022 12:36:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[checkReports]
	-- Add the parameters for the stored procedure here
	@dateforCheck varchar(50),
	@result int out
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SET @result = 	(SELECT count(*) FROM dbo.NotificationReportTable WHERE dateNotifiedFor = @dateforCheck)

END
GO
/****** Object:  StoredProcedure [dbo].[reportNotification]    Script Date: 2/15/2022 12:36:33 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[reportNotification]
	-- Add the parameters for the stored procedure here
	@notifDate varchar(50),
	@notifdateFor varchar(50),
	@notifReason varchar(30),
	@notifLocation int
	
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	
INSERT INTO [dbo].[NotificationReportTable]
           ([notificationDate]
           ,[dateNotifiedFor]
           ,[notificationReason],
		   [notificationLocation])
     VALUES
           (@notifDate
           ,@notifdateFor
           ,@notifReason
		   ,@notifLocation)
		   
END
GO
USE [master]
GO
ALTER DATABASE [TechnicalEvalDB] SET  READ_WRITE 
GO
