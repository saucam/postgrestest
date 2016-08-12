package com.postgrestest.conf

import org.postgresql.jdbc3.Jdbc3PoolingDataSource

/**
  * Created by ydatta on 8/6/2016 AD.
  */
object Conf {

  // Get the configured dbname
  val dbName = scalikejdbc.config.DBs.config.getConfig("db.default").getString("dbName")
  val dbUrl = scalikejdbc.config.DBs.config.getConfig("db.default").getString("url")
  val driverName = scalikejdbc.config.DBs.config.getConfig("db.default").getString("driver")
  val userName = scalikejdbc.config.DBs.config.getConfig("db.default").getString("user")
  val password = scalikejdbc.config.DBs.config.getConfig("db.default").getString("password")

  val source = new Jdbc3PoolingDataSource();
  source.setDataSourceName("A Data Source");
  source.setServerName("localhost");
  source.setDatabaseName(s"$dbName");
  source.setUser(s"$userName");
  source.setPassword(s"$password");
  source.setMaxConnections(10);
}
