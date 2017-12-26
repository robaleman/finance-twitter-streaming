import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.twitter._
import org.apache.spark.streaming.{Seconds, StreamingContext}
import twitter4j.auth.OAuthAuthorization
import twitter4j.conf.ConfigurationBuilder

// add this if you want to disable excess logs in console
import org.apache.log4j.{Level, Logger}


object Twitter {

  def main(args: Array[String]) {

    // authenticate using personal Twitter API tokens, replace "hidden" with your appropriate API keys
    val consumerKey = "hidden"
    val consumerSecret = "hidden"
    val accessToken = "hidden"
    val accessTokenSecret = "hidden"

    // sets up authentication
    System.setProperty("twitter4j.oauth.consumerKey", consumerKey)
    System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret)
    System.setProperty("twitter4j.oauth.accessToken", accessToken)
    System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret)
    val auth = Some(new OAuthAuthorization(new ConfigurationBuilder().build()))


    // creates new Spark streaming context
    val ssc = new StreamingContext("local[*]", "PrintTweets", Seconds(30))

    // creates a Twitter stream to use as input source
    val tweets = TwitterUtils.createStream(ssc, auth)

    // extracts text from incoming tweets
    val statuses = tweets.map(status => status.getText())

    // filters tweets that contain certain keywords and prints to console, replace "bitcoin" with your desired currency
    val btc_tweets = statuses.filter(status => status contains "bitcoin")
    btc_tweets.print()

    // OPTIONAL: toggle these to disable excess messages in console
    val rootLogger = Logger.getRootLogger()
    rootLogger.setLevel(Level.ERROR)

    ssc.start()
    ssc.awaitTermination()
  }
 
}



