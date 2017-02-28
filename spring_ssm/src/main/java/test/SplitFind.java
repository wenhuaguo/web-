package test;

/**
 * Created by Acer on 2017/1/28.
 * Java折半查找
 */
public class SplitFind {
    //递归算法
    public static int splitHalf(int[] arrayData,int searchData,int star,int end){
        int mid = (star + end)/2;
        int data = arrayData[mid];
        if(star > end){
            return -1;
        }
        if(data == searchData){
            return mid;
        }else {
            if (searchData < data) {
                return splitHalf(arrayData, searchData, star, mid - 1);
            } else {
                return splitHalf(arrayData, searchData, mid + 1, end);
            }
        }

    }
    //非递归算法
    public static int biSearch(int[] arrayData,int searchData,int beg,int end){
        //定义一个中间下标的变量
        int mid;
        if(beg > end){
            return -1;
        }

        while (beg <= end){
            mid = (beg + end)/2;
            if(searchData == arrayData[mid]){
                return mid;
            }else if(searchData < arrayData[mid]){
                end = mid -1;
            }else if(searchData > arrayData[mid]){
                beg = mid + 1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7,8};
        System.out.println(array.length);
        //返回的是下标
        int isExist = SplitFind.splitHalf(array,8,0,array.length -1);
        System.out.println("isExit:" +isExist);
        int exit = SplitFind.biSearch(array,3,0,array.length - 1);
        System.out.println("exit:" + exit);
    }

}
