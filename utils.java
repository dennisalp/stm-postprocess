package misc;

import util.fileops.ColumnIO;
import main.SRAW;

public class UGenerator {

 public static double [][][] u (int N){
  double[][][] u = new double[N][N][2];
  int x, y;
  for (int i = 0; i < N; i++)
   for (int j = 0; j < N; j++)
   {
    x = i-N/2;
    y = j-N/2;
    u[i][j][0] = x*x + y*y - 2*x*y + 0.01*x*x*x + 0.005*y*y*y;
    u[i][j][1] = x*x - y*y + 2*x*y + 0.01*x*x*x - 0.005*y*y*y;
    u[i][j][0] *= 0.00005;
    u[i][j][1] *= 0.00005;
    
   }
  return u;
 }
 
 public static double[][] topograph(double[][][] u, double[][] bragg)
 {
  int N = u.length;
  double[][] topo = new double[N][N];
  double x, y;
  for (int i = 0; i < N; i++)
   for (int j = 0; j < N; j++)
   {
    x = i;
    y = j;
    topo[i][j] = Math.cos(bragg[0][0]*(x-u[i][j][0]) + bragg[0][1]*(y-u[i][j][1])) + Math.cos(bragg[1][0]*(x-u[i][j][0]) + bragg[1][1]*(y-u[i][j][1]));
   }
  return topo;
 }
 public static double[][] topographHex(double[][][] u, double[][] bragg)
 {
  int N = u.length;
  double[][] topo = new double[N][N];
  double[] bragg3 = {bragg[0][0] - bragg[1][0], bragg[0][1] - bragg[1][1]};
  double x, y;
  for (int i = 0; i < N; i++)
   for (int j = 0; j < N; j++)
   {
    x = i;
    y = j;
    topo[i][j] = Math.cos(bragg[0][0]*(x-u[i][j][0]) + bragg[0][1]*(y-u[i][j][1])) + Math.cos(bragg[1][0]*(x-u[i][j][0]) + bragg[1][1]*(y-u[i][j][1])) + Math.cos(bragg3[0]*(x-u[i][j][0]) + bragg3[1]*(y-u[i][j][1]));
   }
  return topo;
 }
 
 public static void main(String[] args)
 {
  double[][] bragg = new double[][] {{1, 0}, {0.5, .8660254038}};
  for (int i = 0; i < 2; i++)
   for (int j = 0; j < 2; j++)
    bragg[i][j] *= Math.PI/4;
  double[][] topo = topographHex(u(1024), bragg);
  SRAW.writeImage("C:\\Users\\Dennis\\Dennis\\Research Science Institute\\STM\\Fake4\\topo.bmp", topo);
  ColumnIO.writeBin(topo, "C:\\Users\\Dennis\\Dennis\\Research Science Institute\\STM\\Fake4\\topo.dat");
 }
}

