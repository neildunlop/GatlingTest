import java.io.File

import scala.collection.JavaConversions._
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io._

class DirectoryReader {

  def readFiles(filePath: String) : Seq[Map[String, String]] = {

    val commandNamePattern = "(?:-).*(?:-)".r
    val result = mutable.MutableList[Map[String, String]]()
    var i = 0

    val targetDirectory: File = new File(filePath)
    if (targetDirectory != null) {
      for (file <- targetDirectory.listFiles if file.getName endsWith ".json") {

        val commandName = commandNamePattern.findFirstIn(file.getName()).get.tail.dropRight(1)
        val fileContents = Source.fromFile(file).getLines.mkString
        val entry = Map[String, String]("filename" -> file.getName, "command" -> commandName, "body" -> fileContents)
        println(s"Command: ${commandName}")
        println(s"Body: ${fileContents}")
        result+=entry
        i = i +1
      }
    }
    result
  }
}
