/*
File thông tin giao dịch sản ph input.txt có dạng như sau:
Product_name, city, Transaction_date, Price.
1. Thong ke so lan giao dich cua moi thanh pho.
2. Dua ra danh sach san pham theo tung thanh pho.
Giải;
3. function map()
Input: offset, valOffset:city, product name.
Output: list(city, product_name).
	function reducer()
Input: city, list(product name).
Output: city, detail(product name).
 */
package practive;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class TranSactionDriver {
	protected static String inputFile = "/home/huongvt/Documents/BoDuLieu/de3.txt";
	protected static String outputFile = "/home/huongvt/Documents/output" + Math.random();
	public static void main(String[] args) {
		JobClient my_client = new JobClient();
		// Create a configuration object for the job
		JobConf job_conf = new JobConf(TranSactionDriver.class);

		// Set a name of the Job
		job_conf.setJobName("Word Count1");

		// Specify data type of output key and value
		job_conf.setOutputKeyClass(Text.class);
		job_conf.setOutputValueClass(Text.class);

		// Specify names of Mapper and Reducer Class
		job_conf.setMapperClass(TransactionMapper.class);
		job_conf.setReducerClass(TransactionReducer.class);

		// Specify formats of the data type of Input and output
		job_conf.setInputFormat(TextInputFormat.class);
		job_conf.setOutputFormat(TextOutputFormat.class);

		// Set input and output directories using command line arguments, 
		//arg[0] = name of input directory on HDFS, and arg[1] =  name of output directory to be created to store the output file.
		
		//FileInputFormat.setInputPaths(job_conf, new Path(args[0]));
		//FileOutputFormat.setOutputPath(job_conf, new Path(args[1]));
		FileInputFormat.setInputPaths(job_conf, new Path(inputFile));
		FileOutputFormat.setOutputPath(job_conf, new Path(outputFile));
		
		my_client.setConf(job_conf);
		try {
			// Run the job 
			JobClient.runJob(job_conf);
	    	System.out.println("Transaction OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

