use master
go
if exists(select * from sys.databases where name='hotelDB')
drop database hotelDB
go
create database hotelDB
on
(
	name=hotelDB,
	filename='d:\data\hotelDB.mdf'
)
go
use hotelDB
go
--客房类型表
create table roomType
(
	typeId int primary key identity(1,1),--主键
	typeName varchar(20),--类型名称
	typePrice int,--价格
	typeDeposit int,--押金
	typeTimg nvarchar(220),
	typeRemark  varchar(500)--备注
)
--客房表
create table room
(
	roomNum varchar(20) primary key,--房间编号
	typeId int references roomType(typeId),--类型编号
	roomPhone varchar(7) ,--房间电话
	roomAvailable bit default(0),--是否可用，0可用，1不可用
	roomState int check(roomState>=0 and roomState<=3),--房间状态，0可入住，1已入住，2被预定，3被清洁
	roomRemark varchar(500)--备注
)
--员工信息表
create table employeeInfo
(
	empId int primary key identity(1,1),--主键
	empAccountNum varchar(10),--账号
	empPassword varchar(50),--密码
	empName varchar(10),--名字
	empSex varchar(2),--性别
	empIdNum varchar(18),--身份证号
	empPhone varchar(11),--手机号码
	empAddress varchar(50),--住址
	empAdmin bit default(0),--是否超管，0不是，1是
	empRemark varchar(500),--备注
	empImg varchar(500)--图片
)
--预定表
create table roomReserve
(
	reserveId int primary key identity(1,1),--主键
	roomNum varchar(20) references room(roomNum),--房间编号
	empId int references employeeInfo(empId),--操作员
	reserveTime dateTime,--预定时间
	customerName varchar(10),--客户名字
	customerPhone varchar(11),--客户电话
	reserveRemark varchar(500)
)
--入住登记表
create table roomCheck
(
	checkId int primary key identity(1,1),--主键
	roomNum varchar(20) references room(roomNum),--房间编号
	empId int references employeeInfo(empId),--操作员
	customerName varchar(10),--客户名字
	customerPhone varchar(11),--客户电话
	customerIdNum varchar(18),--证件号码
	checkTime dateTime default(getDate()),--入住时间
	checkHour bit default(0),--是否钟点房，0否，1是
	checkRemark varchar(500)--备注
)

--结账表
create table checkOut
(
	outId int primary key identity(1,1),--主键
	roomNum varchar(20) references room(roomNum),--房间编号
	empId int references employeeInfo(empId),--操作员
	outTime dateTime default(getDate()),--退房时间
	outDiscount float,--折扣
	outRecivalble float,--应收金额
	outActual float,--实收金额
	outRemark varchar(500)--备注
)


--客户信息表
create table customerInfo
(
	customerId int primary key identity(10000,1),--编号
	customerName varchar(10),--客户名字
	customerPhone varchar(11),--客户电话
	customerIdNum varchar(18),--证件号码
	customerVIP bit default(0),--是否VIP,0不是，1是
	customerCount int,--入住次数
	customerRemark varchar(500)--备注
)

--VIP消费表
create table vipConsumption
(
	vipId int primary key identity(1,1),--编号
	customerId int references customerInfo(customerId),--编号
	empId int references employeeInfo(empId),--操作员
	vipRecord float default(0),--充值金额
	vipOut float default(0),--消费金额
	vipBlance float,--余额
	vipIntegral int,--积分
	inTime datetime default(getdate()),--充值时间
	vipRemark varchar(500)--备注
)
go
--数据备份表
create table dataBackup(
	dataId int primary key identity(1,1),
	dataName varchar(100),
	dataSize varchar(20),
	dataTime datetime
)
go
insert into dataBackup values('hotelDB.bak','3.45MB',GETDATE())
go

create procedure sp_Orders  --办理vip
@customerName varchar(10), @customerIdNum varchar(20) ,@customerPhone varchar(20)
as 
	declare @customerId int = 0
	select @customerId=customerId from customerInfo where customerName=@customerName and customerIdNum=@customerIdNum
	if(@customerId > 0)   --判断当前客户是否在客户表里有记录
	begin
		update customerInfo set customerVIP = 1 where customerId = @customerId 
	end
	else
	begin
		insert customerInfo values(@customerName,@customerPhone,@customerIdNum,1,0,'')
	end
go 

--create alter
create procedure sp_vipRecord  --Vip充值
@customerName varchar(10), @customerId int ,@vipRecord float ,@empId int
as 
	declare @vipBlance int ,@vipIntegral int
	select top 1 @vipBlance=vipBlance,@vipIntegral=vipIntegral from vipConsumption where customerId = @customerId order by vipBlance desc 
	insert into vipConsumption (customerId, empId,vipRecord,vipOut,vipBlance,vipIntegral) VALUES (@customerId,@empId, @vipRecord,0,(@vipBlance+@vipRecord),(@vipRecord+@vipIntegral))
	
go --用户编号 操作员、充值金额


insert into roomType values
('单人房',80,50,'timg(1).jpg',''),
('双人房',100,50,'timg(2).jpg',''),
('豪华房',150,50,'timg(5).jpg',''),
('商务间',200,100,'timg(3).jpg','')
go

insert into room values
('1001',1,'2281001',0,0,''),
('1002',1,'2281002',0,0,''),
('1003',1,'2281003',0,0,''),
('1004',1,'2281004',0,0,''),
('1005',1,'2281005',0,0,''),
('1006',1,'2281006',0,0,''),
('1007',1,'2281007',0,0,''),
('1008',1,'2281008',0,0,''),
('1009',1,'2281009',0,0,''),
('1010',1,'2281010',1,0,''),
('1011',1,'2281011',0,0,''),
('1012',1,'2281012',0,0,''),
('1013',1,'2281013',0,0,''),
('1014',1,'2281014',0,0,''),
('1015',1,'2281015',0,0,''),
('1016',1,'2281016',0,0,''),
('1017',1,'2281017',0,0,''),
('1018',1,'2281018',0,0,''),
('1019',1,'2281019',0,0,''),
('1020',1,'2281020',0,0,''),
('2010',2,'2282010',0,0,''),
('2011',2,'2282011',0,0,''),
('2012',2,'2282012',0,0,''),
('2013',2,'2282013',0,0,''),
('2014',2,'2282014',0,0,''),
('2015',2,'2282015',1,0,''),
('2016',2,'2282016',0,0,''),
('2017',2,'2282017',0,0,''),
('2018',2,'2282018',0,0,''),
('2019',2,'2282019',0,0,''),
('2020',2,'2282020',0,0,''),
('2021',2,'2282021',0,0,''),
('2022',2,'2282022',0,0,''),
('2023',2,'2282023',0,0,''),
('2024',2,'2282024',0,0,''),
('2025',2,'2282025',0,0,''),
('3026',3,'2283026',0,0,''),
('3027',3,'2283027',1,0,''),
('3028',3,'2283028',0,0,''),
('3029',3,'2283029',0,0,''),
('3030',3,'2283030',0,0,''),
('3031',3,'2283031',0,3,''),
('3032',3,'2283032',0,0,''),
('3033',3,'2283033',0,0,''),
('3034',3,'2283034',0,0,''),
('3035',3,'2283035',0,0,''),
('3036',3,'2283036',0,0,''),
('3037',3,'2283037',0,0,''),
('3038',3,'2283038',0,0,''),
('3039',3,'2283039',0,0,''),
('3040',3,'2283040',0,0,''),
('3041',3,'2283041',0,0,''),
('3042',3,'2283042',0,0,''),
('3043',3,'2283043',0,3,''),
('3044',3,'2283044',0,0,''),
('3045',3,'2283045',0,0,''),
('4001',4,'2284001',0,0,''),
('4002',4,'2284002',0,0,''),
('4003',4,'2284003',0,0,''),
('4004',4,'2284004',0,3,''),
('4005',4,'2284005',0,0,''),
('4006',4,'2284006',0,0,''),
('4007',4,'2284007',0,0,''),
('4008',4,'2284008',0,0,''),
('4009',4,'2284009',0,0,''),
('4010',4,'2284010',0,0,'')
go




insert into employeeInfo values
('SuperAdmin','e10adc3949ba59abbe56e057f20f883e','','','','','',1,'','user.png'),
('liu001','e10adc3949ba59abbe56e057f20f883e','刘天剑','男','412827199102234191','13902312212','珠海市斗门白蕉36号',0,'','user1.jpg'),
('Xia001','e10adc3949ba59abbe56e057f20f883e','夏冰糖','女','412827199512048918','13989231231','珠海市香洲前山103号',0,'','user2.jpg'),
('Yang001','e10adc3949ba59abbe56e057f20f883e','杨小生','男','412827199708134212','13990908878','珠海市拱北迎宾209号',0,'','user3.jpg'),
('Zhang001','e10adc3949ba59abbe56e057f20f883e','张美玲','女','412827199805235910','13909211210','珠海市香洲南屏36号',0,'','user4.jpg'),
('Yin001','e10adc3949ba59abbe56e057f20f883e','殷淑淑','女','412827199305138671','13902312212','珠海市斗门白蕉306号',0,'','user5.jpg'),
('Chang001','e10adc3949ba59abbe56e057f20f883e','畅舒','女','412827199404137895','13902322469','珠海市香洲金华36号',0,'','user6.jpg')
go
--创建触发器，当向预定表roomReserve里面添加内容时，改变房间表room里面的预定状态
create trigger tri_roomReserve_insert on roomReserve
after insert 
as
	declare @roomNum varchar(20)
	select @roomNum=roomNum from inserted
	update room set roomState=2 where roomNum=@roomNum
go
insert into roomReserve values
('1001',1,getdate(),'李思','13952320953','')
insert into roomReserve values
('1010',2,dateadd(day,-1,GETDATE()),'赵柳','13190512453','')
insert into roomReserve values
('2012',3,getdate(),'李福','13075125353','')
insert into roomReserve values
('2015',4,dateadd(day,-1,GETDATE()),'张田民','13809512212','')
insert into roomReserve values
('3028',5,getdate(),'王珂','13956211890','')
insert into roomReserve values
('2024',2,dateadd(day,-1,GETDATE()),'张名城','15092351212','')
insert into roomReserve values
('3036',3,getdate(),'胡彬彬','13178092412','')
insert into roomReserve values
('4001',1,dateadd(day,-1,GETDATE()),'王珂','13956211890','')
go

insert into customerInfo values
('张山','13908786541','350122197101189144',0,10,''),
('李思','13952320953','610601197503017897',1,20,''),
('王尔','13989013423','152127198503029232',0,30,''),
('王武','13989231532','622126200011148493',0,40,''),
('赵柳','13190512453','130622197012059854',1,21,''),
('田棋','13085235512','420582197402279924',0,11,''),
('金正','13580924125','430702199503056009',0,42,''),
('李福','13075125353','142603198711289092',1,12,''),
('张单','13908786541','640221199703272006',0,41,''),
('王县安','13905123212','412928199102224509',0,10,''),
('张田民','13809512212','512353197009124098',1,4,''),
('鲁德育','13109782131','412852198019221245',0,5,''),
('项华羽','15378975121','412938199108083297',0,16,''),
('张名城','15092351212','670823199409102312',1,21,''),
('刘飞','18609877655','624689199209217822',0,16,''),
('张伟','18609874201','512351199104052321',1,15,''),
('胡彬彬','13178092412','624689199209217822',1,19,''),
('王珂','13956211890','512351199104052321',1,20,'')
go

--创建触发器，当向入住表roomCheck里面添加内容时，改变房间表room里面的入住状态,查询客户是否存在，如果不存在就插入数据
create trigger tri_roomCheck_insert on roomCheck
after insert
as
	declare @roomNum varchar(20),@IdNum varchar(40),@customerName varchar(20),@customerPhone varchar(20)
	select @roomNum=roomNum,@IdNum=customerIdNum,@customerName=customerName,@customerPhone=customerPhone from inserted
	update room set roomState=1 where roomNum=@roomNum
	--if not exists(select * from customerInfo where customerIdNum=@IdNum)
		--insert into customerInfo values(@customerName,@customerPhone,@IdNum,0,0,'')
go

insert into roomCheck values
('1003',2,'张山','13908786541','350122197101189144',dateadd(day,-1,GETDATE()),0,'')
insert into roomCheck values
('2011',3,'李思','13952320953','610601197503017897',dateadd(day,-2,GETDATE()),0,'')
insert into roomCheck values
('1006',4,'王尔','13989013423','152127198503029232',dateadd(day,-3,GETDATE()),0,'')
insert into roomCheck values
('1004',5,'王武','13989231532','622126200011148493',dateadd(day,-1,GETDATE()),0,'')
insert into roomCheck values
('3026',6,'赵柳','13190512453','130622197012059854',dateadd(day,-2,GETDATE()),0,'')
insert into roomCheck values
('3030',7,'田棋','13085235512','420582197402279924',dateadd(day,-3,GETDATE()),0,'')
insert into roomCheck values
('2018',2,'金正','13580924125','430702199503056009',dateadd(day,-2,GETDATE()),0,'')
insert into roomCheck values
('4005',5,'李福','13075125353','142603198711289092',dateadd(day,-1,GETDATE()),0,'')
insert into roomCheck values
('2010',6,'张点点','13908786541','640221199703272006',dateadd(day,-3,GETDATE()),0,'')
go



--创建触发器，当向结账表checkOut里面添加内容时，客户入住次数加一
create trigger tri_checkOut_insert on checkOut
after insert
as
	declare @roomNum varchar(20),@customerIdNum varchar(18)
	select @roomNum=roomNum from inserted
	select top(1) @customerIdNum=customerIdNum from roomCheck where roomNum=@roomNum order by checkTime desc --查询入住的客户信息
	update customerInfo set customerCount+=1 where customerIdNum=@customerIdNum--入住次数加一
go


insert into checkOut values
('1003',2,getdate(),9,80,72,'')
insert into checkOut values
('2011',5,getdate(),0,100,100,'')
insert into checkOut values
('1007',3,getdate(),9,150,135,'')
insert into checkOut values
('1005',6,getdate(),9,200,180,'')
insert into checkOut values
('3026',2,getdate(),0,80,80,'')
insert into checkOut values
('2010',4,getdate(),0,100,100,'')
go
insert into checkOut values
('1001',1,'2018-01-12',0,80,80,''),
('1002',2,'2018-01-21',0,160,160,''),
('1003',3,'2018-01-12',0,80,80,''),
('1004',4,'2018-01-14',0,160,160,''),
('1005',5,'2018-01-12',0,80,80,''),
('1006',6,'2018-01-01',0,80,80,''),
('1007',1,'2018-01-23',0,240,240,''),
('1008',2,'2018-01-31',0,80,80,''),
('1009',3,'2018-01-19',0,80,80,''),
('1010',1,'2018-02-12',0,80,80,''),
('1011',2,'2018-02-21',0,160,160,''),
('1020',3,'2018-02-12',0,80,80,''),
('2010',4,'2018-02-14',0,160,160,''),
('2014',5,'2018-02-12',0,120,120,''),
('2015',6,'2018-02-01',0,120,120,''),
('2016',3,'2018-02-23',0,240,240,''),
('3038',5,'2018-02-17',0,80,80,''),
('4010',1,'2018-02-19',0,80,80,''),
('1001',1,'2018-03-12',0,80,80,''),
('1002',2,'2018-03-21',0,160,160,''),
('1003',3,'2018-03-12',0,80,80,''),
('1004',4,'2018-03-14',0,160,160,''),
('1005',5,'2018-03-12',0,80,80,''),
('1006',6,'2018-03-01',0,80,80,''),
('1007',1,'2018-03-23',0,240,240,''),
('1005',5,'2018-04-12',0,80,80,''),
('1006',6,'2018-04-01',0,80,80,''),
('1007',1,'2018-04-23',0,240,240,''),
('1008',2,'2018-04-25',0,80,80,''),
('1009',4,'2018-04-19',0,80,80,''),
('1010',1,'2018-04-12',0,80,80,''),
('1011',2,'2018-04-21',0,160,160,''),
('1020',3,'2018-04-12',0,80,80,''),
('2010',4,'2018-04-14',0,160,160,''),
('2014',5,'2018-04-12',0,120,120,''),
('4010',4,'2018-05-19',0,80,80,''),
('1001',1,'2018-05-12',0,80,80,''),
('1002',2,'2018-05-21',0,160,160,''),
('1003',3,'2018-05-12',0,80,80,''),
('1004',4,'2018-05-14',0,160,160,''),
('1005',5,'2018-05-12',0,80,80,''),
('1006',6,'2018-05-01',0,80,80,''),
('1007',2,'2018-05-23',0,240,240,''),
('1005',5,'2018-05-12',0,80,80,''),
('1006',6,'2018-05-01',0,80,80,''),
('1007',4,'2018-05-23',0,240,240,''),
('1008',1,'2018-05-31',0,80,80,''),
('1009',2,'2018-05-19',0,80,80,''),
('1008',2,'2018-06-21',0,80,80,''),
('1009',4,'2018-06-19',0,80,80,''),
('1010',1,'2018-06-12',0,80,80,''),
('1011',2,'2018-06-21',0,160,160,''),
('1020',3,'2018-06-12',0,80,80,''),
('2010',4,'2018-06-14',0,160,160,''),
('2014',5,'2018-06-12',0,120,120,''),
('4010',4,'2018-06-19',0,80,80,''),
('1001',1,'2018-06-12',0,80,80,''),
('1002',2,'2018-06-21',0,160,160,''),
('1003',3,'2018-06-12',0,80,80,'')
go




insert into vipConsumption values
('10001',1,1000,0,100,1000,'2017-12-12',''),
('10004',2,1000,0,100,1000,'2017-12-31',''),
('10007',3,1000,0,100,1000,'2017-10-11',''),
('10010',4,1000,0,100,1000,'2017-04-12',''),
('10013',5,1000,0,100,1000,'2017-05-12',''),
('10015',6,1000,0,100,1000,'2017-07-15',''),
('10001',7,0,150,850,1000,'2017-05-18',''),
('10004',2,0,100,900,1000,'2017-03-21',''),
('10007',1,0,450,550,1000,'2017-04-24',''),
('10010',3,0,200,800,1000,'2017-10-11',''),
('10013',4,0,100,900,1000,'2017-08-15',''),
('10015',5,0,180,820,1000,'2017-09-12',''),
('10001',1,100,0,950,1100,'2017-12-12',''),
('10010',7,100,0,900,1100,'2017-12-12',''),
('10013',2,100,0,1000,1100,'2017-12-12','')
go



--创建还原数据库的存储过程该,master数据库执行
create proc dataRestore (@dbname varchar(20),@dbpath varchar(40))       
as       
begin       
declare @sql   nvarchar(500)       
declare @spid  int       
set @sql='declare getspid cursor for select spid from sysprocesses where dbid=db_id('''+@dbname+''')'       
exec (@sql)       
open getspid       
fetch next from getspid into @spid       
while @@fetch_status <> -1       
begin       
	exec('kill '+@spid)       
	fetch next from getspid into @spid       
end       
close getspid       
deallocate getspid       
restore database @dbname from disk= @dbpath with replace
end 



select * from room
select * from roomType
select * from roomReserve
select * from roomCheck
select * from checkOut
select * from customerInfo
select * from employeeInfo
select * from vipConsumption
select * from dataBackup
go

