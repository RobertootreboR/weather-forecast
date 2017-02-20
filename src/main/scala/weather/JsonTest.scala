package weather
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

import scala.io.Source
/**
  * Created by robert on 20.02.17.
  */
class JsonTest {
  def doAll() :Unit = {
    val html = Source.fromURL("http://api.openweathermap.org/data/2.5/forecast/city?id=3094802&APPID=6fdd34768f67187a44e6fe59a0f72e4a")
    val s = html.mkString
    println(s)

    var json = parse(s)
    var json2 = parse("""{"name":"luca", "id": "1q2w3e4r5t", "age": 26, "url":"http://www.nosqlnocry.wordpress.com"}""")
    json2 = json2 merge render("height",175)
    println(json2)
  }
}
