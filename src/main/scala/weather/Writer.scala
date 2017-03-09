package weather
import java.io.{FileWriter, IOException, PrintWriter}
import org.json4s.JsonAST.JValue

/**
  * Created by robert on 09.03.17.
  */
class Writer(city: JValue, cityName : String) {
  val weatherDayList: List[JValue] = JParser.filterDays(city.toOption,15)
  val weatherNightList: List[JValue] = JParser.filterDays(city.toOption,0)

  def saveDay : Unit ={
    weatherDayList
      .map(day => (day,"weather/"+cityName +"_day_+"+JParser.getFileName(day)+".txt"))
      .map(day_file => (JParser.extractData(day_file._1),day_file._2))
      .foreach(day_file => saveToFile(day_file._1,day_file._2))
  }
  def saveNight :Unit={
    weatherNightList
      .map(day => (day,"weather/"+cityName +"_night_+"+JParser.getFileName(day)+".txt"))
      .map(day_file => (JParser.extractData(day_file._1),day_file._2))
      .foreach(day_file => saveToFile(day_file._1,day_file._2))
  }

  @throws[IOException]
  private def saveToFile(text: StringBuilder, path: String) {
    val writer = new FileWriter(path,true)
    writer.append(text)
    writer.close()          //poprawic bo brzydko
  }
}
