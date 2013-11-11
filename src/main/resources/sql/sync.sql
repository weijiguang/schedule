---======================开始 入院登记数据同步任务SQL语句（正式版） 开始==========================================
--autor:wei
--time:2012-9-18
--对应字段：
--数据分割条件限定语句，从目标（ORACLE）数据库查找最后同步的病人信息记录语句。
SELECT CASE WHEN MAX(IP_TIME) IS NULL THEN '1970-01-01 00:00:00' ELSE REPLACE(MAX(IP_TIME),'.','-') END AS IP_TIME FROM IP_REGISTER;

-- 与HIS数据库的病人信息表关联字段数据查询语句
SELECT BLH,BRXM,BRZT,CSRQ,DWDH,DWDZ,GX,HF,
JG,KSBH,LRY,LXDH,LXRDZ,LXRXM,MJZD,MZ,
MZYS,RYRQ,SFLB,SFZH,XB,ZY,ZYCS,ZZDH FROM ZY_BRZL;

--重命名成与目标数据库表字段名一致的查询语句
SELECT BLH AS IP_NO,
BRXM AS NAME,
BRZT AS IP_CASE,
CSRQ AS BIRTH,
DWDH AS C_TEL,
DWDZ AS CORP,
GX AS RELATION,
HF AS MARRY,
JG AS BIRTHAREA,
KSBH AS IP_DEPT,
LRY AS IP_OPER,
LXDH AS CONT_TEL,
LXRDZ AS CONT_ADDR,
LXRXM AS CONTACT,
MJZD AS CL_EDIAG,
MZ AS NATION,
MZYS AS IP_DR,
REPLACE(CONVERT(NVARCHAR(10),RYRQ,120),'-','.') AS IP_DATE,
REPLACE(CONVERT(NVARCHAR(23),RYRQ,120),'-','.') AS IP_TIME,
SFLB AS FEE_CODE,
SFZH AS ID,
XB AS SEX,
ZY AS JOB,
ZZ AS ADDR,
ZYCS AS IP_CNT,
ZZDH AS TEL
FROM ZY_BRZL where RYRQ > CONVERT(varchar(100),:IP_TIME, 120);

--全球唯一码（GUID）
SELECT NEWID();
--SQL SERVER整数唯一值
SELECT RIGHT(CONVERT(BIGINT, ABS(CHECKSUM(NEWID()))), 10);

--将尚未同步的病人信息数据，同步到目标（oracle）数据库。
INSERT INTO PATIENTINFO
(
CARD_NO,PID,IP_NO,FEE_CODE,NAME,SEX,BIRTH,MARRY,
JOB,NATION,BIRTHAREA,ID,ADDR,CORP,C_TEL,TEL,CONTACT,
RELATION,CONT_ADDR,CONT_TEL,REG_DATE,REG_TIME,REG_OPER,LOCK_FLAG
)
VALUES
(
:IP_NO,:IP_NO,:IP_NO,:FEE_CODE,:NAME,:SEX,:BIRTH,:MARRY,
:JOB,:NATION,:BIRTHAREA,:ID,:ADDR,:CORP,:C_TEL,:TEL,:CONTACT,
:RELATION,:CONT_ADDR,:CONT_TEL,:IP_DATE,:IP_TIME,:IP_OPER,'F'
);
--将尚未同步的病人信息数据，同步到目标（oracle）数据库(已经存在了的病人不插入)。
INSERT INTO PATIENTINFO
(
CARD_NO,PID,IP_NO,FEE_CODE,NAME,SEX,BIRTH,MARRY,
JOB,NATION,BIRTHAREA,ID,ADDR,CORP,C_TEL,TEL,CONTACT,
RELATION,CONT_ADDR,CONT_TEL,REG_DATE,REG_TIME,REG_OPER,LOCK_FLAG
)
SELECT :IP_NO,:IP_NO,:IP_NO,:FEE_CODE,:NAME,:SEX,:BIRTH,:MARRY,
:JOB,:NATION,:BIRTHAREA,:ID,:ADDR,:CORP,:C_TEL,:TEL,:CONTACT,
:RELATION,:CONT_ADDR,:CONT_TEL,:IP_DATE,:IP_TIME,:IP_OPER,'F' 
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM PATIENTINFO WHERE PID = :IP_NO);

--将尚未同步的入院登记数据，同步到目标（oracle）数据库。
INSERT INTO IP_REGISTER
(
REG_NO,TYPE,PID,IP_NO,IP_CNT,FEE_CODE,NAME,SEX,BIRTH,MARRY,JOB,
NATION,BIRTHAREA,ID,ADDR,CORP,C_TEL,TEL,CONTACT,RELATION,CONT_ADDR,
CONT_TEL,IP_DEPT,IP_DATE,IP_TIME,IP_CASE,CL_EDIAG,CL_CDIAG,IP_OPER,
LOC_FLAG,IP_DR
)
VALUES
(
TO_CHAR(SYSTIMESTAMP,'YYYYMMDDHH24MISSFF'),'1',:IP_NO,:IP_NO,:IP_CNT,:FEE_CODE,:NAME,:SEX,:BIRTH,:MARRY,:JOB,
:NATION,:BIRTHAREA,:ID,:ADDR,:CORP,:C_TEL,:TEL,:CONTACT,:RELATION,:CONT_ADDR,
:CONT_TEL,:IP_DEPT,:IP_DATE,:IP_TIME,:IP_CASE,:CL_EDIAG,:CL_CDIAG,:IP_OPER,
'F',:IP_DR
);

--oracle生成全球唯一值
SELECT SYS_GUID() FROM DUAL;

SELECT TO_CHAR(SYSDATE,'YYYY-MM-DD HH24:MI:SS') CURRENT_DATE, (SYSDATE- TO_DATE('1970-01-01','YYYY-MM-DD')) * 86400000 CURRENT_MILLI FROM DUAL;
--oracle 获取指定时间到当前时间的毫秒值
SELECT (SYSDATE - TO_DATE('2012-01-01','YYYY-MM-DD HH24:MI:SS')) * 86400000 CURRENT_MILLI FROM DUAL;
select sysdate from dual;
--精确到微妙的时间戳
SELECT TO_CHAR(SYSTIMESTAMP,'YYYYMMDDHH24MISSFF') FROM DUAL;
---======================结束 入院登记数据同步任务SQL语句（正式版） 结束==========================================

--SQL SERVER 2000 HIS 数据库 病人信息查询。
SELECT top
	BLH AS PID,
	RYFS AS FEE_CODE,
	BRXM AS REG_OPER,
	REPLACE(CONVERT(NVARCHAR(10),RYRQ,120),'-','.') AS REG_DATE,
	REPLACE(CONVERT(NVARCHAR(23),RYRQ,120),'-','.') AS REG_TIME,
	BRXM AS NAME,
	XB AS SEX,
	BLH AS CARD_NO 
FROM ZY_BRZLLS BLH > ?? order by ZYCS;
--数据分割条件限定语句，从目标（ORACLE）数据库查找最后同步的记录语句。
--SELECT BLH,BRXM,MJZD FROM PATIENTINFO WHERE ROWNUM = 1 ORDER BY REG_TIME DESC；
select * from (SELECT * PATIENTINFO FROM PATIENTINF order by REG_TIME desc) t where ROWNUM =1；
--将尚未同步的数据块，同步到目标（oracle）数据库。
INSERT INTO PATIENTINFO 
  (PID,FEE_CODE,REG_OPER,REG_DATE,REG_TIME,NAME,SEX,CARD_NO,LOCK_FLAG) 
VALUES 
  (:PID,:FEE_CODE,:REG_OPER,:REG_DATE,:REG_TIME,:NAME,:SEX,:CARD_NO,'F')
  
--防止插入重复记录
INSERT INTO CLIENTS 
    (PID,FEE_CODE,REG_OPER,REG_DATE,REG_TIME,NAME,SEX,CARD_NO,LOCK_FLAG) 
SELECT :PID,:FEE_CODE,:REG_OPER,:REG_DATE,:REG_TIME,:NAME,:SEX,:CARD_NO,'F' 
     FROM DUAL 
WHERE NOT EXISTS 
  (
  SELECT * FROM CLIENTS 
WHERE CLIENTS.CLIENT_ID = 10345
);

SELECT 
BLH AS PID,RYFS AS FEE_CODE,BRXM AS REG_OPER,REPLACE(CONVERT(NVARCHAR(10),RYRQ,120),'-','.') AS REG_DATE,
REPLACE(CONVERT(NVARCHAR(23),RYRQ,120),'-','.') AS REG_TIME,BRXM AS NAME,XB AS SEX,BLH AS CARD_NO 
FROM ZY_BRZLLS WHERE RYRQ >:RYRQ

--同步病人信息（防止插入重复病人）
INSERT INTO PATIENTINFO (PID,FEE_CODE,REG_OPER,REG_DATE,REG_TIME,NAME,SEX,CARD_NO,LOCK_FLAG) SELECT :PID,:FEE_CODE,:REG_OPER,:REG_DATE,:REG_TIME,:NAME,:SEX,:CARD_NO,'F' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM PATIENTINFO WHERE PID = :PID)

--过滤重复值
SELECT * FROM TABLE JOIN (SELECT MAX(ID) FROM TABLE GROUP BY DATE) TEMP
on table.id = temp.id;

select sq_reg_no.currval from dual;

--======================================================================================
--======================================================================================
--条件限定语句
select max(id) as id from zztest;
--源数据库查出语句
select id,content from test where id > :id;
--目标数据
insert into zztest(id,content) values(:id,:content);

USE [master]
GO
--查看SQLServer和其他数据库系统的数据类型对应关系
SELECT *
FROM msdb.dbo.MSdatatype_mappings where dbms_name = 'ORACLE' order by dest_type;

USE [master]
GO
--@server：连接服务器、@srvproduct：产品名称、@provider：访问接口、@datasrc：数据源
EXEC master.dbo.sp_addlinkedserver @server = N'CORA', @srvproduct=N'Oracle', @provider=N'MSDAORA', @datasrc=N'orcl'

USE [master]
GO
--@server：连接服务器、@locallogin：本地登录、@useself：、@rmtuser：远程登录、@rmtpassword：使用密码
EXEC master.dbo.sp_addlinkedsrvlogin @rmtsrvname = N'CORA', @locallogin = NULL , @useself = N'False', @rmtuser = N'TEST', @rmtpassword = N'TEST'
GO

DELETE FROM SERVER..USER.TABLENAME;
--清空Oracle表中的数据
INSERT into SERVER..USER.TABLENAME--将SQLServer中的数据写到Oracle中
SELECT FIELD1,FIELD2,FIELD3,FIELDN
FROM TABLEMANE;

--查看Oracle数据库中表的数据，访问方式为：连接服务器..(必需)用户名.表名（必须要大写）
SELECT *
FROM SERVER..USER.TABLENAME;

use test;
go

INSERT INTO CORA..TEST.ZZTEST--将SQLServer中的数据写到Oracle中
SELECT ID,CONTENT
FROM TEST;

--INSERT INTO CORA..TEST.ZZTEST SELECT ID,CONTENT FROM INSERTED;


INSERT INTO TEST(ID,CONTENT) VALUES(2,'这是测试触发器的');  



/*
============服务器×××上的MSDTC不可用解决办法=============

MSDTC(分布式交易协调器)，协调跨多个数据库、消息队列、文件系统等资源管理器的事务。该服务的进程名为Msdtc.exe,该进程调用系统Microsoft Personal Web Server和Microsoft SQL Server。该服务用于管理多个服务器 .
位置：控制面板－－管理工具－－服务－－Distributed Transaction Coordinator
依存关系：Remote Procedure Call(RPC)和Security Accounts Manager 
建议：一般家用计算机涉及不到，除非你启用Message Queuing服务，可以停止。
解决办法: 1. 在windows控制面版-->管理工具-->服务-->Distributed Transaction Coordinator-->属性-->启动
        2.在CMD下运行"net start msdtc"开启服务后正常。
注：如果在第1步Distributed Transaction Coordinator 无法启动，则是因为丢失了日志文件,重新创建日志文件,再启动就行了。重新创建 MSDTC 日志,并重新启动服务的步骤如下：
(1) 单击"开始",单击"运行",输入 cmd 后按"确定"。
(2) 输入:msdtc -resetlog (注意运行此命令时,不要执行挂起的事务)
(3) 最后输入:net start msdtc 回车,搞定!

*/