USE [BD16179]
GO

/****** Object:  Table [BD16179].[tutor_schedule]    Script Date: 12/04/2018 08:59:15 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [BD16179].[tutor_schedule](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[tutor_ra] [int] NOT NULL,
	[initial_hour] [time](0) NOT NULL,
	[duration] [int] NOT NULL,
	[weekday] [int] NOT NULL,
	[place] [varchar](50) NOT NULL,
 CONSTRAINT [PK_tutor_schedule] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [BD16179].[tutor_schedule]  WITH CHECK ADD  CONSTRAINT [FK_tutor_schedule_tutor] FOREIGN KEY([tutor_ra])
REFERENCES [BD16179].[tutor] ([ra])
GO

ALTER TABLE [BD16179].[tutor_schedule] CHECK CONSTRAINT [FK_tutor_schedule_tutor]
GO

