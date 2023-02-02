#include <stdio.h>
int main() {
	//display message
	printf("Enter two non-negative integers, up to a maximum of 4 digits:\n");
	int m, n;
	scanf("%d%d", &m, &n);
	//check for invalid input
	while (m > 9999 || m < 0 || n > 9999 || n < 0) {
		printf("Invalid Input, re-enter the numbers:\n");
		scanf("%d%d", &m, &n);
	}
	//print input
	printf("m = %d\nn = %d\n", m, n);
	int m1, m2, m3, m4;
	int n1, n2, n3, n4;
	
	//digits of m
	m4 = m % 10;
	m3 = (m % 100 - m % 10)/10;
	m2 = (m % 1000 - m % 100)/100;
	m1 = (m % 10000 - m % 1000)/1000;
	//digits of n
	n4 = n % 10;
	n3 = (n % 100 - n % 10)/10;
	n2 = (n % 1000 - n % 100)/100;
	n1 = (n % 10000 - n % 1000)/1000;
	//print result
	printf("t is %d%d%d%d%d%d%d%d", m4, n4, m3, n3, m2, n2, m1, n1);
	return 0;
}