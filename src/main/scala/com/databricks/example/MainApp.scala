package com.databricks.example

import org.apache.spark.internal.Logging
import org.apache.spark.sql.SparkSession
import org.apache.log4j.LogManager


object MainApp extends Logging {

  private val logger = LogManager.getLogger("MainApp")

  def main(args: Array[String]): Unit = {
    logInfo(s"logInfo Calling MainApp with args ${args.mkString("Array(", ", ", ")")}")
    log.info(s"log.info Calling MainApp with args ${args.mkString("Array(", ", ", ")")}")
    logger.info(s"logger.info Calling MainApp with args ${args.mkString("Array(", ", ", ")")}")
    if (args.nonEmpty && args(0) != "-r") throw new IllegalArgumentException("Expected -r as first argument")

    val spark = SparkSession
      .builder()
      .master("local")
      .appName("Databricks Scala Bundle Example")
      .getOrCreate()

    val dataDF = spark.range(Option(if (args.isEmpty) null else args(1)).getOrElse("5").toInt)

    dataDF.show()

    logInfo("Finished execution")

  }
} 
