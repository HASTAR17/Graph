

//Quick sort
import java.util.*;
import java.lang.*;
import java.io.*;

public class Q {
int partition(int arr[],int left,int right){
int pivot=arr[left],l=left+1,r=right;
while(l<r){
while(l<right&&arr[l]<pivot){
l++;
}
while (r>left&&arr[r]>=pivot){
r--;
}
if(l<r){
int temp=arr[r];
arr[r]=arr[l];
arr[l]=temp;
}
}
arr[left]=arr[r];
arr[r]=pivot;
return r;
}
void Sort(int arr[],int left,int right){
if(left<right){
int p=partition(arr,left,right);
Sort(arr,left,p-1);
Sort(arr,p+1,right);
}
}
public static void main(String[] args) {
int arr[]={90, 23, 101, 45, 65, 23, 67, 89, 34, 23};
Q ob=new Q();
ob.Sort(arr,0,arr.length-1);
System.out.println("Sorted array");
for (int i = 0; i < arr.length; i++) {
System.out.print(arr[i] + " ");

}
}
}