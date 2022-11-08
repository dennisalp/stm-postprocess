package util.fileops;

import java.io.*;
import java.util.Scanner;

import util.FieldOps;
import util.fourier.FFTOps;

import main.SRAW;

public class RHKFileOps {
 //The ASCII file has 13 lines before the table. Also, each table line has some spaces
 //followed by the row number starting from zero, followed by the data.
 public static void makeAsciiTable(String ascii, String table, String dira, String dirt)
 {
  File asc = new File(dira + ascii);
  File out = new File(dirt + table);
  String line;
  PrintStream output = null;
  Scanner in = null;
  try {
   in = new Scanner(asc);
   output = new PrintStream(out);
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  
  int i = 0;
  for (i = 0; i < 13; i++)
   in.nextLine();
  
  i = 0;
  int log = 1, pow = 10;
  while(in.hasNextLine())
  {
   line = in.nextLine();
   if (line.trim().equals(""))
    break;
   
   log = 1; pow = 10;
   while(pow <= i){
    pow *= 10;
    log++;
   }
   
   output.append(line.substring(line.indexOf("" + i) + log) + "\r\n");
   i++;
   
  }
 }
 public static double[][] getTable(String ascii, String dir)
 {
  File asc = new File(dir + ascii);
  String line;
  Scanner in = null;
  try {
   in = new Scanner(asc);
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  
  int i = 0;
  for (i = 0; i < 13; i++)
   in.nextLine();
  
  i = 0;
  int log = 1, pow = 10;
  int nlines = 0;
  String[] words;
  while(in.hasNextLine())
  {
   nlines++;
   line = in.nextLine();
  }
  //done.
  int nperline;
  double[][] data = null;
  try {
   in = new Scanner(asc);
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  for (i = 0; i < 13; i++)
   in.nextLine();
  i = 0;
  while(in.hasNextLine())
  {
   line = in.nextLine();
   line = line.trim();
   words = line.split("\t");
   nperline = words.length - 1;
   if (data == null) data = new double[nperline][nlines];
   for (int j = 1; j < words.length; j++)
   {
    data[j-1][i] = Double.parseDouble(words[j]);
   }
   i++;
  }
  return data;
 }
 public static double[][] getTableLines(File asc)
 {
  String line;
  Scanner in = null;
  try {
   in = new Scanner(asc);
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  
  int i = 0;
  
  i = 0;
  int log = 1, pow = 10;
  int nlines = 0;
  String[] words;
  while(in.hasNextLine())
  {
   nlines++;
   line = in.nextLine();
  }
  //done.
  int nperline;
  double[][] data = null;
  try {
   in = new Scanner(asc);
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  i = 0;
  while(in.hasNextLine())
  {
   line = in.nextLine();
   line = line.trim();
   words = line.split("\t");
   nperline = words.length - 1;
   if (data == null) data = new double[nperline][nlines];
   for (int j = 1; j < words.length; j++)
   {
    data[j-1][i] = Double.parseDouble(words[j]);
   }
   i++;
  }
  return data;
 }
 public static double[][] getTable(File asc)
 {
  String line;
  Scanner in = null;
  try {
   in = new Scanner(asc);
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  
  int i = 0;
  for (i = 0; i < 13; i++)
   in.nextLine();
  
  i = 0;
  int log = 1, pow = 10;
  int nlines = 0;
  String[] words;
  while(in.hasNextLine())
  {
   nlines++;
   line = in.nextLine();
  }
  //done.
  int nperline;
  double[][] data = null;
  try {
   in = new Scanner(asc);
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  for (i = 0; i < 13; i++)
   in.nextLine();
  i = 0;
  while(in.hasNextLine())
  {
   line = in.nextLine();
   line = line.trim();
   words = line.split("\t");
   nperline = words.length - 1;
   if (data == null) data = new double[nperline][nlines];
   for (int j = 1; j < words.length; j++)
   {
    data[j-1][i] = Double.parseDouble(words[j]);
   }
   i++;
  }
  return data;
 }
 public static double[][] getTableSwitched(File asc) //this flips the x-axis
 {
  String line;
  Scanner in = null;
  try {
   in = new Scanner(asc);
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  
  int i = 0;
  for (i = 0; i < 13; i++)
   in.nextLine();
  
  i = 0;
  int log = 1, pow = 10;
  int nlines = 0;
  String[] words;
  while(in.hasNextLine())
  {
   nlines++;
   line = in.nextLine();
  }
  //done.
  int nperline;
  double[][] data = null;
  try {
   in = new Scanner(asc);
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  for (i = 0; i < 13; i++)
   in.nextLine();
  i = 0;
  while(in.hasNextLine())
  {
   line = in.nextLine();
   line = line.trim();
   words = line.split("\t");
   nperline = words.length - 1;
   if (data == null) data = new double[nperline][nlines];
   for (int j = 1; j < words.length; j++)
   {
    data[(words.length-1) - j][i] = Double.parseDouble(words[j]);
   }
   i++;
  }
  return data;
 }
 public static void saveWithRHKRowStarts()
 {
  
 }
 public static void main(String[] args)
 {
  String dir;
//  String asc = "to dan.txt";
//  String out = "topoA";
//  double[][] data = getTable(asc, dir);
//  FieldOps.subtractSlopes1(data);
//  FieldOps.subtractAvg(data);
//  //  ColumnIO.writeTable(data, dir + out + ".txt");
//  ColumnIO.writeBin(data, dir + out + ".dat");
//  ColumnIO.writeString(topoInfo(dir + asc).toString(), dir + out + "info.txt");
//  SRAW.writeImage(dir + out + ".bmp", data);
//  doTxtFilesIn("C:\\Users\\madhavanlab2011\\Documents\\PPTs\\1\\");
//  dir = "C:\\data\\lawlerhex\\labsscobigtopo\\";
  dir = "C:\\Users\\Dennis\\Dennis\\Research Science Institute\\Lab Data\\test\\";
  doItDir1(dir, dir, "9-06-2010--5k-  0026 didv");
//  doIt(dir + "9-06-2010--5k-  0026 didv.txt", dir, "0026didv");
//  makeAsciiFile(dir + "topo.dat", dir, "topo");
 }
 
 public static void makeAsciiFile(String file, String outdir, String name)
 { 
  long t = System.currentTimeMillis();
  
  double[][] data = ColumnIO.readSquareTable(file);
  
  System.out.println((System.currentTimeMillis() - t)/1000 + " to load.");
  t = System.currentTimeMillis();
  
  ColumnIO.writeAscii(data, outdir + name + ".txt");
  
  System.out.println((System.currentTimeMillis() - t)/1000 + " to write [" + outdir + name + ".txt].");
 }
 public static void doIt(String file, String outdir, String name)
 {
  double[][] data = getTableSwitched(new File(file));
  ColumnIO.writeBin(data, outdir + name + ".dat");
  SRAW.writeImage(outdir + name + ".bmp", data);
  FFTOps.writeFFTBMPs(outdir + name + "fft", FFTOps.obtainFFT(data), true, true);
 }
 //Assumes that this is the only file in the directory.
 public static void doItDir1(String dir, String outdir, String name)
 {
  double[][] data = getTableSwitched(new File(dir).listFiles()[0]);
//  double[][] data = getTableLines(new File(dir).listFiles()[0]);
  ColumnIO.writeBin(data, outdir + name + ".dat");
  SRAW.writeImage(outdir + name + ".bmp", data);
  FFTOps.writeFFTBMPs(outdir + name + "fft", FFTOps.obtainFFT(data), true, true);
 }
 
 public static void doTxtFilesIn(String dir)
 {
  File[] files = new File(dir).listFiles();
  double[][] data;
  for (int i = 0; i < files.length; i++)
  {
   if (files[i].toString().endsWith(".txt"))
   {
    data = getTable(files[i]);
    ColumnIO.writeBin(data, files[i].toString().substring(0, files[i].toString().length() - 4) + ".dat");
   }
  }
 }
 
 
 public static TopoInfo topoInfo(String file)
 {
  String header = "";
  Scanner in = null;
  File asc = new File(file);
  try {
   in = new Scanner(asc);
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  int i = 0;
  for (i = 0; i < 8; i++)
   header += in.nextLine() + "\r\n";
  
  double lx, ly;
  String[] lines = header.split("\r\n");
  String[] size = lines[7].split(" ");
  lx = Double.parseDouble(size[2]);
  ly = Double.parseDouble(size[5]);
  
  String[] other = lines[4].split(" ");
  double bias, setp;
//  bias = Double.parseDouble(other[2]);
//  setp = Double.parseDouble(other[5]);
  bias = Double.parseDouble(other[4]);
  setp = Double.parseDouble(other[7]);
  TopoInfo topo = new TopoInfo();
  topo.lx = lx;
  topo.ly = ly;
  topo.bias = bias;
  topo.setpoint = setp;
  System.out.println(topo);
  return topo;
 }
 public static class TopoInfo
 {
  double lx, ly; //in nm
  double bias; //mv
  double setpoint; //pA.
  
  public String toString()
  {
   return "" + lx + "\r\n" + ly + "\r\n" + bias + "\r\n" + setpoint+ "\r\n";
  }
  public double[] toDarray()
  {
   return new double[] {lx, ly, bias, setpoint};
  }
 }
 public static double[] readTopoInfo(String topoInfo)
 {
  double[] result = new double [4];
  Scanner in = null;
  File asc = new File(topoInfo);
  try {
   in = new Scanner(asc);
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  for (int i = 0; i < 4; i++)
   result[i] = in.nextDouble();
  return result;
 }
}
