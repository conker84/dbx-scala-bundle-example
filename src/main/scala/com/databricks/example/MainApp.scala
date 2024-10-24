package com.databricks.example

import org.apache.spark.sql.SparkSession


object MainApp {

  def main(args: Array[String]): Unit = {

    if (args.nonEmpty && args(0) != "-r") throw new IllegalArgumentException("Expected -r as first argument")

    val spark = SparkSession
      .builder()
      .master("local")
      .appName("Databricks Scala Bundle Example")
      .getOrCreate()

    val dataDF = spark.range(Option(if (args.isEmpty) null else args(1)).getOrElse("5").toInt)

    dataDF.show()

  }
} 
