package com.wsj.test

import org.apache.spark.sql.SparkSession

object StructureWordCount {
  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "D:\\Hadoop\\hadoop")
    val spark = SparkSession.builder().appName("StructureWordCount")
      .master("local")
      .config("spark.sql.warehouse.dir","D:\\spark-warehouse")
      .config("spark.sql.shuffle.partitions","4")

      .getOrCreate()


    import spark.implicits._

    val lines = spark.readStream.format("socket")
      .option("host","10.22.66.1")
      .option("port","9998")
      .load()

    val words = lines.as[String].flatMap(_.split(" "))

    val wordCount = words.groupBy("value").count()

    val query = wordCount.writeStream.outputMode("update").format("console").start()

    query.awaitTermination()

  }
}
