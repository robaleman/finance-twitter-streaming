import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.twitter._
import org.apache.spark.streaming.{Seconds, StreamingContext}


object Twitter {
  def main(args: Array[String]) {

    val consumerKey = "hidden"
    val consumerSecret = "hidden"
    val accessToken = "hidden"
    val accessTokenSecret = "hidden"
    
    System.setProperty("twitter4j.oauth.consumerKey", "CONSUMER_API_KEY")
    System.setProperty("twitter4j.oauth.consumerSecret", "CONSUMER_API_SECRET")
    System.setProperty("twitter4j.oauth.accessToken", "ACCESS_TOKEN")
    System.setProperty("twitter4j.oauth.accessTokenSecret", "ACCESS_TOKEN_SECRET")
  }
}
