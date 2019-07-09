package com.dadi.LogMonitor.utils

import java.sql.Connection

import com.mchange.v2.c3p0.ComboPooledDataSource


class  MysqlPool extends Serializable{
  private val cpds: ComboPooledDataSource = new ComboPooledDataSource(true)
  try {
//    val prop = GetProperties.getProperties("/config-test.properties") //测试环境
    val prop = GetProperties.getProperties("/config.properties")
    val user = prop.getProperty("user")
    val pwd = prop.getProperty("pwd")
    val ip = prop.getProperty("ip")
    val database = prop.getProperty("database")

    cpds.setJdbcUrl(s"jdbc:mysql://$ip:3306/$database?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
    cpds.setDriverClass("com.mysql.jdbc.Driver");
    cpds.setUser(user);
    cpds.setPassword(pwd)
    cpds.setMaxPoolSize(200)
    cpds.setMinPoolSize(20)
    cpds.setAcquireIncrement(5)
    cpds.setMaxStatements(180)
  } catch {
    case e: Exception => e.printStackTrace()
  }
  def getConnection: Connection = {
    try {
      return cpds.getConnection();
    } catch {
      case ex: Exception =>
        ex.printStackTrace()
        null
    }
  }
}

object MysqlManager {
  var mysqlManager: MysqlPool = _
  def getMysqlManager: MysqlPool = {
    synchronized {
      if (mysqlManager == null) {
        mysqlManager = new MysqlPool
      }
    }
    mysqlManager
  }
}
