# spark

把文件读入到 rdd

读取一个目录下的所有文件的内容。
> - spark 不仅支持 load 一个文件夹下的所有文件，而且支持使用 wild-card 以及 path glob 的方式读取特定 pattern 文件名的所有文件。

比如 sc.textFile("what")
如果 what 是个目录，那么现在读入的是目录下所有的文件。


---

# Spark DataFrame read  jsonfile

```sql
val data = sqlContext.read.json(<file_name>)
```

上面的方法，不能确保正确读取。不能正确读取时，读出的 DataFrame 中会多出一列 _corrupt_record。

下面的 error.json 文件吗， 上面的 scala 代码就不能正确读取

errror.json
```json
[{"name": "wangmeng"},
{"name"： "haibao"}]
```

但是以下的 json 文件， 上面的 scala 代码就可以正确的读取

correct_1.json

```json
[{"name": "wangmeng"},{"name"： "haibao"}]
```

correct_2.json

```json
{"name": "wangmeng"},
{"name"： "haibao"}
```

correct_3.json

```json
{"name": "wangmeng"}
{"name"： "haibao"}
```
