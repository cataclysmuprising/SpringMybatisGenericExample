
/* Drop Tables */

IF ObJECt_ID('[tbl_role_action]') IS NOT NULL DROP TABLE [tbl_role_action];
IF ObJECt_ID('[tbl_action]') IS NOT NULL DROP TABLE [tbl_action];
IF ObJECt_ID('[tbl_login_history]') IS NOT NULL DROP TABLE [tbl_login_history];
IF ObJECt_ID('[tbl_user_role]') IS NOT NULL DROP TABLE [tbl_user_role];
IF ObJECt_ID('[tbl_role]') IS NOT NULL DROP TABLE [tbl_role];
IF ObJECt_ID('[tbl_role_action_history]') IS NOT NULL DROP TABLE [tbl_role_action_history];
IF ObJECt_ID('[tbl_role_history]') IS NOT NULL DROP TABLE [tbl_role_history];
IF ObJECt_ID('[tbl_setting]') IS NOT NULL DROP TABLE [tbl_setting];
IF ObJECt_ID('[tbl_setting_history]') IS NOT NULL DROP TABLE [tbl_setting_history];
IF ObJECt_ID('[tbl_user]') IS NOT NULL DROP TABLE [tbl_user];
IF ObJECt_ID('[tbl_static_content]') IS NOT NULL DROP TABLE [tbl_static_content];
IF ObJECt_ID('[tbl_static_content_history]') IS NOT NULL DROP TABLE [tbl_static_content_history];
IF ObJECt_ID('[tbl_user_history]') IS NOT NULL DROP TABLE [tbl_user_history];
IF ObJECt_ID('[tbl_user_role_history]') IS NOT NULL DROP TABLE [tbl_user_role_history];




/* Create Tables */

CREATE TABLE [tbl_action]
(
	[id] int NOT NULL UNIQUE IDENTITY ,
	[page] nvarchar(50) NOT NULL,
	[action_name] nvarchar(50) NOT NULL UNIQUE,
	[display_name] nvarchar(100) NOT NULL UNIQUE,
	-- 0[main-action that is the main page action] 
	-- 1[sub-action that process within a page]
	-- 
	[action_type] bit NOT NULL,
	[url] nvarchar(max) NOT NULL,
	[description] nvarchar(max) NOT NULL,
	[record_reg_id] int NOT NULL,
	[record_upd_id] int NOT NULL,
	[record_reg_date] datetime DEFAULT getDate() NOT NULL,
	[record_upd_date] datetime DEFAULT getDate() NOT NULL,
	PRIMARY KEY ([id])
);


CREATE TABLE [tbl_login_history]
(
	[id] int NOT NULL UNIQUE IDENTITY ,
	[user_id] int NOT NULL,
	[client_ip] nvarchar(50) NOT NULL,
	-- Operating System
	[os] nvarchar(100),
	[browser] nvarchar(100),
	[login_date] datetime NOT NULL,
	[record_reg_id] int NOT NULL,
	[record_upd_id] int NOT NULL,
	[record_reg_date] datetime DEFAULT getDate() NOT NULL,
	[record_upd_date] datetime DEFAULT getDate() NOT NULL,
	PRIMARY KEY ([id])
);


CREATE TABLE [tbl_role]
(
	[id] int NOT NULL UNIQUE IDENTITY ,
	[name] nvarchar(20) NOT NULL UNIQUE,
	[description] nvarchar(200),
	[record_reg_id] int NOT NULL,
	[record_upd_id] int NOT NULL,
	[record_reg_date] datetime DEFAULT getDate() NOT NULL,
	[record_upd_date] datetime DEFAULT getDate() NOT NULL,
	PRIMARY KEY ([id])
);


CREATE TABLE [tbl_role_action]
(
	[action_id] int NOT NULL,
	[role_id] int NOT NULL,
	[record_reg_id] int NOT NULL,
	[record_upd_id] int NOT NULL,
	[record_reg_date] datetime DEFAULT getDate() NOT NULL,
	[record_upd_date] datetime DEFAULT getDate() NOT NULL
);


CREATE TABLE [tbl_role_action_history]
(
	[action_id] int,
	[role_id] int,
	[transaction_date] datetime DEFAULT getDate() NOT NULL,
	[transaction_type] int DEFAULT 0,
	[record_reg_id] int NOT NULL,
	[record_upd_id] int NOT NULL,
	[record_reg_date] datetime DEFAULT getDate() NOT NULL,
	[record_upd_date] datetime DEFAULT getDate() NOT NULL
);


CREATE TABLE [tbl_role_history]
(
	[id] int,
	[name] nvarchar(20),
	[description] nvarchar(200),
	[transaction_date] datetime DEFAULT getDate() NOT NULL,
	[transaction_type] int DEFAULT 0,
	[record_reg_id] int NOT NULL,
	[record_upd_id] int NOT NULL,
	[record_reg_date] datetime DEFAULT getDate() NOT NULL,
	[record_upd_date] datetime DEFAULT getDate() NOT NULL
);


CREATE TABLE [tbl_setting]
(
	[id] int NOT NULL UNIQUE IDENTITY ,
	[setting_name] nvarchar(100) NOT NULL UNIQUE,
	[setting_value] nvarchar(100) NOT NULL,
	[setting_type] nvarchar(100) NOT NULL,
	[setting_group] nvarchar(100) NOT NULL,
	[setting_sub_group] nvarchar(50) NOT NULL,
	[record_reg_id] int NOT NULL,
	[record_upd_id] int NOT NULL,
	[record_reg_date] datetime DEFAULT getDate() NOT NULL,
	[record_upd_date] datetime DEFAULT getDate() NOT NULL,
	PRIMARY KEY ([id])
);


CREATE TABLE [tbl_setting_history]
(
	[id] int,
	[setting_name] nvarchar(100),
	[setting_value] nvarchar(100),
	[setting_type] nvarchar(100),
	[setting_group] nvarchar(100),
	[setting_sub_group] nvarchar(50),
	[transaction_date] datetime DEFAULT getDate() NOT NULL,
	[transaction_type] int DEFAULT 0,
	[record_reg_id] int NOT NULL,
	[record_upd_id] int NOT NULL,
	[record_reg_date] datetime DEFAULT getDate() NOT NULL,
	[record_upd_date] datetime DEFAULT getDate() NOT NULL
);


CREATE TABLE [tbl_static_content]
(
	[id] int NOT NULL UNIQUE IDENTITY ,
	[file_name] nvarchar(max) NOT NULL,
	[file_path] nvarchar(max) NOT NULL,
	[file_size] varchar(20),
	[file_type] int NOT NULL,
	[record_reg_id] int NOT NULL,
	[record_upd_id] int NOT NULL,
	[record_reg_date] datetime DEFAULT getDate() NOT NULL,
	[record_upd_date] datetime DEFAULT getDate() NOT NULL,
	PRIMARY KEY ([id])
);


CREATE TABLE [tbl_static_content_history]
(
	[id] int,
	[file_name] nvarchar(max),
	[file_path] nvarchar(max),
	[file_size] varchar(20),
	[file_type] int,
	[transaction_date] datetime DEFAULT getDate() NOT NULL,
	[transaction_type] int DEFAULT 0,
	[record_reg_id] int NOT NULL,
	[record_upd_id] int NOT NULL,
	[record_reg_date] datetime DEFAULT getDate() NOT NULL,
	[record_upd_date] datetime DEFAULT getDate() NOT NULL
);


CREATE TABLE [tbl_user]
(
	[id] int NOT NULL UNIQUE IDENTITY ,
	[login_id] nvarchar(50) NOT NULL UNIQUE,
	[content_id] int NOT NULL UNIQUE,
	[age] int NOT NULL,
	[name] nvarchar(50) NOT NULL,
	[gender] bit NOT NULL,
	[email] nvarchar(50) NOT NULL UNIQUE,
	[password] nvarchar(500) NOT NULL,
	[nrc] nvarchar(50) NOT NULL,
	[phone] nvarchar(50) NOT NULL,
	[dob] nvarchar(50),
	[address] nvarchar(max),
	[record_reg_id] int NOT NULL,
	[record_upd_id] int NOT NULL,
	[record_reg_date] datetime DEFAULT getDate() NOT NULL,
	[record_upd_date] datetime DEFAULT getDate() NOT NULL,
	PRIMARY KEY ([id])
);


CREATE TABLE [tbl_user_history]
(
	[id] int,
	[login_id] nvarchar(50),
	[content_id] int,
	[age] int,
	[name] nvarchar(50),
	[gender] bit,
	[email] nvarchar(50),
	[password] nvarchar(500),
	[nrc] nvarchar(50),
	[phone] nvarchar(50),
	[dob] nvarchar(50),
	[address] nvarchar(max),
	[transaction_date] datetime DEFAULT getDate() NOT NULL,
	[transaction_type] int DEFAULT 0,
	[record_reg_id] int NOT NULL,
	[record_upd_id] int NOT NULL,
	[record_reg_date] datetime DEFAULT getDate() NOT NULL,
	[record_upd_date] datetime DEFAULT getDate() NOT NULL
);


CREATE TABLE [tbl_user_role]
(
	[user_id] int NOT NULL,
	[role_id] int NOT NULL,
	[record_reg_id] int NOT NULL,
	[record_upd_id] int NOT NULL,
	[record_reg_date] datetime DEFAULT getDate() NOT NULL,
	[record_upd_date] datetime DEFAULT getDate() NOT NULL
);


CREATE TABLE [tbl_user_role_history]
(
	[user_id] int,
	[role_id] int,
	[transaction_date] datetime DEFAULT getDate() NOT NULL,
	[transaction_type] int DEFAULT 0,
	[record_reg_id] int NOT NULL,
	[record_upd_id] int NOT NULL,
	[record_reg_date] datetime DEFAULT getDate() NOT NULL,
	[record_upd_date] datetime DEFAULT getDate() NOT NULL
);



/* Create Foreign Keys */

ALTER TABLE [tbl_role_action]
	ADD CONSTRAINT [frk_action_role] FOREIGN KEY ([action_id])
	REFERENCES [tbl_action] ([id])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE [tbl_role_action]
	ADD CONSTRAINT [frk_role_action] FOREIGN KEY ([role_id])
	REFERENCES [tbl_role] ([id])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE [tbl_user_role]
	ADD CONSTRAINT [frk_role_user] FOREIGN KEY ([role_id])
	REFERENCES [tbl_role] ([id])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE [tbl_user]
	ADD CONSTRAINT [frk_user_content] FOREIGN KEY ([content_id])
	REFERENCES [tbl_static_content] ([id])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE [tbl_user_role]
	ADD CONSTRAINT [frk_user_role] FOREIGN KEY ([user_id])
	REFERENCES [tbl_user] ([id])
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;



