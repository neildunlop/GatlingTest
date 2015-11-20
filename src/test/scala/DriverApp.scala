/**
 * Created by neil on 19/11/15.
 */
object DriverApp  extends App {

  override def main(args: Array[String]): Unit = {

    val reader = new DirectoryReader()

    reader.readFiles("../GatlingTest/src/test/resources/request")


  }


}
