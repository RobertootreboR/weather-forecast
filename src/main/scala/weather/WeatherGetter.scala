package weather

import org.json4s._
import org.json4s.jackson.JsonMethods._

import scala.io.Source


class WeatherGetter {

  val cityList= List(("3094802","Krakow"), ("756135","Warszawa"), ("3099434","Gdansk"))//, "759734", "3083271", "3081368", "3090048", "3102014", "765876", "3093133", "776069", "3096472", "769250", "3090104", "3088171", "3083829")
                    //  Krakow     Warszawa   Gdansk    Rzeszow    Torun      Wroclaw    Opole     Bydgoszcz   Lublin    Lodz     Bialystok  Katowice   Kielce     Olsztyn    Poznan     Szczecin
    val cityMap :Map[String,JValue] = getCities

  def download: Unit = {
      cityList.foreach(pair=> println(parse(Source.fromURL("http://api.openweathermap.org/data/2.5/forecast/city?id="+pair._1+"&APPID=6fdd34768f67187a44e6fe59a0f72e4a").mkString) \\ "name"))
    }
    private def download(id : String ) = parse(Source.fromURL("http://api.openweathermap.org/data/2.5/forecast/city?id="+id+"&APPID=6fdd34768f67187a44e6fe59a0f72e4a").mkString)
    def getCities :Map[String,JValue] = {
      cityList.map(pair => pair._2 -> download(pair._1)).toMap
    }
    def allDataValid :Boolean = {
      cityMap.values.forall( something => !(something \ "message").equals(JString("Error")))  //jest zle. naprawic
    }
}
