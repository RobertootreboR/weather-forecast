package weather
import java.time.Instant

import org.json4s.JsonAST.JObject

import scala.io.Source
/**
 * @author ${user.name}
 */
object App {
  // to do:
  // 1. download day/night weather 00:00 and 15:00
  // 2. Saves to a file
  // 3. takes all hours withing a day
  // 4. future  forecast + comparison (actual compared to predicted earlier. think of sensible way of storaging the data)
  // drawing charts
  // more complex comparisons, looking for trends
  def foo(x : Array[String]) = x.foldLeft("")((a,b) => a + b)
  
  def main(args : Array[String]) {
    val xx =new WeatherGetter()
    xx.download
    //println(xx.download)
    val format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-03-10 18:00:00")
    println(format)
    println(java.time.Duration.between(Instant.now(),format.toInstant).toDays)
    println(format.toString)
    if(!xx.allDataValid)
      print("duuupa 2")

    new Writer(xx.getCities.get("Krakow"),"Krakow").saveDay

    println("concat arguments = " + foo(args))
  }

}
