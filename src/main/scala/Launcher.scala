import org.sunlab.utils.Awakable
import org.sunlab.utils.SparkHelper.spark

case class Bank(age: Integer, job: String, marital: String, education: String, balance: Integer)

/**
 * @author yu
 */
object Launcher extends App with Awakable {
  def readBench(uri: String): (Long, Long) = {

    logger.info(s"starting $uri")
    val timeStart = System.currentTimeMillis()

    import spark.implicits._

    //  val data = spark.read.format("org.apache.spark.sql.execution.datasources.csv.CSVFileFormat").
    val data = spark.read.format("com.databricks.spark.csv").
      option("header", "true").
      option("mode", "DROPMALFORMED").
      option("delimiter", ",").
      load(uri)

    val cnt = data.filter('AGE > 3).count()

    val timeElapsed = System.currentTimeMillis() - timeStart

    logger.info(s"read: $uri, result: $cnt, time elasped: $timeElapsed ms")
    (cnt, timeElapsed)
  }

  val baseUri = "/project/truven/CSV/Ccaed113.csv"
  val hdfsUri = s"hdfs://haumea.cc.gatech.edu:9000$baseUri"
  val fsUri = s"file://$baseUri"

  val (cnt0, te0) = readBench(hdfsUri)
  val (cnt1, te1) = readBench(fsUri)

  logger.info("Summary: ")
  logger.info(s"HDFS: $cnt0, time cost: $te0 ms")
  logger.info(s"LocalFS: $cnt1, time cost: $te1 ms")
}
