package top.ssy.share.admin.utils;

import com.alibaba.fastjson2.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 通过坐标和点生成有向网邻接矩阵，然后通过floyd算法计算两点间最短距离
 *
 * @Author ycshang
 * @Date 2023-05-25 10:43
 */
public class FloydUtil {

    /**
     * 由点线数据构造邻接矩阵
     */
    public static int[][] constructAdjacencyMatrix(List<String> pointList, JSONObject lineJson) {
        int[][] matrix = new int[pointList.size()][pointList.size()];
        for (int i = 0; i < pointList.size(); i++) {
            String startPoint = pointList.get(i);
            for (int j = 0; j < pointList.size(); j++) {
                String endPoint = pointList.get(j);
                if (startPoint.equals(endPoint)) {
                    matrix[i][j] = 0;
                } else {
                    String key = startPoint + endPoint;
                    if (lineJson.containsKey(key)) {
                        matrix[i][j] = lineJson.getInteger(key);
                    } else {
                        // -1 代表无限大
                        matrix[i][j] = -1;
                    }
                }
            }
        }
        return matrix;
    }

    /**
     * Dijkstra算法，求任意两点之间的最短路径
     *
     * @param matrix：二维矩阵，代表两个节点之间的距离，其中-1代表两点之间无法直接相连
     * @param startPoint：源节点
     * @param endPoint：目的节点
     * @return 源节点到目的节点之间的最短距离
     */

    public static ArrayList<String> getShortestPaths(int[][] matrix, String startPoint, String endPoint, List<String> pointList) {
        // 用于存放节点src到其它节点的最短距离
        int[] result = new int[matrix.length];
        // 用于判断节点是否被遍历
        boolean[] visited = new boolean[matrix.length];
        // 用于存放(src, des)之间的最短路径
        ArrayList<String> path = new ArrayList<>();
        // 起点下标
        int start = pointList.lastIndexOf(startPoint);
        // 终点下标
        int end = pointList.lastIndexOf(endPoint);

        // step1：初始化，将src节点设置为已经访问，并且初始化该节点到其他所有节点之间的距离
        visited[start] = true;
        path.add(startPoint);

        for (int i = 0; i < matrix.length; i++) {
            result[i] = matrix[start][i];
        }
        while (true) {
            // step2：找到剩余未访问的节点中，距离src最近的一个节点，作为curNode
            int curNode = getNearestNode(matrix, visited, result);
            // step3：更新所有节点距离src节点的最短距离（需要考虑curNode）
            update(matrix, curNode, visited, result, path, pointList,endPoint);
            if (curNode == end) {
                break;
            }
        }

        System.out.println("节点" + startPoint + "到节点" + endPoint + "经过的最短路径是：" + path);
        System.out.println("节点" + startPoint + "到节点" + endPoint + "之间的最短距离是：" + result[end]);
        return path;
    }

    /**
     * 找到剩余未访问的节点中，距离源节点最近的一个节点编号
     */
    public static int getNearestNode(int[][] matrix, boolean[] visited, int[] result) {
        int minIndex = -1;
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            if (!visited[i] && result[i] != -1 && result[i] < minLength) {
                minIndex = i;
                minLength = result[i];
            }
        }

        return minIndex;
    }

    /**
     * 更新与curNode节点直接相连接的未被访问的节点距离，专业术语叫 “松弛”
     */
    public static void update(int[][] matrix, int curNode, boolean[] visited, int[] result, ArrayList<String> path, List<String> pointList,String endPoint) {
        visited[curNode] = true;
        for (int j = 0; j < matrix.length; j++) {
            // 节点j未访问过 && 节点curNode与节点j之间需要直接相连

            if (!visited[j] && matrix[curNode][j] != -1) {
                // 更新result[j]
                if (result[j] > result[curNode] + matrix[curNode][j] || result[j] == -1) {
                    result[j] = result[curNode] + matrix[curNode][j];
                    if (path.size() == 1) {
                        if(pointList.get(curNode).equals(endPoint)){
                            path.add(pointList.get(curNode));
                        }else {
                            path.add(pointList.get(curNode));
                            path.add(pointList.get(j));
                        }
                    } else if(!path.get(path.size() - 1).equals(endPoint)){
                        if (path.get(path.size() - 1).equals(pointList.get(curNode))) {
                            path.add(pointList.get(j));
                        } else {
                            if(pointList.get(curNode).equals(endPoint)){
                                path.set(path.size() - 2, pointList.get(curNode));
                                path.remove(path.size() - 1);
                            }else {
                                path.set(path.size() - 2, pointList.get(curNode));
                                path.set(path.size() - 1, pointList.get(j));
                            }
                        }
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        List<String> pointList = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I");
        JSONObject lineJson = new JSONObject();
        lineJson.put("AB", 3);
        lineJson.put("AD", 2);

        lineJson.put("BA", 3);
        lineJson.put("BC", 4);
        lineJson.put("BE", 1);

        lineJson.put("CF", 3);

        lineJson.put("DE", 1);
        lineJson.put("DG", 2);

        lineJson.put("EB", 1);
        lineJson.put("ED", 1);

        lineJson.put("FI", 3);

        lineJson.put("GD", 2);

        lineJson.put("HG", 2);
        lineJson.put("IH", 2);

        int[][] adjacencyMatrix = constructAdjacencyMatrix(pointList, lineJson);
//        System.out.print(0+" ");
//        for (int i = 0; i < pointList.size(); i++) {
//            System.out.print(pointList.get(i) + " ");
//        }
//        System.out.println();
//        for (int i = 0; i < pointList.size(); i++) {
//            System.out.print(pointList.get(i) + " ");
//            for (int j = 0; j < pointList.size(); j++) {
//                System.out.print((adjacencyMatrix[i][j] == -1? "-": adjacencyMatrix[i][j])+ " ");
//            }
//            System.out.println();
//        }
        getShortestPaths(adjacencyMatrix, "B", "G", pointList);

    }


}
