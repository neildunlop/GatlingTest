import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

//Commented out to avoid it running and polluting our real tests...
class SimpleApiScenario { //extends Simulation {

  val httpConf = http
    .baseURL("http://localhost:8080") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val headers_10 = Map("Content-Type" -> """application/json""") // Note the headers specific to a given request

  val scn = scenario("Scenario Name") // A scenario is a chain of requests and pauses
    .exec(http("request_1")
    .post("/createEvent")
    .headers(headers_10)
    .body(StringBody("""{
                       |  "context" : { },
                       |  "eventId" : "0d36f526-bcb8-496f-a156-202fa2c4aba6",
                       |  "name" : "|Erwin Chargaff| |vs| |Niels Bohr|",
                       |  "startTime" : "2015-12-18T15:58:09.047Z",
                       |  "competitionId" : "6b3d6cad-6cfc-47fb-99e8-ec88a44b8f15",
                       |  "metadata" : { },
                       |  "createMarkets" : [ {
                       |    "context" : { },
                       |    "marketClass" : "HEAD_TO_HEAD",
                       |    "openBetMarketTemplate" : "openBetMarketTemplate",
                       |    "marketInfo" : { },
                       |    "name" : "Market1",
                       |    "createSelections" : [ {
                       |      "context" : { },
                       |      "selectionInfo" : { },
                       |      "name" : "Bob",
                       |      "price" : {
                       |        "type" : "fractional",
                       |        "value" : "2/1"
                       |      }
                       |    } ],
                       |    "applies" : [ "PRE_MATCH" ]
                       |  } ],
                       |  "phase" : "NOT_PROVIDED",
                       |  "sportsbookFormat" : "EURO"
                       |}""")).asJSON)


  //setUp(scn.inject(atOnceUsers(1)).protocols(httpConf))
}
