package weather

/**
 * @author ${user.name}
 */
object App {

  // 4. future  forecast + comparison (actual compared to predicted earlier. think of sensible way of storaging the data)
  // drawing charts
  // more complex comparisons, looking for trends
  
  def main(args : Array[String]) {
    val jsonWeather =new WeatherGetter()
    jsonWeather.download

    if(!jsonWeather.allDataValid)
      print("duuupa 2")
    else
    jsonWeather.cityMap.foreach(KeyVal => new Writer(KeyVal._2,KeyVal._1).saveDay)

  }

}
