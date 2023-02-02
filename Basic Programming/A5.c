#include<stdio.h>
#include<ctype.h>
#include<string.h>
struct account{char username[20], password[15];};
int pwdCheck(struct account);
int pwdLenCheck(struct account);

//for testing code
//int main(){
//	struct account acc1 = {"username", "Test12#7"};
//	printf("Complexity Test: %d\nLength Test: %d", pwdCheck(acc1), pwdLenCheck(acc1));
//	}

int pwdCheck(struct account a){
	int n = strlen(a.password);
	int lc,uc,no,sp;
	lc=uc=no=sp=0;
	for(int i=0;i<n;i++){
		//only checking conditions that have not passed yet
		if(lc==0 && islower(a.password[i])!=0) lc+=1;
		if(uc==0 && isupper(a.password[i])!=0) uc+=1;
		if(no==0 && isdigit(a.password[i])!=0) no+=1;
		if(sp==0 && isalnum(a.password[i])==0) sp+=1;
	}
	//if all tests pass, sum is 4
	if(lc+uc+no+sp==4) return 1;
	else return 0;
}

int pwdLenCheck(struct account a){
	if(strlen(a.password)>=8) return 1;
	else return 0;
}
