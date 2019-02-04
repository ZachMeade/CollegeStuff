#include <stdio.h>
#include <MacTypes.h>
#include <stdlib.h>
#include <math.h>


typedef struct Node
{
    double data;
    struct Node *next;
}Node;
void push(struct Node** head_ref, double new_data)
{

    struct Node* new_node = (struct Node*) malloc(sizeof(struct Node));


    new_node->data  = new_data;


    new_node->next = (*head_ref);


    (*head_ref)    = new_node;
}
int pop(struct Node** head){
    int retval = -1;
    Node * next_node = NULL;

    if (*head == NULL) {
        return -1;
    }

    next_node = (*head)->next;
    retval = (*head)->data;
    free(*head);
    *head = next_node;

    return retval;
}
int endOfString(char sum[]){
    for(int i = 0; i<10; i++){
        if(sum[i] == ' ' && sum[i+1]== ' '){

            return(i+1);
        }

    }

}
int precedence(char operator){
    switch(operator) {
        case '-':
            return 1;
        case '+':
            return 2;
        case '/':
            return 3;
        case 'X':
            return 4;
        case '^':
            return 5;



    }

}
double getTop(struct Node** head){


        return ((*head)->data);

}

int main() {
    FILE *fp;
    char sum[255] = {' '};
    int count; int preference;
    Node * head = malloc(sizeof(Node));
    fp = fopen("/Users/zachmeade/CLionProjects/untitled/sum", "r");
    fgets(sum , 255, fp);

    fclose(fp);
    printf("%s", sum);
    for(int i = 0; i < 255 ; i++ ){
        if(sum[i] == NULL){
            sum[i] = ' ';
        }
    }

    double finalAnswer = 0;
    int emptyList = 1;
    int lengthOfList = 0;
    char poppedValue;
    char outputString[50] = {' '};
    int endOfString = 0;
    for(int i = 0; i< 255; i++){
        char currentVal = sum[i];
        if (sum[i] != ' ') {
            if ('0' <= sum[i] && sum[i] <= '9') {
                int count = 0;
                char number = 0;
                if (sum[i + 1] == ' ' || sum[i + 1] == NULL) {
                    outputString[endOfString] = sum[i];
                    endOfString++;

                } else if ('0' <= sum[i + 1] && sum[i + 1] <= '9') {


                    for (int j = 0; i + j < 255 && sum[i + j] != ' '; j++) {

                        if (sum[i + j] != ' ') {
                            outputString[endOfString] = sum[i + j];
                            endOfString++;
                            count++;
                        }
                    }
                }
                i += count;
                outputString[endOfString] = ' ';
                endOfString++;


            } else if (sum[i] == '(') {
                emptyList = 0;
                lengthOfList++;
                push(head, sum[i]);
            } else if (sum[i] == 'X' || sum[i] == '/' || sum[i] == '^' || sum[i] == '+' ||
                       sum[i] == '-') {
                if (emptyList != 1 && lengthOfList!= 0 && getTop(head) != '(' ) {

                    while (precedence(sum[i]) < precedence(getTop(head)) && getTop(head) != '(' && lengthOfList!= 0) {

                        char poppedValue = pop(head);
                        push(head, sum[i]);
                        outputString[endOfString] = poppedValue;
                        endOfString++;
                        outputString[endOfString] = ' ';
                        endOfString++;
                    }
                }
                else {
                    push(head, sum[i]);
                    lengthOfList++;
                    emptyList = 0;
                }
            } else if (sum[i] == ')') {
                while (getTop(head) != '(') {
                    outputString[endOfString] = pop(head);
                    endOfString++;
                    outputString[endOfString] = ' ';
                    endOfString++;
                    lengthOfList--;
                }
                pop(head);
                lengthOfList--;
            }
        }





    }
    while (lengthOfList != 0) {
        poppedValue = pop(head);
        outputString[endOfString] = poppedValue;
        endOfString++;
        outputString[endOfString] = ' ';
        endOfString++;

        lengthOfList--;
    }
    printf("\n%s ", outputString);

    printf("\n\n"
           "0101011101010101010101011111\n101010010101010101010100101\n101010100101001011100000000\n\n Jack Doyle has been hacked", finalAnswer);
    return 0;
}




