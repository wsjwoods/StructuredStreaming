package com.dadi.LogMonitor.utils

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

import org.apache.commons.lang3.time.FastDateFormat


object MyDateUtils {
  val YYYYMMDDHHMMSS_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss")
  val YYYYMMDDHHMM_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm")
  val FULLTIME_FORMAT = FastDateFormat.getInstance("yyyyMMddHHmmss")
  val YYYYMMDD_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd")


  //Date转换String
  def getStrDate(date:Date,format:String):String={
    val  dateFormat:SimpleDateFormat = new SimpleDateFormat(format)
    dateFormat.format(date)
  }

  /**
    * format1:输入日期格式
    * format2:输出日期格式
    */
  def TransStrDate(date:String,format1:String,format2:String):String={
    val  dateFormat1:SimpleDateFormat = new SimpleDateFormat(format1)
    val  dateFormat2:SimpleDateFormat = new SimpleDateFormat(format2)

    val tdate1 = dateFormat1.parse(date)
    dateFormat2.format(tdate1)

  }

  /**
    * 时间增加n天
    */
  def strDateSub(date:String,format:String="yyyy-MM-dd",num:Int):String={
    val  dateFormat:SimpleDateFormat = new SimpleDateFormat(format)

    val tdate = dateFormat.parse(date)
    val cal:Calendar=Calendar.getInstance()
    cal.setTime(tdate)
    cal.add(Calendar.DATE,num)
    dateFormat.format(cal.getTime())
  }


  /**
    * 时间转换
    * @param input
    * @param inFormat
    * @param outFormat
    * @return
    */
  def str_to_date(input: String, inFormat: String,outFormat:String):String = {
    val sdf1 = new SimpleDateFormat(inFormat)
    val sdf2 = new SimpleDateFormat(outFormat)
    var result=""
    if (input == null || input.length == 0) {}
    else {
      val date1 = sdf1.parse(input)
      result = sdf2.format(date1)
    }
    result
  }

  /**
    * 日期减n小时
    * @param input
    * @param interval
    * @return
    */
  def datesubHour(input: String, interval: Int):String = {
    val sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    var result=""
    if (input == null || input.length == 0) {}
    else {
      val datetime = sdf.parse(input)
      val cal: Calendar = Calendar.getInstance()
      cal.setTime(datetime);

      cal.add(Calendar.HOUR, -interval)
      result = sdf.format(cal.getTime())
    }
    result
  }

}