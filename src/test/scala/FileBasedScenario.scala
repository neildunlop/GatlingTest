import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class FileBasedScenario extends Simulation {

  val httpConf = http
    .baseURL("http://localhost:8080") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val headers_10 = Map("Content-Type" -> """application/json""") // Note the headers specific to a given request

  var messages = new DirectoryReader().readFiles("../GatlingTest/src/test/resources/request")

  val scn = scenario("Scenario Name") // A scenario is a chain of requests and pauses

    .foreach(messages, "message", "requestCounter") {
      exec(http("request_${requestCounter}")
      .post("/${message.command}")
      .headers(headers_10)
      //.body(ELFileBody("../GatlingTest/src/test/resources/request/1446164810033-competitions-edefac20881451626ee5dee5e5975e8e.json")).asJSON)
      .body(StringBody("${message.body}")).asJSON)
      .pause(1 second)
  }



  setUp(scn.inject(atOnceUsers(10)).protocols(httpConf))
}
