# Crypto Twitter Streaming
A quick tool that streams Twitter data realtime and filters for any tweets related to specific cryptocurrencies. This project is built in Scala and uses Spark Streaming with the Twitter API. You will need to generate your own authentication keys from [your Twitter account](https://apps.twitter.com/) and replace the appropriate variables.

```
val consumerKey = "INSERT_KEY"
val consumerSecret = "INSERT_KEY"
val accessToken = "INSERT_KEY"
val accessTokenSecret = "INSERT_KEY"
```


## Usage
On launch, the program will start streaming and will count up all $cashtags in batches of specified intervals (default every 60 seconds). Output should look like the following:
```
-------------------------------------------
Time: 1515995160000 ms
-------------------------------------------
$CLAM
$QTUM
$WAVES
$$$$…

-------------------------------------------
Time: 1515995220000 ms
-------------------------------------------
$enj
$icx
$XRB

-------------------------------------------
Time: 1515995280000 ms
-------------------------------------------
$ven,
$ICX,
$SPHTX,
$TAU,
$VEE,
$BTO,
$HPB
$BCH
```

This data can be collected and filtered to create a mention count that can be used to see which currencies may be trending on social media. You can also run analysis on the text of each tweet.
