package weather

import java.time.{Duration, Instant}

import scala.io.Source

class LaunchGuardian {
  def canLaunch : Boolean ={
    val lastDate = Source.fromFile("weather/lastDate.txt").mkString
    val date = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(lastDate.substring(0,10))
    if(Duration.between(date.toInstant,Instant.now()).toDays>0) true
    else false
  }
}
