#include<stdio.h>
#include<stdlib.h>
#include<conio.h>
#include<string.h>
int find(char *, char []);
int check_pass(char *, char []);
char *copy(char *, int, int);
void concatenate(char *, char [], char [], char []);
//account.txt file structure:
//user1\tpass1\r\n
//user2\tpass2\r\n
//user3\tpass3
int position = 0;
int main(){
	char address[] = "accounts.txt";
	FILE *p = fopen(address, "ab+");
	if(p == NULL){
		printf("Wrong File Address!");
		exit(1);
	}
	int pass_len;
	char ch, choice;
	char username[21], password[16];
	while(1){
		//prompt
		printf("\n\n1) Register\n2) Change Password\n3) Remove\n4) Exit");
		printf("\n\nChoose an option from 1 to 4: ");
		choice = getche();
		//check for invalid input
		if(choice<'1' || choice>'4'){
			printf("\nInvalid input\n\n");
			continue;
		}
		//choice #1
		if(choice == '1'){
			while(1){
				printf("\nEnter the username: ");
				gets(username);
				if(find(address, username) != -1){
					printf("\nUsername already exists.\n");
					continue;
				}
				else break;
			}
			printf("Enter the password: ");
			gets(password);
			fseek(p, 0, SEEK_END);
			fprintf(p, "\r\n%s\t%s", username, password);
			printf("\nAccount successfully added.\n");
			continue;
		}
		//choice #2
		if(choice == '2'){
			while(1){
				printf("\nEnter the username: ");
				gets(username);
				position = find(address, username);
				if(find(address, username) == -1){
					printf("Username not found, please try again.\n");
					continue;
				}
				else{
					while(1){
						printf("Enter the current password: ");
						gets(password);
						if(check_pass(address, password) == -1){
							printf("\nEntered password is incorrect, please try again.\n\n");
						}
						else{
							pass_len = strlen(password);
							printf("Enter the new password: ");
							gets(password);
							fseek(p, 0, SEEK_END);
							char *first_half = (char *) malloc(position * sizeof(char) + 1);
							first_half = copy(address, 0, position);
							char *second_half = (char *) malloc((ftell(p) - (position + pass_len) + 1) * sizeof(char));
							second_half = copy(address, (position + pass_len), ftell(p));
							concatenate(address, first_half, second_half, password);
							printf("\nPassword successfully changed.\n");
							break;
						}
					}
				}
				break;
			}
		}
		//choice #3
		if(choice == '3'){
			while(1){
				printf("\nEnter the username: ");
				gets(username);
				position = find(address, username);
				if(find(address, username) == -1){
					printf("Username not found, please try again.\n");
					continue;
				}
				else{
					while(1){
						printf("Enter the current password: ");
						gets(password);
						if(check_pass(address, password) == -1){
							printf("\nEntered password is incorrect, please try again.\n\n");
						}
						else{
							pass_len = strlen(password);
							fseek(p, 0, SEEK_END);
							char *first_half = copy(address, 0, (position - strlen(username) - 1));
							char *second_half = copy(address, (position + pass_len + 2), ftell(p));
							concatenate(address, first_half, second_half, "");
							printf("\nAccount successfully removed.\n");
							break;
						}
					}
				}
				break;
			}
		}
		//choice #4
		if(choice == '4'){
			exit(0);
		}
		}
}

//find username, if found return the cursor position of the start of the password, otherwise return -1
int find(char *address, char string[]){
	FILE *p = fopen(address, "rb");
	int slen = strlen(string);
	while(!(feof(p))){
		for(int i = 0; i < slen; i++){
			if(getc(p) != string[i]) break;
			else if(i == (slen - 1) && getc(p) == '\t'){
				return ftell(p);
			}
		}
		while(getc(p) != '\n' && !(feof(p))){}
		if(feof(p)) return -1;
	}
	fclose(p);
}

//check if input password is correct
int check_pass(char *address, char string[]){
	FILE *p = fopen(address, "rb");
	fseek(p, position, 0);
	int slen = strlen(string);
	for(int i = 0; i < slen; i++){
		if(feof(p)) return -1;
		if(getc(p) != string[i]) break;
		else if(i == (slen - 1) && getc(p) == '\r'){
			return 1;
			
		}
	}
	fclose(p);
	return -1;
}

char *copy(char *address, int starting_pos, int final_pos){
	FILE *p = fopen(address, "rb");
	char *text = (char *) malloc((final_pos - starting_pos + 1) * sizeof(char));
	fseek(p, starting_pos, 0);
	int position = fread(text, 1, final_pos - starting_pos, p);
	text[position] = '\0'; //adding null terminator
	fclose(p);
	return text;
}


//join two halves of txt file back together after changing password
void concatenate(char *address, char string1[], char string2[], char pass[]){
	FILE *p = fopen(address, "wb");
	int strlen1 = strlen(string1),
		strlen2 = strlen(string2),
		passlen = strlen(pass);
	fwrite(string1, 1, strlen1, p);
	fwrite(pass, 1, passlen, p);
	fwrite(string2, 1, strlen2, p);
	fclose(p);
}