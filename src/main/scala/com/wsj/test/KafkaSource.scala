package com.wsj.test

import org.apache.spark.sql.SparkSession


object KafkaSource {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("KafkaSource").master("local")
      .config("spark.sql.warehouse.dir","D:\\spark-warehouse")
      .getOrCreate()


    import spark.implicits._
    val df = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "10.16.66.33:6667")
      .option("subscribe", "topic1")
      .option("group.id", "topic1")
      .load()
    val ds = df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
      .as[(String, String)]

    val query = ds.writeStream.outputMode("update").format("console").start()

    query.awaitTermination()
  }



}
