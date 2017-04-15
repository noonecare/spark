import org.apache.log4j.PropertyConfigurator
import org.slf4j.LoggerFactory



/**
  * Created by T440P on 2017/4/15.
  */
object LogDemo extends App{
    val logger = LoggerFactory.getLogger(LogDemo.getClass)
    // 载入配置文件
    val in = this.getClass.getClassLoader.getResourceAsStream("log4j.properties")
    PropertyConfigurator.configure(in)
    // 写入日志
    logger.info("this is info")
    logger.error("this is error")
    logger.debug("this is debug")
}
