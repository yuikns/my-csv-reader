import org.sunlab.utils.Awakable
import org.sunlab.utils.SparkHelper.spark

case class Bank(age: Integer, job: String, marital: String, education: String, balance: Integer)

/**
 * @author yu
 */
object Launcher extends App with Awakable {
  def readBench(uri: String): (Long, Long) = {
    logger.info("starting %s", uri)
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

    logger.info("read: %s time elasped: %l ms", uri, timeElapsed)
    (cnt, timeElapsed)
  }

  val baseUri = "/project/truven/CSV/Ccaed113.csv"
  val hdfsUri = s"hdfs://localhost:9000$baseUri"
  val fsUri = s"file://$baseUri"

  val (cnt0, te0) = readBench(hdfsUri)
  val (cnt1, te1) = readBench(fsUri)

  logger.info("Summary: ")
  logger.info("HDFS: %d, time cost: %d ms", cnt0, te0)
  logger.info("LocalFS: %d, time cost: %d ms", cnt1, te1)
}
