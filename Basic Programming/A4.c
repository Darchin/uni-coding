#include<stdio.h>
#include<stdlib.h>

void sort(float *, int);
float average(float *, int);

int main(){
	//read size of set
	printf("Enter the size of the set: ");
	int n;
	scanf("%d", &n);
	if(n<=0){
		printf("Invalid input");
		return 0;
	}
	//allocate memory
	float *set = (float *) malloc(n*sizeof(float));
	//fill the set
	for(int i=0;i<n;i++){
		printf("Enter element #%d: ", i+1);
		scanf("%f", set+i);
	}
	//remove duplicates
	for(int i=0;i<n;i++){
		for(int j=i+1;j<n;j++){
			if(*(set+i)==*(set+j)){
				for(int k=j;k<n-1;k++) *(set+k)=*(set+k+1);
				n--;
				//return to the same index in the next iteration
				//to check if there is more than 1 duplicate
				j--;
			}
		}
	}
	//create the new set, sorting it & releasing previous memory
	float *newSet = (float *) malloc(n*sizeof(float));
	for(int i=0;i<n;i++){
		*(newSet+i)=*(set+i);
	}
	free(set);
	sort(newSet, n);
	//print the new set & its average
	printf("\n\nThe new set is [");
	for(int i=0;i<n;i++){
		printf("%.2f ", *(newSet+i));
	}
	printf("\b]\n");
	printf("The average is: %.2f", average(newSet, n));
	//barname be payan mirese baraye hamin niazi be release kardan memory nist
	//free(newSet);
}

//sort function
void sort(float *array, int n){
	float temp;
	for(int i=0;i<n;i++){
		for(int j=i+1;j<n;j++){
			if(*(array+i)<*(array+j)){
				temp = *(array+i);
				*(array+i)=*(array+j);
				*(array+j)=temp;
			}
		}
	}
}

//averaging function
float average(float *array, int n){
	float sum = 0;
	//calculate sum
	for(int i=0;i<n;i++) sum+=*(array+i);
	return sum/n;
}