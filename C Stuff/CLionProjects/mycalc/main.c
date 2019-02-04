#include <stdio.h>
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
    int retval;
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
        default:
            return 0;
    }

}
double postfix(char sum[], struct Node** head){
    int lengthOfList = 0;
    double finalAnswer = 0;
    int count = 0;
    for (int i = 0; i < 50 && sum[i] != NULL; i++) {
        count = 0;
        int number = 0;
        if (sum[i] != '^' && sum[i] != 'x' && sum[i] != 'X' && sum[i] != '/' && sum[i] != '+' && sum[i] != '-' && 0 <= sum[i] <= 9 && sum[i] != ' ') {
            if (sum[i + 1] == ' ') {
                number = sum[i]- 48;
            } else {
                for (int j = 0; i + j < 50 && sum[i + j] != ' '; j++) {
                    if (sum[i + j] != ' ') {
                        number = number* 10;
                        number = number + sum[i+j]-48;
                        count++;
                    }
                }
            }
            push(head, number);
            lengthOfList++;
        }
        else if(sum[i] != ' ') {
            double answer;
            double firstPopped = pop(head);
            double secondPopped = pop(head);
            switch (sum[i]) {
                case 'x':
                    answer = secondPopped * firstPopped;
                    push(head, secondPopped * firstPopped);
                    finalAnswer = answer;
                    break;
                case 'X':
                    answer = secondPopped * firstPopped;
                    push(head, secondPopped * firstPopped);
                    finalAnswer = answer;
                    break;
                case '/':
                    answer = secondPopped / firstPopped;
                    push(head, secondPopped / firstPopped);
                    finalAnswer = answer;
                    break;
                case '-':
                    answer = secondPopped - firstPopped;
                    push(head, secondPopped - firstPopped);
                    finalAnswer = answer;
                    break;
                case '+':
                    answer = secondPopped + firstPopped;
                    push(head, answer);
                    finalAnswer = answer;
                    break;
                case '^':
                    answer = pow(secondPopped, firstPopped);
                    push(head, answer);
                    finalAnswer = answer;
                    break;
                default:
                    break;
            }
            lengthOfList--;
        }
        i = i + count;
    }
    return finalAnswer;
}
int endOfString(char sum[]){
    for(int i = 0; i<50; i++){
        if(sum[i] == ' ' && sum[i+1]== ' '){

            return(i+1);
        }

    }

}
int getTop(struct Node** head){

    if((*head)->data != '!') {
        return ((*head)->data);
    }
    else return NULL;
}
double infix(char sum[], struct Node** head){
    double finalAnswer = 0;
    int emptyList = 1;
    int lengthOfList = 0;
    char poppedValue;
    char outputString[50] = {' '};
    int endOfString = 0;
    for(int i = 0; i< 50 ; i++){
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

                    while (lengthOfList != 0 && precedence(sum[i]) < precedence(getTop(head)) && getTop(head) != '(') {

                        char poppedValue = pop(head);
                        lengthOfList--;
                        outputString[endOfString] = poppedValue;
                        endOfString++;
                        outputString[endOfString] = ' ';
                        endOfString++;
                    }
                    push(head, sum[i]);
                    lengthOfList++;
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
        if(poppedValue != ')') {
            outputString[endOfString] = poppedValue;
            endOfString++;

            outputString[endOfString] = ' ';
            endOfString++;
        }
        lengthOfList--;
    }

    finalAnswer = postfix(outputString, head);
    return finalAnswer;


}
int main() {
    /*/char * filename;

    if ( argc == 1 ) {
        printf("Error: No input filename provided\n");
        printf("Usage: %s <input filename>\n", argv[0]);
        exit(1);
    }
    else if ( argc > 2 ) {
        printf("Error: Too many command line parameters\n");
        printf("Usage: %s <input filename>\n", argv[0]);
        exit(1);
    }
    else {
        filename = argv[1];
    }*/
    FILE *fp;
    double finalAnswer; int preference;
    Node * head = malloc(sizeof(Node));
    fp = fopen("test.in", "r");
    char sum[50] = {' '};
    char **appenedSum = malloc(sizeof(int)*50);

    while(fgets(sum, 50, fp) != NULL) {
        for(int i = 0; i<50; i++){
            appenedSum[i] = ' ';
        }
        fgets(sum, 50, fp);
        printf("%s", sum);

        if(sum[0] == 'i') {

            for (int i = 0;i<50; i++) {
                char currentValue = sum[i+5];
                if(currentValue != '\n') {
                    appenedSum[i] = sum[i + 5];
                }
            }

            preference = 1;
        }
        else if(sum[0] == 'p'){
            for (int i = 0;i<50; i++) {
                char currentValue = sum[i+7];
                if(currentValue != '\n') {
                    appenedSum[i] = sum[i + 7];
                }
                else{
                    appenedSum[i] = ' ';
                }
            }
            preference = 2;
        }


        if (preference == 1) {
            finalAnswer = infix(appenedSum, head);

        } else if (preference == 2) {

            finalAnswer = postfix(appenedSum, head);
        }
        printf("\n%f \n", finalAnswer);
        for (int j = 0;  j<50 ; j++) {
            appenedSum[j] = ' ';

        }
    }
    fclose(fp);
    return 0;

}