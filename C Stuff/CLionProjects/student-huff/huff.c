// code for a huffman coder


#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <ctype.h>

#include "huff.h"
#include "bitfile.h"

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
c = fgetc(file);   // attempt to read a byte
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
struct huffchar ** list; int i;

list = malloc(sizeof(struct huffchar *) * NUM_CHARS);
// makes a list of huffchars
for( i = 0; i < NUM_CHARS; i++){
  list[i] = malloc(sizeof(struct huffchar));
  list[i] -> freq = this -> freqs[i];
  list[i] -> is_compound = 0;
  list[i] -> seqno = i;
  list[i] -> u.c = 1;

}
struct ** huffchar sort_chars(struct ** huffchar list, int numChars){

}
//build huffman tree
for(i = 0; i < NUM_CHARS - 1; i++) {
  sort_chars(list, NUM_CHARS);
  struct huffchar * compound;
  compound = malloc(sizeof(struct huffchar));
  compound->freq = list[0]->freq + list[1]->freq;
  compound->is_compound = 1;
  compound->seqno = NUM_CHARS + i;

  compound -> u.compound.left = list[0];
  compound -> u.compound.right = list[1];


  list[0] = compound;
  list[1] = NULL;

  }
this -> tree = list[0];
}
void tree2table_recursive(struct huffcoder * this, struct huffchar* node, int *path, int depth){

}
// using the Huffman tree, build a table of the Huffman codes
// with the huffcoder object
void huffcoder_tree2table(struct huffcoder * this)
{
  tree2table_recursive()
}


// print the Huffman codes for each character in order
void huffcoder_print_codes(struct huffcoder * this)
{
  int i, j;
  char buffer[NUM_CHARS];

  for ( i = 0; i < NUM_CHARS; i++ ) {
    // put the code into a string
    assert(this->code_lengths[i] < NUM_CHARS);
    for ( j = this->code_lengths[i]-1; j >= 0; j--) {
      buffer[j] = ((this->codes[i] >> j) & 1) + '0';
    }
    // don't forget to add a zero to end of string
    buffer[this->code_lengths[i]] = '\0';

    // print the code
    printf("char: %d, freq: %d, code: %s\n", i, this->freqs[i], buffer);;
  }
}



// encode the input file and write the encoding to the output file
void huffcoder_encode(struct huffcoder * this, char * input_filename,
		      char * output_filename)
{

}

// decode the input file and write the decoding to the output file
void huffcoder_decode(struct huffcoder * this, char * input_filename,
		      char * output_filename)
{

}
  
