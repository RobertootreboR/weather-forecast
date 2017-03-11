package weather

import java.text.SimpleDateFormat
import java.time.Instant

import scala.io.Source

/**
  * Created by robert on 11.03.17.
  */
class WeatherFile(path: String) {

  class WeatherInstance(val date: Instant, temp: Double, val pressure: Double, val humidity: Int, val clouds: Int, val wind: Double) {

    def this(line: Array[String]) = {
      this(new SimpleDateFormat("yyyy-MM-dd").parse(line(0)).toInstant
        , line(1).toDouble
        , line(2).toDouble
        , line(3).toInt
        , line(4).toInt
        , line(5).toDouble)
    }

    def this(line: String) = {
      this(line.split(";"))
    }

    override def toString: String = date + " " + temp + " " + pressure + " " + humidity + " " + clouds + " " + wind
  }

  private def parseFile(path: String) = {
    Source.fromFile(path)
      .getLines()
      .toList
      .map(line => new WeatherInstance(line))
      .map(instance => instance.date -> instance)
      .toMap

  }

  val weatherMap: Map[Instant, WeatherInstance] = parseFile(path)
  //val weatherMap: List[(Instant,WeatherInstance)] = parseFile(path)
}
