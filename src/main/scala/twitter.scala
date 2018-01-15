import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.twitter._
import org.apache.spark.streaming.{Seconds, StreamingContext}
import twitter4j.auth.OAuthAuthorization
import twitter4j.conf.ConfigurationBuilder


object Twitter {

  def main(args: Array[String]) {

    // authenticate using personal Twitter API tokens
    val consumerKey = "INSERT_KEY"
    val consumerSecret = "INSERT_KEY"
    val accessToken = "INSERT_KEY"
    val accessTokenSecret = "INSERT_KEY"

    // sets up authentication
    System.setProperty("twitter4j.oauth.consumerKey", consumerKey)
    System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret)
    System.setProperty("twitter4j.oauth.accessToken", accessToken)
    System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret)
    val auth = Some(new OAuthAuthorization(new ConfigurationBuilder().build()))

    // how many seconds used per streaming batch
    val TIME = 60


    // creates new Spark streaming context
    val ssc = new StreamingContext("local[*]", "PrintTweets", Seconds(TIME))
    val twitterStream = TwitterUtils.createStream(ssc, auth)

    // extracts text from incoming tweets
    val statuses = twitterStream.map(_.getText)
      .flatMap(_.split(" "))
      .filter(_.startsWith("$"))
      .filter(!_.apply(1).isDigit)

    // filters tweets that contain certain keywords and prints to console
    statuses.print()

    
    ssc.start()
    ssc.awaitTermination()
  }
}
