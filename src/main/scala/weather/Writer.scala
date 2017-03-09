package weather

import org.json4s.jackson.JsonMethods.pretty
import org.json4s.JsonAST.JValue

/**
  * Created by robert on 09.03.17.
  */
class Writer(city: Option[JValue], cityName : String) {
  val weatherList: List[JValue] = JParser.filterDays(city)

  def saveDay : Unit ={
    weatherList
      .map(day => (day,cityName +"_day_+"+JParser.getFileName(day)+".txt"))
      .map(day_file => (JParser.extractData(day_file._1),day_file._2)) //dodac druga czesc krotki
      .foreach(pair => println(pair._1))
  }
}
