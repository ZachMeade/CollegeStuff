// code for a huffman coder


#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <ctype.h>

#include "huff.h"


// create a new huffcoder structure
struct huffcoder *  huffcoder_new()
{
    struct huffcoder *newHuffCoder = malloc(sizeof(struct huffcoder));
    newHuffCoder->freqs = {0};
    newHuffCoder->code_lengths = {0};
    newHuffCoder->codes = {0};
    struct huffchar * tree = malloc(sizeof(struct huffchar));
    return newHuffCoder;

}
struct huffchar *  huffchar_new(struct huffcoder *this, int pos)
{
    struct huffchar *newHuffChar = malloc(sizeof(struct huffchar));
    newHuffChar->freq = this->freqs[pos];
    newHuffChar->is_compound = 0;
    newHuffChar->seqno = this->freqs[pos];

}
struct huffchar *  huffchar_newCompound(struct huffchar *huffCharLeft, struct huffchar *huffcharRight, int pos, int seqnoCompound)
{
    struct huffchar *newHuffChar = malloc(sizeof(struct huffchar));
    newHuffChar->freq = huffCharLeft->freq + huffcharRight->freq;
    newHuffChar->is_compound = 1;
    newHuffChar->seqno = seqnoCompound;
    newHuffChar->u.compound.left = huffCharLeft;
    newHuffChar->u.compound.right = huffcharRight;
    return newHuffChar;
}

// count the frequency of characters in a file; set chars with zero
// frequency to one
void huffcoder_count(struct huffcoder * this, char * filename)
{
    FILE * file;
    unsigned char c;
    file = fopen(filename, "r");
    assert( file != NULL );
    c = fgetc(file);	// attempt to read a byte
    while( !feof(file) ) {
        printf("%c", c);
        this->freqs[(int) c] = this->freqs[(int) c] + 1;
        c = fgetc(file);
    }
    fclose(file);
    for (int i = 0; i < 256; i++) {
        if(this->freqs[i] == 0){
            this->freqs[i] = 1;
        }
    }

}


// using the character frequencies build the tree of compound
// and simple characters that are used to compute the Huffman codes
void huffcoder_build_tree(struct huffcoder * this)
{
    int newSeqNo = 256;
    for (int j = 0; j < 256; j++) {
        int lowestFreqPos = 0;
        int secondLowestPos = 1;
        for (int i = 0; i < 256; i++) {
            int oldSecond = NULL;
            if(this->freqs[i]<this->freqs[secondLowestPos]){
                oldSecond = secondLowestPos;
                secondLowestPos = i;
                if(this->freqs[oldSecond]<this->freqs[lowestFreqPos]){
                    lowestFreqPos = oldSecond;
                }
            }
            else if(this->freqs[i]< this->freqs[lowestFreqPos]){
                lowestFreqPos = i;
            }
            struct huffchar *newHuffLeft = huffchar_new(this, lowestFreqPos);
            struct huffchar *newHuffRight = huffchar_new(this, secondLowestPos);
            struct huffchar *compoundHuff = huffchar_newCompound(newHuffLeft, newHuffRight, )

        }

    }

}


// recursive function to convert the Huffman tree into a table of
// Huffman codes
void tree2table_recursive(struct huffcoder * this, struct huffchar * node,
		 int * path, int depth)
{

}

// using the Huffman tree, build a table of the Huffman codes
// with the huffcoder object
void huffcoder_tree2table(struct huffcoder * this)
{

}


// print the Huffman codes for each character in order
void huffcoder_print_codes(struct huffcoder * this)
{
  int i, j;
  char buffer[NUM_CHARS];

  for ( i = 0; i < NUM_CHARS; i++ ) {
    // put the code into a string
    for ( j = this->code_lengths[i]-1; j >= 0; j--) {
      buffer[j] = (this->codes[i] >> j) & 1 + '0';
    }
    // don't forget to add a zero to end of string
    buffer[this->code_lengths[i]] = '\0';

    // print the code
    printf("char: %d, freq: %d, code: %s\n", i, this->freqs[i], buffer);;
  }
}
