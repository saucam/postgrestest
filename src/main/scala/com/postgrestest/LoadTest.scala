package com.postgrestest

import java.sql.SQLException

import org.apache.spark.rdd.RDD
import org.postgresql.PGConnection
import conf.Conf
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, SQLContext}
import scalikejdbc.{GlobalSettings, LoggingSQLAndTimeSettings}

import scala.collection.mutable

object LoadTest {
  def main(args: Array[String]): Unit = {
    GlobalSettings.loggingSQLAndTime = LoggingSQLAndTimeSettings(
      enabled = true,
      singleLineMode = false,
      printUnprocessedStackTrace = false,
      stackTraceDepth= 15,
      logLevel = 'debug,
      warningEnabled = false,
      warningThresholdMillis = 3000L,
      warningLogLevel = 'warn
    )

    // Initialize the connection pool
    scalikejdbc.config.DBs.setupAll

    val sparkConf = new SparkConf()
      .setAppName("ElasticTest")
      .setMaster("local[*]")
      .set("spark.executor.memory", "6g")

    val sc = new SparkContext(sparkConf)
    val sqlContext = new SQLContext(sc)

    val df = sqlContext.read.parquet("/Users/ydatta/Documents/Workspace/data/new_data/final_final_merge").coalesce(1)

    df.write.format("com.databricks.spark.csv").save("/Users/ydatta/Documents/Workspace/data/home.csv")

  }
}
