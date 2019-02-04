#include <stdio.h>
#include <stdlib.h>

typedef struct bitset
{

    int numberOfBits;
    int *array;
}bitset;
// create a new, empty bit vector set with a universe of 'size' items
struct bitset * bitset_new(int size){
    int numberOfInts = size/32;
    int extraInt = 0;
    if(size%32!= 0){
        extraInt = 1;
    }
    struct bitset *newBitset = malloc(sizeof(bitset));
    newBitset->array = malloc(sizeof(int) * (numberOfInts+extraInt));
    newBitset->numberOfBits = size;
    newBitset->array[numberOfInts+extraInt];
    for(int i = 0; i< numberOfInts+extraInt;i++){
        newBitset->array[i] = 0;
    }
    return newBitset;
}

// get the size of the universe of items that could be stored in the set
int bitset_size(struct bitset * this){
    return this->numberOfBits;
}

// get the number of items that are stored in the set
int bitset_cardinality(struct bitset * this){
    int setCount = 0;
    for (int i = 0; i < this->numberOfBits; i++) {


        int intNumber = i/32;
        int posInInt = i%32;

        unsigned int flag = 1;  // flag = 0000.....00001

        flag = flag << posInInt;     // flag = 0000...010...000   (shifted k positions)

        if (this->array[intNumber] & flag)      // Test the bit at the k-th position in A[i]
            setCount++;

    }
    return setCount;
}

// check to see if an item is in the set
int bitset_lookup(struct bitset * this, int item){
    int intNumber = item/32;
    int posInInt = item%32;
    unsigned int flag = 1;
    flag = flag << posInInt;
    if(this->array[intNumber]&flag){
        return 1;
    }
    else
        return 0;
}


// add an item, with number 'item' to the set
// has no effect if the item is already in the set
void bitset_add(struct bitset * this, int item){
    if(bitset_lookup(this, item)!=1){
        int intNumber = item/32;
        int posInInt = item%32;
        unsigned int flag = 1;
        flag = flag << posInInt;
        this->array[intNumber] = this->array[intNumber] | flag;
    }
}

// remove an item with number 'item' from the set
void bitset_remove(struct bitset * this, int item){
    int intNumber = item/32;
    int posInInt = item%32;
    unsigned int flag = 1;
    flag = flag << posInInt;
    flag = ~flag;
    this->array[intNumber] = this->array[intNumber] & flag;
}

// place the union of src1 and src2 into dest;
// all of src1, src2, and dest must have the same size universe
void bitset_union(struct bitset * dest, struct bitset * src1, struct bitset * src2){
    if(dest->numberOfBits== src1->numberOfBits && src1->numberOfBits ==src2->numberOfBits){
        int numberOfInts = dest->numberOfBits/32;
        int extraInt = 0;
        if(dest->numberOfBits%32!= 0){
            extraInt = 1;
        }
        for(int i= 0;i<(numberOfInts+extraInt);i++){
            dest->array[i]= src1->array[i] | src2->array[i];
        }
    }
}


// place the intersection of src1 and src2 into dest
// all of src1, src2, and dest must have the same size universe
void bitset_intersect(struct bitset * dest, struct bitset * src1, struct bitset * src2){
    if(dest->numberOfBits== src1->numberOfBits && src1->numberOfBits ==src2->numberOfBits){
        int numberOfInts = dest->numberOfBits/32;
        int extraInt = 0;
        if(dest->numberOfBits%32!= 0){
            extraInt = 1;
        }
        for(int i= 0;i<(numberOfInts+extraInt);i++){
            dest->array[i]= src1->array[i] & src2->array[i];
        }
    }
}
void bitset_print(struct bitset * this)
{
    int i;
    int size = bitset_size(this);
    for ( i = 0; i < size; i++ ) {
        if ( bitset_lookup(this, i) == 1 ) {
            printf("%d ", i);
        }
    }
    printf("\n");
}
// add the characters from a string to a bitset
void add_chars_to_set(struct bitset * this, char * s)
{
    int i;
    for ( i = 0; s[i] != 0; i++ ) {
        unsigned char temp = s[i];
        bitset_add(this, temp);
    }
}

// small routine to test a bitset
void mytest()
{
    struct bitset * a = bitset_new(256);
    struct bitset * b = bitset_new(256);
    struct bitset * c = bitset_new(256);
    char * string1 = "What can you hear";
    char * string2 = "Nothing but the rain";

    add_chars_to_set(a, string1);
    add_chars_to_set(b, string2);

    // print the contents of the sets
    bitset_print(a);
    bitset_print(b);

    // compute and print the union of sets
    bitset_union(c, a, b);
    bitset_print(c);

    // compute and print the intersection of sets
    bitset_intersect(c, a, b);
    bitset_print(c);
}

int main() {
    /*struct bitset *dest = bitset_new(32);
    struct bitset *src1 = bitset_new(32);
    struct bitset *src2 = bitset_new(32);
    bitset_add(src1, 1);
    bitset_add(src2, 4);
    bitset_print(src1);
    bitset_print(src2);
    bitset_union(dest, src1, src2);
    bitset_print(dest);
    bitset_add(src1, 2);
    bitset_add(src2, 2);
    bitset_add(src1, 5);
    bitset_add(src2, 7);
    struct bitset *dest2 = bitset_new(32);
    bitset_intersect(dest2, src1, src2);
    bitset_print(src1);
    bitset_print(src2);
    bitset_print(dest2);*/

    mytest();
    return 0;
}