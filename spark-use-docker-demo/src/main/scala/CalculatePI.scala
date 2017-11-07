import org.apache.spark.{SparkConf, SparkContext}

object CalculatePI {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Spark Pi")

    val sc = new SparkContext(conf)
    val slices = if (args.length > 0) args(0).toInt else 2

    val n = 100000 * slices

    val count = sc.parallelize(1 to n, slices).map { _ =>
      val x = Math.random() * 2 - 1
      val y = Math.random() * 2 - 1
      if (x * x + y * y < 1) 1 else 0
    }.reduce(_ + _)
    println("Pi is roughly " + 4.0 * count / n)

  }

}
