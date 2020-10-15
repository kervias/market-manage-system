create database MARKET2 on
(
	name=MARKET_DB2,
	filename='d:\xmgl\MARKET_DB2.mdf',
	size=10,
	maxsize=300,
	filegrowth=5%
)
log on
(
	name=MARKET_LOG2,
	filename='d:\xmgl\MARKET_LOG2.ldf',
	size=10,
	maxsize=300,
	filegrowth=5%
)COLLATE Chinese_PRC_CI_AS;
go;
use MARKET2;


-- 供应商表
CREATE TABLE Supplier
(
	id int identity(1,1) not null PRIMARY KEY,
	name varchar(50) not null,
	address varchar(100) not null,
	tel varchar(15) not null
);

-- 类别表
CREATE TABLE Category
(
	id int identity(1,1) not null PRIMARY KEY,
	name varchar(20) not null
);

-- 商品信息表
CREATE TABLE GoodsInfo
(
	sid int not null,
	cid int not null,
	EN13 char(13) not null PRIMARY KEY,
	name varchar(50) not null,
	info varchar(300) not null,
	FOREIGN KEY(sid) REFERENCES Supplier(id),
	FOREIGN KEY(cid) REFERENCES Category(id)
);

-- 商品表（包括生成批次）
CREATE TABLE Goods
(
	id char(12) not null PRIMARY KEY,
	EN13 char(13) not null,
	batch varchar(10) not null, -- 生产批次
	prodDate datetime not null,  -- 生产日期
	expDate tinyint not null, -- 保质期
	unit varchar(8) not null, --单位：盒、个...
	FOREIGN KEY(EN13) REFERENCES GoodsInfo(EN13),
	buyPrice float not null, -- 进价
	price float not null, -- 售价
	discount float not null check(discount >= 0 and discount <= 1), -- 折扣
	shelfQuantity int not null,
);


--- 角色表
CREATE TABLE Role
(
	id int not null identity(1,1) PRIMARY KEY,
	name varchar(20) not null,  -- 角色名称
	info varchar(200) not null, -- 角色信息描述
);

-- 员工信息表
CREATE TABLE Employee
(
	id int not null identity(1,1) PRIMARY KEY,
	name varchar(16) not null,
	gender bit not null,
	birth datetime not null,
	tel varchar(15) not null,
	email varchar(100) not null unique,
	username varchar(20) not null,
	password char(32) not null,
	forbidden bit not null,  -- 账户是否被禁用
	rid int not null, --角色
	salt char(32) not null,
	FOREIGN KEY(rid) REFERENCES Role(id)
);

-- 仓库表
CREATE TABLE WareHouse
(
	id int not null identity(1,1) PRIMARY KEY,
	name varchar(30) not null,
	info varchar(200) not null,
	address varchar(60) not null
);

-- 库存表
CREATE TABLE Stock
(
	gid char(12) not null,
	wid int not null,
	quantity int not null,
	threshold int not null,
	FOREIGN KEY(gid) REFERENCES Goods(id),
	FOREIGN KEY(wid) REFERENCES WareHouse(id),
	PRIMARY KEY(gid,wid)
);

-- 入库表
CREATE TABLE InBound
(
	id int not null identity(1,1) PRIMARY KEY,
	gid char(12) not null,
	wid int not null,
	eid int not null,
	opTime datetime not null,
	quantity int not null,
	FOREIGN KEY(gid) REFERENCES Goods(id),
	FOREIGN KEY(wid) REFERENCES WareHouse(id),
	FOREIGN KEY(eid) REFERENCES Employee(id)
);

-- 出库表
CREATE TABLE OutBound
(
	id int not null identity(1,1) PRIMARY KEY,
	gid char(12) not null,
	wid int not null,
	eid int not null,
	opTime datetime not null,
	quantity int not null,
	reason tinyint not null,  -- 出库原因
	FOREIGN KEY(gid) REFERENCES Goods(id),
	FOREIGN KEY(wid) REFERENCES WareHouse(id),
	FOREIGN KEY(eid) REFERENCES Employee(id)
);

-- 订单表
CREATE TABLE OrderList
(
	id char(24) not null PRIMARY KEY, -- 订单编号
	createTime datetime not null,  -- 订单创建时间
	payTime datetime,    -- 订单付款时间
	amount float not null, -- 订单总金额
	status smallint not null, -- 订单支付状态
);

-- 订单明细
CREATE TABLE OrderDetail
(
	gid char(12) not null,
	oid char(24) not null,
	quantity int not null,
	discount float not null check(discount >= 0 and discount <= 1),
	FOREIGN KEY(oid) REFERENCES OrderList(id),
	FOREIGN KEY(gid) REFERENCES Goods(id),
	PRIMARY KEY(oid, gid)
);

CREATE TABLE YearSale
(
    id char(4) not null primary key, -- 年份
    amount float not null,  -- 总金额
    cost float not null -- 成本
)

CREATE TABLE MonthSale
(
    id char(6) not null primary key, -- 月份
    amount float not null,  -- 总金额
    cost float not null -- 成本
)

CREATE TABLE DaySale
(
    id char(8) not null primary key, -- 每天
    amount float not null,  -- 总金额
    cost float not null -- 成本
)