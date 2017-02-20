package weather
import scala.io.Source
/**
 * @author ${user.name}
 */
object App {
  
  def foo(x : Array[String]) = x.foldLeft("")((a,b) => a + b)
  
  def main(args : Array[String]) {
    println( "Hello World!" )
    new JsonTest doAll


    println("concat arguments = " + foo(args))
  }

}
