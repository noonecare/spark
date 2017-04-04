spark steaming kafka HDFS

- DStream:
> - 我可以设置间隔时间来控制 DSteam 中每个 RDD 的大小。
> - DSTREAM 写进一个文件
> - DSTREAM.write.parquet("what") 对于收到的每条消息，是覆盖还是重写。如何到一定时间，把文件mv到 impala 表，而不破坏文件的完整性。我的意思是说， 同步处理 DSTREAM 和 mv 文件的操作。
> - checkpoint 是干什么的

DStream 暴露出的方法，是针对 DStream 中每一个 RDD 进行操作。还是对于 DStream 中所有的 RDD 的操作。比如如果是针对每一个 RDD 进行操作。那我可以记录一下，一共有多少个 RDD, 我可以对 100 个 RDD 做操作，这样，就可以解决小文件的问题。


能不能当成是单线程的程序，需不需要考虑竞争。

我学到的 spark 的内容都是操作 RDD 和 DataFrame , Stream 如何与 RDD 和 DataFrame 转化。



