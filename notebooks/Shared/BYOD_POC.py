# Databricks notebook source
from pyspark import SparkContext
from pyspark import SQLContext
from pyspark.sql.functions import *
target_query="SELECT * from databse_name.byod_dbfs_table"
sparkContext = SparkContext.getOrCreate()
sqlContext = SQLContext(sparkContext)
dataframe = sqlContext.sql(target_query)
dataframe.repartition(1).write.format('com.databricks.spark.csv').options(delimiter=",").save("s3n://amgen-edl-acux-aaaa123-bkt/BYOD/", header="true", mode="overwrite")

# COMMAND ----------

print "test2"

# COMMAND ----------

print "test3"