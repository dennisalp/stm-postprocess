package util.fileops;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import util.Complex;
import util.FieldOps;
import main.FieldMain;
public class ColumnIO {
 
 
 public static void writeAscii(double[][] data, String out)
 {
    
  try {
   BufferedWriter outd = new BufferedWriter(new FileWriter(out));
  for (int i = 0; i < data[0].length; i++)
   {
   int k;
   k = Integer.toString(i).length();
   int l;
   l = 11-k;
   for (int m = 1; m < l; m++)
   {
   outd.write(" ");
   }
   outd.write(i + "\t");
   for (int j = 0; j < data.length; j++)
    {
    outd.write(Double.toString(data[j][i])+"\t");
    }
   outd.newLine();
   
   }
   } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }  
 }
 public static double[] readOneColumn(String path)
 {
  double[] v;
  Scanner file = null;
  try{
   file = new Scanner(new File(path));
  }
  catch (FileNotFoundException e){
   System.out.println("File: " + path + " not found!");
   System.exit(0);
  }
  
  int i = 0;
  while (file.hasNextLine()) {
   file.nextLine();
   i++;
  }
  v = new double [i];
  i = 0;
  try{
   file = new Scanner(new File(path));
  }
  catch (FileNotFoundException e)
  {
   System.out.println("No file!");
  }
  
 
  while (file.hasNextLine())
  {
   v[i] = Double.parseDouble(file.nextLine());
   i++;
  }
  return v;
 }
 
 
 //The first column is v[0]; the second is v[1]
 public static double[][] readTwoColumns(String path)
 {
  double[][] v;
  Scanner file = null;
  try{
   file = new Scanner(new File(path));
  }
  catch (FileNotFoundException e){
   System.out.println("No file!");
  }
  
  int i = 0;
  while (file.hasNextLine()) {
   file.nextLine();
   i++;
  }
  v = new double [2][i];
  i = 0;
  try{
   file = new Scanner(new File(path));
  }
  catch (FileNotFoundException e)
  {
   System.out.println("No file!");
  }
  
  
  String[] word;
  while (file.hasNextLine())
  {
   word = file.nextLine().split("\t");
   v[0][i] = Double.parseDouble(word[0]);
   v[1][i] = Double.parseDouble(word[1]);
   i++;
  }
  return v;
 }
 public static double[][] readNColumns(String path, int n, int numLinesSkip)
 {
  double[][] v;
  Scanner file = null;
  try{
   File theFile = new File(path);
   
   file = new Scanner(theFile);
   int a = 0;
  }
  catch (FileNotFoundException e){
   System.out.println("No file!");
  }
  
  int i = 0;
  while (file.hasNextLine()) {
   file.nextLine();
   i++;
  }
  v = new double [n][i - numLinesSkip];
  i = 0;
  try{
   file = new Scanner(new File(path));
  }
  catch (FileNotFoundException e)
  {
   System.out.println("No file!");
  }
  
  
  String[] word;
  for (i = 0; i < numLinesSkip; i++)
   file.nextLine();
  i = 0;
  while (file.hasNextLine())
  {
   word = file.nextLine().split("\t");
   if (word.length == n)
    for (int j = 0; j < n; j++)
     v[j][i] = Double.parseDouble(word[j]);
   i++;
  }
  return v;
 }
 public static int getNumColumns(File theFile, int numLinesSkip)
 {
  double[][] v;
  Scanner file = null;
  try{
   file = new Scanner(theFile);
  }
  catch (FileNotFoundException e){
   System.out.println("No file!");
//   System.exit(0);
  }
  
  int i = 0;
  String line = file.nextLine();
  while (i < numLinesSkip) {
   line = file.nextLine();
   i++;
  }
  Scanner fromLine = new Scanner(line);
  i = 0;
  while (fromLine.hasNextDouble()) {
   fromLine.nextDouble();
   i++;
  }
  return i;
 }
 public static int getNumLinesSkip(File theFile, String skipIndicator)
 {
  double[][] v;
  Scanner file = null;
  try{
   file = new Scanner(theFile);
  }
  catch (FileNotFoundException e){
   System.out.println("No file!");
//   System.exit(0);
  }
  
  int i = 0;
  String line = file.nextLine();
  while (line.startsWith(skipIndicator)) {
   line = file.nextLine();
   i++;
  }
  return i;
 }
 
 public static double[][] readAllColumns(File theFile, String headerPrefix)
 {
  if (headerPrefix == null)
   return readNColumns(theFile, getNumColumns(theFile, 0), 0);
  else
  {
   int headerSize = getNumLinesSkip(theFile, headerPrefix);
   return readNColumns(theFile, getNumColumns(theFile, headerSize), headerSize);
  }
//   return readNColumns(theFile, getNumColumns(theFile, 0), 0);
   
 }
 
 public static void writeBin(double[][] data, String out)
 {
  File file = new File(out);
  FileOutputStream outf = null;
  DataOutputStream outd = null;
  
  try {
   outf = new FileOutputStream(file);
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  
  outd = new DataOutputStream(outf);
  try {
  for (int i = 0; i < data[0].length; i++)
   for (int j = 0; j < data.length; j++)
   {
     outd.writeDouble(data[j][i]);
   }
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
 }
 
 //writes all elements in data from x, y to x + dx, y + dy
 public static void writeBin(double[][] data, String out, int x, int y, int dx, int dy)
 {
  File file = new File(out);
  FileOutputStream outf = null;
  DataOutputStream outd = null;
  
  try {
   outf = new FileOutputStream(file);
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  
  outd = new DataOutputStream(outf);
  try {
   for (int i = y; i < y+dy; i++)
    for (int j = x; j < x+dx; j++)
    {
     outd.writeDouble(data[j][i]);
    }
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
 }
 public static void writeBin(double[][][] data, int thirdindex, String out)
 {
  File file = new File(out);
  FileOutputStream outf = null;
  DataOutputStream outd = null;
  
  try {
   outf = new FileOutputStream(file);
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  
  outd = new DataOutputStream(outf);
  try {
   for (int i = 0; i < data[0].length; i++)
    for (int j = 0; j < data.length; j++)
    {
      outd.writeDouble(data[j][i][thirdindex]);
    }
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
 }
 //assuming data are complex, write one dat file for magnitude and one for phase.
 public static void writeBinsPolar(double[][][] data, String outname)
 {
  File mag = new File(outname + "mag.dat");
  File phase = new File(outname + "phase.dat");
  FileOutputStream outfm = null;
  DataOutputStream outdm = null;
  FileOutputStream outfp = null;
  DataOutputStream outdp = null;
  
  try {
   outfm = new FileOutputStream(mag);
   outfp = new FileOutputStream(phase);
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  
  outdm = new DataOutputStream(outfm);
  outdp = new DataOutputStream(outfp);
  double m, p;
  try {
   for (int i = 0; i < data[0].length; i++)
    for (int j = 0; j < data.length; j++)
    {
     outdm.writeDouble(Complex.mag(data[j][i]));
     outdp.writeDouble(FieldOps.atan(data[j][i][0], data[j][i][1]));
    }
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
 }
 
 //assumes double precision
 public static double[][] readSquareTable(String in)
 {
  File file = new File(in);
  FileInputStream inf = null;
  DataInputStream ind = null;
  
  try {
   inf = new FileInputStream(file);
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  
  int N = (int)Math.sqrt((file.length()/8));
//  System.out.println(N);
  double[][] data = new double[N][N];
  ind = new DataInputStream(inf);
  try {
  for (int i = 0; i < N; i++)
   for (int j = 0; j < N; j++)
   {
     data[j][i] = ind.readDouble();
   }
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  return data;
 }
 //This reads complex data from 2 bin files. If polar, the names are inname + "mag.dat" or "phase.dat".
 public static double[][][] readSquareTables(String inname, boolean polar)
 {
  
  File mag = polar ? new File(inname + "mag.dat") : new File(inname + "re.dat");
  File phase = polar ? new File(inname + "phase.dat") : new File(inname + "im.dat");
  FileInputStream infm = null;
  DataInputStream indm = null;
  FileInputStream infp = null;
  DataInputStream indp = null;
  
  try {
   infm = new FileInputStream(mag);
   infp = new FileInputStream(phase);
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  
  int N = (int)Math.sqrt((mag.length()/8));
//  System.out.println(N);
  double[][][] data = new double[N][N][2];
  indm = new DataInputStream(infm);
  indp = new DataInputStream(infp);
  double m, p;
  try {
  if (polar)
   for (int i = 0; i < N; i++)
    for (int j = 0; j < N; j++)
    {
     m = indm.readDouble(); p = indp.readDouble();
     data[j][i][0] = m*Math.cos(p); data[j][i][1] = m*Math.sin(p);
    }
  else
   for (int i = 0; i < N; i++)
    for (int j = 0; j < N; j++)
    {
     data[j][i][0] = indm.readDouble(); data[j][i][1] = indp.readDouble();
    }
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  return data;
 }
 
 
// <>
 public static double[][][] readTwoSquareTables(String name1, String name2)
 {
  
  File mag = new File(name1);
  File phase = new File(name2);
  FileInputStream infm = null;
  DataInputStream indm = null;
  FileInputStream infp = null;
  DataInputStream indp = null;
  
  try {
   infm = new FileInputStream(mag);
   infp = new FileInputStream(phase);
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  
  int N = (int)Math.sqrt((mag.length()/8));
//  System.out.println(N);
  double[][][] data = new double[N][N][2];
  indm = new DataInputStream(infm);
  indp = new DataInputStream(infp);
  double m, p;
  try {
   for (int i = 0; i < N; i++)
    for (int j = 0; j < N; j++)
    {
     data[j][i][0] = indm.readDouble(); data[j][i][1] = indp.readDouble();
    }
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  return data;
 }
 public static double[][] readAllColumnsB(File theFile, String headerPrefix, String delimiter)
 {
  if (headerPrefix == null)
  {
   String contents = "";
   String[] lines;
   String[] things;
   Scanner s = null;
   try {
    s = new Scanner(theFile);
   } catch (FileNotFoundException e) {
    // TODO Auto-generated catch block
//    e.printStackTrace();
    System.out.println("no file :(");
   }
   while(s.hasNextLine())
   {
    contents += s.nextLine() + "\n";
   }
   System.out.println("read");
   lines = contents.split("\n");
   System.out.println("split");
   lines[0].trim();
   things = lines[0].split(delimiter);
   double[][] data = new double [things.length][lines.length];
   for (int i = 0; i < lines.length; i++){
    things = lines[i].split(delimiter);
    for (int j = 0; j < things.length; j++)
    {
     data[j][i] = Double.parseDouble(things[j]);
    }
   }
   return data;
  }
  else
  {
   int headerSize = getNumLinesSkip(theFile, headerPrefix);
   return readNColumns(theFile, getNumColumns(theFile, headerSize), headerSize);
  }
//   return readNColumns(theFile, getNumColumns(theFile, 0), 0);
   
 }
 
 public static String getASCII(String path)
 {
  File file = new File(path);
  Scanner it = null;
  try{
   it = new Scanner(file);
  }
  catch (FileNotFoundException e){
   System.out.println("No file!");
//   System.exit(0);
  }
  String data = "";
  while(it.hasNextLine())
  {
   data += it.nextLine() + "\r\n";
  }
  return data;
 }
 public static double[][] readNColumns(File theFile, int n, int numLinesSkip)
 {
  double[][] v;
  Scanner file = null;
  try{
   file = new Scanner(theFile);
  }
  catch (FileNotFoundException e){
   System.out.println("No file!");
//   System.exit(0);
  }
  
  int i = 0;
  while (file.hasNextLine()) {
   file.nextLine();
   i++;
  }
  v = new double [n][i - numLinesSkip];
  i = 0;
  try{
   file = new Scanner(theFile);
  }
  catch (FileNotFoundException e)
  {
   System.out.println("No file!");
  }
  
  
  String[] word;
  for (i = 0; i < numLinesSkip; i++)
   file.nextLine();
  i = 0;
  for (i = 0; i < v[0].length; i++){
   for (int k = 0; k < v.length; k++)
   { 
    v[k][i] = file.nextDouble();
//    System.out.println(i + "\t" + v[0].length);
   }
//   word = file.nextLine().split("\t");
//   if (word.length == n)
//    for (int j = 0; j < n; j++)
//     v[j][i] = Double.parseDouble(word[j]);
//   i++;
  }
  return v;
 }
 public static double[] readNthLine(File theFile, int N)
 {
  Scanner file = null;
  try{
   file = new Scanner(theFile);
  }
  catch (FileNotFoundException e){
   System.out.println("No file!");
//   System.exit(0);
  }
  
  int i = 0, len = 0;
  String s = null;
  while (file.hasNextLine() && i < N) {
   s = file.nextLine();
   i++;
  // System.out.println(i);
  }
 // System.out.println("line found");
  file = new Scanner(s);
  while(file.hasNextDouble()){
   file.nextDouble();
   len++;
  // if (len % 1000 == 0) System.out.println(len/1000);
  }
 // System.out.println("length gotten");
  file = new Scanner(s);
  double[] v = new double[len];
  for (i = 0; i < len; i++)
   v[i] = file.nextDouble();
  
 // System.out.println("array captured");
  return v;
 }
 public static void writeOneColumn(double[] v, String writeFile, String folder)
 {
  PrintStream firstStream = null;
  
  try{
   firstStream = new PrintStream(new File(folder + writeFile));
  }
  catch (FileNotFoundException e)
  {
   System.out.println("No new file.");
  }
  for (int i = 0; i < v.length; i++)
  {
   firstStream.append(v[i] + "\n");
  }  
 }
 public static void writeString(String info, String path)
 {
  PrintStream firstStream = null;
  
  try{
   firstStream = new PrintStream(new File(path));
  }
  catch (FileNotFoundException e)
  {
   System.out.println("No new file.");
  }
  firstStream.append(info);
 }
 public static void writeLines(String header, String[] lines, String path)
 {
  PrintStream firstStream = null;
  
  try{
   firstStream = new PrintStream(new File(path));
  }
  catch (FileNotFoundException e)
  {
   System.out.println("No new file.");
  }
  firstStream.append(header);
  for (int i = 0; i < lines.length; i++)
   firstStream.append(lines[i]);
 }
 public static void writeTwoColumns(double[] x, double[] y, String writeFile, String folder)
 {
  //This will only work properly if both arrays are of the same length:
  int length = Math.min(x.length, y.length);
  PrintStream firstStream = null;
  
  try{
   firstStream = new PrintStream(new File(folder + writeFile));
  }
  catch (FileNotFoundException e)
  {
   System.out.println("No new file.");
  }
  for (int i = 0; i < length; i++)
  {
   firstStream.append(x[i] + "\t" + y[i] + "\n");
  }  
 }
 //Used for histograms
 public static void writeTwoColumns(double[] x, int[] y, String writeFile, String folder)
 {
  //This will only work properly if both arrays are of the same length:
  int length = Math.min(x.length, y.length);
  PrintStream firstStream = null;
  
  try{
   firstStream = new PrintStream(new File(folder + writeFile));
  }
  catch (FileNotFoundException e)
  {
   System.out.println("No new file.");
  }
  for (int i = 0; i < length; i++)
  {
   firstStream.append(x[i] + "\t" + y[i] + "\n");
  }  
 }
 
 //Used for histograms
 public static void writeThreeColumns(double[] x, int[] y, double[] z, String writeFile, String folder)
 {
  //This will only work properly if both arrays are of the same length:
  int length = Math.min(Math.min(x.length, y.length), z.length);
  
  PrintStream firstStream = null;
  
  try{
   firstStream = new PrintStream(new File(folder + writeFile));
  }
  catch (FileNotFoundException e)
  {
   System.out.println("No new file.");
  }
  for (int i = 0; i < length; i++)
  {
   firstStream.append(x[i] + "\t" + y[i] + "\t" + z[i] + "\n");
  }  
 }
 public static void writeTable(int[][] table, String writeFile, String folder)
 {
  //This will only work properly if both arrays are of the same length:
  int width = table[0].length, height = table.length;
  
  PrintStream firstStream = null;
  
  try{
   firstStream = new PrintStream(new File(folder + writeFile));
  }
  catch (FileNotFoundException e)
  {
   System.out.println("No new file.");
  }
  for (int i = 0; i < height; i++){
   for (int j = 0; j < width; j++)
    firstStream.append(table[j][i] + "\t");
   if (i < height - 1)
    firstStream.append("\r\n");
  }
 }
 public static void writeTable(double[][] table, String filepath)
 {
  //This will only work properly if both arrays are of the same length:
  int width = table.length, height = table[0].length;
  
  PrintStream firstStream = null;
  
  try{
   firstStream = new PrintStream(new File(filepath));
  }
  catch (FileNotFoundException e)
  {
   System.out.println("No new file.");
  }
  for (int i = 0; i < height; i++){
   for (int j = 0; j < width; j++)
    firstStream.append(table[j][i] + "\t");
   if (i < height - 1)
    firstStream.append("\r\n");
  }
 }
 
 public static String getTwoColumns(double[] x, double[] y, int numDecimals)
 {
  String answer = "";
  if (numDecimals > 0 && numDecimals < 20)
   for (int i = 0; i < x.length; i++)
    answer += String.format("%." + numDecimals + "f", x[i]) + "\t" + String.format("%." + numDecimals + "f", y[i]) + "\n";
  else
   for (int i = 0; i < x.length; i++)
    answer += x[i] + "\t" + y[i] + "\n";
  return answer;
 }
 public static String getNColumns(double[][] x, int numDecimals)
 {
  String answer = "";
  if (numDecimals > 0 && numDecimals < 20)
   for (int i = 0; i < x[0].length; i++)
   {
    for (int j = 0; j < x.length; j++)
     answer += String.format("%." + numDecimals + "f", x[j][i]) + "\t";
    answer += "\n";
   }
  
  else
   for (int i = 0; i < x[0].length; i++)
   {
    for (int j = 0; j < x.length; j++)
     answer += String.format("%." + numDecimals + "f", x[j][i]) + "\t";
    answer += "\n";
   }
  return answer;
 }
 public static String addStringToLineStart(String paragraph, String prefix)
 {
  String answer = "";
  String[] lines = paragraph.split("\n");
  for (int i = 0; i < lines.length; i++)
   answer += prefix + lines[i] + "\r\n";
//  System.out.println(answer);
  return answer;
 }
 
 public static void main(String[] args){
  long t = System.currentTimeMillis();
  double[][] data = FieldMain.load("C:\\Users\\madhavanlab2011\\Desktop\\divadata.txt");
 }
 
 
}