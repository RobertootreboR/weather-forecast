package weather

import org.json4s.JsonAST.JValue

class Writer(city: JValue, cityName : String) {

  val weatherDayList: List[JValue] = JParser.filterDays(city.toOption,15)
  //val weatherNightList: List[JValue] = JParser.filterDays(city.toOption,0)

  def save(): Unit ={
    _save("_day_+")
    //_save("_night_+")
  }
  private def _save(when : String) : Unit ={
    weatherDayList
      .map(day => (day,"weather/"+cityName +"_day_+"+JParser.getFileName(day)+".txt"))
      .map(day_file => (JParser.extractData(day_file._1),day_file._2))
      .foreach(day_file => new ToFileWriter().saveToFile(day_file._1,day_file._2))
  }
}


