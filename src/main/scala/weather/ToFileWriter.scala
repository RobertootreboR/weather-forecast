package weather
import java.io.{FileWriter, IOException, PrintWriter}
import java.time.Instant

class ToFileWriter {
  @throws[IOException]
  def saveToFile(text: StringBuilder, path: String) {
    val writer = new FileWriter(path,true)
    writer.append(text)
    writer.close()          //poprawic bo brzydko
  }

  @throws[IOException]
  def saveDate(){
    val writer = new PrintWriter("weather/lastDate.txt")
    writer.print((new StringBuilder).append(Instant.now))//swiezo zmnieniane
    writer.close()
  }
}
