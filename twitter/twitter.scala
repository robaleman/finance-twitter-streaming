// INCOMPLETE
import org.apache.spark.streaming.twitter._
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.slf4j.LoggerFactory
import scopt.OptionParser


object Twitter {
  
  def main(args: Array[String]) {

    // authenticate using personal Twitter API tokens 
    val consumerKey = "hidden"
    val consumerSecret = "hidden"
    val accessToken = "hidden"
    val accessTokenSecret = "hidden"

    System.setProperty("twitter4j.oauth.consumerKey", "CONSUMER_API_KEY")
    System.setProperty("twitter4j.oauth.consumerSecret", "CONSUMER_API_SECRET")
    System.setProperty("twitter4j.oauth.accessToken", "ACCESS_TOKEN")
    System.setProperty("twitter4j.oauth.accessTokenSecret", "ACCESS_TOKEN_SECRET")

    // create stream
    val ssc = new StreamingContext("local[*]", "PrintTweets", Seconds(1))
    val tweets = TwitterUtils.createStream(ssc, None)

    val statuses = tweets.map(status => status.getText())
    statuses.print()

    // start running stream
    ssc.start()
    ssc.awaitTermination()
  }
}
