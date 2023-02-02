#include<stdio.h>
main(){
	int a = 10;
	int *p;
	p = &a;
	printf("%d", *p);
}
//int function(float a[],int n)
//{
//	float avg, sum=0;
//	
//	//calculate sum
//	for(int i=0; i<n; i++) sum += a[i];
//	//calculate average
//	avg = sum/n;
//	
//	float min;
//	int index;
//	//calculate first element bigger than the average
//	for(int i=0; i<n; i++)
//	{
//		if(a[i]>avg) {
//			min = a[i];
//			index=i;
//			break;
//		}
//	}
//	//find the index of the smallest element which is bigger then the average
//	for(int i=index; i<n; i++){
//		if(a[i]>avg && a[i]<min) {
//			min = a[i];
//			index = i;
//		}
//	}
//	return index;
//}
