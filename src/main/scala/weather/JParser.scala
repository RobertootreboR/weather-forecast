package weather

import java.time.Instant

import org.json4s.JsonAST.{JDouble, JInt, JString, JValue}

/**
  * Created by robert on 09.03.17.
  */
object JParser {

  def getFileName(day: JValue) :String ={
    val date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formatJValue(day \ "dt_txt"))
    java.time.Duration.between(Instant.now(),date.toInstant).toDays.toString
  }
  def formatJValue(str : JValue): String ={
    str match{
      case JString(x) => x
      case JDouble(x) => x.toString
      case JInt(x)    => x.toString
      case _          => throw new Exception
    }
  }

  def extractData(day: JValue): StringBuilder = {
    var result = new StringBuilder("")
    result
      .append(formatJValue(day \ "dt_txt").substring(0, 10)).append(";")
      .append(formatJValue (day \ "main" \ "temp")).append(";")
      .append(formatJValue (day \ "main" \ "pressure")).append(";")
      .append(formatJValue (day \ "main" \ "humidity")).append(";")
      .append(formatJValue (day \ "clouds" \ "all")).append(";")
      .append(formatJValue (day \ "wind" \ "speed"))
  }

  def filterDays(city: Option[JValue], hour:Int): List[JValue] = {
    JParser.getListArray(city)
      .children
      .filter(child => JParser.filterHour(child, hour))
  }

  private def getListArray(city: Option[JValue]): JValue = {
    city match {
      case Some(value) => value \ "list"
      case None => throw new Exception
    }
  }

  private def filterHour(child: JValue, hour: Int): Boolean = {
    (child \ "dt_txt").toString.contains(hour + ":00:00")
  }
}
