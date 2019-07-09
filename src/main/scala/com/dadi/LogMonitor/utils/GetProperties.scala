package com.dadi.LogMonitor.utils

import java.io.BufferedInputStream
import java.util.Properties

object GetProperties {
    def getProperties(filePath:String): Properties ={
      val inputStream = getClass.getResourceAsStream(filePath)
      val props = new Properties()
      props.load(new BufferedInputStream(inputStream))
      props
    }
}
