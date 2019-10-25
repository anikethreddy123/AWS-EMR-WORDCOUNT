package edu.rmit.cosc2637.e20925.bigdata;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Nimma {

public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {
private final static IntWritable n_one = new IntWritable(1);
private Text word = new Text();

public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
StringTokenizer iteration = new StringTokenizer(value.toString());//creating an object for tokenizing the word
while (iteration.hasMoreTokens()) {
word.set(iteration.nextToken());
context.write(word, n_one);
}
}
}

public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
private IntWritable result = new IntWritable();

public void reduce(Text key, Iterable<IntWritable> values, Context context)
throws IOException, InterruptedException {
int sum = 0;
for (IntWritable val : values) {
sum += val.get();
}
result.set(sum);
context.write(key, result);

}
}

public static void main(String[] args) throws Exception {
// TODO Auto-generated method stub
Configuration conf = new Configuration();
conf.set("mapreduce.input.fileinputformat.split.minsize","50000000");//setting the input file size for mapper
Job wc_job = Job.getInstance(conf, "word count");
wc_job.setJarByClass(Nimma.class);
wc_job.setMapperClass(TokenizerMapper.class);
wc_job.setCombinerClass(IntSumReducer.class);
wc_job.setReducerClass(IntSumReducer.class);
wc_job.setNumReduceTasks(4);//setting number of reduces,change the number for number of reducers
wc_job.setOutputKeyClass(Text.class);
wc_job.setOutputValueClass(IntWritable.class);
FileInputFormat.addInputPath(wc_job, new Path(args[0]));
FileOutputFormat.setOutputPath(wc_job, new Path(args[1]));
System.exit(wc_job.waitForCompletion(true) ? 0 : 1);
}
}